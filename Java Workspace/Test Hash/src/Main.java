import hyperLogLog.HyperLogLog;
import filter.Filter;


public class Main {
	
	
	public static void main(String[] args) {
		if (args.length > 0) {
			
			if (args[0].compareTo("comptage") == 0)
				HyperLogLog.exec( args[1], "hash.hashFunctions.LookUp3", 11);
			
			if (args[0].compareTo("filtre") == 0)
				Filter.exec();
			
		}
		
	}
	
	// The exec functions do the default behavior as described in the subject :
	// Filter : filter a file from stdin to an adress
	// HyperLogLog : estimate the number of words in the stdin
	// hashFunctionTests2 (why "2" ?) : Description of the results of the hashing functions on the stdin
	// etc (remains to be implemented)
	//
	// These functions have to be called in the main function, so that they respond to the command :
	// java Filter < file  , java HyperLogLog < file , ...
	//
	// They can also be called through Main.java with a parameter
	//
	// if no parameter is given to Main.java, we could for example provide a more friendly user interface...
}
