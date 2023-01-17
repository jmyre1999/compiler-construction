package parser;

import java.io.*;
import java.io.FileInputStream;

public class Scan {
	public static void main(String args[]) throws IOException {
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

		if (verbose) {
	  		parser.enable_tracing();
	  	}
		else {
			parser.disable_tracing(); // for parsing only
		}

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
	            System.err.println(String.format("%s:%03d.%03d: ERROR -- illegal character %s",
	                filename, t.beginLine, t.beginColumn, t.image));
	        }
	        token_count++;
	    }

		parser.ReInit(new FileInputStream (filename));

		if(do_parse) {
			try {
				parser.Start();
				System.out.println("filename=" + filename + ", errors=" + Integer.toString(lex_errors + parser.num_errors));
			}
			catch (ParseException error) {
				int error_col = error.currentToken.beginColumn;
				int error_line = error.currentToken.beginLine;
				System.err.println(filename + ":" + error_line + "." + error_col + ": Syntax Error: beats me!'");
			}
		}
	}
}