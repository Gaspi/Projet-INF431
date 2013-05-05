package sampling;

import hash.hashFunction;
import hash.hashFunctionTests2;
import hash.hashFunctions.LookUp3;
import hash.hashFunctions.MurmurHash3;
import FileManager.WordReader;

import java.nio.file.Path;
import java.util.LinkedList;

/**
 * 
 * Class meant to extract significant words from a text file so that a Google research with these
 * words lead to the proper file.
 * 
 */
public class SignificantWords {

    /**
     * Provide a list of a given length of characteristic words in a file.
     * 
     * @param path
     *            Path the the file.
     * @param nbWords
     *            Number of desired characteristic words.
     * @param func
     *            The hash function used on words.
     * @param k
     *            Parameter from the subject (half the size of the bag used for sampling)
     *            
     * @return A list of 'nbWords' characteristic words.
     */
    public static LinkedList<String> getSignificantWords(Path path, int nbWords, hashFunction func,
	    int k) {

	if (2 * k < nbWords)
	    throw new AssertionError("Too much significant words asked for the given sample size.");

	WordsSample ws = new WordsSampleOne(k, func);

	for(String str: new WordReader(path)) {
	    ws.addWord(str);
	}

	// Extract only nbWords
	LinkedList<String> wordsList = ws.words();
	LinkedList<String> l = new LinkedList<String>();
	for (int i = 0; i < nbWords; i++)
	    l.add(wordsList.removeFirst());

	return l;
    }

    
    
    
    
    
    public static void main(String[] args) {

	LookUp3 f = new LookUp3();
	for (String s : SignificantWords.getSignificantWords(hashFunctionTests2.hamlet, 5, f, 200)) {
	    System.out.println(s);
	    System.out.println(Integer.toBinaryString(f.hashString(s)));
	}
    }
}
