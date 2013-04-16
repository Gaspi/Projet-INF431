package filter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
	public void filterFile(String fileForReading, String fileForWritting) {
		Path readFile = Paths.get(fileForReading);
		Path writeFile = Paths.get(fileForWritting);

		if (!Files.exists(writeFile))
			try {
				writeFile = Files.createFile(writeFile);
			} catch (IOException e) {
				e.printStackTrace();
			}

		Charset charset = Charset.forName("US-ASCII");
		try {
			BufferedReader reader = Files.newBufferedReader(readFile, charset);
			BufferedWriter writer = Files.newBufferedWriter(writeFile, charset);
			String line = null;
			try {
				while ((line = reader.readLine()) != null) {
					line = filter(line);
					line += "\n";
					writer.write(line);
				}
			} finally {
				writer.close();
				reader.close();
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Filter f = new EnglishWordsFilter();
		f.filterFile(
				"/home/jonathan/Documents/Projet-INF431/Ressources/Preprocessed files/Shakespeare_complete_processed.txt",
				"/home/jonathan/Documents/Projet-INF431/Ressources/Preprocessed files/Shakespeare_complete_processed2.txt");
	}

}
