package drafts;

import hash.hashFunction;
import hash.hashFunctionTests2;
import hash.hashFunctions.*;

import java.nio.file.Path;
import java.util.Hashtable;

import FileManager.WordReader;

public class HyperLogLog {

	/**
	 * alpha[b] is meant to be equal to \alpha_{2^b} Values computed by Wolfram
	 * Mathematica
	 */
	private static double[] alpha = { 0, 0.351194, 0.532435, 0.625609,
			0.673102, 0.697123, 0.709208, 0.715271, 0.718308, 0.719827,
			0.720587, 0.720967, 0.721157, 0.721252, 0.721300, 0.721324,
			0.721336 };

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

		// First modification: initialize the registers with 0 (not -\infty) to
		// get correct estimate
		// for small cardinalities.
		for (int i = 0; i < m; i++)
			M[i] = 0;

		for (String s : new WordReader(path)) {
			long x = func.hashString(s);
			int j = (int) (x & (m - 1));
			long w = x >>> b;
			M[j] = Math.max(M[j], rho(w));
		}

		// Calculation of the result
		double sum = 0;
		for (int j = 0; j < m; j++)
			sum += Math.pow(2, -M[j]);

		double e = alpha[b] * ((double) m) * ((double) m) / sum;

		double n = Math.pow(2., 32.);

		// Second modification (first if statement): corrections when e is comparatively small with
		// respect to m 
		// Third modification (second if statement): corrections to account for collision effects due
		// to the hash function
		if (e < (5. / 2.) * m) {
			double v = 0;
			for (int i = 0; i < m; i++)
				if (M[i] == 0)
					v++;

			if (v != 0)
				e = m * Math.log(m / v);
		}
		else if (e > n / 30)
			e = -n * Math.log(1 - e / n);
		
		return e;
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

		for (String s : new WordReader(path))
			if (!tab.containsKey(s))
				tab.put(s, s);

		return (double) tab.size();
	}

	public static void main(String[] args) {

		Path path = hashFunctionTests2.shakespeare;
		//Path path = hashFunctionTests2.englishWords;
		//Path path = hashFunctionTests2.bible;

		double bench = benchmark(path);
		System.out.println(bench);
		for (int i = 1; i < 16; i++) {
			double h = hyperLogLog(path, new LookUp3(), i);
			System.out.println("b = " + i + " : " + h + " ; error : "
					+ (Math.abs(h - bench) / bench) * 100 + "%");
		}


	}
}
