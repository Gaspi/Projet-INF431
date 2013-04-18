package FileManager;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.StringTokenizer;


/**
 * A class to read a file one-time only.
 * It is made to be read word by word using the Iterator.
 * Nevertheless, it's still possible to read line by line.
 */
public class FileReader implements Iterable<String>  {
	
	public static final Charset charset = Charset.forName("US-ASCII");
	private BufferedReader reader = null;
	
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
	
	public String readLine() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Function called before the destruction of the object (to make sure the file is closed in the end).
	 */
	@Override
	public void finalize() {
		close();
	}
	
	
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

//
//Autres idées en vrac :
//	Faire une classe Line qui correspond en gros à une ligne (string)
//		mais qui implémente Iterable<String> : le parcours des mots de la ligne.
//	Du coup File reader implemente à la fois Iterable<Line> pour le parcours des lignes et Iterable<String>
//		pour le parcours des mots...
//A réfléchir...