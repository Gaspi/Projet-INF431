package sampling;

import hash.hashFunction;
import hash.hashFunctionTests2;
import hash.hashFunctions.LookUp3;
import FileManager.WordReader;

import java.nio.file.Path;
import java.util.Iterator;
import java.util.LinkedList;

public class SignificantWords {

    public static LinkedList<String> getSignificantWords(Path path,
	    int nbWords, hashFunction func, int k, int bMax) {

	if (2 * k < nbWords)
	    throw new AssertionError(
		    "Too much significant words asked for the given sample size.");

	WordsSample ws = new WordsSampleOne(k, bMax, func);
	Iterator<String> wr = (new WordReader(path)).iterator();

	while (wr.hasNext() && !ws.addWord(wr.next())) {
	}

	LinkedList<String> wordsList = ws.words();
	LinkedList<String> l = new LinkedList<String>();
	for (int i = 0; i < nbWords; i++)
	    l.add(wordsList.poll());

	return l;
    }

    public static void main(String[] args) {

	for (String s : SignificantWords.getSignificantWords(hashFunctionTests2.shakespeare,
			5, new LookUp3(), 1000, 20))
	    System.out.println(s);
    }
}
