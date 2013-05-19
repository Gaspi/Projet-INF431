package sampling;

import hash.HashFunction;
import hash.HashFunctionTests;
import hash.hashFunctions.MurmurHash3;

import java.nio.file.Path;
import java.util.Hashtable;
import java.util.LinkedList;

import FileManager.WordReader;

public class Sampling {
	
	/**
     * Provide a list of a given length of characteristic words in a file.
     * 
     * @param path Path the the file.
     * @param nbWords Number of desired characteristic words.
     * @param func The hash function used on words.
     * @param k Parameter from the subject (half the size of the bag used for sampling)
     * 
     * @return A list of 'nbWords' characteristic words.
     */
    public static LinkedList<String> findSignificantWords(Path path, int nbWords, HashFunction func, int k) {
    	
    	if (2 * k < nbWords)
    		throw new AssertionError("Too much significant words asked for the given sample size.");
    	
    	SignificantWordsSample ws = new SignificantWordsArraySample(k, func);
    	
    	for(String str: new WordReader(path))
    		ws.addWord(str);
    	
    	// Extract only nbWords
    	LinkedList<String> wordsList = ws.words();
    	LinkedList<String> l = new LinkedList<String>();
    	for (int i = 0; i < nbWords; i++)
    		l.add(wordsList.removeFirst());
    	
    	return l;
    }
	
 
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
	
	
	/**
	 * 
	 * @param path Path to the file to read for icebergs.
	 * @param frequency The lower bound of the iceberg's frequencies that the function should return
	 * @return Approximately all icebergs whose frequency is over 'frequency'
	 */
	public static LinkedList<String> findIcebergs(Path path, double frequency){
		IcebergSample sample = new IcebergTreeSampleImproved(frequency, 0.9);
		
		for(String str: new WordReader(path))
			sample.addWord(str);
		
		return sample.getIcebergs();
	}
	
	
	/**
	 * @param path Path to the file to read for icebergs.
	 * @param frequency The lower bound of the iceberg's frequencies that the function returns
	 * 
	 * @return All icebergs whose frequency is over 'frequency' (no approximation)
	 */
	public static LinkedList<String> benchmarkIcebergs(Path path, double frequency){
		Hashtable<String, Integer> tab = new Hashtable<String, Integer>();
		int comp = 0;
    	for (String s : new WordReader(path)){
    	    if (!tab.containsKey(s))
    	    	tab.put(s, 1);
    	    else{
    	    	int d = tab.remove(s);
    	    	tab.put(s, d+1);
    	    }
    	    
    	    comp++;
    	}
    	
    	LinkedList<String> l = new LinkedList<String>();
    	for(String s: tab.keySet())
    		if(tab.get(s)>=frequency*comp)
    			l.add(s);
    	
		return l;
	}

	
	
    public static void main(String[] args) {
    	
    	MurmurHash3 f = new MurmurHash3();
    	
    	// Testing significant words
    	for (String s : Sampling.findSignificantWords(FileManager.Files.bible, 5, f, 100)) {
    		System.out.println(s);
    		//System.out.println(Integer.toBinaryString(f.hashString(s)));
    	}
    	
    	
    	
    	
    	
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
    	
    	
    	
    	
    	
    	// Testing icebergs
    	
    	double threshold = 0.05;
		System.out.println("Estimation");
		for(String str: Sampling.findIcebergs(FileManager.Files.bible, threshold))
			System.out.println(str);
		System.out.println("-----------------------------------");
		System.out.println("Real");
		for(String str: Sampling.benchmarkIcebergs(FileManager.Files.bible, threshold))
			System.out.println(str);
		System.out.println("-----------------------------------");
		
		
		// --> The algorithm from the subject gives many false positive
    	
    }
}
