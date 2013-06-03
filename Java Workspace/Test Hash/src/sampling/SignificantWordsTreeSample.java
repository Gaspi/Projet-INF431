package sampling;

import hash.HashFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * Not working
 *
 */
public class SignificantWordsTreeSample extends SignificantWordsSample {

    // Array of list of Occurrence objects of length 32. The list at index i will contain words
    // whose hash value has exactly i trailing zeros (number of 0 before the first 1 encountered
    // from right to left in a binary representation on the integer)
    ArrayList<TreeMap<String, Integer>> strTab = new ArrayList<TreeMap<String, Integer>>(33);
    
    
    public SignificantWordsTreeSample(int k, HashFunction func) {
    	super(k, func);
    	
    	for (int i = 0; i <= 32; i++)
    		strTab.add(new TreeMap<String, Integer>());
    }
    

    @Override
    public void addWord(String s) {
    	// Equivalent of rho function
    	int nbTrailingZeros = Integer.numberOfTrailingZeros(func.hashString(s));
    	
    	// Consider only words whose hash value begins with more than b zeros.
    	if (nbTrailingZeros >= b) {
    		TreeMap<String, Integer> l = strTab.get(nbTrailingZeros);
    		
    		Integer i = l.get(s);
    		// If we have already seen the word, increase its number of occurrences.
    		// Else add the word to the array at the proper position.
			if (i == null)
				l.put(s, 1);
			else
				l.put(s, i.intValue() + 1);
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
    	LinkedList<String> ll = new LinkedList<String>();
    	
    	// Return only the words appearing with low frequencies. Here words with less than 5 occurrences
    	// in the text.
    	for (int i = b; i <= 32; i++){
    		TreeMap<String, Integer> t = strTab.get(i);
    		for (String s : t.keySet()){
    			int occ = t.get(s);
    			if (occ <= 5)
    				ll.add(s);
    		}
    	}
    	
    	return ll;
    }
    

    @Override
    public double estimateMiceNumber(int nbOcc){
    	double comp = 0;
    	for(int i = b; i <= 32 ; i++){
    		TreeMap<String, Integer> t = strTab.get(i);
    		for (String s : t.keySet())
    			if (t.get(s) == nbOcc)
    				comp++;
    				
    	}
    	
    	return comp * Math.pow(2, b);
    }
    

}
