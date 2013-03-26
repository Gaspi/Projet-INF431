package drafts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class PreprocessingFiles {

	public static void prepare(String fileForReading, String fileForWritting){
		Path readFile = Paths.get(fileForReading);
		Path writeFile = Paths.get(fileForWritting);
		
		if(!Files.exists(writeFile))
			try {
				writeFile = Files.createFile(writeFile);
			} catch (IOException e) {e.printStackTrace();}
		
		
		Charset charset = Charset.forName("US-ASCII");	
		try {
		    BufferedReader reader = Files.newBufferedReader(readFile, charset);
		    BufferedWriter writer = Files.newBufferedWriter(writeFile, charset);
		    String line = null;

		    try{
			    while ((line = reader.readLine()) != null ) {
			        line = line.toLowerCase();
			        line = line.replaceAll("[^a-z ]|(?<=^)[ ]+", "");
			        line = line += "\n";
			        writer.write(line);		        
			    }
		    }
		    finally{writer.close();}
		    
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
	}
	
	public static void writeUUIDs(String directory, int number){
		String s = String.valueOf(number);
		Path writeFile = Paths.get(directory + "/UUID_"+s);
		
		Charset charset = Charset.forName("US-ASCII");	
		try {
			writeFile = Files.createFile(writeFile);
			BufferedWriter writer = Files.newBufferedWriter(writeFile, charset);
			
			try{
			    for(int i = 0 ; i<= number ; i++)
			    	writer.write(UUID.randomUUID().toString()); 
			} 
			finally{writer.close();}    
		} catch (IOException e1) {e1.printStackTrace();}
	}
	
	
	public static void main(String[] args) {	
	}

}
