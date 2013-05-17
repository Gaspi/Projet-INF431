package FileManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;

/**
 * Iterable<String> to read a file line by line.
 */
public class LineReader extends FileReader {
	
	public LineReader() { super(); }
	public LineReader(Path path) { super(path); }
	public LineReader(String path) { super(path); }
	
	@Override
	public Iterator<String> iterator() { return new MyIter(); }
	
	private class MyIter implements Iterator<String> {
		
		private String nextLine;
		
		public MyIter() { 
			try {
				nextLine = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			};
		}
		
		
		@Override
		public String next() {
			String result = nextLine;
			try {
				nextLine = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result;
		}
		
		@Override public boolean hasNext() { return nextLine != null; }
		@Override public void remove() { }
		@Override public void finalize() { close(); }
		
	}
	
	
}
