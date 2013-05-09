package sampling;

import hash.hashFunction;

import java.util.LinkedList;

/**
 * Template class for the implementation required for question 5. This way we can try several different ways.
 */
public abstract class SignificantWordsSample {
	// Half the maximum size of the sample
    int k;
    // Current number of elements in the sample
    int nbElements;
    // Hash function used
    hashFunction func;
    // Current depth of the algorithm
    int b;
    
    public SignificantWordsSample(int k, hashFunction func){
    	this.k = k;
    	this.func = func;
    	nbElements = 0;
    	b = 0;
    }
    
    // Make the algorithm notice that the word 'word' has been read.
    public abstract void addWord(String string);
    // Retrieve a list of significant words
    public abstract LinkedList<String> words();
    // Retrieve the approximate number of mice occurring nbOcc times in the text
    public abstract double estimateMiceNumber(int nbOcc);
}
