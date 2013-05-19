package FileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Files {
	
	public static final Path shakespeare = Paths.get("Shakespeare_complete_processed.txt");
	public static final Path numbers = Paths.get("Numbers_1000000.txt");
	public static final Path uuids = Paths.get("UUID_200000");
	public static final Path englishWords = Paths.get("English_words.txt");
	public static final Path bible = Paths.get("Bible_english_processed.txt");
	public static final Path hamlet = Paths.get("Hamlet.txt");
	public static final Path[] paths = { shakespeare, englishWords, uuids, numbers, bible};
	
	public static final String[] descriptions = { "Shakespeare", "English words", "UUIDs",
			"Numbers from 1 to 1,000,000", "Old and new testaments", "Hamlet"};
}
