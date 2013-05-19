package hash.hashFunctions;

import hash.HashFunction;
import drafts.UnsignedArithmetic;

/**
 * A trivial and inefficient hash function.
 * Found here :
 * http://www.cse.yorku.ca/~oz/hash.html
 */
public class LoseLose implements HashFunction{


	@SuppressWarnings("deprecation")
	@Override
	public int hashString(String s) {
		byte[] b = new byte[s.length()];
		// Increase speed.
		// Safe if char is less than 8 bits long, for exemple for ASCII caracters.
		s.getBytes(0, s.length(), b, 0);
		return hashByteArray(b);
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
