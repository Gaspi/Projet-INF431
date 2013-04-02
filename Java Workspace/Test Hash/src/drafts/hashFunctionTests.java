package drafts;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;





public class hashFunctionTests {
	static final Path shakespeare = Paths.get("Shakespeare_complete_processed.txt");
	static final Path numbers = Paths.get("Numbers_1000000");
	static final Path uuids = Paths.get("UUID_200000");
	static final Path englishWords = Paths.get("English_words");
	
	private static String speedTestOnFile(Path path, hashFunction func){
		Charset charset = Charset.forName("US-ASCII");
		long start = System.nanoTime();
		try {
			BufferedReader reader = Files.newBufferedReader(path, charset);
			 
			String line = null;
			String[] strings = null;
	
			while ((line = reader.readLine()) != null ){
			    strings = line.split("[\\s]+");
			    for(String g: strings)
			    	func.hashString(g);    
			}		
		} catch (IOException e1) {e1.printStackTrace();}
		
		return ((float)(System.nanoTime() - start))/1000000000. + " s";
	}
	
	//Unfinished
	
	/*private static String collisionTestOnFile(Path path, hashFunction func, int number){
		int[] array = new int[1000000];
		Charset charset = Charset.forName("US-ASCII");
		long start = System.nanoTime();
		try {
			BufferedReader reader = Files.newBufferedReader(path, charset);
			 
			String line = null;
			String[] strings = null;
	
			while ((line = reader.readLine()) != null ){
			    strings = line.split("[\\s]+");
			    for(String g: strings)
			    	func.hashString(g);    
			}		
		} catch (IOException e1) {e1.printStackTrace();}
		
		return ((float)(System.nanoTime() - start))/1000000000. + " s";

	}*/
	
	public static void speedTests(hashFunction func){
		System.out.println("---Speed test---");
		System.out.println("Using " + func.getClass().getSimpleName() + " hash function");
		
		//Shakespeare
		System.out.println("Shakespeare: " + System.lineSeparator() + speedTestOnFile(shakespeare, func));
		//Numbers
		System.out.println("Numbers from 0 to 1,000,000: " + System.lineSeparator()  + speedTestOnFile(numbers, func));
		//UUIDs
		System.out.println("200,000 random UUIDS: " + System.lineSeparator()  + speedTestOnFile(uuids, func));
	}
	
	public static void collisionTest(hashFunction func){
		
	}
	
	public static void uniformDistribTest(hashFunction func){
		
	}

	public static void main(String[] args) {
		speedTests(new DJB2());
	}

}
