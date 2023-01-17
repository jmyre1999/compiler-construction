package parser;

import java.io.*;
import java.io.FileInputStream;
import syntax.*;
import java.util.HashMap;
import visitor.*;
import java.util.Iterator;

public class Scan {
	public static void main(String[] args) throws IOException {
		try {
			boolean verbose = true;
			String filename;
			if (args.length > 0) {
				filename = args[0];
			}
			else {
				System.out.println("No file name given.");
				return;
			}

			if (filename.equals("")) {
				System.out.println("No file name given.");
				return;
			}

			final MiniJavaParser parser;
			try {
				parser = new MiniJavaParser (filename, new FileInputStream (filename));
			}
			catch (FileNotFoundException error) {
				System.out.println("File at path '" + filename + "' could not be accessed.");
				return;
			}

			int total_errors = 0;

			int lex_errors = 0;
			int token_count = 0;
			boolean do_parse = true;
		    while(true){
		        final Token t = parser.getNextToken();
		        //System.out.println(t);
		        if (t.kind == parser.EOF) {
		        	if (token_count == 0) {
		        		do_parse = false;
		        	}
		        	break;
		        }
		        else if (t.kind == parser.ERROR) {
		            lex_errors += 1;
		            System.err.println(String.format("%s:%03d.%03d: ERROR -- illegal character %s", filename, t.beginLine, t.beginColumn, t.image));
		        }
		        token_count++;
		    }
		    total_errors += lex_errors;

			parser.ReInit(new FileInputStream (filename));

			if (verbose) {
		  		parser.enable_tracing();
		  	}

			if(do_parse) {
				try {
					Program start = parser.Start();
					total_errors += parser.num_errors;
					/* If no parse errors, do pretty print and syntax checks */
					if (parser.num_errors == 0) {

						//new PrettyPrint().visit(start);

						SymbolTableGen symbolTableGenerator = new SymbolTableGen(filename);
						symbolTableGenerator.visit(start);
						total_errors += symbolTableGenerator.num_errors;
						//symbolTableGenerator.printTable();
						HashMap<String, MiniJavaClass> symbolTable = symbolTableGenerator.symbolTable;

						TypeChecker typeChecker = new TypeChecker(filename, symbolTable);
						typeChecker.visit(start);
						total_errors += typeChecker.num_errors;

						if (total_errors == 0) {
							Translate translaterIR = new Translate(symbolTable);
							translaterIR.visit(start);

							Iterator<Fragment> fragments = translaterIR.fragments.iterator();
							while(fragments.hasNext()) {
								System.out.println(fragments.next().method_body.toString());
							}
						}
					}
				}
				catch (ParseException error) {
					int error_col = error.currentToken.beginColumn;
					int error_line = error.currentToken.beginLine;
					System.err.println(filename + ":" + error_line + "." + error_col + ": Syntax Error: beats me!'");
				}
			}
			System.out.println("filename=" + filename + ", errors=" + Integer.toString(total_errors));
		}
		catch (Exception error) {
			System.err.println(error);
		}
		finally {
			return;
		}
	}
}