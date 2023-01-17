        .global start
start:
Test$main:
Test$main$preludeEnd:
        SET 2,%l0
        MOV %l0,%o0
        call malloc_object
        nop
        MOV %o0, %l1
        MOV %l1,%l2
        MOV %l2,%o0
        call C$Demo
        nop
        MOV %o0, %l3
        MOV %l3,%l4
        MOV %l4,%o0
        call print_int
        nop
        MOV %o0, %l5
        BA Test$main$epilogBegin
        nop
Test$main$epilogBegin:
        clr %o0
        call exit
        nop
A$CallFib:
        .set LOCLS,0
        .set TEMPS,3
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
A$CallFib$preludeEnd:
        MOV %i1,%o0
        SET 0,%l0
        MOV %l0,%o1
        SET 1,%l1
        MOV %l1,%o2
        call Fib$CalculateFib
        nop
        MOV %o0, %l2
        MOV %l2,%i0
        BA A$CallFib$epilogBegin
        nop
A$CallFib$epilogBegin:
        ret
        restore
B$setX:
        .set LOCLS,0
        .set TEMPS,1
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
B$setX$preludeEnd:
        ST %i1, [%i0+4]
        SET 0,%l0
        MOV %l0,%i0
        BA B$setX$epilogBegin
        nop
B$setX$epilogBegin:
        ret
        restore
B$getX:
        .set LOCLS,0
        .set TEMPS,1
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
B$getX$preludeEnd:
        LD [%i0+4],%l0
        MOV %l0,%i0
        BA B$getX$epilogBegin
        nop
B$getX$epilogBegin:
        ret
        restore
B$createArray:
        .set LOCLS,0
        .set TEMPS,1
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
B$createArray$preludeEnd:
        MOV %i1,%o0
        call malloc_array
        nop
        MOV %o0, %l0
        MOV %l0,%i0
        BA B$createArray$epilogBegin
        nop
B$createArray$epilogBegin:
        ret
        restore
B$setArray:
        .set LOCLS,0
        .set TEMPS,5
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
B$setArray$preludeEnd:
        MOV %i1,%o0
        call print_int
        nop
        MOV %o0, %l0
        ADD %i3,1,%l1
        SMUL %l1,4,%l2
        ADD %i2,%l2,%l3
        ST %i1, [%l3]
        SET 0,%l4
        MOV %l4,%i0
        BA B$setArray$epilogBegin
        nop
B$setArray$epilogBegin:
        ret
        restore
B$getArray:
        .set LOCLS,0
        .set TEMPS,6
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
B$getArray$preludeEnd:
        SET 7,%l0
        MOV %l0,%o0
        call print_int
        nop
        MOV %o0, %l1
        ADD %i2,1,%l2
        SMUL %l2,4,%l3
        ADD %i1,%l3,%l4
        LD [%l4],%l5
        MOV %l5,%i0
        BA B$getArray$epilogBegin
        nop
B$getArray$epilogBegin:
        ret
        restore
C$Demo:
        .set LOCLS,1
        .set TEMPS,17
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
C$Demo$preludeEnd:
        SET 1,%l0
        MOV %l0,%o0
        call print_int
        nop
        MOV %o0, %l1
        SUB %fp,4,%l2
        MOV %l2,%l3
        MOV %i0,%o0
        call C$FibTest
        nop
        MOV %o0, %l4
        MOV %l4,%l5
        ST %l5, [%l3]
        SUB %fp,4,%l6
        MOV %l6,%l7
        MOV %i0,%o0
        SET 6,%i1
        MOV %i1,%o1
        call C$xTest
        nop
        MOV %o0, %i2
        MOV %i2,%i3
        ST %i3, [%l7]
        SUB %fp,4,%i4
        MOV %i4,%i5
        MOV %i0,%o0
        LD [%fp-4],%g1
        MOV %g1,%o1
        call C$arrayTest
        nop
        MOV %o0, %g2
        MOV %g2,%g3
        ST %g3, [%i5]
        SET 0,%g4
        MOV %g4,%i0
        BA C$Demo$epilogBegin
        nop
C$Demo$epilogBegin:
        ret
        restore
C$FibTest:
        .set LOCLS,1
        .set TEMPS,9
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
C$FibTest$preludeEnd:
        SET 2,%l0
        MOV %l0,%o0
        call print_int
        nop
        MOV %o0, %l1
        SUB %fp,4,%l2
        MOV %l2,%l3
        SET 0,%l4
        MOV %l4,%o0
        call malloc_object
        nop
        MOV %o0, %l5
        MOV %l5,%l6
        ST %l6, [%l3]
        MOV %i0,%o0
        LD [%fp-4],%l7
        MOV %l7,%o1
        call A$CallFib
        nop
        MOV %o0, %i1
        MOV %i1,%i0
        BA C$FibTest$epilogBegin
        nop
C$FibTest$epilogBegin:
        ret
        restore
C$xTest:
        .set LOCLS,0
        .set TEMPS,4
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
C$xTest$preludeEnd:
        SET 3,%l0
        MOV %l0,%o0
        call print_int
        nop
        MOV %o0, %l1
        MOV %i0,%o0
        MOV %i1,%o1
        call B$setX
        nop
        MOV %o0, %l2
        MOV %l2,%i1
        MOV %i0,%o0
        call B$getX
        nop
        MOV %o0, %l3
        MOV %l3,%i0
        BA C$xTest$epilogBegin
        nop
C$xTest$epilogBegin:
        ret
        restore
C$arrayTest:
        .set LOCLS,1
        .set TEMPS,16
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
C$arrayTest$preludeEnd:
        SET 4,%l0
        MOV %l0,%o0
        call print_int
        nop
        MOV %o0, %l1
        ADD %i0,0,%l2
        MOV %l2,%l3
        MOV %i0,%o0
        MOV %i1,%o1
        call B$createArray
        nop
        MOV %o0, %l4
        MOV %l4,%l5
        ST %l5, [%l3]
        SUB %fp,4,%l6
        MOV %l6,%l7
        MOV %i0,%o0
        LD [%i0+0],%i2
        MOV %i2,%o1
        SET 4,%i3
        MOV %i3,%o2
        SET 11,%i4
        MOV %i4,%o3
        call B$setArray
        nop
        MOV %o0, %i5
        MOV %i5,%g1
        ST %g1, [%l7]
        MOV %i0,%o0
        LD [%i0+0],%g2
        MOV %g2,%o1
        SET 4,%g3
        MOV %g3,%o2
        call B$getArray
        nop
        MOV %o0, %g4
        MOV %g4,%i0
        BA C$arrayTest$epilogBegin
        nop
C$arrayTest$epilogBegin:
        ret
        restore
Fib$CalculateFib:
        .set LOCLS,1
        .set TEMPS,8
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Fib$CalculateFib$preludeEnd:
        MOV %i1,%o0
        call print_int
        nop
        MOV %o0, %l0
        SET 1000,%l1
        CMP %l1,%i2
        BL if$then001
        nop
        BA if$else002
        nop
if$else002:
        SUB %fp,4,%l2
        MOV %l2,%l3
        MOV %i0,%o0
        MOV %i2,%o1
        ADD %i1,%i2,%l4
        MOV %l4,%o2
        call Fib$CalculateFib
        nop
        MOV %o0, %l5
        MOV %l5,%l6
        ST %l6, [%l3]
        BA if$end003
        nop
if$end003:
        SET 0,%l7
        MOV %l7,%i0
        BA Fib$CalculateFib$epilogBegin
        nop
if$then001:
        ST %i2, [%fp-4]
        BA if$end003
        nop
Fib$CalculateFib$epilogBegin:
        ret
        restore
