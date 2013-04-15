package hash.hashFunctions;

import hash.hashFunction;
import drafts.UnsignedArithmetic;

/**
 * A trivial and uneffective hash function.
 * Found here :
 * http://www.cse.yorku.ca/~oz/hash.html
 */
public class LoseLose implements hashFunction{


	@Override
	public int hashString(String s) {
		return hashByteArray(s.getBytes());
	}

	@Override
	public int hashByteArray(byte[] array) {
		
		int hash = 0;
		for(byte c: array)
		    hash = UnsignedArithmetic.unsignedAdd(hash, c);
		

		return hash;
	    
	}
	
	public static void main(String[] args) {
		

	}

}
