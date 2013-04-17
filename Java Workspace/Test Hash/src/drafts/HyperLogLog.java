package drafts;

import hash.hashFunction;
import hash.hashFunctionTests;
import hash.hashFunctions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class HyperLogLog {

	/**
	 * alpha[b] is meant to be equal to \alpha_{2^b} Values computed by Wolfram
	 * Mathematica
	 */
	private static double[] alpha = { 0, 0.351194, 0.532435, 0.625609, 0.673102, 0.697123, 0.709208, 0.715271, 0.718308, 0.719827, 0.720587, 0.720967, 0.721157, 0.721252, 0.721300, 0.721324, 0.721336 };

	// Just for the record, the suggested values are : 0,
	// 0 , 0 , 0 , 0.673000,
	// 0.697000, 0.709000, 0.715270, 0.718273,
	// 0.719783, 0.720541, 0.720920, 0.721110,
	// 0.721205, 0.721253, 0.721276, 0.721288

	/**
	 * Asses the number of different words in a text file using the hyperloglog
	 * algorithm.
	 * 
	 * @param path
	 *            Path of the file to read.
	 * @param func
	 *            Hash function used.
	 * @param b
	 *            Parameter of the algorithm. (4 <= b <= 16 expected)
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
		// We consider anyway that this value is erased during the main loop,
		// otherwise the result is 0.

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
						int j = (int) (x & (m - 1));
						long w = x >>> b;
						M[j] = Math.max(M[j], rho(w));
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
		return alpha[b] * ((double) m) * ((double) m) / sum;
	}

	/**
	 * @return The position of the first 1 encountered when reading the 2-base
	 *         decomposition of the positive integer x.
	 */
	private static int rho(long x) {
		int res = 1;
		// While the Least Significant Bit is 0 but x is not zero
		while ((x & 1) == 0 && x != 0) {
			res++;
			x >>>= 1; // Offset one bit to the left
		}
		return res;
	}

	public static double benchmark(Path path) {
		Hashtable<String, String> tab = new Hashtable<String, String>();
		Charset charset = Charset.forName("US-ASCII");
		try {
			BufferedReader reader = Files.newBufferedReader(path, charset);
			String line = null;
			StringTokenizer st = new StringTokenizer("");
			String token = "";
			try {

				while ((line = reader.readLine()) != null) {
					st = new StringTokenizer(line);

					while (st.hasMoreTokens()) {
						token = st.nextToken();
						if (!tab.containsKey(token))
							tab.put(token, token);
					}
				}

			} finally {
				reader.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return (double) tab.size();
	}

	public static void main(String[] args) {
		// Linux command to count the number of different words in the file
		// tr ' ' '     > ' < Shakespeare_complete_processed.txt | sort | uniq -c | wc -l 
		//  --> give 27910 so the benchmark is pretty correct
		
		Path path = hashFunctionTests.englishWords;
		
		System.out.println(benchmark(path));
		System.out.println(benchmark(Paths.get("Shakespeare_complete_processed2.txt")));
		for(int i=1; i<16; i++)
			System.out.println("b = " + i + " : " + hyperLogLog(path, new LookUp3(), i));
		
		for(int i=1; i<16; i++)
			System.out.println("b = " + i + " : " + hyperLogLog(path, new JavaHash(), i));
		
		// There a peak in performance around b = 10 - 11 - 12. Maybe we could try with greater values of b.
	}
}
