package sampling;

import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.LinkedList;

import drafts.Draft;
import drafts.GetInput;

import FileManager.WordReader;

public class Icebergs {

	/**
	 * 
	 * @param path Path to the file to read for icebergs.
	 * @param frequency The lower bound of the iceberg's frequencies that the function should return
	 * @return Approximately all icebergs whose frequency is over 'frequency'
	 */
	public static LinkedList<String> findIcebergs(Path path, double frequency){
		IcebergSample sample = new IcebergHashMapSampleImproved(frequency, 0.9);
		
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
	
	
	// This answers question 7
	public static void main(String[] args) throws NoSuchFileException, IllegalArgumentException {
		
//    	if (args.length == 2) {
//    		
//        	Draft.checkRange( args[0], 0., 1. );
//        	Draft.checkPath( args[1] );
//        	exec(args[1], Double.parseDouble(args[0]));
//        	
//    	} else if (args.length == 0) {
//    		
//        	String path = GetInput.askPath("Path to the file");
//        	double frequency = GetInput.askParameterInRange("Frequency", 0., 1.);
//        	exec( path, frequency );
//        	
//    	} else
//    		throw new IllegalArgumentException("Wrong number of arguments (2 expected)");
		
		
		
	}
	
	
    public static void exec(String path, double frequency){
    	LinkedList<String> l = findIcebergs(Paths.get(path), frequency);
    	System.out.println("----------------------------------------------------------------");
    	System.out.println(System.lineSeparator() + "Approximating the number of " + frequency + "-icebergs for file:");
    	System.out.println("	" + path);
    	System.out.println("Results :");
    	for(String s: l)
    		System.out.println("	" + s);
    	System.out.println("----------------------------------------------------------------");
    }

}
