package filter;


public class EnglishWordsFilter extends Filter{

	public String filter(String line) {
		line = line.toLowerCase();
		line = line.replaceAll("'st|'[sd]|[^a-z -]|(?<=^)[ ]+", "");
		line = line.replaceAll("--", " ");
		return line;
	}

}
