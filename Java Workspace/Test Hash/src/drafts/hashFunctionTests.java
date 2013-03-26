package drafts;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class hashFunctionTests {
	static final Path shakespeare = Paths.get("Shakespeare_complete_processed.txt");
	static final Path uuids = Paths.get("UUID_200000");

	public static void speedTest(hashFunction func){
		System.out.println("Speed test");
		Charset charset = Charset.forName("US-ASCII");
		
		//Shakespeare
		long start = System.nanoTime();
		try {
			BufferedReader reader = Files.newBufferedReader(shakespeare, charset);
			 
			String line = null;
			String[] strings = null;
	
			while ((line = reader.readLine()) != null ){
			    strings = line.split("[\\s]+");
			    for(String g: strings)
			    	func.hashString(g);    
			}		
		} catch (IOException e1) {e1.printStackTrace();}
		System.out.println("Shakespeare: " + ((float)(System.nanoTime() - start))/1000000000. + "s");
		
	}
	
	public static void collisionTest(hashFunction func){
		
	}
	
	public static void uniformDistribTest(hashFunction func){
		
	}

	public static void main(String[] args) {
		speedTest(new LookUp3());
	}

}
