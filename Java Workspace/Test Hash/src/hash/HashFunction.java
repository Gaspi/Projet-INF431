package hash;

public abstract class HashFunction {
	
	public final static String[] names = {"LookUp3", "MurmurHash3", "JavaHash", "LoseLose", "HomemadeHash", "DJB2"}; 
	
	/**
	 * @return The hash code corresponding to the string parameter.
	 */
	public abstract int hashString(String s);
	
	/**
	 * @return The hash code corresponding to the byte array parameter.
	 */
	public abstract int hashByteArray(byte[] array);
	
	
	public final static boolean isHashFunction(String name){
		boolean found = false;
		for(String s: names)
			found = found || name.equals(s);
		return found;
	}
	
	
	public final static HashFunction getHashFunction(String name){
		try {
			return (HashFunction) Class.forName("hash." + name).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		    return null;
		}
	}
	
//	public static HashFunction DJB2 = new DJB2();
//	public static HashFunction HomemadeHash = new HomemadeHash();
//	public static HashFunction JavaHash = new JavaHash();
//	public static HashFunction LookUp3 = new LookUp3();
//	public static HashFunction LoseLose = new LoseLose();
//	public static HashFunction MurmurHash3 = new MurmurHash3();
//	
//	public static HashFunction[] hashFunctions = {
//		DJB2, HomemadeHash, JavaHash, LookUp3, LoseLose, MurmurHash3
//	};
	
}
