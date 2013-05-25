package sampling;

import hash.HashFunction;

import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
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
	


	public static void main(String[] args) throws NoSuchFileException, IllegalArgumentException {
		
    	if(args.length != 2)
    		throw new IllegalArgumentException("Wrong number of arguments");
    	
    	if(Double.parseDouble(args[0]) < 0 || Double.parseDouble(args[0])  > 1)
    		throw new IllegalArgumentException("Frequency should be in range 0-1");
    	
    	if(!Files.exists(Paths.get(args[1])))
        	throw new NoSuchFileException(args[1]);

		
    	exec(args[1], Double.parseDouble(args[0]));

	}
	
    public static void exec(String path, double frequency){
    	LinkedList<String> l = findIcebergs(Paths.get(path), frequency);
    	
    	System.out.println(System.lineSeparator() + "Approximating the number of " + frequency + "-icebergs for file:");
    	System.out.println("	" + path);
    	System.out.println("Results :");
    	for(String s: l)
    		System.out.println("	" + s);
    	System.out.println("----------------------------------------------------------------");
    }

}
