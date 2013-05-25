package hash;


/**
 * Using of the Java default hash function.
 */
public class JavaHash extends HashFunction{

	@Override
	public int hashString(String s){
		return s.hashCode();
	}

	@Override
	public int hashByteArray(byte[] array){
		String s = new String(array);
		return this.hashString(s);
	}

}
