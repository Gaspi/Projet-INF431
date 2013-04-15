package drafts;

public class EnglishWordsFilter implements LineFilter{

	public String filter(String line){
		line = line.toLowerCase();
		line = line.replaceAll("'st|'[sd]|[^a-z -]|(?<=^)[ ]+", "");
		line = line.replaceAll("[-]{2}", " ");
		return line;
	}

}
