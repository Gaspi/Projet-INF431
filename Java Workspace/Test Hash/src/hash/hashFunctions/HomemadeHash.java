package hash.hashFunctions;

import hash.hashFunction;
import drafts.UnsignedArithmetic;

/**
 * A simple homemade hash function
 * @author Jonathan VISBECQ
 */
public class HomemadeHash implements hashFunction{

	@SuppressWarnings("deprecation")
	@Override
	public int hashString(String s){
		byte[] b = new byte[s.length()];
		// Increase speed.
		// Safe if char is less than 8 bits long, for exemple for ASCII caracters.
		s.getBytes(0, s.length(), b, 0);
		return hashByteArray(b);
	}

	@Override
	public int hashByteArray(byte[] array){
		int hash = 0;
		for(byte c: array){
		    hash = (hash<<6);
		    hash = (hash>>6);
		    hash += UnsignedArithmetic.unsignedAdd(hash, c);
		}

		return hash;
	}


	public static void main(String[] args){

	}

}