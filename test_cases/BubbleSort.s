        .global start
start:
BubbleSort$main:
BubbleSort$main$preludeEnd:
        SET 2,%l0
        MOV %l0,%o0
        call malloc_object
        nop
        MOV %o0, %l1
        MOV %l1,%l2
        MOV %l2,%o0
        SET 10,%l3
        MOV %l3,%o1
        call BBS$Start
        nop
        MOV %o0, %l4
        MOV %l4,%l5
        MOV %l5,%o0
        call print_int
        nop
        MOV %o0, %l6
        BA BubbleSort$main$epilogBegin
        nop
BubbleSort$main$epilogBegin:
        clr %o0
        call exit
        nop
BBS$Start:
        .set LOCLS,1
        .set TEMPS,19
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
BBS$Start$preludeEnd:
        SUB %fp,4,%l0
        MOV %l0,%l1
        MOV %i0,%o0
        MOV %i1,%o1
        call BBS$Init
        nop
        MOV %o0, %l2
        MOV %l2,%l3
        ST %l3, [%l1]
        SUB %fp,4,%l4
        MOV %l4,%l5
        MOV %i0,%o0
        call BBS$Print
        nop
        MOV %o0, %l6
        MOV %l6,%l7
        ST %l7, [%l5]
        SET 99999,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        SUB %fp,4,%g1
        MOV %g1,%g1
        MOV %i0,%o0
        call BBS$Sort
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,4,%g1
        MOV %g1,%g1
        MOV %i0,%o0
        call BBS$Print
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SET 0,%g1
        MOV %g1,%i0
        BA BBS$Start$epilogBegin
        nop
BBS$Start$epilogBegin:
        ret
        restore
BBS$Sort:
        .set LOCLS,9
        .set TEMPS,30
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
BBS$Sort$preludeEnd:
        LD [%i0+4],%l0
        SUB %l0,1,%l1
        ST %l1, [%fp-28]
        SET 0,%l2
        SUB %l2,1,%l3
        ST %l3, [%fp-36]
        LD [%fp-36],%l4
        LD [%fp-28],%l5
        CMP %l4,%l5
        BL while$do006
        nop
        BA while$end007
        nop
while$end007:
        SET 0,%l6
        MOV %l6,%i0
        BA BBS$Sort$epilogBegin
        nop
while$do006:
        SET 1,%l7
        ST %l7, [%fp-32]
        LD [%fp-32],%g1
        LD [%fp-28],%g1
        ADD %g1,1,%g1
        CMP %g1,%g1
        BL while$do004
        nop
        BA while$end005
        nop
while$end005:
        LD [%fp-28],%g1
        SUB %g1,1,%g1
        ST %g1, [%fp-28]
        LD [%fp-36],%g1
        LD [%fp-28],%g1
        CMP %g1,%g1
        BL while$do006
        nop
        BA trace$false010
        nop
trace$false010:
        BA while$end007
        nop
while$do004:
        LD [%fp-32],%g1
        SUB %g1,1,%g1
        ST %g1, [%fp-20]
        ST %g1, [%fp-12]
        ST %g1, [%fp-4]
        LD [%fp-4],%g1
        LD [%fp-12],%g1
        CMP %g1,%g1
        BL if$then001
        nop
        BA if$else002
        nop
if$else002:
        SET 0,%g1
        ST %g1, [%fp-16]
        BA if$end003
        nop
if$end003:
        LD [%fp-32],%g1
        ADD %g1,1,%g1
        ST %g1, [%fp-32]
        LD [%fp-32],%g1
        LD [%fp-28],%g1
        ADD %g1,1,%g1
        CMP %g1,%g1
        BL while$do004
        nop
        BA trace$false011
        nop
trace$false011:
        BA while$end005
        nop
if$then001:
        LD [%fp-32],%g1
        SUB %g1,1,%g1
        ST %g1, [%fp-24]
        ST %g1, [%fp-8]
        BA if$end003
        nop
BBS$Sort$epilogBegin:
        ret
        restore
BBS$Print:
        .set LOCLS,1
        .set TEMPS,10
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
BBS$Print$preludeEnd:
        SET 0,%l0
        ST %l0, [%fp-4]
        LD [%fp-4],%l1
        LD [%i0+4],%l2
        CMP %l1,%l2
        BL while$do008
        nop
        BA while$end009
        nop
while$end009:
        SET 0,%l3
        MOV %l3,%i0
        BA BBS$Print$epilogBegin
        nop
while$do008:
        MOV %l4,%o0
        call print_int
        nop
        MOV %o0, %l5
        LD [%fp-4],%l6
        ADD %l6,1,%l7
        ST %l7, [%fp-4]
        LD [%fp-4],%g1
        LD [%i0+4],%g1
        CMP %g1,%g1
        BL while$do008
        nop
        BA trace$false012
        nop
trace$false012:
        BA while$end009
        nop
BBS$Print$epilogBegin:
        ret
        restore
BBS$Init:
        .set LOCLS,0
        .set TEMPS,5
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
BBS$Init$preludeEnd:
        ST %i1, [%i0+4]
        ADD %i0,0,%l0
        MOV %l0,%l1
        MOV %i1,%o0
        call malloc_array
        nop
        MOV %o0, %l2
        MOV %l2,%l3
        ST %l3, [%l1]
        SET 0,%l4
        MOV %l4,%i0
        BA BBS$Init$epilogBegin
        nop
BBS$Init$epilogBegin:
        ret
        restore
