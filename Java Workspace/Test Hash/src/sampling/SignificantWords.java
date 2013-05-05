package sampling;

import hash.hashFunction;
import hash.hashFunctionTests2;
import hash.hashFunctions.LookUp3;
import hash.hashFunctions.MurmurHash3;
import FileManager.WordReader;

import java.nio.file.Path;
import java.util.Iterator;
import java.util.LinkedList;

public class SignificantWords {

    public static LinkedList<String> getSignificantWords(Path path,
	    int nbWords, hashFunction func, int k) {

	if (2 * k < nbWords)
	    throw new AssertionError(
		    "Too much significant words asked for the given sample size.");

	WordsSample ws = new WordsSampleOne(k, func);
	Iterator<String> wr = (new WordReader(path)).iterator();

	while (wr.hasNext() && !ws.addWord(wr.next())) {
	}

	LinkedList<String> wordsList = ws.words();
	LinkedList<String> l = new LinkedList<String>();
	for (int i = 0; i < nbWords; i++)
	    l.add(wordsList.removeFirst());

	return l;
    }

    public static void main(String[] args) {

	LookUp3 f = new LookUp3();
	for (String s : SignificantWords.getSignificantWords(
		hashFunctionTests2.hamlet, 5, f, 200)) {
	    System.out.println(s);
	    //System.out.println(Integer.toBinaryString(f.hashString(s)));
	}
    }
}
