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
		System.out.println(msg + " (END to finish) :");
		while (carryOn) {
			System.out.print("\n-> ");
			String aux = sc.nextLine();
			if (aux.startsWith("END"))
				carryOn = false;
			else
				res.add( aux );
		}
		return (String[]) res.toArray();
	}
	
	
	public void finalize(){
		sc.close();
	}
}
