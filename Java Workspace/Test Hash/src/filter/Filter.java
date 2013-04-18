package filter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import FileManager.FileReader;
import FileManager.FileWriter;
import FileManager.LineReader;

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
	
	
	public final void filterFile2(String fileForReading, String fileForWritting) {
		
		FileWriter fw = new FileWriter(fileForWritting);
		FileReader lr = new LineReader(fileForReading);
		
		for (String line : lr)
			fw.write( filter(line) + "\n" );
	}
	

	public static void main(String[] args) {

		Filter f = new EnglishWordsFilter();
		
		long start,end;
		
		// Le premier appel est toujours plus long...
		f.filterFile2(
				"Shakespeare_complete_processed.txt",
				"Shakespeare_complete_processed2.txt");
		
		start = System.nanoTime();
		f.filterFile(
				"Shakespeare_complete_processed.txt",
				"Shakespeare_complete_processed2.txt");
		end = System.nanoTime();
		System.out.println("filterFile -> " +   ((end - start) / 1000000000.) + " s");
		
		start = System.nanoTime();
		f.filterFile2(
				"Shakespeare_complete_processed.txt",
				"Shakespeare_complete_processed2.txt");
		end = System.nanoTime();
		System.out.println("filterFile2 -> " +   ((end - start) / 1000000000.) + " s");
		
	}

}
