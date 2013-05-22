package filter;

import java.util.Scanner;

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
			fw.write(filter(line) + "\n");
	}
	
	
	
	public static void main(String[] args) {

		Filter f = new EnglishWordsFilter();

		f.filterFile2("files/raw/Whole_Mahabharata.txt",
				"files/processed/Whole_Mahabharata.txt");

	}
	
	
    /**
     * Function meant to be called in main in order for this file to have 
     * the behaviour expected in the subject.
     */
    public static void exec() {
    	System.out.println("--- English words filter ---");
    	System.out.println("Please indicate where to save the processed file.\n-> ");
    	Scanner sc = new Scanner(System.in);
    	String adresse = sc.nextLine();
    	 new EnglishWordsFilter().filterFile2( null, adresse );
    }
	
}
