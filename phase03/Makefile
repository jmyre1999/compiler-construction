JAVAC := javac
JAVACC := javacc

default: compiler

compiler: parse.jar  compile
	chmod u+x compile

parse.jar: parser
	jar cfe $@ parser.Scan parser/*.class

parser: parsergen
	$(JAVAC) parser/*.java

parsergen:
	$(JAVACC) -OUTPUT_DIRECTORY=parser parser/scanner.jj


clean:
	rm *~ */*~
	rm */*.class
