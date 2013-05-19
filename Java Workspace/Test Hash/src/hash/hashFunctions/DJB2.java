package hash.hashFunctions;

import hash.HashFunction;

/**
 * Found here :
 * http://www.cse.yorku.ca/~oz/hash.html
 */
public class DJB2 extends HashFunction {

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
		
		int hash = 5381;
		for(byte b: array)
			hash = ((hash << 5) + hash) + b; //hash * 33 + b
		
		return hash;
	}


	public static void main(String[] args) {

	}

}
