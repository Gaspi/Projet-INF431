package FileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Files {
	
	public static final Path shakespeare = Paths.get("files/processed/Shakespeare.txt");
	public static final Path numbers = Paths.get("files/processed/Numbers_1000000.txt");
	public static final Path uuids = Paths.get("files/processed/UUID_200000");
	public static final Path englishWords = Paths.get("files/processed/English_Words.txt");
	public static final Path bible = Paths.get("files/processed/Bible.txt");
	public static final Path hamlet = Paths.get("files/processed/Hamlet.txt");
	public static final Path[] paths = { shakespeare, englishWords, uuids, numbers, bible};
	
	public static final String[] descriptions = { "Shakespeare", "English words", "UUIDs",
			"Numbers from 1 to 1,000,000", "Old and new testaments", "Hamlet"};
	
}
