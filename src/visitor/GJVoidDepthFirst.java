/**
 Copyright (c) 2011 Daniele Biagi
Permission is hereby granted, free of charge, to any person obtaining a copy of this 
software and associated documentation files (the "Software"), to deal in the Software 
without restriction, including without limitation the rights to use, copy, modify, 
merge, publish, distribute, sublicense, and/or sell copies of the Software, and to 
permit persons to whom the Software is furnished to do so, subject to the following 
conditions:

The above copyright notice and this permission notice shall be included in all copies
or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE 
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, 
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE 
OR OTHER DEALINGS IN THE SOFTWARE.
 */

// Generated by JTB 1.3.2
//

package visitor;
import syntaxtree.*;
import java.util.*;

/**
 * Provides default methods which visit each node in the tree in depth-first
 * order.  Your visitors may extend this class.
 */
public class GJVoidDepthFirst<A> implements GJVoidVisitor<A> {
   //
   // Auto class visitors--probably don't need to be overridden.
   //
   public void visit(NodeList n, A argu) {
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this,argu);
         _count++;
      }
   }

   public void visit(NodeListOptional n, A argu) {
      if ( n.present() ) {
         int _count=0;
         for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
            e.nextElement().accept(this,argu);
            _count++;
         }
      }
   }

   public void visit(NodeOptional n, A argu) {
      if ( n.present() )
         n.node.accept(this,argu);
   }

   public void visit(NodeSequence n, A argu) {
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this,argu);
         _count++;
      }
   }

   public void visit(NodeToken n, A argu) {}

   //
   // User-generated visitor methods below
   //

   /**
    * f0 -> [ Label() ]
    * f1 -> Instruction()
    * f2 -> <SEMICOLON>
    */
   public void visit(Goal n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> <LABEL_COLON>
    */
   public void visit(Label n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> Instr3Regs()
    *       | Instr2RegImm()
    *       | InstrLoad()
    *       | InstrStore()
    *       | InstrBranch()
    *       | InstrJumpReg()
    *       | InstrJumpLab()
    *       | Nop()
    */
   public void visit(Instruction n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> Op3Regs()
    * f1 -> Register()
    * f2 -> <COMMA>
    * f3 -> Register()
    * f4 -> <COMMA>
    * f5 -> Register()
    */
   public void visit(Instr3Regs n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
   }

   /**
    * f0 -> "ADD"
    *       | "ADDU"
    *       | "SUB"
    *       | "SUBU"
    *       | "MULT"
    *       | "MULTU"
    *       | "DIV"
    *       | "DIVU"
    *       | "AND"
    *       | "OR"
    *       | "XOR"
    *       | "SLL"
    *       | "SRL"
    *       | "SLT"
    *       | "SGT"
    *       | "SLE"
    *       | "SGE"
    *       | "SEQ"
    *       | "SNE"
    */
   public void visit(Op3Regs n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> Op2RegsImm()
    * f1 -> Register()
    * f2 -> <COMMA>
    * f3 -> Register()
    * f4 -> <COMMA>
    * f5 -> Immed()
    */
   public void visit(Instr2RegImm n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
   }

   /**
    * f0 -> "ADDI"
    *       | "ADDUI"
    *       | "SUBI"
    *       | "SUBUI"
    *       | "ANDI"
    *       | "ORI"
    *       | "XORI"
    *       | "SLLI"
    *       | "SRLI"
    *       | "SLTI"
    *       | "SGTI"
    *       | "SLEI"
    *       | "SGEI"
    *       | "SEQI"
    *       | "SNEI"
    */
   public void visit(Op2RegsImm n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> OpLoad()
    * f1 -> Register()
    * f2 -> <COMMA>
    * f3 -> Immed()
    * f4 -> <OPEN_BRACKET>
    * f5 -> Register()
    * f6 -> <CLOSE_BRACKET>
    */
   public void visit(InstrLoad n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
   }

   /**
    * f0 -> "LB"
    *       | "LBU"
    *       | "LH"
    *       | "LHU"
    *       | "LW"
    */
   public void visit(OpLoad n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> OpStore()
    * f1 -> Immed()
    * f2 -> <OPEN_BRACKET>
    * f3 -> Register()
    * f4 -> <CLOSE_BRACKET>
    * f5 -> <COMMA>
    * f6 -> Register()
    */
   public void visit(InstrStore n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
   }

   /**
    * f0 -> "SB"
    *       | "SH"
    *       | "SW"
    */
   public void visit(OpStore n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> OpBranch()
    * f1 -> Register()
    * f2 -> <COMMA>
    * f3 -> <LABELJ>
    */
   public void visit(InstrBranch n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
   }

   /**
    * f0 -> "BEQZ"
    *       | "BNEZ"
    */
   public void visit(OpBranch n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> OpJumpReg()
    * f1 -> Register()
    */
   public void visit(InstrJumpReg n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> "JR"
    *       | "JALR"
    */
   public void visit(OpJumpReg n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> OpJumpLab()
    * f1 -> <LABELJ>
    */
   public void visit(InstrJumpLab n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> "J"
    *       | "JAL"
    */
   public void visit(OpJumpLab n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> "NOP"
    */
   public void visit(Nop n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> <NUMBER>
    */
   public void visit(Immed n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> "R0"
    *       | "R1"
    *       | "R2"
    *       | "R3"
    *       | "R4"
    *       | "R5"
    *       | "R6"
    *       | "R7"
    *       | "R8"
    *       | "R9"
    *       | "R10"
    *       | "R11"
    *       | "R12"
    *       | "R13"
    *       | "R14"
    *       | "R15"
    *       | "R16"
    *       | "R17"
    *       | "R18"
    *       | "R19"
    *       | "R20"
    *       | "R21"
    *       | "R22"
    *       | "R23"
    *       | "R24"
    *       | "R25"
    *       | "R26"
    *       | "R27"
    *       | "R28"
    *       | "R29"
    *       | "R30"
    *       | "R31"
    */
   public void visit(Register n, A argu) {
      n.f0.accept(this, argu);
   }

}
