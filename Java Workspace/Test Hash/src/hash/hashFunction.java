package hash;

public interface hashFunction {
	
	/**
	 * @return The hash code corresponding to the string parameter.
	 */
	public int hashString(String s);
	
	/**
	 * @return The hash code corresponding to the byte array parameter.
	 */
	public int hashByteArray(byte[] array);
}
