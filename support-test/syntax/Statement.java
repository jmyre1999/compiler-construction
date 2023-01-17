/*
 * Florida Tech, CSE4251: Compiler Design.  Part of the compiler project from
 * "Modern Compiler Implementation in Java," 2nd edition, by Andrew W. Appel.
 */
package syntax;

public abstract class Statement extends AST {

   protected Statement () { this (0,0); }
   public Statement (int l, int c) { super (l,c); }
}

