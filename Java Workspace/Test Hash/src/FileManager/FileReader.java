package FileManager;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.StringTokenizer;



public class FileReader implements Iterable<String>  {
	
	public final Charset charset = Charset.forName("US-ASCII");
	public Path path;
	
	public FileReader(Path path) { this.path = path; }
	
	@Override
	public Iterator<String> iterator() {
		return new MyIter();
	}
	
	
	
	private class MyIter implements Iterator<String> {
		
		private BufferedReader reader = null;
		private String line = "";
		private StringTokenizer st = new StringTokenizer(line);
		
		public MyIter() {
			try {
				reader = Files.newBufferedReader(path, charset);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			update();
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
		
		/**
		 * Make sure the iterator is closed as soon as there is no more word to read ( st.hasMoreTokens() is false ).
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
		public boolean hasNext() {
			return reader != null;
		}
	
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


