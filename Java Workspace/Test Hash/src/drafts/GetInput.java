package drafts;

import java.util.LinkedList;
import java.util.Scanner;

public final class GetInput {
	private final Scanner sc = new Scanner(System.in);
	
	public String ask(String msg){
		System.out.print(msg + " :\n-> ");
		return sc.nextLine();
	}
	
	public String[] askSet(String msg) {
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
	
	
	public void finalize(){
		sc.close();
	}
}
