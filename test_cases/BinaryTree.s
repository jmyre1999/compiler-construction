        .global start
start:
BinaryTree$main:
BinaryTree$main$preludeEnd:
        SET 0,%l0
        MOV %l0,%o0
        call malloc_object
        nop
        MOV %o0, %l1
        MOV %l1,%l2
        MOV %l2,%o0
        call BT$Start
        nop
        MOV %o0, %l3
        MOV %l3,%l4
        MOV %l4,%o0
        call print_int
        nop
        MOV %o0, %l5
        BA BinaryTree$main$epilogBegin
        nop
BinaryTree$main$epilogBegin:
        clr %o0
        call exit
        nop
BT$Start:
        .set LOCLS,3
        .set TEMPS,112
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
BT$Start$preludeEnd:
        SUB %fp,8,%l0
        MOV %l0,%l1
        SET 6,%l2
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
        SET 16,%g1
        MOV %g1,%o1
        call Tree$Init
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%l6]
        SUB %fp,12,%g1
        MOV %g1,%g1
        LD [%fp-8],%g1
        MOV %g1,%o0
        call Tree$Print
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SET 100000000,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        SUB %fp,12,%g1
        MOV %g1,%g1
        LD [%fp-8],%g1
        MOV %g1,%o0
        SET 8,%g1
        MOV %g1,%o1
        call Tree$Insert
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,12,%g1
        MOV %g1,%g1
        LD [%fp-8],%g1
        MOV %g1,%o0
        call Tree$Print
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,12,%g1
        MOV %g1,%g1
        LD [%fp-8],%g1
        MOV %g1,%o0
        SET 24,%g1
        MOV %g1,%o1
        call Tree$Insert
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,12,%g1
        MOV %g1,%g1
        LD [%fp-8],%g1
        MOV %g1,%o0
        SET 4,%g1
        MOV %g1,%o1
        call Tree$Insert
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,12,%g1
        MOV %g1,%g1
        LD [%fp-8],%g1
        MOV %g1,%o0
        SET 12,%g1
        MOV %g1,%o1
        call Tree$Insert
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,12,%g1
        MOV %g1,%g1
        LD [%fp-8],%g1
        MOV %g1,%o0
        SET 20,%g1
        MOV %g1,%o1
        call Tree$Insert
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,12,%g1
        MOV %g1,%g1
        LD [%fp-8],%g1
        MOV %g1,%o0
        SET 28,%g1
        MOV %g1,%o1
        call Tree$Insert
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,12,%g1
        MOV %g1,%g1
        LD [%fp-8],%g1
        MOV %g1,%o0
        SET 14,%g1
        MOV %g1,%o1
        call Tree$Insert
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,12,%g1
        MOV %g1,%g1
        LD [%fp-8],%g1
        MOV %g1,%o0
        call Tree$Print
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        LD [%fp-8],%g1
        MOV %g1,%o0
        SET 24,%g1
        MOV %g1,%o1
        call Tree$Search
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        LD [%fp-8],%g1
        MOV %g1,%o0
        SET 12,%g1
        MOV %g1,%o1
        call Tree$Search
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        LD [%fp-8],%g1
        MOV %g1,%o0
        SET 16,%g1
        MOV %g1,%o1
        call Tree$Search
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        LD [%fp-8],%g1
        MOV %g1,%o0
        SET 50,%g1
        MOV %g1,%o1
        call Tree$Search
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        LD [%fp-8],%g1
        MOV %g1,%o0
        SET 12,%g1
        MOV %g1,%o1
        call Tree$Search
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        SUB %fp,12,%g1
        MOV %g1,%g1
        LD [%fp-8],%g1
        MOV %g1,%o0
        SET 12,%g1
        MOV %g1,%o1
        call Tree$Delete
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,12,%g1
        MOV %g1,%g1
        LD [%fp-8],%g1
        MOV %g1,%o0
        call Tree$Print
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        LD [%fp-8],%g1
        MOV %g1,%o0
        SET 12,%g1
        MOV %g1,%o1
        call Tree$Search
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        MOV %g1,%o0
        call print_int
        nop
        MOV %o0, %g1
        SET 0,%g1
        MOV %g1,%i0
        BA BT$Start$epilogBegin
        nop
BT$Start$epilogBegin:
        ret
        restore
Tree$Init:
        .set LOCLS,0
        .set TEMPS,3
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Tree$Init$preludeEnd:
        ST %i1, [%i0+20]
        SET 0,%l0
        ST %l0, [%i0+16]
        SET 0,%l1
        ST %l1, [%i0+4]
        SET 0,%l2
        MOV %l2,%i0
        BA Tree$Init$epilogBegin
        nop
Tree$Init$epilogBegin:
        ret
        restore
Tree$SetRight:
        .set LOCLS,0
        .set TEMPS,1
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Tree$SetRight$preludeEnd:
        ST %i1, [%i0+12]
        SET 0,%l0
        MOV %l0,%i0
        BA Tree$SetRight$epilogBegin
        nop
Tree$SetRight$epilogBegin:
        ret
        restore
Tree$SetLeft:
        .set LOCLS,0
        .set TEMPS,1
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Tree$SetLeft$preludeEnd:
        ST %i1, [%i0+0]
        SET 0,%l0
        MOV %l0,%i0
        BA Tree$SetLeft$epilogBegin
        nop
Tree$SetLeft$epilogBegin:
        ret
        restore
Tree$GetRight:
        .set LOCLS,0
        .set TEMPS,1
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Tree$GetRight$preludeEnd:
        LD [%i0+12],%l0
        MOV %l0,%i0
        BA Tree$GetRight$epilogBegin
        nop
Tree$GetRight$epilogBegin:
        ret
        restore
Tree$GetLeft:
        .set LOCLS,0
        .set TEMPS,1
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Tree$GetLeft$preludeEnd:
        LD [%i0+0],%l0
        MOV %l0,%i0
        BA Tree$GetLeft$epilogBegin
        nop
Tree$GetLeft$epilogBegin:
        ret
        restore
Tree$GetKey:
        .set LOCLS,0
        .set TEMPS,1
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Tree$GetKey$preludeEnd:
        LD [%i0+20],%l0
        MOV %l0,%i0
        BA Tree$GetKey$epilogBegin
        nop
Tree$GetKey$epilogBegin:
        ret
        restore
Tree$SetKey:
        .set LOCLS,0
        .set TEMPS,1
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Tree$SetKey$preludeEnd:
        ST %i1, [%i0+20]
        SET 0,%l0
        MOV %l0,%i0
        BA Tree$SetKey$epilogBegin
        nop
Tree$SetKey$epilogBegin:
        ret
        restore
Tree$GetHas_Right:
        .set LOCLS,0
        .set TEMPS,1
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Tree$GetHas_Right$preludeEnd:
        LD [%i0+4],%l0
        MOV %l0,%i0
        BA Tree$GetHas_Right$epilogBegin
        nop
Tree$GetHas_Right$epilogBegin:
        ret
        restore
Tree$GetHas_Left:
        .set LOCLS,0
        .set TEMPS,1
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Tree$GetHas_Left$preludeEnd:
        LD [%i0+16],%l0
        MOV %l0,%i0
        BA Tree$GetHas_Left$epilogBegin
        nop
Tree$GetHas_Left$epilogBegin:
        ret
        restore
Tree$SetHas_Left:
        .set LOCLS,0
        .set TEMPS,1
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Tree$SetHas_Left$preludeEnd:
        ST %i1, [%i0+16]
        SET 0,%l0
        MOV %l0,%i0
        BA Tree$SetHas_Left$epilogBegin
        nop
Tree$SetHas_Left$epilogBegin:
        ret
        restore
Tree$SetHas_Right:
        .set LOCLS,0
        .set TEMPS,1
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Tree$SetHas_Right$preludeEnd:
        ST %i1, [%i0+4]
        SET 0,%l0
        MOV %l0,%i0
        BA Tree$SetHas_Right$epilogBegin
        nop
Tree$SetHas_Right$epilogBegin:
        ret
        restore
Tree$Compare:
        .set LOCLS,2
        .set TEMPS,11
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Tree$Compare$preludeEnd:
        SET 0,%l0
        ST %l0, [%fp-8]
        ADD %i2,1,%l1
        ST %l1, [%fp-4]
        CMP %i1,%i2
        BL if$then007
        nop
        BA if$else008
        nop
if$else008:
        LD [%fp-4],%l2
        CMP %i1,%l2
        BL cond$true001
        nop
        BA cond$false002
        nop
cond$false002:
        SET 0,%l3
        MOV %l3,%l4
        BA cond$end003
        nop
cond$end003:
        SET 0,%l5
        CMP %l4,%l5
        BE if$then004
        nop
        BA if$else005
        nop
if$else005:
        SET 0,%l6
        ST %l6, [%fp-8]
        BA if$end006
        nop
if$end006:
        BA if$end009
        nop
if$end009:
        LD [%fp-8],%l7
        MOV %l7,%i0
        BA Tree$Compare$epilogBegin
        nop
if$then007:
        SET 0,%g1
        ST %g1, [%fp-8]
        BA if$end009
        nop
cond$true001:
        SET 1,%g1
        MOV %g1,%l4
        BA cond$end003
        nop
if$then004:
        SET 0,%g1
        ST %g1, [%fp-8]
        BA if$end006
        nop
Tree$Compare$epilogBegin:
        ret
        restore
Tree$Insert:
        .set LOCLS,5
        .set TEMPS,66
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Tree$Insert$preludeEnd:
        SUB %fp,8,%l0
        MOV %l0,%l1
        SET 6,%l2
        MOV %l2,%o0
        call malloc_object
        nop
        MOV %o0, %l3
        MOV %l3,%l4
        ST %l4, [%l1]
        SUB %fp,16,%l5
        MOV %l5,%l6
        LD [%fp-8],%l7
        MOV %l7,%o0
        MOV %i1,%o1
        call Tree$Init
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%l6]
        ST %i0, [%fp-4]
        SET 0,%g1
        ST %g1, [%fp-12]
        LD [%fp-12],%g1
        SET 1,%g1
        CMP %g1,%g1
        BE while$do019
        nop
        BA while$end020
        nop
while$end020:
        SET 0,%g1
        MOV %g1,%i0
        BA Tree$Insert$epilogBegin
        nop
while$do019:
        SUB %fp,20,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        call Tree$GetKey
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        LD [%fp-20],%g1
        CMP %i1,%g1
        BL if$then016
        nop
        BA if$else017
        nop
if$else017:
        LD [%fp-4],%g1
        MOV %g1,%o0
        call Tree$GetHas_Right
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
        ST %g1, [%fp-12]
        SUB %fp,16,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        SET 0,%g1
        MOV %g1,%o1
        call Tree$SetHas_Right
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,16,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        LD [%fp-8],%g1
        MOV %g1,%o1
        call Tree$SetRight
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        BA if$end015
        nop
if$end015:
        BA if$end018
        nop
if$end018:
        LD [%fp-12],%g1
        SET 1,%g1
        CMP %g1,%g1
        BE while$do019
        nop
        BA trace$false080
        nop
trace$false080:
        BA while$end020
        nop
if$then016:
        LD [%fp-4],%g1
        MOV %g1,%o0
        call Tree$GetHas_Left
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
        ST %g1, [%fp-12]
        SUB %fp,16,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        SET 0,%g1
        MOV %g1,%o1
        call Tree$SetHas_Left
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,16,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        LD [%fp-8],%g1
        MOV %g1,%o1
        call Tree$SetLeft
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        BA if$end012
        nop
if$end012:
        BA if$end018
        nop
if$then010:
        SUB %fp,4,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        call Tree$GetLeft
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        BA if$end012
        nop
if$then013:
        SUB %fp,4,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        call Tree$GetRight
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        BA if$end015
        nop
Tree$Insert$epilogBegin:
        ret
        restore
Tree$Delete:
        .set LOCLS,7
        .set TEMPS,70
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Tree$Delete$preludeEnd:
        ST %i0, [%fp-4]
        ST %i0, [%fp-12]
        SET 0,%l0
        ST %l0, [%fp-16]
        SET 0,%l1
        ST %l1, [%fp-8]
        SET 0,%l2
        ST %l2, [%fp-20]
        LD [%fp-16],%l3
        SET 1,%l4
        CMP %l3,%l4
        BE while$do045
        nop
        BA while$end046
        nop
while$end046:
        LD [%fp-8],%l5
        MOV %l5,%i0
        BA Tree$Delete$epilogBegin
        nop
while$do045:
        SUB %fp,28,%l6
        MOV %l6,%l7
        LD [%fp-4],%g1
        MOV %g1,%o0
        call Tree$GetKey
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%l7]
        LD [%fp-28],%g1
        CMP %i1,%g1
        BL if$then042
        nop
        BA if$else043
        nop
if$else043:
        LD [%fp-28],%g1
        CMP %g1,%i1
        BL if$then039
        nop
        BA if$else040
        nop
if$else040:
        LD [%fp-20],%g1
        SET 1,%g1
        CMP %g1,%g1
        BE if$then036
        nop
        BA if$else037
        nop
if$else037:
        SUB %fp,24,%g1
        MOV %g1,%g1
        MOV %i0,%o0
        LD [%fp-12],%g1
        MOV %g1,%o1
        LD [%fp-4],%g1
        MOV %g1,%o2
        call Tree$Remove
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        BA if$end038
        nop
if$end038:
        SET 0,%g1
        ST %g1, [%fp-8]
        SET 0,%g1
        ST %g1, [%fp-16]
        BA if$end041
        nop
if$end041:
        BA if$end044
        nop
if$end044:
        SET 0,%g1
        ST %g1, [%fp-20]
        LD [%fp-16],%g1
        SET 1,%g1
        CMP %g1,%g1
        BE while$do045
        nop
        BA trace$false081
        nop
trace$false081:
        BA while$end046
        nop
if$then042:
        LD [%fp-4],%g1
        MOV %g1,%o0
        call Tree$GetHas_Left
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        SET 1,%g1
        CMP %g1,%g1
        BE if$then021
        nop
        BA if$else022
        nop
if$else022:
        SET 0,%g1
        ST %g1, [%fp-16]
        BA if$end023
        nop
if$end023:
        BA if$end044
        nop
if$then021:
        LD [%fp-4],%g1
        ST %g1, [%fp-12]
        SUB %fp,4,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        call Tree$GetLeft
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        BA if$end023
        nop
if$then039:
        LD [%fp-4],%g1
        MOV %g1,%o0
        call Tree$GetHas_Right
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        SET 1,%g1
        CMP %g1,%g1
        BE if$then024
        nop
        BA if$else025
        nop
if$else025:
        SET 0,%g1
        ST %g1, [%fp-16]
        BA if$end026
        nop
if$end026:
        BA if$end041
        nop
if$then024:
        LD [%fp-4],%g1
        ST %g1, [%fp-12]
        SUB %fp,4,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        call Tree$GetRight
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        BA if$end026
        nop
if$then036:
        LD [%fp-4],%g1
        MOV %g1,%o0
        call Tree$GetHas_Right
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        SET 0,%g1
        CMP %g1,%g1
        BE cond$true027
        nop
        BA cond$false028
        nop
cond$false028:
        SET 0,%g1
        MOV %g1,%g1
        BA cond$end029
        nop
cond$end029:
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        call Tree$GetHas_Left
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        SET 0,%g1
        CMP %g1,%g1
        BE cond$true030
        nop
        BA cond$false031
        nop
cond$false031:
        SET 0,%g1
        MOV %g1,%g1
        BA cond$end032
        nop
cond$end032:
        CMP %g1,%g1
        BE if$then033
        nop
        BA if$else034
        nop
if$else034:
        SUB %fp,24,%g1
        MOV %g1,%g1
        MOV %i0,%o0
        LD [%fp-12],%g1
        MOV %g1,%o1
        LD [%fp-4],%g1
        MOV %g1,%o2
        call Tree$Remove
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        BA if$end035
        nop
if$end035:
        BA if$end038
        nop
cond$true027:
        SET 1,%g1
        MOV %g1,%g1
        BA cond$end029
        nop
cond$true030:
        SET 1,%g1
        MOV %g1,%g1
        BA cond$end032
        nop
if$then033:
        SET 0,%g1
        ST %g1, [%fp-24]
        BA if$end035
        nop
Tree$Delete$epilogBegin:
        ret
        restore
Tree$Remove:
        .set LOCLS,3
        .set TEMPS,50
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Tree$Remove$preludeEnd:
        MOV %i1,%o0
        call Tree$GetHas_Left
        nop
        MOV %o0, %l0
        MOV %l0,%l1
        SET 1,%l2
        CMP %l1,%l2
        BE if$then053
        nop
        BA if$else054
        nop
if$else054:
        MOV %i1,%o0
        call Tree$GetHas_Right
        nop
        MOV %o0, %l3
        MOV %l3,%l4
        SET 1,%l5
        CMP %l4,%l5
        BE if$then050
        nop
        BA if$else051
        nop
if$else051:
        SUB %fp,4,%l6
        MOV %l6,%l7
        MOV %i1,%o0
        call Tree$GetKey
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%l7]
        SUB %fp,8,%g1
        MOV %g1,%g1
        MOV %i2,%o0
        call Tree$GetLeft
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        MOV %g1,%o0
        call Tree$GetKey
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        MOV %i0,%o0
        LD [%fp-4],%g1
        MOV %g1,%o1
        LD [%fp-8],%g1
        MOV %g1,%o2
        call Tree$Compare
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        SET 1,%g1
        CMP %g1,%g1
        BE if$then047
        nop
        BA if$else048
        nop
if$else048:
        SUB %fp,12,%g1
        MOV %g1,%g1
        MOV %i2,%o0
        LD [%i0+8],%g1
        MOV %g1,%o1
        call Tree$SetRight
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,12,%g1
        MOV %g1,%g1
        MOV %i2,%o0
        SET 0,%g1
        MOV %g1,%o1
        call Tree$SetHas_Right
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        BA if$end049
        nop
if$end049:
        BA if$end052
        nop
if$end052:
        BA if$end055
        nop
if$end055:
        SET 0,%g1
        MOV %g1,%i0
        BA Tree$Remove$epilogBegin
        nop
if$then053:
        SUB %fp,12,%g1
        MOV %g1,%g1
        MOV %i0,%o0
        MOV %i2,%o1
        MOV %i1,%o2
        call Tree$RemoveLeft
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        BA if$end055
        nop
if$then050:
        SUB %fp,12,%g1
        MOV %g1,%g1
        MOV %i0,%o0
        MOV %i2,%o1
        MOV %i1,%o2
        call Tree$RemoveRight
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        BA if$end052
        nop
if$then047:
        SUB %fp,12,%g1
        MOV %g1,%g1
        MOV %i2,%o0
        LD [%i0+8],%g1
        MOV %g1,%o1
        call Tree$SetLeft
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SUB %fp,12,%g1
        MOV %g1,%g1
        MOV %i2,%o0
        SET 0,%g1
        MOV %g1,%o1
        call Tree$SetHas_Left
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        BA if$end049
        nop
Tree$Remove$epilogBegin:
        ret
        restore
Tree$RemoveRight:
        .set LOCLS,1
        .set TEMPS,27
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Tree$RemoveRight$preludeEnd:
        MOV %i1,%o0
        call Tree$GetHas_Right
        nop
        MOV %o0, %l0
        MOV %l0,%l1
        SET 1,%l2
        CMP %l1,%l2
        BE while$do056
        nop
        BA while$end057
        nop
while$end057:
        SUB %fp,4,%l3
        MOV %l3,%l4
        MOV %i2,%o0
        LD [%i0+8],%l5
        MOV %l5,%o1
        call Tree$SetRight
        nop
        MOV %o0, %l6
        MOV %l6,%l7
        ST %l7, [%l4]
        SUB %fp,4,%g1
        MOV %g1,%g1
        MOV %i2,%o0
        SET 0,%g1
        MOV %g1,%o1
        call Tree$SetHas_Right
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SET 0,%g1
        MOV %g1,%i0
        BA Tree$RemoveRight$epilogBegin
        nop
while$do056:
        SUB %fp,4,%g1
        MOV %g1,%g1
        MOV %i1,%g1
        MOV %i1,%o0
        call Tree$GetRight
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        MOV %g1,%o0
        call Tree$GetKey
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        MOV %g1,%o0
        MOV %g1,%o1
        call Tree$SetKey
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        MOV %i1,%i2
        MOV %i1,%o0
        call Tree$GetRight
        nop
        MOV %o0, %g1
        MOV %g1,%i1
        MOV %i1,%o0
        call Tree$GetHas_Right
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        SET 1,%g1
        CMP %g1,%g1
        BE while$do056
        nop
        BA trace$false082
        nop
trace$false082:
        BA while$end057
        nop
Tree$RemoveRight$epilogBegin:
        ret
        restore
Tree$RemoveLeft:
        .set LOCLS,1
        .set TEMPS,27
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Tree$RemoveLeft$preludeEnd:
        MOV %i1,%o0
        call Tree$GetHas_Left
        nop
        MOV %o0, %l0
        MOV %l0,%l1
        SET 1,%l2
        CMP %l1,%l2
        BE while$do058
        nop
        BA while$end059
        nop
while$end059:
        SUB %fp,4,%l3
        MOV %l3,%l4
        MOV %i2,%o0
        LD [%i0+8],%l5
        MOV %l5,%o1
        call Tree$SetLeft
        nop
        MOV %o0, %l6
        MOV %l6,%l7
        ST %l7, [%l4]
        SUB %fp,4,%g1
        MOV %g1,%g1
        MOV %i2,%o0
        SET 0,%g1
        MOV %g1,%o1
        call Tree$SetHas_Left
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        SET 0,%g1
        MOV %g1,%i0
        BA Tree$RemoveLeft$epilogBegin
        nop
while$do058:
        SUB %fp,4,%g1
        MOV %g1,%g1
        MOV %i1,%g1
        MOV %i1,%o0
        call Tree$GetLeft
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        MOV %g1,%o0
        call Tree$GetKey
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        MOV %g1,%o0
        MOV %g1,%o1
        call Tree$SetKey
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        MOV %i1,%i2
        MOV %i1,%o0
        call Tree$GetLeft
        nop
        MOV %o0, %g1
        MOV %g1,%i1
        MOV %i1,%o0
        call Tree$GetHas_Left
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        SET 1,%g1
        CMP %g1,%g1
        BE while$do058
        nop
        BA trace$false083
        nop
trace$false083:
        BA while$end059
        nop
Tree$RemoveLeft$epilogBegin:
        ret
        restore
Tree$Search:
        .set LOCLS,4
        .set TEMPS,36
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Tree$Search$preludeEnd:
        ST %i0, [%fp-4]
        SET 0,%l0
        ST %l0, [%fp-12]
        SET 0,%l1
        ST %l1, [%fp-8]
        LD [%fp-12],%l2
        SET 1,%l3
        CMP %l2,%l3
        BE while$do072
        nop
        BA while$end073
        nop
while$end073:
        LD [%fp-8],%l4
        MOV %l4,%i0
        BA Tree$Search$epilogBegin
        nop
while$do072:
        SUB %fp,16,%l5
        MOV %l5,%l6
        LD [%fp-4],%l7
        MOV %l7,%o0
        call Tree$GetKey
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%l6]
        LD [%fp-16],%g1
        CMP %i1,%g1
        BL if$then069
        nop
        BA if$else070
        nop
if$else070:
        LD [%fp-16],%g1
        CMP %g1,%i1
        BL if$then066
        nop
        BA if$else067
        nop
if$else067:
        SET 1,%g1
        ST %g1, [%fp-8]
        SET 0,%g1
        ST %g1, [%fp-12]
        BA if$end068
        nop
if$end068:
        BA if$end071
        nop
if$end071:
        LD [%fp-12],%g1
        SET 1,%g1
        CMP %g1,%g1
        BE while$do072
        nop
        BA trace$false084
        nop
trace$false084:
        BA while$end073
        nop
if$then069:
        LD [%fp-4],%g1
        MOV %g1,%o0
        call Tree$GetHas_Left
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        SET 1,%g1
        CMP %g1,%g1
        BE if$then060
        nop
        BA if$else061
        nop
if$else061:
        SET 0,%g1
        ST %g1, [%fp-12]
        BA if$end062
        nop
if$end062:
        BA if$end071
        nop
if$then060:
        SUB %fp,4,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        call Tree$GetLeft
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        BA if$end062
        nop
if$then066:
        LD [%fp-4],%g1
        MOV %g1,%o0
        call Tree$GetHas_Right
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        SET 1,%g1
        CMP %g1,%g1
        BE if$then063
        nop
        BA if$else064
        nop
if$else064:
        SET 0,%g1
        ST %g1, [%fp-12]
        BA if$end065
        nop
if$end065:
        BA if$end068
        nop
if$then063:
        SUB %fp,4,%g1
        MOV %g1,%g1
        LD [%fp-4],%g1
        MOV %g1,%o0
        call Tree$GetRight
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        BA if$end065
        nop
Tree$Search$epilogBegin:
        ret
        restore
Tree$Print:
        .set LOCLS,2
        .set TEMPS,6
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Tree$Print$preludeEnd:
        ST %i0, [%fp-4]
        SUB %fp,8,%l0
        MOV %l0,%l1
        MOV %i0,%o0
        LD [%fp-4],%l2
        MOV %l2,%o1
        call Tree$RecPrint
        nop
        MOV %o0, %l3
        MOV %l3,%l4
        ST %l4, [%l1]
        SET 0,%l5
        MOV %l5,%i0
        BA Tree$Print$epilogBegin
        nop
Tree$Print$epilogBegin:
        ret
        restore
Tree$RecPrint:
        .set LOCLS,1
        .set TEMPS,26
        .set ARGSB,0
        save %sp,-4*(LOCLS+TEMPS+ARGSB+1+16)&-8,%sp
Tree$RecPrint$preludeEnd:
        MOV %i1,%o0
        call Tree$GetHas_Left
        nop
        MOV %o0, %l0
        MOV %l0,%l1
        SET 1,%l2
        CMP %l1,%l2
        BE if$then074
        nop
        BA if$else075
        nop
if$else075:
        SET 0,%l3
        ST %l3, [%fp-4]
        BA if$end076
        nop
if$end076:
        MOV %i1,%o0
        call Tree$GetKey
        nop
        MOV %o0, %l4
        MOV %l4,%l5
        MOV %l5,%o0
        call print_int
        nop
        MOV %o0, %l6
        MOV %i1,%o0
        call Tree$GetHas_Right
        nop
        MOV %o0, %l7
        MOV %l7,%g1
        SET 1,%g1
        CMP %g1,%g1
        BE if$then077
        nop
        BA if$else078
        nop
if$else078:
        SET 0,%g1
        ST %g1, [%fp-4]
        BA if$end079
        nop
if$end079:
        SET 0,%g1
        MOV %g1,%i0
        BA Tree$RecPrint$epilogBegin
        nop
if$then074:
        SUB %fp,4,%g1
        MOV %g1,%g1
        MOV %i0,%g1
        MOV %i1,%o0
        call Tree$GetLeft
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        MOV %g1,%o0
        MOV %g1,%o1
        call Tree$RecPrint
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        BA if$end076
        nop
if$then077:
        SUB %fp,4,%g1
        MOV %g1,%g1
        MOV %i0,%g1
        MOV %i1,%o0
        call Tree$GetRight
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        MOV %g1,%o0
        MOV %g1,%o1
        call Tree$RecPrint
        nop
        MOV %o0, %g1
        MOV %g1,%g1
        ST %g1, [%g1]
        BA if$end079
        nop
Tree$RecPrint$epilogBegin:
        ret
        restore
