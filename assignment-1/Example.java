class Example {
   // Stm :: a:=5+3; b := (print (a, a-1), 10*a); print(b)
   // Out ::
   //        8 7
   //        80
   static Stm a_program = 
      new CompoundStm(new AssignStm("a",new OpExp(new NumExp(5), OpExp.Plus, new NumExp(3))),
        new CompoundStm(new AssignStm("b",
           new EseqExp(new PrintStm(new PairExpList(new IdExp("a"),
              new LastExpList(new OpExp(new IdExp("a"), OpExp.Minus, new NumExp(1))))),
                 new OpExp(new NumExp(10), OpExp.Times, new IdExp("a")))),
                    new PrintStm(new LastExpList(new IdExp("b")))));

   // Stm :: print(1, 2, (print(3, 4, (a := (print(5, 6, 7, 8), 9), 10)), 11));
   // Out ::
   //        5 6 7 8
   //        3 4 10
   //        1 2 11
   static Stm another_program =  new PrintStm(new PairExpList(new NumExp(1), new PairExpList(new NumExp(2), new LastExpList(new EseqExp(new PrintStm(new PairExpList(new NumExp(3), new PairExpList(new NumExp(4), new LastExpList(new EseqExp(new AssignStm("a", new EseqExp(new PrintStm(new PairExpList(new NumExp(5), new PairExpList(new NumExp(6), new PairExpList(new NumExp(7), new LastExpList(new NumExp(8)))))), new NumExp(9))), new NumExp(10)))))), new NumExp(11)))))); 
}