package FileManager;

import java.util.Iterator;
import java.util.StringTokenizer;

public class Line implements Iterable<String> {
	
	public String content;
	public Line(String line) { this.content = line; }
	@Override public Iterator<String> iterator() { return new MyIter(); }
	
	private class MyIter implements Iterator<String> {
		private StringTokenizer st;
		public MyIter() { st = new StringTokenizer(content); }
		@Override public boolean hasNext() { return st.hasMoreTokens(); }
		@Override public String next() { return st.nextToken(); }
		@Override public void remove() { }
	}
	
}
