package filter;

import drafts.GetInput;
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
	public final void filterFile2(String fileForReading, String fileForWritting) {

		FileWriter fw = new FileWriter(fileForWritting);
		FileReader lr = new LineReader(fileForReading);
		
		for (String line : lr)
			fw.write(filter(line));
	}
	
	
	
	// This allow to filter files
	public static void main(String[] args) {
		if (args.length == 2) {
			exec( args[0], args[1] );
		} else {
			GetInput gi = new GetInput();
			System.out.println("--- English words filter ---");
			String origin = gi.ask("File to process");
			String target = gi.ask("File to create");
			exec(origin, target);
		}
	}
	
    public static void exec(String origin, String target) {
    	new EnglishWordsFilter().filterFile2( origin, target );
    }
	
}
