package sampling;

import hash.HashFunction;
import hash.MurmurHash3;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

import FileManager.WordReader;

public class SignificantWords {

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
    
    
	public static void main(String[] args) {
    	MurmurHash3 f = new MurmurHash3();
    	Path path = Paths.get( "files/processed/Whole_Mahabharata.txt" );
    	
    	// Testing significant words
    	for (String s : findSignificantWords(path, 5, f, 100)) {
    		System.out.println(s);
    		//System.out.println(Integer.toBinaryString(f.hashString(s)));
    	}
    	

	}
	
    public static void exec(String path, int nbWords, String func, int k){
    	LinkedList<String> l = findSignificantWords(Paths.get(path), nbWords, HashFunction.getHashFunction(func), k);
    	
    	System.out.println("Finding frequent words for file:");
    	System.out.println("	" + path + System.lineSeparator());
    	System.out.println("With parameters :");
    	System.out.println("	Sampling bag of size = " + 2*k + " words" + System.lineSeparator());
    	System.out.println("Results :");
    	for(String s: l)
    		System.out.println("	" + s);
    	
    	System.out.println("----------------------------------------------------------------");

    }

}
