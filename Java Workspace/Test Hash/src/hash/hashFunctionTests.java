package hash;

import static org.math.array.DoubleArray.max;
import static org.math.array.DoubleArray.min;
import static org.math.array.StatisticSample.histogram;
import hash.hashFunctions.DJB2;
import hash.hashFunctions.LookUp3;
import hash.hashFunctions.LoseLose;
import hash.hashFunctions.MurmurHash3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JFrame;

import org.apache.commons.math3.stat.inference.ChiSquareTest;
import org.math.plot.Plot2DPanel;

import drafts.Draft;

public class hashFunctionTests {
	static final Path shakespeare = Paths.get("Shakespeare_complete_processed.txt");
	static final Path numbers = Paths.get("Numbers_1000000");
	static final Path uuids = Paths.get("UUID_200000");
	static final Path englishWords = Paths.get("English_words.txt");
	static final Path[] paths = { shakespeare, englishWords, uuids, numbers };
	static final String[] descriptions = { "Shakespeare", "English words", "UUIDs",
			"Numbers from 1 to 1,000,000" };

	/**
	 * @param path
	 *            The path for the file used for computing speed. The file is
	 *            read one line at a time and the line is then split to give a
	 *            collection of String objects.
	 * @param func
	 *            A hashFunction instance.
	 * @return A String object representing the duration of the hashing of all
	 *         Strings in the file, in seconds.
	 */
	private static float speedTestOnFile(Path path, hashFunction func) {
		Charset charset = Charset.forName("US-ASCII");
		long start = System.nanoTime();
		try {
			BufferedReader reader = Files.newBufferedReader(path, charset);
			String line = null;
			StringTokenizer st = new StringTokenizer("");
			try {
				while ((line = reader.readLine()) != null) {
					st = new StringTokenizer(line);
					while (st.hasMoreTokens())
						func.hashString(st.nextToken());
				}
			} finally {
				reader.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		long end = System.nanoTime();
		return (float) ((end - start) / 1000000000.);
	}

	/**
	 * 
	 * @param path
	 *            The path for the file used for counting collisions. The file
	 *            is read one line at a time and the line is then split to give
	 *            a collection of String objects.
	 * @param func
	 *            A hashFunction instance.
	 * @return A String object representing the number of collisions between the
	 *         Strings in the file.
	 */
	private static int collisionTestOnFile(Path path, hashFunction func) {
		Hashtable<Integer, String> tab = new Hashtable<Integer, String>();
		Charset charset = Charset.forName("US-ASCII");

		int comp = 0;
		String line = null;
		StringTokenizer st = new StringTokenizer("");
		try {
			BufferedReader reader = Files.newBufferedReader(path, charset);
			try {
				while ((line = reader.readLine()) != null) {
					st = new StringTokenizer(line);
					while (st.hasMoreTokens()) {
						String g = st.nextToken();
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

		return comp;
	}

	/**
	 * Plot the histogram of the distribution of the hash function on the keys
	 * in the file given in parameter
	 * 
	 * @param path
	 *            Path to the file on which the function is tested
	 * @param func
	 *            The function to be tested
	 */
	private static void distributionTestOnFile(Path path, hashFunction func) {
		Vector<Double> values = new Vector<Double>();
		Charset charset = Charset.forName("US-ASCII");
		String line = null;
		StringTokenizer st = new StringTokenizer("");
		try {
			BufferedReader reader = Files.newBufferedReader(path, charset);
			try {
				while ((line = reader.readLine()) != null) {
					st = new StringTokenizer(line);

					while (st.hasMoreTokens()) {
						int hash = func.hashString(st.nextToken());
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
		JFrame frame = new JFrame("Histogram of frequencies for " + func.getClass().getSimpleName()
				+ " on file " + path.toString());
		frame.setSize(600, 600);
		frame.setContentPane(plot);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private static String chiSquareTestOnFile(Path path, hashFunction func) {
		Vector<Double> values = new Vector<Double>();
		Charset charset = Charset.forName("US-ASCII");
		try {
			BufferedReader reader = Files.newBufferedReader(path, charset);
			String line = null;
			StringTokenizer st = new StringTokenizer("");

			try {
				while ((line = reader.readLine()) != null) {
					st = new StringTokenizer(line);

					while (st.hasMoreTokens()) {
						int hash = func.hashString(st.nextToken());
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
			// Expected frequencies for a uniform distribution on the hash
			// values
			expected[j] = tab.length / bins;
		}

		// Chi2 test from the Apache commons math package.
		ChiSquareTest chi = new ChiSquareTest();
		return new String("Result: "
				// Returns true if the null hypothesis (that the observed counts
				// conform
				// to the frequency
				// distribution described by the expected counts) can be
				// rejected with
				// 100 * (1 - alpha)
				// percent confidence. We choose alpha = 0.05.
				+ Boolean.toString(!chi.chiSquareTest(expected, frequencies, 0.05))
				+ System.lineSeparator() + "p-value is: "
				// Return the p-value for the test
				+ chi.chiSquareTest(expected, frequencies));

	}

	/**
	 * Display the results of a speed test on the files given in paths
	 * 
	 * @param func
	 *            The hash function to be tested
	 */
	public static void speedTests(hashFunction func) {
		System.out.println("\n----Speed test----");
		System.out.println("Using " + func.getClass().getSimpleName() + " hash function\n");
		for (int i = 0; i < paths.length; i++)
			System.out.println(descriptions[i] + ":\n" + speedTestOnFile(paths[i], func) + " s");
	}

	/**
	 * Display the results of a collision test on the files given in paths
	 * 
	 * @param func
	 *            The hash function to be tested
	 */
	public static void collisionTests(hashFunction func) {
		System.out.println("\n----Collision test----");
		System.out.println("Using " + func.getClass().getSimpleName() + " hash function\n");
		for (int i = 0; i < paths.length; i++)
			System.out.println(descriptions[i] + ":\n" + collisionTestOnFile(paths[i], func)
					+ " collisions");
	}

	/**
	 * Print a matrix of the results of the two tests (speed and collision) on
	 * several hash functions.
	 * 
	 * @param funcs
	 *            The functions to be tested
	 */
	public static void speedCollisionTests(hashFunction[] funcs) {
		System.out.println("\n---------   Speed - Collision tests   ---------");

		String[][] mat = new String[2 * funcs.length + 2][paths.length + 1];
		mat[0][0] = "  Hash";
		mat[1][0] = "--------";

		// Legends
		for (int i = 0; i < paths.length; i++) {
			mat[0][i + 1] = descriptions[i];
			mat[1][i + 1] = "---------";
		}
		for (int i = 0; i < funcs.length; i++) {
			mat[2 * i + 2][0] = funcs[i].getClass().getSimpleName();
			mat[2 * i + 3][0] = "";
		}

		for (int i = 0; i < funcs.length; i++)
			for (int j = 0; j < paths.length; j++) {
				mat[2 * i + 2][j + 1] = collisionTestOnFile(paths[j], funcs[i]) + " collisions";
				mat[2 * i + 3][j + 1] = "  " + speedTestOnFile(paths[j], funcs[i]) + " s";
			}

		Draft.printMatrix(mat);

	}

	public static void uniformDistribTest(hashFunction func, boolean histogram) {
		System.out.println("---Test of uniform distribution---");
		System.out.println("Using " + func.getClass().getSimpleName() + " hash function"
				+ System.lineSeparator());

		// English words are used for testing the distribution. That is because
		// we want the hash to work on text. Moreover, if the distribution of
		// the String used for testing is uniform (as the uuids are), even a
		// poor hash function performs well.
		System.out.println("EnglishWords : " + System.lineSeparator()
				+ chiSquareTestOnFile(englishWords, func));

		if (histogram)
			distributionTestOnFile(englishWords, func);

	}

	public static void main(String[] args) {
		// distributionTestOnFile(uuids, new LoseLose());
		// uniformDistribTest(new LoseLose(), true);
		speedTests(new LookUp3());
		// collisionTests(new HomemadeHash());
		// System.out.println(chiSquareTestOnFile(uuids, new LookUp3()));

		hashFunction[] tab = new hashFunction[4];
		tab[0] = new LoseLose();
		tab[1] = new DJB2();
		tab[2] = new MurmurHash3();
		tab[3] = new LookUp3();
		speedCollisionTests(tab);
	}

}
