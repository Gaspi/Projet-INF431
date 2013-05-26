package drafts;

import hash.HashFunction;

import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

public final class GetInput {
	
	private static final Scanner sc = new Scanner(System.in);
	
	public static String ask(String msg) {
		System.out.print(msg + " :\n-> ");
		return sc.nextLine();
	}
	
	public static String[] askSet(String msg) {
		LinkedList<String> res = new LinkedList<String>();
		boolean carryOn = true;
		System.out.println(msg + " (empty line to finish) :");
		while (carryOn) {
			System.out.print("\n-> ");
			String aux = sc.nextLine();
			if (aux.equals(""))
				carryOn = false;
			else
				res.add( aux );
		}
		
		String[] strs = new String[res.size()];
		for(int i=0; i<res.size(); i++)
			strs[i] = res.remove(0);
		
		for(int i=0; i<res.size(); i++)
			System.out.println(strs[i]);
		
		return strs;
	}
	
	
	public static void checkPath(String path) throws NoSuchFileException {
    	if( !Files.exists( Paths.get(path) )  )
        	throw new NoSuchFileException(path);
	}
	
	
	public static void checkHash(String hash) {
		if( ! HashFunction.isHashFunction(hash) )
			throw new IllegalArgumentException( "Not a valid hash function: " + hash );
	}
	
	
}
