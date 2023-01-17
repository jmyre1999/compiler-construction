JAVAC := javac
JAVACC := javacc
SUPPORT := support/support.jar

default: compiler

compiler: check.jar

# check.jar: parser
# 	jar cfe $@ parser.Scan */*.class

check.jar: parsergen
	$(JAVAC) -Xlint:deprecation -classpath .:$(SUPPORT) */*.java
	jar cfm check.jar manifest.txt */*.class

parsergen:
	$(JAVACC) -OUTPUT_DIRECTORY:parser parser/scanner.jj

clean:
	rm *~ */*~
	rm */*.class
