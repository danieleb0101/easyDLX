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
public class GJNoArguDepthFirst<R> implements GJNoArguVisitor<R> {
   //
   // Auto class visitors--probably don't need to be overridden.
   //
   public R visit(NodeList n) {
      R _ret=null;
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this);
         _count++;
      }
      return _ret;
   }

   public R visit(NodeListOptional n) {
      if ( n.present() ) {
         R _ret=null;
         int _count=0;
         for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
            e.nextElement().accept(this);
            _count++;
         }
         return _ret;
      }
      else
         return null;
   }

   public R visit(NodeOptional n) {
      if ( n.present() )
         return n.node.accept(this);
      else
         return null;
   }

   public R visit(NodeSequence n) {
      R _ret=null;
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this);
         _count++;
      }
      return _ret;
   }

   public R visit(NodeToken n) { return null; }

   //
   // User-generated visitor methods below
   //

   /**
    * f0 -> [ Label() ]
    * f1 -> Instruction()
    * f2 -> <SEMICOLON>
    */
   public R visit(Goal n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      return _ret;
   }

   /**
    * f0 -> <LABEL_COLON>
    */
   public R visit(Label n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
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
   public R visit(Instruction n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * f0 -> Op3Regs()
    * f1 -> Register()
    * f2 -> <COMMA>
    * f3 -> Register()
    * f4 -> <COMMA>
    * f5 -> Register()
    */
   public R visit(Instr3Regs n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      n.f4.accept(this);
      n.f5.accept(this);
      return _ret;
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
   public R visit(Op3Regs n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * f0 -> Op2RegsImm()
    * f1 -> Register()
    * f2 -> <COMMA>
    * f3 -> Register()
    * f4 -> <COMMA>
    * f5 -> Immed()
    */
   public R visit(Instr2RegImm n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      n.f4.accept(this);
      n.f5.accept(this);
      return _ret;
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
   public R visit(Op2RegsImm n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
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
   public R visit(InstrLoad n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      n.f4.accept(this);
      n.f5.accept(this);
      n.f6.accept(this);
      return _ret;
   }

   /**
    * f0 -> "LB"
    *       | "LBU"
    *       | "LH"
    *       | "LHU"
    *       | "LW"
    */
   public R visit(OpLoad n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
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
   public R visit(InstrStore n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      n.f4.accept(this);
      n.f5.accept(this);
      n.f6.accept(this);
      return _ret;
   }

   /**
    * f0 -> "SB"
    *       | "SH"
    *       | "SW"
    */
   public R visit(OpStore n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * f0 -> OpBranch()
    * f1 -> Register()
    * f2 -> <COMMA>
    * f3 -> <LABELJ>
    */
   public R visit(InstrBranch n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      return _ret;
   }

   /**
    * f0 -> "BEQZ"
    *       | "BNEZ"
    */
   public R visit(OpBranch n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * f0 -> OpJumpReg()
    * f1 -> Register()
    */
   public R visit(InstrJumpReg n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * f0 -> "JR"
    *       | "JALR"
    */
   public R visit(OpJumpReg n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * f0 -> OpJumpLab()
    * f1 -> <LABELJ>
    */
   public R visit(InstrJumpLab n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * f0 -> "J"
    *       | "JAL"
    */
   public R visit(OpJumpLab n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * f0 -> "NOP"
    */
   public R visit(Nop n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * f0 -> <NUMBER>
    */
   public R visit(Immed n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
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
   public R visit(Register n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

}
