package sampling;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.LinkedList;

import FileManager.WordReader;

public class Icebergs {

	/**
	 * 
	 * @param path Path to the file to read for icebergs.
	 * @param frequency The lower bound of the iceberg's frequencies that the function should return
	 * @return Approximately all icebergs whose frequency is over 'frequency'
	 */
	public static LinkedList<String> findIcebergs(Path path, double frequency){
		IcebergSample sample = new IcebergTreeSampleImproved(frequency, 0.9);
		
		for(String str: new WordReader(path))
			sample.addWord(str);
		
		return sample.getIcebergs();
	}
	
	/**
	 * @param path Path to the file to read for icebergs.
	 * @param frequency The lower bound of the iceberg's frequencies that the function returns
	 * 
	 * @return All icebergs whose frequency is over 'frequency' (no approximation)
	 */
	public static LinkedList<String> benchmarkIcebergs(Path path, double frequency){
		Hashtable<String, Integer> tab = new Hashtable<String, Integer>();
		int comp = 0;
    	for (String s : new WordReader(path)){
    	    if (!tab.containsKey(s))
    	    	tab.put(s, 1);
    	    else{
    	    	int d = tab.remove(s);
    	    	tab.put(s, d+1);
    	    }
    	    
    	    comp++;
    	}
    	
    	LinkedList<String> l = new LinkedList<String>();
    	for(String s: tab.keySet())
    		if(tab.get(s)>=frequency*comp)
    			l.add(s);
    	
		return l;
	}
	


	public static void main(String[] args) {
    	// Testing icebergs
    	
//    	double threshold = 0.02;
//		System.out.println("Estimation");
//		for(String str: Sampling.findIcebergs(path, threshold))
//			System.out.println(str);
//		System.out.println("-----------------------------------");
//		System.out.println("Real");
//		for(String str: Sampling.benchmarkIcebergs(path, threshold))
//			System.out.println(str);
//		System.out.println("-----------------------------------");

	}
	
    public static void exec(String path, double frequency){
    	LinkedList<String> l = findIcebergs(Paths.get(path), frequency);
    	
    	System.out.println("Approximating the number of " + frequency + "-icebergs for file:");
    	System.out.println("	" + path);
    	System.out.println("Results :");
    	for(String s: l)
    		System.out.println("	" + s);
    	System.out.println("----------------------------------------------------------------");
    }

}
