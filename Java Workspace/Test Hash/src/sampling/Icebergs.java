package sampling;

import hash.hashFunctionTests2;

import java.nio.file.Path;
import java.util.Hashtable;
import java.util.LinkedList;

import FileManager.WordReader;

/**
 * 
 * Class for finding frequent elements in a text file (icebergs).
 *
 */
public class Icebergs {

	/**
	 * 
	 * @param path Path to the file to read for icebergs.
	 * @param frequency The lower bound of the iceberg's frequencies that the function should return
	 * @return Approximately all icebergs whose frequency is over 'frequency'
	 */
	public static LinkedList<String> findIcebergs(Path path, double frequency){
		IcebergSample sample = new IcebergTreeSample(frequency);
		
		for(String str: new WordReader(path))
			sample.addWord(str);
		
		return sample.getIcebergs();
	}
	
	
	/**
	 * 
	 * @param path Path to the file to read for icebergs.
	 * @param frequency The lower bound of the iceberg's frequencies that the function returns
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
		System.out.println("Estimation");
		for(String str: Icebergs.findIcebergs(hashFunctionTests2.bible, 0.05))
			System.out.println(str);
		System.out.println("-----------------------------------");
		System.out.println("Real");
		for(String str: Icebergs.benchmarkIcebergs(hashFunctionTests2.bible, 0.05))
			System.out.println(str);
		System.out.println("-----------------------------------");
		
		
		// --> The algorithm from the subject gives many false positive
	}

}
