import drafts.HyperLogLog;
import filter.Filter;


public class Main {
	
	
	public static void main(String[] args) {
		if (args.length > 0) {
			
			if (args[0].compareTo("comptage") == 0)
				HyperLogLog.exec();
			
			if (args[0].compareTo("filtre") == 0)
				Filter.exec();
						
		}

	}

}
