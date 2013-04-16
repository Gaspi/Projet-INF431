package hash.hashFunctions;

import hash.hashFunction;

/**
 * Using of the Java default hash function.
 */
public class JavaHash implements hashFunction{

	@Override
	public int hashString(String s){
		return s.hashCode();
	}

	@Override
	public int hashByteArray(byte[] array){
		String s = new String(array);
		return this.hashString(s);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args){

	}

}
