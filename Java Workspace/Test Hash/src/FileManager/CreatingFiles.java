package FileManager;

import java.util.UUID;

public class CreatingFiles {

	/**
	 * Generate a file with "number" random UUID generated by java in the given directory.
	 * @param directory
	 * @param number
	 */
	public static void writeUUIDs(String directory, int number) {
		FileWriter fw = new FileWriter(directory);
		for (int i = 0; i <= number; i++)
			fw.write(UUID.randomUUID() + "\n");
	}
	
	
	/**
	 * Generate a file with all numbers from 0 to number in the given directory.
	 * @param directory
	 * @param max
	 */
	public static void writeNumbers(String directory, int max) {		
		FileWriter fw= new FileWriter( directory + "/Numbers_" + max + ".txt" );
		for (int i = 0; i <= max; i++)
			fw.write( ""+i );
	}
	
	
	public static void main(String[] args) {
		writeUUIDs("files/processed", 1000000);
		writeNumbers("files/processed", 1000000);
	}

}
