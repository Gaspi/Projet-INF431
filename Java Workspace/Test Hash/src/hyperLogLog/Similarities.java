package hyperLogLog;

import drafts.Draft;
import drafts.GetInput;

import hash.HashFunction;
import hash.LookUp3;

import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Class meant to answer question 3.
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
    private int size;

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
     * Initialize the resemblance array with fingerPrint arrays of the files whose paths
     * are stored in 'urls'.
     */
    private void initialize() {
    	size = urls.length;
    	resemblances = new int[size][];
    	
    	for (int i = 0; i < size; i++)
    		resemblances[i] = HyperLogLog.buildFingerPrint(Paths.get(urls[i]), hashFunc, b, k);
    }
    
    
    /**
     * Display the names of the files which share resemblance beyond a given threshold.
     * 
     * @param threshold
     *            The threshold to use.
     */
    public void similarFiles(double threshold) {   
		for (int i = 0; i < size; i++)
		    for (int j = i + 1; j < size; j++) {
				double d = calculateResemblance(resemblances[i], resemblances[j], b);
				if (d > threshold)
				    System.out.println(
				    		"Resemblance between file\n" +
				    		"    " + urls[i] +"\n" +
				    		"and file\n" +
				    		"    " + urls[j] + "\n" +
				    		"is beyond threshold " + threshold + ".");
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
    
    // This answers question 3
    public static void main(String[] args) throws NoSuchFileException, IllegalArgumentException {
    	
    	if (args.length >= 5) {
    		
        	Draft.checkHash( args[0] );
        	Draft.checkRange(args[1], 4, 15);
        	Draft.checkRange(args[2], 1, 50);
        	Draft.checkRange(args[3], 0., 1.);
        	for(int j=4; j < args.length; j++)
        		Draft.checkPath( args[j] );
        	
        	String[] files = new String[args.length - 4];
        	for(int j=4; j<args.length; j++)
        		files[j-4]=args[j];
        	
        	exec(files, Integer.parseInt(args[2]), Integer.parseInt(args[1]), args[0],
        			Double.parseDouble(args[3]));
        	
    	} else if (args.length == 0) {
    		
        	String hash =		GetInput.askHash("Hash function");
        	String[] urls = 	GetInput.askPathSet("Paths to the files");
        	int b = 			GetInput.askParameterInRange("Parameter b", 4, 15);
        	int k = 			GetInput.askParameterInRange("Parameter k", 1, 50);
        	double threshold =  GetInput.askParameterInRange("Threshold", 0., 1.);
        	exec(urls, k, b, hash, threshold);
        	
    	} else
    		throw new IllegalArgumentException("Wrong number of arguments (5 or more expected)");
    	
    }
    
    
    public static void exec(String[] urls, int k, int b, String func, double threshold) {
    	System.out.println("----------------------------------------------------------------");
        System.out.println(System.lineSeparator() + "Looking for similar files between");
        for(String s: urls)
        	System.out.println("	" + s);
    	System.out.println("With parameters");
    	System.out.println("	b = " + b);
    	System.out.println("	k = " + k);
    	System.out.println("	threshold = " + threshold + System.lineSeparator());
    	
    	Similarities sim = new Similarities(urls, k, b, HashFunction.getHashFunction(func));
    	sim.similarFiles(threshold);
    }

}
