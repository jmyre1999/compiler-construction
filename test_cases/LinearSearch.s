        .global start
start:
LinearSearch$main:
LinearSearch$main$preludeEnd:
        SET 2,%l0
        MOV %l0,%o0
        call malloc_object
        nop
        MOV %o0, %l1
        MOV %l1,%l2
        MOV %l2,%o0
        SET 10,%l3
        MOV %l3,%o1
        call LS$Start
        nop
        MOV %o0, %l4
        MOV %l4,%l5
        MOV %l5,%o0
        call print_int
        nop
        MOV %o0, %l6
        BA LinearSearch$main$epilogBegin
        nop
LinearSearch$main$epilogBegin:
        clr %o0
        call exit
        nop
LS$Start:
        .set LOCLS,2
        .set TEMPS,27
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
LS$Start$preludeEnd:
        SUB %fp,4,%l0
        MOV %l0,%l1
        MOV %i0,%o0
        MOV %i1,%o1
        call LS$Init
        nop
        MOV %o0, %l2
        MOV %l2,%l3
        ST %l3, [%l1]
        SUB %fp,8,%l4
        MOV %l4,%l5
        MOV %i0,%o0
        call LS$Print
        nop
        MOV %o0, %l6
        MOV %l6,%l7
        ST %l7, [%l5]
        SET 9999,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        MOV %i0,%o0
        SET 8,%g1
        MOV %g1,%o1
        call LS$Search
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        MOV %i0,%o0
        SET 12,%g1
        MOV %g1,%o1
        call LS$Search
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        MOV %i0,%o0
        SET 17,%g1
        MOV %g1,%o1
        call LS$Search
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        MOV %i0,%o0
        SET 50,%g1
        MOV %g1,%o1
        call LS$Search
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        SET 55,%g1
        MOV %g1,%i0
        BA LS$Start$epilogBegin
        nop
LS$Start$epilogBegin:
        ret
        restore
LS$Print:
        .set LOCLS,1
        .set TEMPS,10
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
LS$Print$preludeEnd:
        SET 1,%l0
        ST %l0, [%fp-4]
        LD [%fp-4],%l1
        LD [%i0+4],%l2
        CMP %l1,%l2
        BL while$do001
        nop
        BA while$end002
        nop
while$end002:
        SET 0,%l3
        MOV %l3,%i0
        BA LS$Print$epilogBegin
        nop
while$do001:
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
        BL while$do001
        nop
        BA trace$false016
        nop
trace$false016:
        BA while$end002
        nop
LS$Print$epilogBegin:
        ret
        restore
LS$Search:
        .set LOCLS,6
        .set TEMPS,24
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
LS$Search$preludeEnd:
        SET 1,%l0
        ST %l0, [%fp-16]
        SET 0,%l1
        ST %l1, [%fp-20]
        SET 0,%l2
        ST %l2, [%fp-8]
        LD [%fp-16],%l3
        LD [%i0+4],%l4
        CMP %l3,%l4
        BL while$do012
        nop
        BA while$end013
        nop
while$end013:
        LD [%fp-8],%l5
        MOV %l5,%i0
        BA LS$Search$epilogBegin
        nop
while$do012:
        ST %l6, [%fp-12]
        ADD %i1,1,%l7
        ST %l7, [%fp-24]
        LD [%fp-12],%g1
        CMP %g1,%i1
        BL if$then009
        nop
        BA if$else010
        nop
if$else010:
        LD [%fp-12],%g1
        LD [%fp-24],%g1
        CMP %g1,%g1
        BL cond$true003
        nop
        BA cond$false004
        nop
cond$false004:
        SET 0,%g1
        MOV %g1,%g1
        BA cond$end005
        nop
cond$end005:
        SET 0,%g1
        CMP %g1,%g1
        BE if$then006
        nop
        BA if$else007
        nop
if$else007:
        SET 0,%g1
        ST %g1, [%fp-20]
        SET 1,%g1
        ST %g1, [%fp-8]
        LD [%i0+4],%g1
        ST %g1, [%fp-16]
        BA if$end008
        nop
if$end008:
        BA if$end011
        nop
if$end011:
        LD [%fp-16],%g1
        ADD %g1,1,%g1
        ST %g1, [%fp-16]
        LD [%fp-16],%g1
        LD [%i0+4],%g1
        CMP %g1,%g1
        BL while$do012
        nop
        BA trace$false017
        nop
trace$false017:
        BA while$end013
        nop
if$then009:
        SET 0,%g1
        ST %g1, [%fp-4]
        BA if$end011
        nop
cond$true003:
        SET 1,%g1
        MOV %g1,%g1
        BA cond$end005
        nop
if$then006:
        SET 0,%g1
        ST %g1, [%fp-4]
        BA if$end008
        nop
LS$Search$epilogBegin:
        ret
        restore
LS$Init:
        .set LOCLS,4
        .set TEMPS,20
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
LS$Init$preludeEnd:
        ST %i1, [%i0+4]
        ADD %i0,0,%l0
        MOV %l0,%l1
        MOV %i1,%o0
        call malloc_array
        nop
        MOV %o0, %l2
        MOV %l2,%l3
        ST %l3, [%l1]
        SET 1,%l4
        ST %l4, [%fp-8]
        LD [%i0+4],%l5
        ADD %l5,1,%l6
        ST %l6, [%fp-12]
        LD [%fp-8],%l7
        LD [%i0+4],%g1
        CMP %l7,%g1
        BL while$do014
        nop
        BA while$end015
        nop
while$end015:
        SET 0,%g1
        MOV %g1,%i0
        BA LS$Init$epilogBegin
        nop
while$do014:
        LD [%fp-8],%g1
        SMUL %g1,2,%g1
        ST %g1, [%fp-4]
        LD [%fp-12],%g1
        SUB %g1,3,%g1
        ST %g1, [%fp-16]
        LD [%fp-8],%g1
        ADD %g1,1,%g1
        ST %g1, [%fp-8]
        LD [%fp-12],%g1
        SUB %g1,1,%g1
        ST %g1, [%fp-12]
        LD [%fp-8],%g1
        LD [%i0+4],%g1
        CMP %g1,%g1
        BL while$do014
        nop
        BA trace$false018
        nop
trace$false018:
        BA while$end015
        nop
LS$Init$epilogBegin:
        ret
        restore
