package drafts;

import java.util.LinkedList;
import java.util.Scanner;

public final class GetInput {
	
	private static final Scanner sc = new Scanner(System.in);
	
	public static String ask(String msg) {
		System.out.print(msg + " :\n-> ");
		String res = sc.nextLine();
		System.out.println();
		return res;
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
		System.out.println();
		
		String[] strs = new String[res.size()];
		for(int i=0; i<res.size(); i++)
			strs[i] = res.remove(0);
		
		return strs;
	}
	
	
	public static String askPath(String msg) {
		String res = null;
		while (res == null || !Draft.isPath(res) ) {
			res = ask(msg);
			if ( !Draft.isPath(res) )
				System.out.println("\n!!! No such file !!!");
		}
		return res;
	}
	
	public static String askHash(String msg) {
		String res = null;
		while (res == null || !Draft.isHash(res) ) {
			res = ask(msg);
			if ( !Draft.isHash(res) )
				System.out.println("\n!!! Not an implemented hash function !!!");
		}
		return "hash." + res;
	}
	
	public static int askParameterInRange(String msg, int min ,int max) {
		String res = null;
		while (res == null || !Draft.isInRange(res, min, max) ) {
			res = ask(msg);
			if ( !Draft.isInRange(res, min, max) )
				System.out.println("\n!!! Not in range " + min + " to " + max + " !!!");
		}
		return Integer.parseInt(res);
	}
	
	
	
}
