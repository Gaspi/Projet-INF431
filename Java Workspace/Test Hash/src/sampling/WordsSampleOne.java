package sampling;

import hash.hashFunction;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * First implementation: array of LinkedList. Easier but I think a tree would be better.
 */
public class WordsSampleOne extends WordsSample {

    ArrayList<LinkedList<String>> strTab;

    public WordsSampleOne(int k, int bMax, hashFunction func) {
	super(k, bMax, func);

	this.strTab = new ArrayList<LinkedList<String>>(this.bMax+1);
	for (int i = 0; i <= this.bMax; i++)
	    strTab.add(new LinkedList<String>());
    }

    @Override
    public boolean addWord(String s) {
	if(this.b <= this.bMax){
	    int nbTrailingZeros = Integer.numberOfTrailingZeros(func.hashString(s));
	    if(nbTrailingZeros > this.bMax)
		nbTrailingZeros = this.bMax;
		
	    if(nbTrailingZeros >= this.b){
		LinkedList<String> l = this.strTab.get(nbTrailingZeros);
		if(!l.contains(s)){
		    this.strTab.get(nbTrailingZeros).add(s);
		    this.nbElements++;
		}
	    }
	    
	    if(this.nbElements == 2*this.k){
		this.nbElements -= this.strTab.get(this.b).size();
		this.strTab.set(this.b, new LinkedList<String>());
		this.b++;
	    }
	}
	
	return (this.b > this.bMax);
    }

    @Override
    public LinkedList<String> words() {
	if(this.b >= this.bMax){
	    System.out.println("eeee");    	    
	    return this.strTab.get(this.bMax);
	}
	else{
	    LinkedList<String> l = new LinkedList<String>();
	    for(int i=this.b; i<this.bMax; i++)
		l.addAll(this.strTab.get(i));
	    
	    return l;
	}
    }
}
