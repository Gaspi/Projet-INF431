package sampling;

import hash.hashFunction;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * First implementation: array of LinkedList. Easier but I think a tree-based
 * class would be better.
 */
public class WordsSampleOne extends WordsSample {

    ArrayList<LinkedList<Occurrence>> strTab;

    public WordsSampleOne(int k, hashFunction func) {
	super(k, func);

	this.strTab = new ArrayList<LinkedList<Occurrence>>(33);
	for (int i = 0; i <= 32; i++)
	    strTab.add(new LinkedList<Occurrence>());
    }

    @Override
    public boolean addWord(String s) {
	int nbTrailingZeros = Integer.numberOfTrailingZeros(func.hashString(s));

	if (nbTrailingZeros >= this.b) {
	    LinkedList<Occurrence> l = this.strTab.get(nbTrailingZeros);
//	    if(!l.contains(s)){
//		l.add(s);
//		this.nbElements++;
//	    }
	    Occurrence sOcc = new Occurrence(s, 1);
	    int i = l.indexOf(sOcc);
	    if (i == -1) {
		l.add(sOcc);
		this.nbElements++;
	    } else
		l.get(i).nbOcc++;
	}

	if (this.nbElements == 2 * this.k) {
	    this.nbElements -= this.strTab.get(this.b).size();
	    this.strTab.set(this.b, new LinkedList<Occurrence>());
	    this.b++;
	}

	return (this.b >= 32);
    }

    @Override
    public LinkedList<String> words() {
	LinkedList<String> l = new LinkedList<String>();
	
	for (int i = this.b; i <= 32; i++){
//	    l.addAll(this.strTab.get(i));
	    for (Occurrence occ : this.strTab.get(i)) 
		if(occ.nbOcc <= 1)
		    l.add(occ.word);
	}

	return l;

    }
}
