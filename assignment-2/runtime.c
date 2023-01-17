#include <stdlib.h>
#include <stdio.h>
/*
   stdio.h is required for:
      extern int printf (const char *format, ...);
   Now the C library required '-lc' also.

   Compile with 'gcc':
     sparc-linux-gcc -c print_int.c -o print_int.o
*/
void println (int n) {
   printf ("%d\n", n);
}

int* genIntArray(int size) {
	int* new_array = (int*)malloc(size * sizeof *new_array);
	int i;
	for(i=0;i<size;i++) {
		new_array[i] = 0;
	}
	return new_array;
}

void setArrayIndex(int* array, int index, int val) {
	if(index < 10 && index >= 0) {
		array[index] = val;
	}
}

int getArrayIndex(int* array, int index) {
	if(index < 10 && index >= 0) {
		return array[index];
	}
	return -1;
}

void setClassVar(int* array, int index, int val) {
	if(index < 3 && index >= 0) {
		array[index] = val;
	}
}

int getClassVar(int* array, int index) {
	if(index < 3 && index >= 0) {
		return array[index];
	}
	return -1;
}