package drafts;

import hash.hashFunction;
import hash.hashFunctionTests2;
import hash.hashFunctions.*;

import java.nio.file.Path;
import java.nio.file.Paths;



/**
 * 
 * Class meant to answer question 3.
 * 
 */
public class Similarities {

    // Urls of the files whose levels of similarity are to be assessed.
    private String[] urls;
    // Hash function used in the algorithm.
    private hashFunction hashFunc;
    // Parameter k from the subject (length of the shingles used)
    private int k;
    // Parameter b from the subject (accuracy of the HyperLogLog algorithm)
    private int b;
    // Matrix of resemblance value between all files.
    private int[][] resemblances;

    public Similarities(String[] urls, int k) {
    	this.urls = urls;
    	this.k = k;
    	hashFunc = new LookUp3();
    	b = 15;
    	
    	initialize();
    }
    
    
    public Similarities(String[] urls, int k, int b, hashFunction func) {
    	this.urls = urls;
    	this.hashFunc = func;
    	this.k = k;
    	
    	if (b <= 0 || b > 16)
    		throw new AssertionError("hyperLogLog :  b <= 0 or b > 16");
    	
    	this.b = b;
    	
    	initialize();
    }
    
    
    /**
     * Initialize the resemblance array with fingerPrint arrays of the files whose paths are stored
     * in 'urls'.
     */
    private void initialize() {
    	resemblances = new int[this.urls.length][];
    	
    	for (int i = 0; i < urls.length; i++)
    		this.resemblances[i] = HyperLogLog.buildFingerPrint(Paths.get(urls[i]), this.hashFunc,
    				this.b, k);
    }
    
    
    /**
     * Display the names of the files which share resemblance beyond a given threshold.
     * 
     * @param threshold
     *            The threshold to use.
     */
    public void similarFiles(double threshold) {

	int n = this.resemblances.length;

	for (int i = 0; i < n; i++)
	    for (int j = i + 1; j < n; j++) {
		double d = calculateResemblance(this.resemblances[i], this.resemblances[j], this.b);
		if (d > threshold) {
		    System.out.println("Resemblance between file" + System.lineSeparator() + "    "
			    + this.urls[i]);
		    System.out.println("and file" + System.lineSeparator() + "    " + this.urls[j]);
		    System.out.println("is beyond threshold " + threshold + ".");
		    System.out
			    .println("-----------------------------------------------------------------");
		}
	    }

    }

    /**
     * Calculate the resemblance for two given fingerPrint arrays.
     * 
     * @param MA
     *            First fingerPrint array.
     * @param MB
     *            Second fingerPrint array.
     * @param b
     *            Parameter used in the HyperLogLog algorithm.
     * 
     * @return The resemblance as a double value.
     */
    private static double calculateResemblance(int[] MA, int[] MB, int b) {
	int m = 1 << b; // m = 2^b

	int[] MAuB = new int[m];
	for (int i = 0; i < m; i++)
	    MAuB[i] = Math.max(MA[i], MB[i]);

	// Compute the number of k-shingles
	double SA = HyperLogLog.hyperLogLog(MA);
	double SB = HyperLogLog.hyperLogLog(MB);

	// Compute the resemblance
	double SAuB = HyperLogLog.hyperLogLog(MAuB);
	double SAnB = SA + SB - SAuB;

	return SAnB / SAuB;
    }

    /**
     * 
     * Compare two files with respect to their number of k-shingles.
     * 
     * @param pathA
     *            Path to the first file to use.
     * @param pathB
     *            Path to the second file to use.
     * @param func
     *            The hash function used for calculating the fingerPrints.
     * @param b
     *            Parameter of the HyperLoglog algorithm.
     * @param k
     *            Length of the shingles.
     * 
     * @return The resemblance between the two files, as described in Broder & all, Syntactic
     *         clustering of the web. In 6th International World Wide Web Conference (1997).
     */
    public static double resemblance(Path pathA, Path pathB, hashFunction func, int b, int k) {

	if (b <= 0 || b > 16)
	    throw new AssertionError("hyperLogLog :  b <= 0 or b > 16");

	// Build fingerPrint arrays
	int[] MA = HyperLogLog.buildFingerPrint(pathA, func, b, k);
	int[] MB = HyperLogLog.buildFingerPrint(pathB, func, b, k);

	return calculateResemblance(MA, MB, b);
    }

    /**
     * Provided for convenience.
     */
    public static double resemblance(String pathA, String pathB, hashFunction func, int b, int k) {
	return resemblance(Paths.get(pathA), Paths.get(pathB), func, b, k);
    }

    /**
     * To test the resemblance function behavior.
     */
    public static void testResemblance(int k) {

	// For the same file, resemblance should be 1
	System.out.println("Same files :");
	System.out.println(Similarities.resemblance(hashFunctionTests2.bible,
		hashFunctionTests2.bible, new LookUp3(), 15, k));

	// For files with high similarities, resemblance should be approximately
	// 1. Here I have deleted some
	// words from bible to build bible2.
	System.out.println("Approximately same files:");
	System.out
		.println(Similarities
			.resemblance(
				"/home/jonathan/Documents/Projet-INF431/Ressources/Preprocessed files/Bible_english_processed.txt",
				"/home/jonathan/Documents/Projet-INF431/Ressources/Preprocessed files/Bible_english_processed (copie).txt",
				new LookUp3(), 15, k));

	// For distinct files, resemblance should be around 0.
	System.out.println("Completely different files (but same langage):");
	System.out.println(Similarities.resemblance(hashFunctionTests2.shakespeare,
		hashFunctionTests2.bible, new LookUp3(), 15, k));
    }

    public static void main(String[] args) {

	// Similarities.testResemblance(1);

	String[] strs = { "Shakespeare_complete_processed.txt", "Bible_english_processed.txt",
		"Bible_english_processed.txt" };

	Similarities sim = new Similarities(strs, 1);
	sim.similarFiles(0.3);

    }

}
