package filter;

import java.nio.file.NoSuchFileException;

import drafts.*;
import FileManager.*;


public abstract class Filter {

	/**
	 * Filter an unique string
	 * 
	 * @param line
	 * @return The line filtered
	 */
	public abstract String filter(String line);

	/**
	 * Read the file line by line. Each line is filtered with the filter method
	 * and written in the target file.
	 * 
	 * @param fileForReading
	 *            A String representing the path to the file we want to process.
	 * @param fileForWritting
	 *            A String representing the path to the location where the
	 *            processed file should be written.
	 */
	public final void filterFile(String fileForReading, String fileForWritting) {

		FileWriter fw = new FileWriter(fileForWritting);
		FileReader lr = new LineReader(fileForReading);
		
		for (String line : lr)
			fw.write(filter(line));
	}
	
	
	
	// This allow to filter files
	public static void main(String[] args) throws NoSuchFileException, IllegalArgumentException {
		
    	if (args.length == 2) {
    		
        	Draft.checkPath( args[0] );
        	exec(args[0], args[1]);
        	
    	} else if (args.length == 0) {
    		
        	String origin = GetInput.askPath("Path of the origin");
        	String target = GetInput.askPath("Path of the target");
        	exec(origin, target);
        	
    	} else
    		throw new IllegalArgumentException("Wrong number of arguments (2 expected)");
		
	}
	
	
    public static void exec(String origin, String target) {
    	new EnglishWordsFilter().filterFile( origin, target );
    }
	
}
