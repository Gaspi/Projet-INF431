package hyperLogLog;

import hash.HashFunction;
import hash.LookUp3;

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
    private HashFunction hashFunc;
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
    
    
    public Similarities(String[] urls, int k, int b, HashFunction func) {
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
    
    // Display info
    System.out.println("Looking for similar files between");
    for(String s: this.urls)
    	System.out.println("	" + s);
	System.out.println("With parameters");
	System.out.println("	b = " + b);
	System.out.println("	k = " + k);
	System.out.println("	threshold = " + threshold + System.lineSeparator());


	int n = this.resemblances.length;

	for (int i = 0; i < n; i++)
	    for (int j = i + 1; j < n; j++) {
			double d = calculateResemblance(this.resemblances[i], this.resemblances[j], this.b);
			if (d > threshold) {
			    System.out.println("Resemblance between file" + System.lineSeparator() + "    "
				    + this.urls[i]);
			    System.out.println("and file" + System.lineSeparator() + "    " + this.urls[j]);
			    System.out.println("is beyond threshold " + threshold + "." + System.lineSeparator());
			}
		}
	
	System.out.println("----------------------------------------------------------------");

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
    public static double resemblance(Path pathA, Path pathB, HashFunction func, int b, int k) {

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
    public static double resemblance(String pathA, String pathB, HashFunction func, int b, int k) {
	return resemblance(Paths.get(pathA), Paths.get(pathB), func, b, k);
    }
    
    
    /**
     * To test the resemblance function behavior.
     */
    public static void testResemblance(int k) {

	// For the same file, resemblance should be 1
	System.out.println("Same files :");
	System.out.println(Similarities.resemblance(FileManager.Files.bible,
			FileManager.Files.bible, new LookUp3(), 15, k));

	// For files with high similarities, resemblance should be approximately
	// 1. Here I have deleted some
	// words from bible to build bible2.
	System.out.println("Approximately same files:");
	System.out
		.println(Similarities
			.resemblance(
				"Bible_english_processed.txt",
				"Bible_english_processed_minus_a_few_words.txt",
				new LookUp3(), 15, k));

	// For distinct files, resemblance should be around 0.
	System.out.println("Completely different files (but same langage):");
	System.out.println(Similarities.resemblance(FileManager.Files.shakespeare,
			FileManager.Files.bible, new LookUp3(), 15, k));
    }

    public static void main(String[] args) {
		// Implement here the command line interface for question 3

//	Similarities.testResemblance(4);

	String[] strs = { "files/processed/Shakespeare.txt", "files/processed/Bible.txt",
		"files/processed/Bible_minus_a_few_words.txt" };

	Similarities sim = new Similarities(strs, 1);
	sim.similarFiles(0.1);

    }
    
    public static void exec(String[] urls, int k, int b, String func, double threshold){
    	Similarities sim = new Similarities(urls, k, b, HashFunction.getHashFunction(func));
    	sim.similarFiles(threshold);
    }

}