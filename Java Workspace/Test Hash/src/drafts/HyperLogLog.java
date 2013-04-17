package drafts;

import hash.hashFunction;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringTokenizer;

public class HyperLogLog {
	
	/**
	 * alpha[b] is meant to be equal to \alpha_{2^b}
	 * Values processed by Wolfram Mathematica
	 */
	private static double[] alpha = {0,
			0.351194, 0.532435, 0.625609, 0.673102,
			0.697123, 0.709208, 0.715271, 0.718308,
			0.719827, 0.720587, 0.720967, 0.721157,
			0.721252, 0.721300, 0.721324, 0.721336
	};
	
//	Just for the record, the suggested values are : 0,
//			0       , 0       , 0       , 0.673000,
//			0.697000, 0.709000, 0.715270, 0.718273,
//			0.719783, 0.720541, 0.720920, 0.721110,
//			0.721205, 0.721253, 0.721276, 0.721288
	
	
	
	/**
	 * Asses the number of different words in a text file using the hyperloglog algorithm.
	 * @param path Path of the file to read.
	 * @param func Hash function used.
	 * @param b Parameter of the algorithm. (4 <= b <= 16 expected)
	 * @return The approximative number of different words in the file
	 */
	public static double hyperLogLog(Path path, hashFunction func, int b) {
		
		if (b <= 0 || b > 16)
			throw new AssertionError("hyperLogLog :  b <= 0 or b > 16");
		
		int m = 1 << b; // m = 2^b
		int[] M = new int[m];
		
		for (int i = 0; i < m; i++)
			M[i] = -1;
		// Rq : -1 = -\infty
		// We consider anyway that this value is erased during the main loop, otherwise the result is 0.
		
		Charset charset = Charset.forName("US-ASCII");
		try {
			BufferedReader reader = Files.newBufferedReader(path, charset);
			String line = null;
			StringTokenizer st = new StringTokenizer("");
			try {
				while ((line = reader.readLine()) != null) {
					st = new StringTokenizer(line);
					
					while (st.hasMoreTokens()) {
						long x = func.hashString(st.nextToken());
						int j = (int) ( x & (m-1) );
						long w = x >>> b;
						M[j] = Math.max( M[j], rho(w) );
					}
					
				}
			} finally {
				reader.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// Calculation of the result
		double sum = 0;
		for (int j = 0; j < m; j++)
			sum += Math.pow(2, -M[j]);
		return alpha[b] * ( (double)m ) * ( (double)m ) / sum ;
	}
	
	
	
	/**
	 * @return La position du premier 1 dans le codage en base 2 de x positif.
	 */
	private static int rho(long x) {
		int res = 1;
		// Tant que le bit de poids faible est un 0 et qu'on est non nul
		while ( (x & 1) == 0 && x != 0) { 
			res++;
			x >>>= 1; // Décalage d'un bit à gauche.
		}
		return res;
	}
	
	
	
	
	public static void main(String[] args) {
		
	}

}
