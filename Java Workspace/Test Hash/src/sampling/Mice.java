package sampling;

import hash.HashFunction;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;

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
     * Calculate the number of mice.
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
    
    
	public static void main(String[] args) {
    	// Testing number of mice
	   	
//    	Path path = hashFunctionTests2.shakespeare;
//    	int k = 2;
//    	
//    	SignificantWordsArraySample ws = new SignificantWordsArraySample(1000, f);	
//    	for(String str: new WordReader(path)) {
//    		ws.addWord(str);
//    	}
//    	
//    	double a = benchmarkMiceNumber(path, k);
//    	double b = ws.estimateMiceNumber(k);
//    	    	
//    	
//    	System.out.println(a + " - " + b + " diff = " + Math.abs(a-b)/a*100.);

	}
	
    public static void exec(String path, int nbOcc, String func, int k){
    	double d = findMiceNumber(Paths.get(path), nbOcc, HashFunction.getHashFunction(func), k);
    	
    	System.out.println("Approximating the number of " + nbOcc + "-mice for file:");
    	System.out.println("	" + path + System.lineSeparator());
    	System.out.println("With parameters :");
    	System.out.println("	Sampling bag of size " + 2*k + " wordss");
    	System.out.println("Result :");
    	System.out.println("	" + Math.round(d));
    	System.out.println("----------------------------------------------------------------");
    }

}
