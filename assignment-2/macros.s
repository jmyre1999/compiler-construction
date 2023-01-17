/* define SYS_exit = 1 (from sys/syscalls.h)  */
.set   SYS_exit, 1

/* define SP_TRAP_LINUX -- Linux System Call (from asm/traps.h) */
.set   SP_TRAP_LINUX, 0x90

/* exit -- trap to operating system */
.macro exit_program
clr     %o0             ! %o0 := 0;  program status=0=success
mov     SYS_exit, %g1   ! %g1 := SYS_exit; determine system call
ta      SP_TRAP_LINUX
.endm

/* Will put pointer value to y array in %g0 */
.macro intialize_array
mov 10, %o0
call genIntArray
nop
mov %o0, %l0 
.endm

/* Will put pointer value to r array in %g1 */
.macro intialize_class
mov 3, %o0
call genIntArray
nop
mov %o0, %l1
.endm

.macro set_array_val index val
mov %l0, %o0
mov \index, %o1
mov \val, %o2
call setArrayIndex
nop
.endm

.macro print_array_val index
mov %l0, %o0
mov \index, %o1
call getArrayIndex
nop
call println
nop
.endm

.macro set_class_var index val
mov %l1, %o0
mov \index, %o1
mov \val, %o2
call setClassVar
nop
.endm

.macro print_class_var index
mov %l1, %o0
mov \index, %o1
call getClassVar
nop
call println
nop
.endm
