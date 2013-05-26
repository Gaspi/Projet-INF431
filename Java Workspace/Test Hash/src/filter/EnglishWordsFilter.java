package filter;

// Filter used to process English written texts suppressing any pathological case.
public class EnglishWordsFilter extends Filter {
	
	public String filter(String line) {
		line = line.toLowerCase();
		line = line.replaceAll("'st|'s|[^a-z -]|(?<=^)[ ]+", "");
		line = line.replaceAll("--", " ");
		return line;
	}
	
}
