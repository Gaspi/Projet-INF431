package FileManager;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import drafts.Draft;


/**
 * A class to read a file one time only.
 */
public abstract class FileReader implements Iterable<String>  {
	
	private static final Charset charset = Charset.forName("US-ASCII");
	
	
	protected BufferedReader reader = null;
	
	// Useless ??
	public FileReader() { 
		this("");  //To call a constructor from another constructor --> use the 'this' keyword
		// Use "" as argument because null is ambiguous (either Path or String)
	}

	public FileReader(Path path)   { 
		if (path == null)
			path = Paths.get( Draft.ask("the file's path") );
		openFile(path);
	}
	
	public FileReader(String path) {
		if (path == null || path.length() == 0)
			path = Draft.ask("the file's path");
		openFile( Paths.get(path) );
	}
	
	public void openFile(Path path) {
		try {
			reader = Files.newBufferedReader(path, charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public abstract Iterator<String> iterator();
	
	
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
	public void finalize() {  close();  }
	
}