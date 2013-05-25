package drafts;

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
	
}
