package drafts;

import hash.HashFunction;

import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Random functions that may be useful
 */
public class Draft {

	/**
	 * Print in the standard output the matrix given.
	 * 
	 * @param M
	 *            The square matrix to be printed.
	 */
	public static void printMatrix(Object[][] M) {

		// Calculation of the length of each column
		int[] maxLength = new int[M[0].length];
		for (int j = 0; j < M[0].length; j++) {
			maxLength[j] = M[0][j].toString().length();
			for (int i = 1; i < M.length; i++)
				maxLength[j] = Math.max(M[i][j].toString().length(), maxLength[j]);
			maxLength[j] += 3;
		}
		
		// Display
		String line, word;
		for (int i = 0; i < M.length; i++) {
			line = "";
			for (int j = 0; j < M[i].length; j++) {
				word = M[i][j].toString();
				while (word.length() < maxLength[j])
					word += " ";
				line += word;
			}
			System.out.println(line);
		}
	}
	
	
	public static String ask(String msg) {
    	Scanner sc = new Scanner(System.in);
    	System.out.print(msg + " :\n-> ");
    	//while(!sc.hasNextLine());
    	String res = sc.nextLine();
    	System.out.println(res);
    	sc.close();
    	return res;
	}
	
	public static boolean isInRange(String number, int min, int max) {
		try {
			int a = Integer.parseInt(number);
			return min <= a && a <= max;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static void checkRange(String number, int min, int max) {
		if ( !isInRange(number, min, max) )
			throw new IllegalArgumentException(
				"Parameter should be in range " + min + " to " + max + " : " + number);
	}
	
	public static boolean isInRange(String number, double min, double max) {
		try {
			double a = Double.parseDouble(number);
			return min <= a && a <= max;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static void checkRange(String number, double min, double max) {
		if ( !isInRange(number, min, max) )
			throw new IllegalArgumentException(
				"Parameter should be in range " + min + " to " + max + " : " + number);
	}
	
	public static boolean isPath(String path) {
		return Files.exists( Paths.get(path) );
	}
	
	public static void checkPath(String path) throws NoSuchFileException {
    	if( !isPath(path) )
        	throw new NoSuchFileException(path);
	}
	
	public static boolean isHash(String hash) {
		return HashFunction.isHashFunction(hash);
	}
	
	public static void checkHash(String hash) {
		if( ! isHash(hash) )
			throw new IllegalArgumentException( "Not a valid hash function: " + hash );
	}
	
}
