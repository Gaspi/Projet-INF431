package sampling;

import hash.HashFunction;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * First implementation: array of LinkedList. Easier but I think a tree-based class would be better.
 * The use of the class Occurrence is to track for each word the frequency at which it appears in
 * the text.
 */
public class SignificantWordsArraySample extends SignificantWordsSample {

    // Array of list of Occurrence objects of length 32. The list at index i will contain words
    // whose hash value has exactly i trailing zeros (number of 0 before the first 1 encountered
    // from right to left in a binary representation on the integer)
    ArrayList<LinkedList<Occurrence>> strTab = new ArrayList<LinkedList<Occurrence>>(33);
    
    
    public SignificantWordsArraySample(int k, HashFunction func) {
    	super(k, func);
    	
    	for (int i = 0; i <= 32; i++)
    		strTab.add(new LinkedList<Occurrence>());
    }
    

    @Override
    public void addWord(String s) {
    	// Equivalent of rho function
    	int nbTrailingZeros = Integer.numberOfTrailingZeros(func.hashString(s));
    	
    	// Consider only words whose hash value begins with more than b zeros.
    	if (nbTrailingZeros >= b) {
    		LinkedList<Occurrence> l = strTab.get(nbTrailingZeros);
    		
    		Occurrence sOcc = new Occurrence(s, 1);
    		int i = l.indexOf(sOcc);
    		// If we have already seen the word, increase its number of occurrences.
    		// Else add the word to the array at the proper position.
    		if (i == -1) {
    				l.add(sOcc);
    				this.nbElements++;
    		} else
    			l.get(i).nbOcc++;
    	}
    	
    	// If the array has more than 2*k elements stored, remove the elements with less than b
    	// trailing zeros then increase b, until the array has less than 2*k elements.
    	while (nbElements >= 2 * k) {
    		nbElements -= strTab.get(b).size();
    		strTab.set(b, null);
    		b++;
    	}
    }
    

    @Override
    public LinkedList<String> words() {
    	LinkedList<String> l = new LinkedList<String>();
    	
    	// Return only the words appearing with low frequencies. Here words with less than 5 occurrences
    	// in the text.
    	for (int i = b; i <= 32; i++) 
    		for (Occurrence occ : strTab.get(i))
    			if (occ.nbOcc <= 5)
    				l.add(occ.word);
    	
    	return l;
    }
    

    @Override
    public double estimateMiceNumber(int nbOcc){
    	double comp = 0;
    	for(int i = b; i <= 32 ; i++)
    		for(Occurrence occ : strTab.get(i))
    			if(occ.nbOcc == nbOcc)
    				comp++;
    	
    	return comp * Math.pow(2, b);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
