        .global start
start:
Factorial$main:
Factorial$main$preludeEnd:
        SET 0,%l0
        MOV %l0,%o0
        call malloc_object
        nop
        MOV %o0, %l1
        MOV %l1,%l2
        MOV %l2,%o0
        SET 10,%l3
        MOV %l3,%o1
        call Fac$ComputeFac
        nop
        MOV %o0, %l4
        MOV %l4,%l5
        MOV %l5,%o0
        call print_int
        nop
        MOV %o0, %l6
        BA Factorial$main$epilogBegin
        nop
Factorial$main$epilogBegin:
        clr %o0
        call exit
        nop
Fac$ComputeFac:
        .set LOCLS,1
        .set TEMPS,10
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Fac$ComputeFac$preludeEnd:
        SET 1,%l0
        CMP %i1,%l0
        BL if$then001
        nop
        BA if$else002
        nop
if$else002:
        SUB %fp,4,%l1
        MOV %l1,%l2
        MOV %i1,%l3
        MOV %i0,%o0
        SUB %i1,1,%l4
        MOV %l4,%o1
        call Fac$ComputeFac
        nop
        MOV %o0, %l5
        MOV %l5,%l6
        SMUL %l3,%l6,%l7
        ST %l7, [%l2]
        BA if$end003
        nop
if$end003:
        LD [%fp-4],%g1
        MOV %g1,%i0
        BA Fac$ComputeFac$epilogBegin
        nop
if$then001:
        SET 1,%g1
        ST %g1, [%fp-4]
        BA if$end003
        nop
Fac$ComputeFac$epilogBegin:
        ret
        restore
