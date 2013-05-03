package filter;

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

		f.filterFile2("/home/jonathan/Documents/Projet-INF431/Ressources/Raw files/Bible_english.txt",
				"/home/jonathan/Documents/Projet-INF431/Ressources/Preprocessed files/Bible_english_processed.txt");

	}

}
