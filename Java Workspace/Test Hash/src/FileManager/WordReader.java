package FileManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.StringTokenizer;


public class WordReader extends FileReader {
	
	public WordReader(Path path) { super(path); }
	public WordReader(String path) { super(path); }
	
	/**
	 * Return a Iterator<String> starting at the next line of the file.
	 * The first if the file was just created.
	 */
	@Override
	public Iterator<String> iterator() {
		return new MyIter();
	}
	
	private class MyIter implements Iterator<String> {
		
		private String line = "";
		private StringTokenizer st = new StringTokenizer(line);
		
		public MyIter() {
			update();
		}
		
		
		/**
		 * Make sure the FileReader is closed as soon as there is no more word to read ( st.hasMoreTokens() is false ).
		 */
		private void update() {
			try {
				while ( !st.hasMoreTokens() && line != null ) {
					
					line = reader.readLine();
					if (line == null) {
						close();
						return;
					} else
						st = new StringTokenizer(line);
				}
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
		
		
		@Override
		public boolean hasNext() { return reader != null; }
	
		@Override
		public String next() {
			String result = st.nextToken();
			update();
			return result;
		}
	
		@Override
		public void remove() { }
		
	}
	
	
}
