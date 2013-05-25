package sampling;

import hash.HashFunction;

import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
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
    
    
	public static void main(String[] args) throws NoSuchFileException, IllegalArgumentException {
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
		
    	if(args.length != 4)
    		throw new IllegalArgumentException("Wrong number of arguments");
    	
    	if(HashFunction.isHashFunction(args[0]))
    		throw new IllegalArgumentException("Not a valid hash function: " + args[0]);
    	
    	if(Integer.parseInt(args[1]) < 0)
    		throw new IllegalArgumentException("Number of occurrences should be nonnegative");
    	
    	if(Integer.parseInt(args[2]) < 0)
    		throw new IllegalArgumentException("Half bag size should be nonnegative");
    	
    	if(!Files.exists(Paths.get(args[3])))
        	throw new NoSuchFileException(args[3]);

		
    	exec(args[3], Integer.parseInt(args[1]), args[0], Integer.parseInt(args[2]));

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
