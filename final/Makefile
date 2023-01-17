JAVAC := javac
JAVACC := javacc
SUPPORT := support/support.jar
GCC   := sparc-linux-gcc

default: compiler

compiler: compile.jar compile assemble runtime.o
	chmod u+x compile
	chmod u+x assemble

# compile.jar: parser
# 	jar cfe $@ parser.Scan */*.class

compile.jar: parsergen
	$(JAVAC) -Xlint:deprecation -classpath .:$(SUPPORT) */*.java
	jar cfm compile.jar manifest.txt */*.class

parsergen:
	$(JAVACC) -OUTPUT_DIRECTORY:parser parser/scanner.jj

runtime.o : runtime.c
		$(GCC) -Wall -c runtime.c -o runtime.o

submit:
	jar -cf phase12.jar Makefile manifest.txt parser/scanner.jj parser/Scan.java visitor/*.java sparc/*.java compile.sh assemble.sh runtime.c README.txt Test.java

clean:
	rm *~ */*~
	rm */*.class
