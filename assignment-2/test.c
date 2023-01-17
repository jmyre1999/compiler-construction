int main(void) {
	int x = 1;
	int y = 3;
	int z = x + y;
	return 0;
}

qemu-sparc-wrapper -g 1234 executable &
sparc-linux-gdb -ex 'target remote :1234' executable