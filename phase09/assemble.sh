#!/bin/sh
dir=$(dirname $0)                   # root dir: location of script
file=$(dirname $1)/$(basename $1 .s) # remove ext of arg, keep dir
sparc-linux-as -g -Asparc "$file".s -o "$file".o
sparc-linux-ld -dynamic-linker /lib/ld-uClibc.so.0 -e start "$file".o "$dir"/runtime.o -lc -o "$file"