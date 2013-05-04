package sampling;

import hash.hashFunction;

import java.util.LinkedList;

/**
 * Template class for the implementation required for question 5.
 */
public abstract class WordsSample {
    int k;
    int nbElements;
    hashFunction func;
    int b;
    int bMax;
    
    public WordsSample(int k, int bMax, hashFunction func){
	this.k = k;
	this.bMax = bMax;
	this.func = func;
	this.nbElements = 0;
	this.b = 0;
    }
    
    public abstract boolean addWord(String string);
    
    public abstract LinkedList<String> words();
}
