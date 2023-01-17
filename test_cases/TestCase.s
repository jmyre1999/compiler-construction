        .global start
start:
TestCase$main:
TestCase$main$preludeEnd:
        SET 3,%l0               ! munchExp(CONST(i))
        MOV %l0,%o0
        call malloc_object
        nop
        MOV %o0, %l1
        MOV %l1,%l2
        MOV %l2,%o0
        call B$Test
        nop
        MOV %o0, %l3
        MOV %l3,%l4
        MOV %l4,%o0
        call print_int
        nop
        MOV %o0, %l5
        BA TestCase$main$epilogBegin
        nop
TestCase$main$epilogBegin:
        clr %o0
        call exit
        nop
A$CheckInherit:
        .set LOCLS,0
        .set TEMPS,2
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
A$CheckInherit$preludeEnd:
        SMUL %i3,%i2,%l0
        ADD %i1,%l0,%l1
        MOV %l1,%i0
        BA A$CheckInherit$epilogBegin
        nop
A$CheckInherit$epilogBegin:
        ret
        restore
B$Test:
        .set LOCLS,1
        .set TEMPS,6
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
B$Test$preludeEnd:
        SET 3,%l0               ! munchExp(CONST(i))
        ST %l0, [%i0+0]
        SET 2,%l1               ! munchExp(CONST(i))
        ST %l1, [%i0+4]
        LD [%i0+0],%l2
        LD [%i0+4],%l3
        ADD %l2,%l3,%l4
        ST %l4, [%i0+8]
        LD [%i0+8],%l5
        MOV %l5,%i0
        BA B$Test$epilogBegin
        nop
B$Test$epilogBegin:
        ret
        restore
