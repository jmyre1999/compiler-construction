import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.*;

class Main {
	public static String assignClassVarAsm(String name, String val) {
		int index = name.charAt(0)-'a';
		return ("set_class_var " + index + " " + val + "\n");
	}

	public static String printClassVarAsm(String name) {
		int index = name.charAt(0)-'a';
		return ("print_class_var " + index + "\n");
	}

	public static String assignArrayIndexAsm(String index, String val) {
		return ("set_array_val " + index + " " + val + "\n");
	}

	public static String printArrayIndexAsm(String index) {
		return ("print_array_val " + index + "\n");
	}

	public static void main(String args[]) throws IOException {
		System.out.print(".global start\n");
		System.out.print("start:\n");
		System.out.print("intialize_array\n");
		System.out.print("intialize_class\n");
		Scanner reader = new Scanner(System.in);
		while (reader.hasNextLine()) {
			/* Get next line */
			String line = reader.nextLine();
			/* Split into tokens */
			String[] tokens = line.split("\\s+");
			if (tokens.length > 0) {
				if (tokens[0].equals("a") || tokens[0].equals("b") || tokens[0].equals("c")) {
					if (tokens.length == 2) {
						System.out.print(assignClassVarAsm(tokens[0], tokens[1]));
					}
					else if (tokens.length == 1) {
						System.out.print(printClassVarAsm(tokens[0]));
					}
				}
				else if (Character.isDigit(tokens[0].charAt(0))) {
					if (tokens.length == 2) {
						System.out.print(assignArrayIndexAsm(tokens[0], tokens[1]));
					}
					else if (tokens.length == 1) {
						System.out.print(printArrayIndexAsm(tokens[0]));
					}
				}
			}
		}
		System.out.print("exit_program\n");
		reader.close();
		return;
	}
}