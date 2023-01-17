        .global start
start:
BinarySearch$main:
BinarySearch$main$preludeEnd:
        SET 2,%l0
        MOV %l0,%o0
        call malloc_object
        nop
        MOV %o0, %l1
        MOV %l1,%l2
        MOV %l2,%o0
        SET 20,%l3
        MOV %l3,%o1
        call BS$Start
        nop
        MOV %o0, %l4
        MOV %l4,%l5
        MOV %l5,%o0
        call print_int
        nop
        MOV %o0, %l6
        BA BinarySearch$main$epilogBegin
        nop
BinarySearch$main$epilogBegin:
        clr %o0
        call exit
        nop
BS$Start:
        .set LOCLS,2
        .set TEMPS,73
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
BS$Start$preludeEnd:
        SUB %fp,4,%l0
        MOV %l0,%l1
        MOV %i0,%o0
        MOV %i1,%o1
        call BS$Init
        nop
        MOV %o0, %l2
        MOV %l2,%l3
        ST %l3, [%l1]
        SUB %fp,8,%l4
        MOV %l4,%l5
        MOV %i0,%o0
        call BS$Print
        nop
        MOV %o0, %l6
        MOV %l6,%l7
        ST %l7, [%l5]
        MOV %i0,%o0
        SET 8,%g1
        MOV %g1,%o1
        call BS$Search
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        SET 1,%g1
        CMP %g1,%g1
        BE if$then001
        nop
        BA if$else002
        nop
if$else002:
        SET 0,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        BA if$end003
        nop
if$end003:
        MOV %i0,%o0
        SET 19,%g1
        MOV %g1,%o1
        call BS$Search
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        SET 1,%g1
        CMP %g1,%g1
        BE if$then004
        nop
        BA if$else005
        nop
if$else005:
        SET 0,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        BA if$end006
        nop
if$end006:
        MOV %i0,%o0
        SET 20,%g1
        MOV %g1,%o1
        call BS$Search
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        SET 1,%g1
        CMP %g1,%g1
        BE if$then007
        nop
        BA if$else008
        nop
if$else008:
        SET 0,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        BA if$end009
        nop
if$end009:
        MOV %i0,%o0
        SET 21,%g1
        MOV %g1,%o1
        call BS$Search
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        SET 1,%g1
        CMP %g1,%g1
        BE if$then010
        nop
        BA if$else011
        nop
if$else011:
        SET 0,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        BA if$end012
        nop
if$end012:
        MOV %i0,%o0
        SET 37,%g1
        MOV %g1,%o1
        call BS$Search
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        SET 1,%g1
        CMP %g1,%g1
        BE if$then013
        nop
        BA if$else014
        nop
if$else014:
        SET 0,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        BA if$end015
        nop
if$end015:
        MOV %i0,%o0
        SET 38,%g1
        MOV %g1,%o1
        call BS$Search
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        SET 1,%g1
        CMP %g1,%g1
        BE if$then016
        nop
        BA if$else017
        nop
if$else017:
        SET 0,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        BA if$end018
        nop
if$end018:
        MOV %i0,%o0
        SET 39,%g1
        MOV %g1,%o1
        call BS$Search
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        SET 1,%g1
        CMP %g1,%g1
        BE if$then019
        nop
        BA if$else020
        nop
if$else020:
        SET 0,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        BA if$end021
        nop
if$end021:
        MOV %i0,%o0
        SET 50,%g1
        MOV %g1,%o1
        call BS$Search
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        SET 1,%g1
        CMP %g1,%g1
        BE if$then022
        nop
        BA if$else023
        nop
if$else023:
        SET 0,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        BA if$end024
        nop
if$end024:
        SET 999,%g1
        MOV %g1,%i0
        BA BS$Start$epilogBegin
        nop
if$then001:
        SET 1,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        BA if$end003
        nop
if$then004:
        SET 1,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        BA if$end006
        nop
if$then007:
        SET 1,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        BA if$end009
        nop
if$then010:
        SET 1,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        BA if$end012
        nop
if$then013:
        SET 1,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        BA if$end015
        nop
if$then016:
        SET 1,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        BA if$end018
        nop
if$then019:
        SET 1,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        BA if$end021
        nop
if$then022:
        SET 1,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        BA if$end024
        nop
BS$Start$epilogBegin:
        ret
        restore
BS$Search:
        .set LOCLS,7
        .set TEMPS,43
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
BS$Search$preludeEnd:
        SET 0,%l0
        ST %l0, [%fp-16]
        SET 0,%l1
        ST %l1, [%fp-28]
        LD [%i0+0],%l2
        LD [%l2],%l3
        ST %l3, [%fp-20]
        LD [%fp-20],%l4
        SUB %l4,1,%l5
        ST %l5, [%fp-20]
        SET 0,%l6
        ST %l6, [%fp-8]
        SET 0,%l7
        ST %l7, [%fp-4]
        LD [%fp-4],%g1
        SET 1,%g1
        CMP %g1,%g1
        BE while$do034
        nop
        BA while$end035
        nop
while$end035:
        MOV %i0,%o0
        LD [%fp-16],%g1
        MOV %g1,%o1
        MOV %i1,%o2
        call BS$Compare
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        SET 1,%g1
        CMP %g1,%g1
        BE if$then036
        nop
        BA if$else037
        nop
if$else037:
        SET 0,%g1
        ST %g1, [%fp-28]
        BA if$end038
        nop
if$end038:
        LD [%fp-28],%g1
        MOV %g1,%i0
        BA BS$Search$epilogBegin
        nop
while$do034:
        LD [%fp-8],%g1
        LD [%fp-20],%g1
        ADD %g1,%g1,%g1
        ST %g1, [%fp-24]
        SUB %fp,24,%g1
        MOV %g1,%g1
        MOV %i0,%o0
        LD [%fp-24],%g1
        MOV %g1,%o1
        call BS$Div
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        ST %g1, [%fp-16]
        LD [%fp-16],%g1
        CMP %i1,%g1
        BL if$then025
        nop
        BA if$else026
        nop
if$else026:
        LD [%fp-24],%g1
        ADD %g1,1,%g1
        ST %g1, [%fp-8]
        BA if$end027
        nop
if$end027:
        MOV %i0,%o0
        LD [%fp-16],%g1
        MOV %g1,%o1
        MOV %i1,%o2
        call BS$Compare
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        SET 1,%g1
        CMP %g1,%g1
        BE if$then028
        nop
        BA if$else029
        nop
if$else029:
        SET 0,%g1
        ST %g1, [%fp-4]
        BA if$end030
        nop
if$end030:
        LD [%fp-20],%g1
        LD [%fp-8],%g1
        CMP %g1,%g1
        BL if$then031
        nop
        BA if$else032
        nop
if$else032:
        SET 0,%g1
        ST %g1, [%fp-12]
        BA if$end033
        nop
if$end033:
        LD [%fp-4],%g1
        SET 1,%g1
        CMP %g1,%g1
        BE while$do034
        nop
        BA trace$false054
        nop
trace$false054:
        BA while$end035
        nop
if$then025:
        LD [%fp-24],%g1
        SUB %g1,1,%g1
        ST %g1, [%fp-20]
        BA if$end027
        nop
if$then028:
        SET 0,%g1
        ST %g1, [%fp-4]
        BA if$end030
        nop
if$then031:
        SET 0,%g1
        ST %g1, [%fp-4]
        BA if$end033
        nop
if$then036:
        SET 0,%g1
        ST %g1, [%fp-28]
        BA if$end038
        nop
BS$Search$epilogBegin:
        ret
        restore
BS$Div:
        .set LOCLS,3
        .set TEMPS,12
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
BS$Div$preludeEnd:
        SET 0,%l0
        ST %l0, [%fp-8]
        SET 0,%l1
        ST %l1, [%fp-4]
        SUB %i1,1,%l2
        ST %l2, [%fp-12]
        LD [%fp-4],%l3
        LD [%fp-12],%l4
        CMP %l3,%l4
        BL while$do039
        nop
        BA while$end040
        nop
while$end040:
        LD [%fp-8],%l5
        MOV %l5,%i0
        BA BS$Div$epilogBegin
        nop
while$do039:
        LD [%fp-8],%l6
        ADD %l6,1,%l7
        ST %l7, [%fp-8]
        LD [%fp-4],%g1
        ADD %g1,2,%g1
        ST %g1, [%fp-4]
        LD [%fp-4],%g1
        LD [%fp-12],%g1
        CMP %g1,%g1
        BL while$do039
        nop
        BA trace$false055
        nop
trace$false055:
        BA while$end040
        nop
BS$Div$epilogBegin:
        ret
        restore
BS$Compare:
        .set LOCLS,2
        .set TEMPS,11
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
BS$Compare$preludeEnd:
        SET 0,%l0
        ST %l0, [%fp-8]
        ADD %i2,1,%l1
        ST %l1, [%fp-4]
        CMP %i1,%i2
        BL if$then047
        nop
        BA if$else048
        nop
if$else048:
        LD [%fp-4],%l2
        CMP %i1,%l2
        BL cond$true041
        nop
        BA cond$false042
        nop
cond$false042:
        SET 0,%l3
        MOV %l3,%l4
        BA cond$end043
        nop
cond$end043:
        SET 0,%l5
        CMP %l4,%l5
        BE if$then044
        nop
        BA if$else045
        nop
if$else045:
        SET 0,%l6
        ST %l6, [%fp-8]
        BA if$end046
        nop
if$end046:
        BA if$end049
        nop
if$end049:
        LD [%fp-8],%l7
        MOV %l7,%i0
        BA BS$Compare$epilogBegin
        nop
if$then047:
        SET 0,%g1
        ST %g1, [%fp-8]
        BA if$end049
        nop
cond$true041:
        SET 1,%g1
        MOV %g1,%l4
        BA cond$end043
        nop
if$then044:
        SET 0,%g1
        ST %g1, [%fp-8]
        BA if$end046
        nop
BS$Compare$epilogBegin:
        ret
        restore
BS$Print:
        .set LOCLS,1
        .set TEMPS,12
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
BS$Print$preludeEnd:
        SET 1,%l0
        ST %l0, [%fp-4]
        LD [%fp-4],%l1
        LD [%i0+4],%l2
        CMP %l1,%l2
        BL while$do050
        nop
        BA while$end051
        nop
while$end051:
        SET 99999,%l3
        MOV %l3,%o0
        call print_int
        nop
        MOV %o0, %l4
        SET 0,%l5
        MOV %l5,%i0
        BA BS$Print$epilogBegin
        nop
while$do050:
        MOV %l6,%o0
        call print_int
        nop
        MOV %o0, %l7
        LD [%fp-4],%g1
        ADD %g1,1,%g1
        ST %g1, [%fp-4]
        LD [%fp-4],%g1
        LD [%i0+4],%g1
        CMP %g1,%g1
        BL while$do050
        nop
        BA trace$false056
        nop
trace$false056:
        BA while$end051
        nop
BS$Print$epilogBegin:
        ret
        restore
BS$Init:
        .set LOCLS,4
        .set TEMPS,20
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
BS$Init$preludeEnd:
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
        BL while$do052
        nop
        BA while$end053
        nop
while$end053:
        SET 0,%g1
        MOV %g1,%i0
        BA BS$Init$epilogBegin
        nop
while$do052:
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
        BL while$do052
        nop
        BA trace$false057
        nop
trace$false057:
        BA while$end053
        nop
BS$Init$epilogBegin:
        ret
        restore
