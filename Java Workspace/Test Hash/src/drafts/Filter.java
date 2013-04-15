package drafts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Filter{

	/**
	 * 
	 * @param fileForReading A String representing the path to the file we want to process.
	 * @param fileForWritting A String representing the path to the location where the processed 
	 * file should be written.
	 * @param f A linefilter instance to filter each line in the file
	 * 
	 * The function read the file one line at a time. Then filter the text with the Linefilter
	 * and write the processed line in the taget file.
	 */
	public static void filter(String fileForReading, String fileForWritting, LineFilter f){
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
					line = f.filter(line);
					line = line += "\n";
					writer.write(line);
				}
			} finally {
				writer.close();
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void main(String[] args){

		Filter.filter(
				"/home/jonathan/Documents/Projet-INF431/Ressources/Raw files/Shakespeare_complete.txt",
				"/home/jonathan/Documents/Projet-INF431/Ressources/Preprocessed files/Shakespeare_complete_processed.txt",
				new EnglishWordsFilter());
	}

}
