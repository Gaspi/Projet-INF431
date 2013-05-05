package sampling;

import hash.hashFunction;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * First implementation: array of LinkedList. Easier but I think a tree-based class would be better.
 * The use of the class Occurrence is to track for each word the frequency at which it appears in
 * the text.
 */
public class WordsSampleOne extends WordsSample {

    // Array of list of Occurrence objects of length 32. The list at index i will contain words
    // whose hash value has exactly i trailing zeros (number of 0 before the first 1 encountered
    // from right to left in a binary representation on the integer)
    ArrayList<LinkedList<Occurrence>> strTab = new ArrayList<LinkedList<Occurrence>>(33);

    public WordsSampleOne(int k, hashFunction func) {
	super(k, func);

	for (int i = 0; i <= 32; i++)
	    strTab.add(new LinkedList<Occurrence>());
    }

    @Override
    public void addWord(String s) {
	int nbTrailingZeros = Integer.numberOfTrailingZeros(func.hashString(s));

	// Consider only words whose hash value begins with more than b zeros.
	if (nbTrailingZeros >= this.b) {
	    LinkedList<Occurrence> l = this.strTab.get(nbTrailingZeros);

	    // if(!l.contains(s)){
	    // l.add(s);
	    // this.nbElements++;
	    // }

	    Occurrence sOcc = new Occurrence(s, 1);
	    int i = l.indexOf(sOcc);

	    // If we have already seen the word, increase its number of occurrences. Else add the
	    // word to the array at the proper position.
	    if (i == -1) {
		l.add(sOcc);
		this.nbElements++;
	    } else
		l.get(i).nbOcc++;
	}

	// If the array has more than 2*k elements stored, remove the elements with less than b
	// trailing zeros then increase b.
	if (this.nbElements == 2 * this.k) {
	    this.nbElements -= this.strTab.get(this.b).size();
	    this.strTab.set(this.b, new LinkedList<Occurrence>());
	    this.b++;
	}
    }

    @Override
    public LinkedList<String> words() {
	LinkedList<String> l = new LinkedList<String>();

	// Return only the words appearing with low frequencies. Here with less than 5 occurencies
	// in the text.
	for (int i = this.b; i <= 32; i++) {
	    // l.addAll(this.strTab.get(i));
	    for (Occurrence occ : this.strTab.get(i))
		if (occ.nbOcc <= 5)
		    l.add(occ.word);
	}

	return l;

    }
}
