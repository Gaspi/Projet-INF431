package FileManager;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Scanner;


/**
 * A class to read a file one-time only.
 */
public abstract class FileReader implements Iterable<String>  {
	
	private static final Charset charset = Charset.forName("US-ASCII");
	
	
	protected BufferedReader reader = null;
	
	public FileReader() { 
		this("");  //To call a constructor from another constructor --> use this
		// Pass "" cause null is ambiguous
	}

	public FileReader(Path path)   { 
		if (path == null){
	    	Scanner sc = new Scanner(System.in);
	    	System.out.println("Please enter the file's path : ");
	    	path = Paths.get(sc.nextLine());
	    	sc.close();
		}
		
		openFile(path);
	}
	
	public FileReader(String path) {
		if (path == null || path.length() == 0){
	    	Scanner sc = new Scanner(System.in);
	    	System.out.println("Please enter the file's path : ");
	    	path = sc.nextLine();
	    	sc.close();
		}
		
		openFile( Paths.get(path) );
	}
	

	
	
	public void openFile(Path path) {
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