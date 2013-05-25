package drafts;

import java.util.Scanner;

public final class GetInput {
	private final Scanner sc = new Scanner(System.in);
	
	public String ask(String msg){
		System.out.print(msg + " :\n-> ");
		return sc.nextLine();
	}

	public void finalize(){
		sc.close();
	}
}
