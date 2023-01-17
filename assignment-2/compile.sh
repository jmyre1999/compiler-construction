#!/bin/csh
set dir=`dirname $1`
set base=`basename $1 .java`
set file=$dir/$base
set run=.
# Assumes files $1, $run/macros.s, and $run/runtime.o exist
# Creates file  $file
/bin/rm -f $file.s
java Main < $file.java > $file.s
sparc-linux-as -g -Asparc $run/macros.s $file.s -o $file.o
# do not delete $file.s; leave for debugging and evaluation
sparc-linux-ld -dynamic-linker /lib/ld-uClibc.so.0 -e start $file.o $run/runtime.o -lc -o $file
/bin/rm -f $file.o  # delete temporary files