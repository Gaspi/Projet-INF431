package hash;

import static org.math.array.DoubleArray.max;
import static org.math.array.DoubleArray.min;
import static org.math.array.StatisticSample.histogram;


import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;

import org.apache.commons.math3.stat.inference.ChiSquareTest;
import org.math.plot.Plot2DPanel;

import FileManager.Files;
import FileManager.WordReader;

import drafts.Draft;
import drafts.GetInput;


public class HashFunctionTests {


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
	private static float speedTestOnFile(Path path, HashFunction func) {
		long start, end;
		start = System.nanoTime();
		for (String s : new WordReader(path))
			func.hashString(s);
		end = System.nanoTime();
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
	private static int collisionTestOnFile(Path path, HashFunction func) {
		int comp = 0;
		Hashtable<Integer, String> tab = new Hashtable<Integer, String>();
		
		for (String s : new WordReader(path) ) {
			int hash = func.hashString(s);
			if (tab.containsKey(hash))
				comp++;
			else
				tab.put(hash, s);
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
	private static void distributionTestOnFile(Path path, HashFunction func) {
		Vector<Double> values = new Vector<Double>();
		for (String w : new WordReader(path))
			values.add((double) func.hashString(w) );
		
		
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
	
	
	private static void chiSquareTestOnFile(Path path, HashFunction func) {
		Vector<Double> values = new Vector<Double>();
		for (String w : new WordReader(path))
			values.add((double) func.hashString(w) );

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
		
		System.out.println("Result of a chi-square test with confidence level of 95% :");
		// Returns true if the null hypothesis (that the observed counts
		// conform
		// to the frequency
		// distribution described by the expected counts) can be
		// rejected with
		// 100 * (1 - alpha)
		// percent confidence. We choose alpha = 0.05.
		System.out.println("	" + Boolean.toString(!chi.chiSquareTest(expected, frequencies, 0.05)));
		System.out.println("The p-value is");
		System.out.println("	"  + chi.chiSquareTest(expected, frequencies));
	}

	/**
	 * Display the results of a speed test on the files given in paths
	 * @param func
	 *            The hash function to be tested
	 */
	public static void speedTests(Path[] paths, HashFunction func) {
		System.out.println("Speed test for hash function");
		System.out.println("	" + func.getClass().getSimpleName() + System.lineSeparator());
		
		for (int i = 0; i < paths.length; i++){
			System.out.println("On " + paths[i].getFileName());
			System.out.println("	" + speedTestOnFile(paths[i], func) + " s");
		}
		
		System.out.println("----------------------------------------------------------------");
	}

	/**
	 * Display the results of a collision test on the files given in paths
	 * 
	 * @param func
	 *            The hash function to be tested
	 */
	public static void collisionTests(Path[] paths, HashFunction func) {
		System.out.println("Collision test for hash function");
		System.out.println("	" + func.getClass().getSimpleName() + System.lineSeparator());

		for (int i = 0; i < paths.length; i++){
			System.out.println("On " + paths[i].getFileName());
			System.out.println("	" + collisionTestOnFile(paths[i], func) + " collisions");
		}
		
		System.out.println("----------------------------------------------------------------");
	}

	/**
	 * Print a matrix of the results of the two tests (speed and collision) on
	 * several hash functions.
	 * 
	 * @param funcs
	 *            The functions to be tested
	 */
	public static void speedCollisionTests(HashFunction[] funcs, Path[] paths) {
		System.out.println("\n---------   Speed - Collision tests   ---------");

		String[][] mat = new String[2 * funcs.length + 2][FileManager.Files.paths.length + 1];
		mat[0][0] = "  Hash";
		mat[1][0] = "--------";

		// Legends
		for (int i = 0; i < paths.length; i++) {
			mat[0][i + 1] = paths[i].getFileName().toString();
			mat[1][i + 1] = "---------";
		}
		for (int i = 0; i < funcs.length; i++) {
			mat[2 * i + 2][0] = funcs[i].getClass().getSimpleName();
			mat[2 * i + 3][0] = "";
		}
		
		// Reading just to prevent disk cache effect to interfere with the result
		for (int j = 0; j < paths.length; j++)
			speedTestOnFile(paths[j], new LookUp3());
		
		for (int i = 0; i < funcs.length; i++)
			for (int j = 0; j < paths.length; j++) {
				mat[2 * i + 2][j + 1] = collisionTestOnFile(paths[j], funcs[i]) + " collisions";
				mat[2 * i + 3][j + 1] = "  " + speedTestOnFile(paths[j], funcs[i]) + " s";
			}
		
		Draft.printMatrix(mat);
	}

	public static void uniformDistribTest(HashFunction func, boolean histogram) {
		System.out.println("Test of uniform distribution for hash function");
		System.out.println("	" + func.getClass().getSimpleName());
		System.out.println("Only for file");
		System.out.println("	" + FileManager.Files.englishWords);

		// English words are used for testing the distribution. That is because
		// we want the hash to work on text. Moreover, if the distribution of
		// the String used for testing is uniform (as the uuids are), even a
		// poor hash function performs well.		
		chiSquareTestOnFile(FileManager.Files.englishWords, func);
		
		if (histogram)
			distributionTestOnFile(FileManager.Files.englishWords, func);
		
    	System.out.println("----------------------------------------------------------------");
	}
	
	
	
	// This answers question 1
	public static void main(String[] args) throws NoSuchFileException {
		
//    	if (args.length > 0) {
//        	for(int j=0; j < args.length; j++)
//        		Draft.checkPath( args[j] );
//        	exec(args);
//        	
//    	} else
//        	exec( GetInput.askPathSet("Paths to the files") );
    	
		//*
		HashFunction[] tab = new HashFunction[6];
		tab[0] = new LoseLose();
		tab[1] = new DJB2();
		tab[2] = new LookUp3();
		tab[3] = new MurmurHash3();
		tab[4] = new JavaHash();
		tab[5] = new HomemadeHash();
		speedCollisionTests(tab, Files.paths);
		//*/
	}
	
	
    public static void exec(String[] paths) {
    	
    	String[] hashFuncs = GetInput.askHashSet("Hash functions to test");
    	HashFunction[] tab = new HashFunction[hashFuncs.length];
    	for (int i = 0; i < tab.length; i++)
    		tab[i] = HashFunction.getHashFunction(hashFuncs[i]);
    	
    	Path[] tab2 = new Path[paths.length];
    	for (int i = 0; i < paths.length; i++)
    		tab2[i] = Paths.get(paths[i]);
    	
    	speedCollisionTests(tab, tab2);
    }
	
	
	
	
}
