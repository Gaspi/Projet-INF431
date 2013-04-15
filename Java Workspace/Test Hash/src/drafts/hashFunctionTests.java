package drafts;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;
import static org.math.array.StatisticSample.*;

import org.apache.commons.math3.stat.inference.ChiSquareTest;

public class hashFunctionTests{
	static final Path	shakespeare		= Paths.get("Shakespeare_complete_processed.txt");
	static final Path	numbers			= Paths.get("Numbers_1000000");
	static final Path	uuids			= Paths.get("UUID_200000");
	static final Path	englishWords	= Paths.get("English_words.txt");

	/**
	 * 
	 * @param path
	 *            The path for the file used for computing speed. The file is read one line at a
	 *            time and the line is then split to give a collection of String objects.
	 * @param func
	 *            A hashFunction instance.
	 * @return A String object representing the duration of the hashing of all Strings in the file,
	 *         in seconds.
	 */
	private static String speedTestOnFile(Path path, hashFunction func){
		Charset charset = Charset.forName("US-ASCII");
		long start = System.nanoTime();
		try {
			BufferedReader reader = Files.newBufferedReader(path, charset);
			String line = null;
			String[] strings = null;
			try {
				while ((line = reader.readLine()) != null) {
					strings = line.split("[\\s]+");
					for (String g : strings)
						func.hashString(g);
				}
			} finally {
				reader.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return ((float) (System.nanoTime() - start)) / 1000000000. + " s";
	}

	/**
	 * 
	 * @param path
	 *            The path for the file used for counting collisions. The file is read one line at a
	 *            time and the line is then split to give a collection of String objects.
	 * @param func
	 *            A hashFunction instance.
	 * @return A String object representing the number of collisions between the Strings in the
	 *         file.
	 */
	private static String collisionTestOnFile(Path path, hashFunction func){
		Hashtable<Integer, String> tab = new Hashtable<Integer, String>();
		Charset charset = Charset.forName("US-ASCII");
		int comp = 0;
		try {
			BufferedReader reader = Files.newBufferedReader(path, charset);
			String line = null;
			String[] strings = null;

			try {
				while ((line = reader.readLine()) != null) {

					strings = line.split("[\\s]+");

					for (String g : strings) {
						int hash = func.hashString(g);

						if (tab.containsKey(hash))
							comp++;
						else
							tab.put(hash, g);
					}
				}
			} finally {
				reader.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return Integer.toString(comp);

	}

	private static void distributionTestOnFile(Path path, hashFunction func){
		Vector<Double> values = new Vector<Double>();
		Charset charset = Charset.forName("US-ASCII");
		try {
			BufferedReader reader = Files.newBufferedReader(path, charset);
			String line = null;
			String[] strings = null;

			try {
				while ((line = reader.readLine()) != null) {
					strings = line.split("[\\s]+");

					for (String g : strings) {
						int hash = func.hashString(g);
						values.add((double) hash);
					}
				}
			} finally {
				reader.close();
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// addHistogramPlot function uses arrays of doubles. We need to convert
		// from Vector to array
		int i = 0;
		double[] tab = new double[values.size()];
		for (Double d : values) {
			tab[i] = d.doubleValue();
			i++;
		}

		// Do the plotting
		Plot2DPanel plot = new Plot2DPanel();
		plot.addHistogramPlot("test", tab, 100);
		JFrame frame = new JFrame("Histogram of frequencies for "
				+ func.getClass().getSimpleName() + " on file "
				+ path.toString());
		frame.setSize(600, 600);
		frame.setContentPane(plot);
		frame.setVisible(true);
	}

	private static String chiSquareTestOnFile(Path path, hashFunction func){
		Vector<Double> values = new Vector<Double>();
		Charset charset = Charset.forName("US-ASCII");
		try {
			BufferedReader reader = Files.newBufferedReader(path, charset);
			String line = null;
			String[] strings = null;

			try {
				while ((line = reader.readLine()) != null) {
					strings = line.split("[\\s]+");

					for (String g : strings) {
						int hash = func.hashString(g);
						values.add((double) hash);
					}
				}
			} finally {
				reader.close();
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Convert the Vector<Double> into an array of doubles
		int i = 0;
		double[] tab = new double[values.size()];
		for (Double d : values) {
			tab[i] = d.doubleValue();
			i++;
		}

		// Number of bins in the histogram
		int bins = 100;
		// Frequencies of the hash values (doubles)
		double[] freq = histogram(tab, min(tab), max(tab), bins);
		// Array of longs. To cast from freq.
		long[] frequencies = new long[bins];
		// Expected frequencies.
		double[] expected = new double[bins];
		for (int j = 0; j < bins; j++) {
			// Cast
			frequencies[j] = (long) freq[j];
			// Expected frequencies for a uniform distribution on the hash values
			expected[j] = tab.length / bins;
		}

		// Chi2 test from the Apache commons math package.
		ChiSquareTest chi = new ChiSquareTest();
		return new String("Result: "
		// Returns true iff the null hypothesis (that the observed counts conform to the frequency
		// distribution described by the expected counts) can be rejected with 100 * (1 - alpha)
		// percent confidence. We choose alpha = 0.05.
				+ Boolean.toString(!chi.chiSquareTest(expected, frequencies,
						0.05)) + System.lineSeparator() + "p-value is: "
				// Return the p-value for the test
				+ chi.chiSquareTest(expected, frequencies));

	}

	public static void speedTests(hashFunction func){
		System.out.println("---Speed test---");
		System.out.println("Using " + func.getClass().getSimpleName()
				+ " hash function");

		// Shakespeare
		System.out.println("Shakespeare: " + System.lineSeparator()
				+ speedTestOnFile(shakespeare, func));
		// Numbers
		System.out.println("Numbers from 0 to 1,000,000: "
				+ System.lineSeparator() + speedTestOnFile(numbers, func));
		// UUIDs
		System.out.println("200,000 random UUIDS: " + System.lineSeparator()
				+ speedTestOnFile(uuids, func));
	}

	public static void collisionTests(hashFunction func){
		System.out.println("---Collision test---");
		System.out.println("Using " + func.getClass().getSimpleName()
				+ " hash function" + System.lineSeparator());

		// English words
		System.out.println("All english words: " + System.lineSeparator()
				+ collisionTestOnFile(englishWords, func) + " collisions");
		// Numbers
		System.out.println("Numbers from 0 to 1,000,000: "
				+ System.lineSeparator() + collisionTestOnFile(numbers, func)
				+ " collisions");
		// UUIDS
		System.out.println("200,000 random UUIDS: " + System.lineSeparator()
				+ collisionTestOnFile(uuids, func) + " collisions");

	}

	public static void uniformDistribTest(hashFunction func, boolean histogram){
		System.out.println("---Test of uniform distribution---");
		System.out.println("Using " + func.getClass().getSimpleName()
				+ " hash function" + System.lineSeparator());

		// UUIDS
		System.out.println("200,000 random UUIDS: " + System.lineSeparator()
				+ chiSquareTestOnFile(uuids, func));

		if (histogram)
			distributionTestOnFile(uuids, func);

	}

	public static void main(String[] args){
		// distributionTestOnFile(uuids, new LoseLose());
		uniformDistribTest(new JavaHash(), true);
		collisionTests(new JavaHash());
		// System.out.println(chiSquareTestOnFile(uuids, new LookUp3()));
		//gyfdutdud
	}

}
