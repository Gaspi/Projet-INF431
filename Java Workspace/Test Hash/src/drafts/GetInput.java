package drafts;

import java.util.LinkedList;
import java.util.Scanner;

public final class GetInput {
	
	private static final Scanner sc = new Scanner(System.in);
	
	public static String ask() {
		System.out.print("-> ");
		String res = sc.nextLine();
		System.out.println();
		return res;
	}
	
	public static String ask(String msg) {
		System.out.println(msg);
		return ask();
	}
	
	public static String[] askPathSet(String msg) {
		LinkedList<String> res = new LinkedList<String>();
		boolean carryOn = true;
		System.out.println(msg + " (empty line to finish) :");
		while (carryOn) {
			String aux = ask();
			if (aux.equals(""))
				carryOn = false;
			else if ( Draft.isPath(aux) )
				res.add( aux );
			else
				System.out.println("!!! No such file !!!");
		}
		
		String[] strs = new String[res.size()];
		int i = 0;
		for (String s : res) {
			strs[i] = s;
			i++;
		}
		
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
		return res;
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
	
	public static double askParameterInRange(String msg, double min, double max) {
		String res = null;
		while (res == null || !Draft.isInRange(res, min, max) ) {
			res = ask(msg);
			if ( !Draft.isInRange(res, min, max) )
				System.out.println("\n!!! Not in range " + min + " to " + max + " !!!");
		}
		return Double.parseDouble(res);
	}
	
	
}
