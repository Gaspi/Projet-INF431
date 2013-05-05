package sampling;

import hash.hashFunction;

import java.util.LinkedList;

/**
 * Template class for the implementation required for question 5. This way we can try several different ways.
 */
public abstract class WordsSample {
    int k;
    int nbElements;
    hashFunction func;
    int b;
    
    public WordsSample(int k, hashFunction func){
	this.k = k;
	this.func = func;
	this.nbElements = 0;
	this.b = 0;
    }
    
    public abstract void addWord(String string);
    
    public abstract LinkedList<String> words();
}
