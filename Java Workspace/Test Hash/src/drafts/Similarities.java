package drafts;

import hash.hashFunction;
import hash.hashFunctionTests2;
import hash.hashFunctions.*;

import java.nio.file.Path;

public class Similarities {

	public static double resemblance(Path pathA, Path pathB, hashFunction func, int b, int k){
		
		if (b <= 0 || b > 16)
			throw new AssertionError("hyperLogLog :  b <= 0 or b > 16");

		int m = 1 << b; // m = 2^b
		
		// Build fingerPrint arrays
		int [] MA = HyperLogLog.buildFingerPrint(pathA, func, b, k);
		int [] MB = HyperLogLog.buildFingerPrint(pathB, func, b, k);
		
		int[] MAuB = new int[m];
		for(int i=0; i<m; i++)
			MAuB[i] = Math.max(MA[i], MB[i]);
		
		// Compute the number of k-shingles
		double SA = HyperLogLog.hyperLogLog(MA);
		double SB = HyperLogLog.hyperLogLog(MB);
		
		// Compute the resemblance
		double SAuB = HyperLogLog.hyperLogLog(MAuB);
		double SAnB = SA + SB - SAuB;
			
		return SAnB/SAuB;
	}
	
	
	public static void main(String[] args) {
		System.out.println(Similarities.resemblance(hashFunctionTests2.shakespeare, hashFunctionTests2.bible, new LookUp3() , 15, 1));
	}

}
