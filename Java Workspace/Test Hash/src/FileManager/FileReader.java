package FileManager;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;


/**
 * A class to read a file one-time only.
 */
public abstract class FileReader implements Iterable<String>  {
	
	public static final Charset charset = Charset.forName("US-ASCII");
	
	protected BufferedReader reader = null;
	
	public FileReader(Path path)   {  open(path);  }
	public FileReader(String path) {  open( Paths.get(path) ); }
	
	public void open(Path path) {
		try {
			reader = Files.newBufferedReader(path, charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if (reader != null)
				reader.close();
			reader = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// This is to make sure the close() function is called. (useless ?)
	@Override
	public void finalize() { close(); }
	
	
	// To be implemented in the daughter classes
	@Override
	public Iterator<String> iterator() { return null; }
	
}