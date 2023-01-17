        .global start
start:
QuickSort$main:
QuickSort$main$preludeEnd:
        SET 2,%l0
        MOV %l0,%o0
        call malloc_object
        nop
        MOV %o0, %l1
        MOV %l1,%l2
        MOV %l2,%o0
        SET 10,%l3
        MOV %l3,%o1
        call QS$Start
        nop
        MOV %o0, %l4
        MOV %l4,%l5
        MOV %l5,%o0
        call print_int
        nop
        MOV %o0, %l6
        BA QuickSort$main$epilogBegin
        nop
QuickSort$main$epilogBegin:
        clr %o0
        call exit
        nop
QS$Start:
        .set LOCLS,1
        .set TEMPS,23
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
QS$Start$preludeEnd:
        SUB %fp,4,%l0
        MOV %l0,%l1
        MOV %i0,%o0
        MOV %i1,%o1
        call QS$Init
        nop
        MOV %o0, %l2
        MOV %l2,%l3
        ST %l3, [%l1]
        SUB %fp,4,%l4
        MOV %l4,%l5
        MOV %i0,%o0
        call QS$Print
        nop
        MOV %o0, %l6
        MOV %l6,%l7
        ST %l7, [%l5]
        SET 9999,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        LD [%i0+4],%g1
        SUB %g1,1,%g1
        ST %g1, [%fp-4]
        SUB %fp,4,%g1
        MOV %g1,%g1
        MOV %i0,%o0
        SET 0,%g1
        MOV %g1,%o1
        LD [%fp-4],%g1
        MOV %g1,%o2
        call QS$Sort
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,4,%g1
        MOV %g1,%g1
        MOV %i0,%o0
        call QS$Print
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SET 0,%g1
        MOV %g1,%i0
        BA QS$Start$epilogBegin
        nop
QS$Start$epilogBegin:
        ret
        restore
QS$Sort:
        .set LOCLS,8
        .set TEMPS,60
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
QS$Sort$preludeEnd:
        SET 0,%l0
        ST %l0, [%fp-4]
        CMP %i1,%i2
        BL if$then022
        nop
        BA if$else023
        nop
if$else023:
        SET 0,%l1
        ST %l1, [%fp-12]
        BA if$end024
        nop
if$end024:
        SET 0,%l2
        MOV %l2,%i0
        BA QS$Sort$epilogBegin
        nop
if$then022:
        ST %l3, [%fp-8]
        SUB %i1,1,%l4
        ST %l4, [%fp-16]
        ST %i2, [%fp-20]
        SET 0,%l5
        ST %l5, [%fp-32]
        LD [%fp-32],%l6
        SET 1,%l7
        CMP %l6,%l7
        BE while$do020
        nop
        BA while$end021
        nop
while$end021:
        SUB %fp,12,%g1
        MOV %g1,%g1
        MOV %i0,%o0
        MOV %i1,%o1
        LD [%fp-16],%g1
        SUB %g1,1,%g1
        MOV %g1,%o2
        call QS$Sort
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,12,%g1
        MOV %g1,%g1
        MOV %i0,%o0
        LD [%fp-16],%g1
        ADD %g1,1,%g1
        MOV %g1,%o1
        MOV %i2,%o2
        call QS$Sort
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        BA if$end024
        nop
while$do020:
        SET 0,%g1
        ST %g1, [%fp-28]
        LD [%fp-28],%g1
        SET 1,%g1
        CMP %g1,%g1
        BE while$do007
        nop
        BA while$end008
        nop
while$end008:
        SET 0,%g1
        ST %g1, [%fp-28]
        LD [%fp-28],%g1
        SET 1,%g1
        CMP %g1,%g1
        BE while$do015
        nop
        BA while$end016
        nop
while$end016:
        ST %g1, [%fp-4]
        LD [%fp-20],%g1
        LD [%fp-16],%g1
        ADD %g1,1,%g1
        CMP %g1,%g1
        BL if$then017
        nop
        BA if$else018
        nop
if$else018:
        SET 0,%g1
        ST %g1, [%fp-32]
        BA if$end019
        nop
if$end019:
        LD [%fp-32],%g1
        SET 1,%g1
        CMP %g1,%g1
        BE while$do020
        nop
        BA trace$false027
        nop
trace$false027:
        BA while$end021
        nop
while$do007:
        LD [%fp-16],%g1
        ADD %g1,1,%g1
        ST %g1, [%fp-16]
        ST %g1, [%fp-24]
        LD [%fp-24],%g1
        LD [%fp-8],%g1
        CMP %g1,%g1
        BL cond$true001
        nop
        BA cond$false002
        nop
cond$false002:
        SET 0,%g1
        MOV %g1,%g1
        BA cond$end003
        nop
cond$end003:
        SET 0,%g1
        CMP %g1,%g1
        BE if$then004
        nop
        BA if$else005
        nop
if$else005:
        SET 0,%g1
        ST %g1, [%fp-28]
        BA if$end006
        nop
if$end006:
        LD [%fp-28],%g1
        SET 1,%g1
        CMP %g1,%g1
        BE while$do007
        nop
        BA trace$false028
        nop
trace$false028:
        BA while$end008
        nop
cond$true001:
        SET 1,%g1
        MOV %g1,%g1
        BA cond$end003
        nop
if$then004:
        SET 0,%g1
        ST %g1, [%fp-28]
        BA if$end006
        nop
while$do015:
        LD [%fp-20],%g1
        SUB %g1,1,%g1
        ST %g1, [%fp-20]
        ST %g1, [%fp-24]
        LD [%fp-8],%g1
        LD [%fp-24],%g1
        CMP %g1,%g1
        BL cond$true009
        nop
        BA cond$false010
        nop
cond$false010:
        SET 0,%g1
        MOV %g1,%g1
        BA cond$end011
        nop
cond$end011:
        SET 0,%g1
        CMP %g1,%g1
        BE if$then012
        nop
        BA if$else013
        nop
if$else013:
        SET 0,%g1
        ST %g1, [%fp-28]
        BA if$end014
        nop
if$end014:
        LD [%fp-28],%g1
        SET 1,%g1
        CMP %g1,%g1
        BE while$do015
        nop
        BA trace$false029
        nop
trace$false029:
        BA while$end016
        nop
cond$true009:
        SET 1,%g1
        MOV %g1,%g1
        BA cond$end011
        nop
if$then012:
        SET 0,%g1
        ST %g1, [%fp-28]
        BA if$end014
        nop
if$then017:
        SET 0,%g1
        ST %g1, [%fp-32]
        BA if$end019
        nop
QS$Sort$epilogBegin:
        ret
        restore
QS$Print:
        .set LOCLS,1
        .set TEMPS,10
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
QS$Print$preludeEnd:
        SET 0,%l0
        ST %l0, [%fp-4]
        LD [%fp-4],%l1
        LD [%i0+4],%l2
        CMP %l1,%l2
        BL while$do025
        nop
        BA while$end026
        nop
while$end026:
        SET 0,%l3
        MOV %l3,%i0
        BA QS$Print$epilogBegin
        nop
while$do025:
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
        BL while$do025
        nop
        BA trace$false030
        nop
trace$false030:
        BA while$end026
        nop
QS$Print$epilogBegin:
        ret
        restore
QS$Init:
        .set LOCLS,0
        .set TEMPS,5
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
QS$Init$preludeEnd:
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
        BA QS$Init$epilogBegin
        nop
QS$Init$epilogBegin:
        ret
        restore
