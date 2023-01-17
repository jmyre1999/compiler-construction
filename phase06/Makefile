JAVAC := javac
JAVACC := javacc
SUPPORT := support/support.jar

default: compiler

compiler: translate.jar compile
	chmod u+x compile

# translate.jar: parser
# 	jar cfe $@ parser.Scan */*.class

translate.jar: parsergen
	$(JAVAC) -Xlint:deprecation -classpath .:$(SUPPORT) */*.java
	jar cfm translate.jar manifest.txt */*.class

parsergen:
	$(JAVACC) -OUTPUT_DIRECTORY:parser parser/scanner.jj

submit:
	jar -cf phase07.jar Makefile manifest.txt parser/scanner.jj parser/Scan.java visitor/*.java sparc/*.java compile.sh

clean:
	rm *~ */*~
	rm */*.class
