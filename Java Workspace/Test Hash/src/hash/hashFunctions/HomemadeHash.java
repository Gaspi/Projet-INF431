package hash.hashFunctions;

import hash.hashFunction;
import drafts.UnsignedArithmetic;

public class HomemadeHash implements hashFunction{

	@Override
	public int hashString(String s){
		return this.hashByteArray(s.getBytes());
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
