package sampling;

import java.util.LinkedList;

/**
 * 
 * Template class for the sample used for finding icebergs. Functions from the Iceberg class
 * only use methods from the IcebergSample class. This way we can try various implementation
 * and algorithms without disturbing the Iceberg class.
 *
 */
public abstract class IcebergSample {
	// Lower bound on frequency
	final double frequency;
	
	public IcebergSample(double frequency){
		this.frequency = frequency;
	}
	
	// Make the algorithm notice that the word 'word' has been read.
	public abstract void addWord(String word);
	// Retrieve all icebergs
	public abstract LinkedList<String> getIcebergs();
}
