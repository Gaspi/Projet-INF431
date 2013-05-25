package hyperLogLog;

import hash.HashFunction;
import hash.LookUp3;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.LinkedList;

import drafts.Draft;
import drafts.GetInput;

import FileManager.WordReader;



public class HyperLogLog {

    /**
     * alpha[b] is meant to be equal to \alpha_{2^b}. Values computed by Wolfram Mathematica
     */
    static double[] alpha = { 0, 0.351194, 0.532435, 0.625609, 0.673102, 0.697123,
	    0.709208, 0.715271, 0.718308, 0.719827, 0.720587, 0.720967, 0.721157, 0.721252,
	    0.721300, 0.721324, 0.721336 };

    // Just for the record, the suggested values are : 0,
    // 0 , 0 , 0 , 0.673000,
    // 0.697000, 0.709000, 0.715270, 0.718273,
    // 0.719783, 0.720541, 0.720920, 0.721110,
    // 0.721205, 0.721253, 0.721276, 0.721288

    /**
     * Build the fingerPrint of k-shingles in a file. Used in class Similarities.
     * 
     * @param path
     *            The path to the file.
     * @param func
     *            The hash function to use.
     * @param b
     *            Parameter of the HyperLogLog algorithm.
     * @param k
     *            Length of the shingles.
     *            
     * @return The fingerPrint as an array of integers.
     */
    public static int[] buildFingerPrint(Path path, HashFunction func, int b, int k) {
    
	if (b <= 0 || b > 16)
	    throw new AssertionError("hyperLogLog :  b <= 0 or b > 16");

	int m = 1 << b; // m = 2^b
	int[] M = new int[m]; // M initialized to 0 by default
	
	// A little more complicated than the first version because we want to
	// be able to count the number of k-shingles.
	int comp = 0;
	StringBuilder strBuilder = new StringBuilder();
	LinkedList<Integer> indexes = new LinkedList<Integer>();

	for (String s : new WordReader(path))
		if (comp < k) {
	    	strBuilder.append(s);
	    	indexes.add(s.length());
	    	comp++;
		} else {
	    	strBuilder.replace(0, indexes.poll(), "");
	    	strBuilder.append(s);
	    	indexes.add(s.length());
	    	long x = func.hashString(strBuilder.substring(0));
	    	int j = (int) (x & (m - 1));
	    	long w = x >>> b;
			M[j] = Math.max(M[j], rho(w));
	    }

	return M;
    }
    
    
    /**
     * Asses the number of different patterns in a text file whose fingerPrint array is given as an
     * argument using the HyperLogLog algorithm. Used in the Similarities class.
     * 
     * @param M
     *            The fingerPrint array
     * 
     * @return The approximative number of different patterns.
     */
    public static double hyperLogLog(int[] M) {

	int m = M.length;
	int b = (int) (Math.log(m) / Math.log(2)); // m = 2^b

	// Calculation of the result
	double sum = 0;
	for (int j = 0; j < m; j++)
	    sum += Math.pow(2, -M[j]);

	double e = alpha[b] * ((double) m) * ((double) m) / sum;

	double n = Math.pow(2., 32.);

	// Second modification (first if statement):
	// corrections when e is comparatively small
	// with respect to m
	// Third modification (second if statement):
	// corrections to account for collision effects
	// due to the hash function
	if (e < (5. / 2.) * m) {
	    double v = 0;
	    for (int i = 0; i < m; i++)
	    	if (M[i] == 0) v++;
	    
	    if (v != 0)
	    	e = m * Math.log(m / v);
	} else if (e > n / 30)
	    e = -n * Math.log(1 - e / n);

	return e;
    }
    
    
    /**
     * Asses the number of different k-shingles in a text file using the HyperLogLog algorithm (for
     * k = 1, the returned value is an estimation of the number of distinct words in the file).
     * 
     * @param path
     *            Path to the file to read.
     * @param func
     *            Hash function used.
     * @param b
     *            Parameter of the algorithm. (4 <= b <= 16 expected)
     * @param k
     *            Length of the shingles
     * @return The approximative number of different words in the file
     */
    public static double hyperLogLog(Path path, HashFunction func, int b, int k) {
    	return hyperLogLog(buildFingerPrint(path, func, b, k));
    }
    
    /**
     * Alias of the previous hyperLogLog
     */
    public static double hyperLogLog(String path, HashFunction func, int b, int k) {
    	return hyperLogLog(Paths.get(path), func, b, k);
    }
    
    
    /**
     * @return The position to the first 1 encountered when reading the 2-base decomposition of the
     *         positive integer x (it equals the number of trailing 0, and therefore this method is
     *         equivalent to Integer.numberOfTrailingZeros).
     */
    public static int rho(long x) {
    	int res = 1;
    	// While the Least Significant Bit is 0 but x is not zero
    	while ((x & 1) == 0 && x != 0) {
    		res++;
    		x >>>= 1; // Offset one bit to the left
    	}
    	return res;
    }

    /**
     * @param path
     *            The path to the file we want to perform the HyperLogLog algorithm on.
     * 
     * @return The exact number of distinct words in the file.
     */
    public static double benchmark(Path path) {
	Hashtable<String, String> tab = new Hashtable<String, String>();

	for (String s : new WordReader(path))
	    if (!tab.containsKey(s))
		tab.put(s, s);
	return (double) tab.size();
    }
    
    
    /**
     * 
     * @param path
     *            The path to the file we want to estimate the performance of HyperLogLog on.
     * 
     * @return Display the percentage error for increasing value of m.
     */
    public static void performanceEstimator(Path path) {
		double bench = benchmark(path);
		System.out.println("Number of distinct words: " + (int) bench);
		for (int i = 1; i < 16; i++) {
		    double h = hyperLogLog(path, new LookUp3(), i, 1);
		    System.out.println("b = " + i + " : " + h + " ; error : "
			    + (Math.abs(h - bench) / bench) * 100 + "%");
		}
    }
    
    
    public static void main(String[] args) {
		// Implement here the command line interface for question 2
    	
    	GetInput gi = new GetInput();
    	
    	String path = gi.ask("Path to the file");
    	String hashFunc = "hash." + gi.ask("Hash function");
    	int b = Integer.parseInt(gi.ask("Parameter b"));
    	
    	System.out.println();
    	
    	exec(path, hashFunc, b);
    }
    
    
    
    public static void exec(String path, String hashFunc, int b) {
    	System.out.println("Estimating the number of distincts words in file");
    	System.out.println("	" + path);
    	System.out.println("With parameters :");
    	System.out.println("	b = " + b);
    	System.out.println("Result is :");
    	System.out.println("	 " + Math.round(hyperLogLog(path, HashFunction.getHashFunction(hashFunc), b, 1)));
    	System.out.println("----------------------------------------------------------------");
    	
    }
    
    
}
