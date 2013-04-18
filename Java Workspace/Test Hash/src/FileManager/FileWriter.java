package FileManager;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * A class to write in a file line by line.
 */
public class FileWriter {
	
	public static final Charset charset = Charset.forName("US-ASCII");
	private BufferedWriter writer = null;
	
	public FileWriter(Path path) {  open(path);  }
	
	public FileWriter(String path) {  open( Paths.get(path) );  }
	
	public void open(Path path) {
		try {
			if (!Files.exists(path))
				path = Files.createFile(path);
			writer = Files.newBufferedWriter(path, charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if (writer != null)
				writer.close();
			writer = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void write(String line) {
		try {
			writer.write(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Function called before the destruction of the object (to make sure the file is closed in the end).
	 */
	@Override
	public void finalize() {
		close();
	}
}