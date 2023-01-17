JAVAC := javac
JAVACC := javacc
SUPPORT := support/support.jar

default: compiler

compiler: check.jar compile
	chmod u+x compile

# check.jar: parser
# 	jar cfe $@ parser.Scan */*.class

check.jar: parsergen
	$(JAVAC) -Xlint:deprecation -classpath .:$(SUPPORT) */*.java
	jar cfm check.jar manifest.txt */*.class

parsergen:
	$(JAVACC) -OUTPUT_DIRECTORY:parser parser/scanner.jj

submit:
	jar -cf phase05.jar Makefile manifest.txt parser/scanner.jj parser/Scan.java visitor/*.java compile.sh

clean:
	rm *~ */*~
	rm */*.class
