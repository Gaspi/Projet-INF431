package FileManager;

import hash.hashFunction;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringTokenizer;



public class HashTester {
	
	private final Charset charset = Charset.forName("US-ASCII");
	
	private BufferedReader reader = null;
	private String line = null;
	private StringTokenizer st = null;
	
	private hashFunction func;
	
	
	public HashTester(hashFunction func) {
		this.func = func;
	}
	
	public HashTester(hashFunction func, Path path) {
		this.func = func;
		open(path);
	}
	
	public void open(Path path) {
		try {
			reader = Files.newBufferedReader(path, charset);
			line = reader.readLine();
			st = new StringTokenizer(line);
			update();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Make sure the object is closed as soon as there is no more word to read ( st.hasMoreTokens() is false ).
	 */
	private void update() {
		try {
			while ( !st.hasMoreTokens() && line != null ) {
				line = reader.readLine();
				st = new StringTokenizer(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (line == null)
			close();
	}
	
	public String nextWord() {
		if (!isReading())
			throw new RuntimeException("Nothing else to read !");
		String result = st.nextToken();
		update();
		return result;
	}
	
	public int nextHash() {
		return func.hashString(nextWord());
	}
	
	public boolean isReading() { return reader != null; }
	
	public void close() {
		try {
			reader.close();
			reader = null;
			line = null;
			st = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Function called before the destruction of the object (to make sure the file is closed in the end).
	 */
	public void finalize() {
		if (isReading())
			close();
	}
	
	
}
