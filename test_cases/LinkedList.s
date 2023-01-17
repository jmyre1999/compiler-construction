        .global start
start:
LinkedList$main:
LinkedList$main$preludeEnd:
        SET 0,%l0
        MOV %l0,%o0
        call malloc_object
        nop
        MOV %o0, %l1
        MOV %l1,%l2
        MOV %l2,%o0
        call LL$Start
        nop
        MOV %o0, %l3
        MOV %l3,%l4
        MOV %l4,%o0
        call print_int
        nop
        MOV %o0, %l5
        BA LinkedList$main$epilogBegin
        nop
LinkedList$main$epilogBegin:
        clr %o0
        call exit
        nop
Element$Init:
        .set LOCLS,0
        .set TEMPS,1
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Element$Init$preludeEnd:
        ST %i3, [%i0+8]
        ST %i1, [%i0+0]
        ST %i2, [%i0+4]
        SET 0,%l0
        MOV %l0,%i0
        BA Element$Init$epilogBegin
        nop
Element$Init$epilogBegin:
        ret
        restore
Element$GetAge:
        .set LOCLS,0
        .set TEMPS,1
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Element$GetAge$preludeEnd:
        LD [%i0+8],%l0
        MOV %l0,%i0
        BA Element$GetAge$epilogBegin
        nop
Element$GetAge$epilogBegin:
        ret
        restore
Element$GetSalary:
        .set LOCLS,0
        .set TEMPS,1
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Element$GetSalary$preludeEnd:
        LD [%i0+0],%l0
        MOV %l0,%i0
        BA Element$GetSalary$epilogBegin
        nop
Element$GetSalary$epilogBegin:
        ret
        restore
Element$GetMarried:
        .set LOCLS,0
        .set TEMPS,1
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Element$GetMarried$preludeEnd:
        LD [%i0+4],%l0
        MOV %l0,%i0
        BA Element$GetMarried$epilogBegin
        nop
Element$GetMarried$epilogBegin:
        ret
        restore
Element$Equal:
        .set LOCLS,4
        .set TEMPS,34
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Element$Equal$preludeEnd:
        SET 0,%l0
        ST %l0, [%fp-16]
        SUB %fp,8,%l1
        MOV %l1,%l2
        MOV %i1,%o0
        call Element$GetAge
        nop
        MOV %o0, %l3
        MOV %l3,%l4
        ST %l4, [%l2]
        MOV %i0,%o0
        LD [%fp-8],%l5
        MOV %l5,%o1
        LD [%i0+8],%l6
        MOV %l6,%o2
        call Element$Compare
        nop
        MOV %o0, %l7
        MOV %l7,%g1
        SET 0,%g1
        CMP %g1,%g1
        BE if$then013
        nop
        BA if$else014
        nop
if$else014:
        SUB %fp,12,%g1
        MOV %g1,%g1
        MOV %i1,%o0
        call Element$GetSalary
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        MOV %i0,%o0
        LD [%fp-12],%g1
        MOV %g1,%o1
        LD [%i0+0],%g1
        MOV %g1,%o2
        call Element$Compare
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        SET 0,%g1
        CMP %g1,%g1
        BE if$then010
        nop
        BA if$else011
        nop
if$else011:
        LD [%i0+4],%g1
        SET 1,%g1
        CMP %g1,%g1
        BE if$then007
        nop
        BA if$else008
        nop
if$else008:
        MOV %i1,%o0
        call Element$GetMarried
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
        ST %g1, [%fp-4]
        BA if$end006
        nop
if$end006:
        BA if$end009
        nop
if$end009:
        BA if$end012
        nop
if$end012:
        BA if$end015
        nop
if$end015:
        LD [%fp-16],%g1
        MOV %g1,%i0
        BA Element$Equal$epilogBegin
        nop
if$then013:
        SET 0,%g1
        ST %g1, [%fp-16]
        BA if$end015
        nop
if$then010:
        SET 0,%g1
        ST %g1, [%fp-16]
        BA if$end012
        nop
if$then007:
        MOV %i1,%o0
        call Element$GetMarried
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        SET 0,%g1
        CMP %g1,%g1
        BE if$then001
        nop
        BA if$else002
        nop
if$else002:
        SET 0,%g1
        ST %g1, [%fp-4]
        BA if$end003
        nop
if$end003:
        BA if$end009
        nop
if$then001:
        SET 0,%g1
        ST %g1, [%fp-16]
        BA if$end003
        nop
if$then004:
        SET 0,%g1
        ST %g1, [%fp-16]
        BA if$end006
        nop
Element$Equal$epilogBegin:
        ret
        restore
Element$Compare:
        .set LOCLS,2
        .set TEMPS,11
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Element$Compare$preludeEnd:
        SET 0,%l0
        ST %l0, [%fp-8]
        ADD %i2,1,%l1
        ST %l1, [%fp-4]
        CMP %i1,%i2
        BL if$then022
        nop
        BA if$else023
        nop
if$else023:
        LD [%fp-4],%l2
        CMP %i1,%l2
        BL cond$true016
        nop
        BA cond$false017
        nop
cond$false017:
        SET 0,%l3
        MOV %l3,%l4
        BA cond$end018
        nop
cond$end018:
        SET 0,%l5
        CMP %l4,%l5
        BE if$then019
        nop
        BA if$else020
        nop
if$else020:
        SET 0,%l6
        ST %l6, [%fp-8]
        BA if$end021
        nop
if$end021:
        BA if$end024
        nop
if$end024:
        LD [%fp-8],%l7
        MOV %l7,%i0
        BA Element$Compare$epilogBegin
        nop
if$then022:
        SET 0,%g1
        ST %g1, [%fp-8]
        BA if$end024
        nop
cond$true016:
        SET 1,%g1
        MOV %g1,%l4
        BA cond$end018
        nop
if$then019:
        SET 0,%g1
        ST %g1, [%fp-8]
        BA if$end021
        nop
Element$Compare$epilogBegin:
        ret
        restore
List$Init:
        .set LOCLS,0
        .set TEMPS,2
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
List$Init$preludeEnd:
        SET 0,%l0
        ST %l0, [%i0+8]
        SET 0,%l1
        MOV %l1,%i0
        BA List$Init$epilogBegin
        nop
List$Init$epilogBegin:
        ret
        restore
List$InitNew:
        .set LOCLS,0
        .set TEMPS,1
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
List$InitNew$preludeEnd:
        ST %i2, [%i0+8]
        ST %i3, [%i0+0]
        ST %i1, [%i0+4]
        SET 0,%l0
        MOV %l0,%i0
        BA List$InitNew$epilogBegin
        nop
List$InitNew$epilogBegin:
        ret
        restore
List$Insert:
        .set LOCLS,3
        .set TEMPS,13
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
List$Insert$preludeEnd:
        ST %i0, [%fp-4]
        SUB %fp,8,%l0
        MOV %l0,%l1
        SET 3,%l2
        MOV %l2,%o0
        call malloc_object
        nop
        MOV %o0, %l3
        MOV %l3,%l4
        ST %l4, [%l1]
        SUB %fp,12,%l5
        MOV %l5,%l6
        LD [%fp-8],%l7
        MOV %l7,%o0
        MOV %i1,%o1
        LD [%fp-4],%g1
        MOV %g1,%o2
        SET 0,%g1
        MOV %g1,%o3
        call List$InitNew
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%l6]
        LD [%fp-8],%g1
        MOV %g1,%i0
        BA List$Insert$epilogBegin
        nop
List$Insert$epilogBegin:
        ret
        restore
List$SetNext:
        .set LOCLS,0
        .set TEMPS,1
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
List$SetNext$preludeEnd:
        ST %i1, [%i0+4]
        SET 0,%l0
        MOV %l0,%i0
        BA List$SetNext$epilogBegin
        nop
List$SetNext$epilogBegin:
        ret
        restore
List$Delete:
        .set LOCLS,9
        .set TEMPS,67
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
List$Delete$preludeEnd:
        ST %i0, [%fp-32]
        SET 0,%l0
        ST %l0, [%fp-28]
        SET 0,%l1
        SUB %l1,1,%l2
        ST %l2, [%fp-8]
        ST %i0, [%fp-20]
        ST %i0, [%fp-16]
        LD [%i0+8],%l3
        ST %l3, [%fp-24]
        LD [%i0+0],%l4
        ST %l4, [%fp-36]
        LD [%fp-24],%l5
        SET 0,%l6
        CMP %l5,%l6
        BE cond$true025
        nop
        BA cond$false026
        nop
cond$false026:
        SET 0,%l7
        MOV %l7,%g1
        BA cond$end027
        nop
cond$end027:
        MOV %g1,%g1
        LD [%fp-28],%g1
        SET 0,%g1
        CMP %g1,%g1
        BE cond$true028
        nop
        BA cond$false029
        nop
cond$false029:
        SET 0,%g1
        MOV %g1,%g1
        BA cond$end030
        nop
cond$end030:
        CMP %g1,%g1
        BE while$do040
        nop
        BA while$end041
        nop
while$end041:
        LD [%fp-32],%g1
        MOV %g1,%i0
        BA List$Delete$epilogBegin
        nop
cond$true025:
        SET 1,%g1
        MOV %g1,%g1
        BA cond$end027
        nop
cond$true028:
        SET 1,%g1
        MOV %g1,%g1
        BA cond$end030
        nop
while$do040:
        MOV %i1,%o0
        LD [%fp-36],%g1
        MOV %g1,%o1
        call Element$Equal
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        SET 1,%g1
        CMP %g1,%g1
        BE if$then034
        nop
        BA if$else035
        nop
if$else035:
        SET 0,%g1
        ST %g1, [%fp-12]
        BA if$end036
        nop
if$end036:
        LD [%fp-28],%g1
        SET 0,%g1
        CMP %g1,%g1
        BE if$then037
        nop
        BA if$else038
        nop
if$else038:
        SET 0,%g1
        ST %g1, [%fp-12]
        BA if$end039
        nop
if$end039:
        LD [%fp-24],%g1
        SET 0,%g1
        CMP %g1,%g1
        BE cond$true025
        nop
        BA trace$false049
        nop
trace$false049:
        BA cond$false026
        nop
if$then034:
        SET 0,%g1
        ST %g1, [%fp-28]
        LD [%fp-8],%g1
        SET 0,%g1
        CMP %g1,%g1
        BL if$then031
        nop
        BA if$else032
        nop
if$else032:
        SET 0,%g1
        SUB %g1,555,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        SUB %fp,4,%g1
        MOV %g1,%g1
        LD [%fp-16],%g1
        MOV %g1,%g1
        LD [%fp-20],%g1
        MOV %g1,%o0
        call List$GetNext
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        MOV %g1,%o0
        MOV %g1,%o1
        call List$SetNext
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SET 0,%g1
        SUB %g1,555,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        BA if$end033
        nop
if$end033:
        BA if$end036
        nop
if$then031:
        SUB %fp,32,%g1
        MOV %g1,%g1
        LD [%fp-20],%g1
        MOV %g1,%o0
        call List$GetNext
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        BA if$end033
        nop
if$then037:
        LD [%fp-20],%g1
        ST %g1, [%fp-16]
        SUB %fp,20,%g1
        MOV %g1,%g1
        LD [%fp-20],%g1
        MOV %g1,%o0
        call List$GetNext
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,24,%g1
        MOV %g1,%g1
        LD [%fp-20],%g1
        MOV %g1,%o0
        call List$GetEnd
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,36,%g1
        MOV %g1,%g1
        LD [%fp-20],%g1
        MOV %g1,%o0
        call List$GetElem
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SET 1,%g1
        ST %g1, [%fp-8]
        BA if$end039
        nop
List$Delete$epilogBegin:
        ret
        restore
List$Search:
        .set LOCLS,5
        .set TEMPS,29
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
List$Search$preludeEnd:
        SET 0,%l0
        ST %l0, [%fp-16]
        ST %i0, [%fp-8]
        LD [%i0+8],%l1
        ST %l1, [%fp-12]
        LD [%i0+0],%l2
        ST %l2, [%fp-20]
        LD [%fp-12],%l3
        SET 0,%l4
        CMP %l3,%l4
        BE while$do045
        nop
        BA while$end046
        nop
while$end046:
        LD [%fp-16],%l5
        MOV %l5,%i0
        BA List$Search$epilogBegin
        nop
while$do045:
        MOV %i1,%o0
        LD [%fp-20],%l6
        MOV %l6,%o1
        call Element$Equal
        nop
        MOV %o0, %l7
        MOV %l7,%g1
        SET 1,%g1
        CMP %g1,%g1
        BE if$then042
        nop
        BA if$else043
        nop
if$else043:
        SET 0,%g1
        ST %g1, [%fp-4]
        BA if$end044
        nop
if$end044:
        SUB %fp,8,%g1
        MOV %g1,%g1
        LD [%fp-8],%g1
        MOV %g1,%o0
        call List$GetNext
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,12,%g1
        MOV %g1,%g1
        LD [%fp-8],%g1
        MOV %g1,%o0
        call List$GetEnd
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,20,%g1
        MOV %g1,%g1
        LD [%fp-8],%g1
        MOV %g1,%o0
        call List$GetElem
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        LD [%fp-12],%g1
        SET 0,%g1
        CMP %g1,%g1
        BE while$do045
        nop
        BA trace$false050
        nop
trace$false050:
        BA while$end046
        nop
if$then042:
        SET 1,%g1
        ST %g1, [%fp-16]
        BA if$end044
        nop
List$Search$epilogBegin:
        ret
        restore
List$GetEnd:
        .set LOCLS,0
        .set TEMPS,1
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
List$GetEnd$preludeEnd:
        LD [%i0+8],%l0
        MOV %l0,%i0
        BA List$GetEnd$epilogBegin
        nop
List$GetEnd$epilogBegin:
        ret
        restore
List$GetElem:
        .set LOCLS,0
        .set TEMPS,1
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
List$GetElem$preludeEnd:
        LD [%i0+0],%l0
        MOV %l0,%i0
        BA List$GetElem$epilogBegin
        nop
List$GetElem$epilogBegin:
        ret
        restore
List$GetNext:
        .set LOCLS,0
        .set TEMPS,1
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
List$GetNext$preludeEnd:
        LD [%i0+4],%l0
        MOV %l0,%i0
        BA List$GetNext$epilogBegin
        nop
List$GetNext$epilogBegin:
        ret
        restore
List$Print:
        .set LOCLS,3
        .set TEMPS,26
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
List$Print$preludeEnd:
        ST %i0, [%fp-4]
        LD [%i0+8],%l0
        ST %l0, [%fp-8]
        LD [%i0+0],%l1
        ST %l1, [%fp-12]
        LD [%fp-8],%l2
        SET 0,%l3
        CMP %l2,%l3
        BE while$do047
        nop
        BA while$end048
        nop
while$end048:
        SET 0,%l4
        MOV %l4,%i0
        BA List$Print$epilogBegin
        nop
while$do047:
        LD [%fp-12],%l5
        MOV %l5,%o0
        call Element$GetAge
        nop
        MOV %o0, %l6
        MOV %l6,%l7
        MOV %l7,%o0
        call print_int
        nop
        MOV %o0, %g1
        SUB %fp,4,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        call List$GetNext
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,8,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        call List$GetEnd
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,12,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        call List$GetElem
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        LD [%fp-8],%g1
        SET 0,%g1
        CMP %g1,%g1
        BE while$do047
        nop
        BA trace$false051
        nop
trace$false051:
        BA while$end048
        nop
List$Print$epilogBegin:
        ret
        restore
LL$Start:
        .set LOCLS,6
        .set TEMPS,176
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
LL$Start$preludeEnd:
        SUB %fp,8,%l0
        MOV %l0,%l1
        SET 3,%l2
        MOV %l2,%o0
        call malloc_object
        nop
        MOV %o0, %l3
        MOV %l3,%l4
        ST %l4, [%l1]
        SUB %fp,24,%l5
        MOV %l5,%l6
        LD [%fp-8],%l7
        MOV %l7,%o0
        call List$Init
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%l6]
        LD [%fp-8],%g1
        ST %g1, [%fp-4]
        SUB %fp,24,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        call List$Init
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,24,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        call List$Print
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,20,%g1
        MOV %g1,%g1
        SET 3,%g1
        MOV %g1,%o0
        call malloc_object
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,24,%g1
        MOV %g1,%g1
        LD [%fp-20],%g1
        MOV %g1,%o0
        SET 25,%g1
        MOV %g1,%o1
        SET 37000,%g1
        MOV %g1,%o2
        SET 0,%g1
        MOV %g1,%o3
        call Element$Init
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,4,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        LD [%fp-20],%g1
        MOV %g1,%o1
        call List$Insert
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,24,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        call List$Print
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SET 10000000,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        SUB %fp,20,%g1
        MOV %g1,%g1
        SET 3,%g1
        MOV %g1,%o0
        call malloc_object
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,24,%g1
        MOV %g1,%g1
        LD [%fp-20],%g1
        MOV %g1,%o0
        SET 39,%g1
        MOV %g1,%o1
        SET 42000,%g1
        MOV %g1,%o2
        SET 0,%g1
        MOV %g1,%o3
        call Element$Init
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        LD [%fp-20],%g1
        ST %g1, [%fp-16]
        SUB %fp,4,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        LD [%fp-20],%g1
        MOV %g1,%o1
        call List$Insert
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,24,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        call List$Print
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SET 10000000,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        SUB %fp,20,%g1
        MOV %g1,%g1
        SET 3,%g1
        MOV %g1,%o0
        call malloc_object
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,24,%g1
        MOV %g1,%g1
        LD [%fp-20],%g1
        MOV %g1,%o0
        SET 22,%g1
        MOV %g1,%o1
        SET 34000,%g1
        MOV %g1,%o2
        SET 0,%g1
        MOV %g1,%o3
        call Element$Init
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,4,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        LD [%fp-20],%g1
        MOV %g1,%o1
        call List$Insert
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,24,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        call List$Print
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,12,%g1
        MOV %g1,%g1
        SET 3,%g1
        MOV %g1,%o0
        call malloc_object
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,24,%g1
        MOV %g1,%g1
        LD [%fp-12],%g1
        MOV %g1,%o0
        SET 27,%g1
        MOV %g1,%o1
        SET 34000,%g1
        MOV %g1,%o2
        SET 0,%g1
        MOV %g1,%o3
        call Element$Init
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        LD [%fp-4],%g1
        MOV %g1,%o0
        LD [%fp-16],%g1
        MOV %g1,%o1
        call List$Search
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        LD [%fp-12],%g1
        MOV %g1,%o1
        call List$Search
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        SET 10000000,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        SUB %fp,20,%g1
        MOV %g1,%g1
        SET 3,%g1
        MOV %g1,%o0
        call malloc_object
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,24,%g1
        MOV %g1,%g1
        LD [%fp-20],%g1
        MOV %g1,%o0
        SET 28,%g1
        MOV %g1,%o1
        SET 35000,%g1
        MOV %g1,%o2
        SET 0,%g1
        MOV %g1,%o3
        call Element$Init
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,4,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        LD [%fp-20],%g1
        MOV %g1,%o1
        call List$Insert
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,24,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        call List$Print
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SET 2220000,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        SUB %fp,4,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        LD [%fp-16],%g1
        MOV %g1,%o1
        call List$Delete
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,24,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        call List$Print
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SET 33300000,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        SUB %fp,4,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        LD [%fp-20],%g1
        MOV %g1,%o1
        call List$Delete
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,24,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        call List$Print
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SET 44440000,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        SET 0,%g1
        MOV %g1,%i0
        BA LL$Start$epilogBegin
        nop
LL$Start$epilogBegin:
        ret
        restore
