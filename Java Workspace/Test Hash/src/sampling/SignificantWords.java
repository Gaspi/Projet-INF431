package sampling;

import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

import hash.HashFunction;

import drafts.Draft;
import drafts.GetInput;

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
    
    
    // This answers question 5
	public static void main(String[] args) throws NoSuchFileException, IllegalArgumentException {
		
    	if (args.length == 4) {
    		
        	Draft.checkHash(  args[0] );
        	Draft.checkRange( args[1], 1, 1000 );
        	Draft.checkRange( args[2], 1, 1000000 );
        	Draft.checkPath(  args[3] );
        	exec(args[3], Integer.parseInt(args[1]), args[0], Integer.parseInt(args[2]));
        	
    	} else if (args.length == 0) {
    		
        	String path = GetInput.askPath("Path to the file");
        	String hash = GetInput.askHash("Hash function");
        	int nbWords = GetInput.askParameterInRange("Number of words needed", 1, 1000);
        	int halfBag = GetInput.askParameterInRange("Half size of the bag", 1, 1000000);
        	exec(path, nbWords, hash, halfBag);
        	
    	} else
    		throw new IllegalArgumentException("Wrong number of arguments (4 expected)");
		
	}
	
    public static void exec(String path, int nbWords, String func, int k){
    	LinkedList<String> l = findSignificantWords(Paths.get(path), nbWords, HashFunction.getHashFunction(func), k);
    	
    	System.out.println("----------------------------------------------------------------");
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
