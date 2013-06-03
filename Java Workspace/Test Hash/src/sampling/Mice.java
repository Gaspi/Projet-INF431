package sampling;

import hash.HashFunction;
import hash.HashFunctionTests;
import hash.LookUp3;

import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;

import drafts.Draft;
import drafts.GetInput;

import FileManager.WordReader;

public class Mice {


	/**
     * Estimate the number of Mice with a given number of occurrences in a file.
     * 
     * @param path Path the the file.
     * @param nbOcc Number of occurrences.
     * @param func The hash function used on words.
     * @param k Parameter from the subject (half the size of the bag used for sampling)
     *            
     * @return Estimation of the number of k-mice.
     */
	public static double findMiceNumber(Path path, int nbOcc, HashFunction func, int k){
		SignificantWordsArraySample ws = new SignificantWordsArraySample(k, func);
    	
    	for(String str: new WordReader(path))
    		ws.addWord(str);
    	
    	return ws.estimateMiceNumber(nbOcc);
	}
	
	
	 /**
     * Calculate the real number of mice.
     * 
     * @param path Path to the file to use.
     * @param k Number of occurrences.
     * 
     * @return Number of words which appear k times in the file.
     */
    public static double benchmarkMiceNumber(Path path, int k){
    	Hashtable<String, Double> tab = new Hashtable<String, Double>();

    	for (String s : new WordReader(path))
    	    if (!tab.containsKey(s))
    	    	tab.put(s, 1.);
    	    else {
    	    	double d = tab.remove(s);
    	    	tab.put(s, d+1.);
    	    }
    	
    	double comp = 0;
    	for(String s: tab.keySet())
    		if(tab.get(s) == k)
    			comp++;
    	
    	return comp;
    }
    
    /**
     * Display the percentage error when estimating the number of k-mice
     * @param path Path to the file to use.
     * @param k Number of occurrences.
     */
    public static void performanceEstimator(Path path, int k){   	
    	SignificantWordsArraySample ws = new SignificantWordsArraySample(1000, new LookUp3());	
    	for(String str: new WordReader(path)) {
    		ws.addWord(str);
    	}
    	
    	double a = benchmarkMiceNumber(path, k);
    	double b = ws.estimateMiceNumber(k);
    	
    	System.out.println("----------------------------------------------------------------");
    	System.out.println("For file " + path.getFileName());
    	System.out.println("The estimated number of " + k + "-mice is: " + b + ".");
    	System.out.println("The real number of " + k + "-mice is: " + a + ".");
    	System.out.println("The percentage error is: " + Math.abs(a-b)/a*100. + ".");
    	System.out.println("----------------------------------------------------------------");
    }
    
    // This answers question 6
	public static void main(String[] args) throws NoSuchFileException, IllegalArgumentException {
		
    	if (args.length == 4) {
    		
        	Draft.checkHash( args[0] );
        	Draft.checkRange(args[1], 1, 1000000000);
        	Draft.checkRange(args[2], 1, 1000000000);
        	Draft.checkPath( args[3] );
        	
        	exec(args[3], Integer.parseInt(args[1]), args[0], Integer.parseInt(args[2]));
        	
    	} else if (args.length == 0) {
    		
        	String path = GetInput.askPath("Path to the file");
        	String hash = GetInput.askHash("Hash function");
        	int nbOcc = GetInput.askParameterInRange("Number of occurences", 1, 1000000000);
        	int k = 	GetInput.askParameterInRange("Parameter k", 1,  1000000000);
        	exec(path, nbOcc, hash, k);
        	
    	} else
    		throw new IllegalArgumentException("Wrong number of arguments (4 expected)");
	}
	
	
    public static void exec(String path, int nbOcc, String func, int k){
    	double d = findMiceNumber(Paths.get(path), nbOcc, HashFunction.getHashFunction(func), k);
    	
    	System.out.println("----------------------------------------------------------------");
    	System.out.println("Approximating the number of " + nbOcc + "-mice for file:");
    	System.out.println("	" + path + System.lineSeparator());
    	System.out.println("With parameters :");
    	System.out.println("	Sampling bag of size " + 2*k + " wordss");
    	System.out.println("Result :");
    	System.out.println("	" + Math.round(d));
    	System.out.println("----------------------------------------------------------------");
    }

}





// Old code in Main...
//
//Path path = hashFunctionTests2.shakespeare;
//int k = 2;
//
//SignificantWordsArraySample ws = new SignificantWordsArraySample(1000, f);	
//for(String str: new WordReader(path)) {
//	ws.addWord(str);
//}
//
//double a = benchmarkMiceNumber(path, k);
//double b = ws.estimateMiceNumber(k);
//    	
//
//System.out.println(a + " - " + b + " diff = " + Math.abs(a-b)/a*100.);