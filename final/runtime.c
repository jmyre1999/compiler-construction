#include <stdlib.h>
#include <stdio.h>
/*
   stdio.h is required for:
      extern int printf (const char *format, ...);
   Now the C library required '-lc' also.

   Compile with 'gcc':
     sparc-linux-gcc -c print_int.c -o print_int.o
*/
void print_int (int n) {
   printf ("%d\n", n);
}

int* malloc_object(int size) {
	int* new_array;
	if (size > 0) {
		new_array = (int*)calloc(size, sizeof *new_array);
	}
	else {
		new_array = (int*)calloc(1, sizeof *new_array);
	}
	return new_array;
}

int* malloc_array(int size) {
	int* new_array;
	new_array = (int*)calloc(size+1, sizeof *new_array);
	new_array[0] = size;
	return new_array;
}