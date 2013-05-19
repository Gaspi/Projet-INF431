package hash;

public interface HashFunction {
	
	/**
	 * @return The hash code corresponding to the string parameter.
	 */
	public int hashString(String s);
	
	/**
	 * @return The hash code corresponding to the byte array parameter.
	 */
	public int hashByteArray(byte[] array);
}
