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
 * All void visitors must implement this interface.
 */

public interface Visitor {

   //
   // void Auto class visitors
   //

   public void visit(NodeList n);
   public void visit(NodeListOptional n);
   public void visit(NodeOptional n);
   public void visit(NodeSequence n);
   public void visit(NodeToken n);

   //
   // User-generated visitor methods below
   //

   /**
    * f0 -> [ Label() ]
    * f1 -> Instruction()
    * f2 -> <SEMICOLON>
    */
   public void visit(Goal n);

   /**
    * f0 -> <LABEL_COLON>
    */
   public void visit(Label n);

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
   public void visit(Instruction n);

   /**
    * f0 -> Op3Regs()
    * f1 -> Register()
    * f2 -> <COMMA>
    * f3 -> Register()
    * f4 -> <COMMA>
    * f5 -> Register()
    */
   public void visit(Instr3Regs n);

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
   public void visit(Op3Regs n);

   /**
    * f0 -> Op2RegsImm()
    * f1 -> Register()
    * f2 -> <COMMA>
    * f3 -> Register()
    * f4 -> <COMMA>
    * f5 -> Immed()
    */
   public void visit(Instr2RegImm n);

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
   public void visit(Op2RegsImm n);

   /**
    * f0 -> OpLoad()
    * f1 -> Register()
    * f2 -> <COMMA>
    * f3 -> Immed()
    * f4 -> <OPEN_BRACKET>
    * f5 -> Register()
    * f6 -> <CLOSE_BRACKET>
    */
   public void visit(InstrLoad n);

   /**
    * f0 -> "LB"
    *       | "LBU"
    *       | "LH"
    *       | "LHU"
    *       | "LW"
    */
   public void visit(OpLoad n);

   /**
    * f0 -> OpStore()
    * f1 -> Immed()
    * f2 -> <OPEN_BRACKET>
    * f3 -> Register()
    * f4 -> <CLOSE_BRACKET>
    * f5 -> <COMMA>
    * f6 -> Register()
    */
   public void visit(InstrStore n);

   /**
    * f0 -> "SB"
    *       | "SH"
    *       | "SW"
    */
   public void visit(OpStore n);

   /**
    * f0 -> OpBranch()
    * f1 -> Register()
    * f2 -> <COMMA>
    * f3 -> <LABELJ>
    */
   public void visit(InstrBranch n);

   /**
    * f0 -> "BEQZ"
    *       | "BNEZ"
    */
   public void visit(OpBranch n);

   /**
    * f0 -> OpJumpReg()
    * f1 -> Register()
    */
   public void visit(InstrJumpReg n);

   /**
    * f0 -> "JR"
    *       | "JALR"
    */
   public void visit(OpJumpReg n);

   /**
    * f0 -> OpJumpLab()
    * f1 -> <LABELJ>
    */
   public void visit(InstrJumpLab n);

   /**
    * f0 -> "J"
    *       | "JAL"
    */
   public void visit(OpJumpLab n);

   /**
    * f0 -> "NOP"
    */
   public void visit(Nop n);

   /**
    * f0 -> <NUMBER>
    */
   public void visit(Immed n);

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
   public void visit(Register n);

}
