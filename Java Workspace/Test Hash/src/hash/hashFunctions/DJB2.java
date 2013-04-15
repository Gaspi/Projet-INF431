package hash.hashFunctions;

import hash.hashFunction;

/**
 * Found here :
 * http://www.cse.yorku.ca/~oz/hash.html
 */
public class DJB2 implements hashFunction {

	@Override
	public int hashString(String s) {
		return hashByteArray(s.getBytes());
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
