package table.generator;

import java.util.ArrayList;
import java.util.List;

public class Login {
	
	public static void main(String args[]) {
		String[] firstNames = new String[] {"Adam", "Ben", "Christine", "David", "Ellen",
				"Frank", "Gina", "Hannah", "Ian", "John"};
		
		String[] lastNames = new String[] {"Adamson", "Benson", "Christinson", "Davidson", "Ellenson",
				"Frankson", "Ginason", "Hannahson", "Ianson", "Johnson"};
		
		String[] fullNames = new String[100];
		int count = 0;
		
		System.out.println("INSERT INTO login (username, pass, id) VALUES (");
	
		for (String first: firstNames) {
			for (String last: lastNames) {
				//System.out.println
			}
		}
		
		System.out.println(");");
		
	}

}
