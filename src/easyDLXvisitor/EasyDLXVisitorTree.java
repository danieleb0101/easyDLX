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
package easyDLXvisitor;

import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import syntaxtree.Goal;
import syntaxtree.Immed;
import syntaxtree.Instr2RegImm;
import syntaxtree.Instr3Regs;
import syntaxtree.InstrBranch;
import syntaxtree.InstrJumpLab;
import syntaxtree.InstrJumpReg;
import syntaxtree.InstrLoad;
import syntaxtree.InstrStore;
import syntaxtree.Instruction;
import syntaxtree.Label;
import syntaxtree.Node;
import syntaxtree.NodeList;
import syntaxtree.NodeListOptional;
import syntaxtree.NodeOptional;
import syntaxtree.NodeSequence;
import syntaxtree.NodeToken;
import syntaxtree.Nop;
import syntaxtree.Op2RegsImm;
import syntaxtree.Op3Regs;
import syntaxtree.OpBranch;
import syntaxtree.OpJumpLab;
import syntaxtree.OpJumpReg;
import syntaxtree.OpLoad;
import syntaxtree.OpStore;
import syntaxtree.Register;
import visitor.DepthFirstVisitor;

/**
 * 
 * @author Daniele Biagi  biagi.daniele@gmail.com
 *
 */

public class EasyDLXVisitorTree extends DepthFirstVisitor {

	private DefaultMutableTreeNode parentNode;

	public void visit(NodeList n) {
		for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); )
			e.nextElement().accept(this);
	}

	public void visit(NodeListOptional n) {
		if ( n.present() )
			for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); )
				e.nextElement().accept(this);
	}

	public void visit(NodeOptional n) {
		if ( n.present() )
			n.node.accept(this);
	}

	public void visit(NodeSequence n) {
		for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); )
			e.nextElement().accept(this);
	}

	public void visit(NodeToken n) { }


	public DefaultMutableTreeNode getTree() {
		return parentNode;
	}

	public TreeNode getTreeNode() {
		return this.parentNode.getRoot();
	}

	//
	// User-generated visitor methods below
	//

	/**
	 * f0 -> [ Label() ]
	 * f1 -> Instruction()
	 * f2 -> <SEMICOLON>
	 */
	public void visit(Goal n) {

		parentNode = new DefaultMutableTreeNode("Goal");
		@SuppressWarnings("unused")
		DefaultMutableTreeNode myParent = parentNode;

		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
	}

	/**
	 * f0 -> <LABEL_COLON>
	 */
	public void visit(Label n) {

		DefaultMutableTreeNode myParent = parentNode;
		DefaultMutableTreeNode labelNode = new DefaultMutableTreeNode("Label");
		parentNode = labelNode;
		myParent.add(labelNode);

		n.f0.accept(this);

		parentNode.add(new DefaultMutableTreeNode(n.f0.tokenImage.toString()));
		parentNode = myParent;
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
	public void visit(Instruction n) {
		DefaultMutableTreeNode myParent = parentNode;
		DefaultMutableTreeNode instructionNode = new DefaultMutableTreeNode("Instruction");
		parentNode = instructionNode;
		myParent.add(instructionNode);

		n.f0.accept(this);

		parentNode = myParent;

	}

	/**
	 * f0 -> Op3Regs()
	 * f1 -> Register()
	 * f2 -> <COMMA>
	 * f3 -> Register()
	 * f4 -> <COMMA>
	 * f5 -> Register()
	 */
	public void visit(Instr3Regs n) {

		DefaultMutableTreeNode myParent = parentNode;
		DefaultMutableTreeNode Instr3RegsNode = new DefaultMutableTreeNode("Instr3Regs");
		parentNode = Instr3RegsNode;
		myParent.add(Instr3RegsNode);


		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		n.f5.accept(this);
		parentNode = myParent;
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
	public void visit(Op3Regs n) {
		DefaultMutableTreeNode myParent = parentNode;
		DefaultMutableTreeNode op3RegsNode = new DefaultMutableTreeNode("Op3Regs");
		parentNode = op3RegsNode;
		myParent.add(op3RegsNode);

		n.f0.accept(this);

		parentNode.add(new DefaultMutableTreeNode(n.f0.choice.toString()));
		parentNode = myParent;
	}

	/**
	 * f0 -> Op2RegsImm()
	 * f1 -> Register()
	 * f2 -> <COMMA>
	 * f3 -> Register()
	 * f4 -> <COMMA>
	 * f5 -> Immed()
	 */
	public void visit(Instr2RegImm n) {

		DefaultMutableTreeNode myParent = parentNode;
		DefaultMutableTreeNode instr2RegsNode = new DefaultMutableTreeNode("Instr2RegImm");
		parentNode = instr2RegsNode;
		myParent.add(instr2RegsNode);

		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		n.f5.accept(this);

		parentNode = myParent;
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
	public void visit(Op2RegsImm n) {
		
		DefaultMutableTreeNode myParent = parentNode;
		DefaultMutableTreeNode op2RegsImmNode = new DefaultMutableTreeNode("Op2RegsImm");
		parentNode = op2RegsImmNode;
		myParent.add(op2RegsImmNode);
		
		n.f0.accept(this);
		parentNode.add(new DefaultMutableTreeNode(n.f0.choice.toString()));
		parentNode = myParent;
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
	public void visit(InstrLoad n) {
		
		DefaultMutableTreeNode myParent = parentNode;
		DefaultMutableTreeNode instrLoadNode = new DefaultMutableTreeNode("InstrLoad");
		parentNode = instrLoadNode;
		myParent.add(instrLoadNode);
		
		
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		n.f5.accept(this);
		n.f6.accept(this);
		
		parentNode = myParent;
	}

	/**
	 * f0 -> "LB"
	 *       | "LBU"
	 *       | "LH"
	 *       | "LHU"
	 *       | "LW"
	 */
	public void visit(OpLoad n) {
		
		DefaultMutableTreeNode myParent = parentNode;
		DefaultMutableTreeNode opLoadImmNode = new DefaultMutableTreeNode("OpLoad");
		parentNode = opLoadImmNode;
		myParent.add(opLoadImmNode);
		
		n.f0.accept(this);
		
		parentNode.add(new DefaultMutableTreeNode(n.f0.choice.toString()));
		parentNode = myParent;
		
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
	public void visit(InstrStore n) {
		
		DefaultMutableTreeNode myParent = parentNode;
		DefaultMutableTreeNode instrStoreNode = new DefaultMutableTreeNode("InstrStore");
		parentNode = instrStoreNode;
		myParent.add(instrStoreNode);
		
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		n.f5.accept(this);
		n.f6.accept(this);
		
		parentNode = myParent;
		
	}

	/**
	 * f0 -> "SB"
	 *       | "SH"
	 *       | "SW"
	 */
	public void visit(OpStore n) {
		
		DefaultMutableTreeNode myParent = parentNode;
		DefaultMutableTreeNode opStoreNode = new DefaultMutableTreeNode("OpStore");
		parentNode = opStoreNode;
		myParent.add(opStoreNode);
		
		n.f0.accept(this);
		
		parentNode.add(new DefaultMutableTreeNode(n.f0.choice.toString()));
		parentNode = myParent;
	}

	/**
	 * f0 -> OpBranch()
	 * f1 -> Register()
	 * f2 -> <COMMA>
	 * f3 -> <LABELJ>
	 */
	public void visit(InstrBranch n) {
		
		DefaultMutableTreeNode myParent = parentNode;
		DefaultMutableTreeNode instrBranchNode = new DefaultMutableTreeNode("InstrBranch");
		parentNode = instrBranchNode;
		myParent.add(instrBranchNode);
		
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		
		parentNode.add(new DefaultMutableTreeNode(n.f3.tokenImage.toString()));
		parentNode = myParent;
	}

	/**
	 * f0 -> "BEQZ"
	 *       | "BNEZ"
	 */
	public void visit(OpBranch n) {
		
		DefaultMutableTreeNode myParent = parentNode;
		DefaultMutableTreeNode opBranchNode = new DefaultMutableTreeNode("OpBranch");
		parentNode = opBranchNode;
		myParent.add(opBranchNode);
		
		n.f0.accept(this);
		
		parentNode.add(new DefaultMutableTreeNode(n.f0.choice.toString()));
		parentNode = myParent;
	}

	/**
	 * f0 -> OpJumpReg()
	 * f1 -> Register()
	 */
	public void visit(InstrJumpReg n) {
		
		DefaultMutableTreeNode myParent = parentNode;
		DefaultMutableTreeNode instrJumpRegNode = new DefaultMutableTreeNode("InstrJumpReg");
		parentNode = instrJumpRegNode;
		myParent.add(instrJumpRegNode);
		
		n.f0.accept(this);
		n.f1.accept(this);
		
		parentNode = myParent;
	}

	/**
	 * f0 -> "JR"
	 *       | "JALR"
	 */
	public void visit(OpJumpReg n) {
		DefaultMutableTreeNode myParent = parentNode;
		DefaultMutableTreeNode opJumpRegNode = new DefaultMutableTreeNode("OpJumpReg");
		parentNode = opJumpRegNode;
		myParent.add(opJumpRegNode);
		
		n.f0.accept(this);
		
		parentNode.add(new DefaultMutableTreeNode(n.f0.choice.toString()));
		parentNode = myParent;
		
	}

	/**
	 * f0 -> OpJumpLab()
	 * f1 -> <LABELJ>
	 */
	public void visit(InstrJumpLab n) {
		
		DefaultMutableTreeNode myParent = parentNode;
		DefaultMutableTreeNode instrJumpLabNode = new DefaultMutableTreeNode("InstrJumpLab");
		parentNode = instrJumpLabNode;
		myParent.add(instrJumpLabNode);
		
		n.f0.accept(this);
		n.f1.accept(this);
		
		parentNode.add(new DefaultMutableTreeNode(n.f1.tokenImage.toString()));
		parentNode = myParent;
	}

	/**
	 * f0 -> "J"
	 *       | "JAL"
	 */
	public void visit(OpJumpLab n) {
		
		DefaultMutableTreeNode myParent = parentNode;
		DefaultMutableTreeNode opJumpLabNode = new DefaultMutableTreeNode("OpJumpLab");
		parentNode = opJumpLabNode;
		myParent.add(opJumpLabNode);
		
		n.f0.accept(this);
		
		parentNode.add(new DefaultMutableTreeNode(n.f0.choice.toString()));
		parentNode = myParent;
	}

	/**
	 * f0 -> "NOP"
	 */
	public void visit(Nop n) {
		DefaultMutableTreeNode myParent = parentNode;
		DefaultMutableTreeNode nopNode = new DefaultMutableTreeNode("Nop");
		parentNode = nopNode;
		myParent.add(nopNode);
		
		n.f0.accept(this);
		
		parentNode.add(new DefaultMutableTreeNode(n.f0.tokenImage.toString()));
		parentNode = myParent;
	}

	/**
	 * f0 -> <NUMBER>
	 */
	public void visit(Immed n) {
		
		DefaultMutableTreeNode myParent = parentNode;
		DefaultMutableTreeNode immedNode = new DefaultMutableTreeNode("Immed");
		parentNode = immedNode;
		myParent.add(immedNode);
		
		n.f0.accept(this);
		
		parentNode.add(new DefaultMutableTreeNode(n.f0.tokenImage.toString()));
		parentNode = myParent;
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
	public void visit(Register n) {
		
		DefaultMutableTreeNode myParent = parentNode;
		DefaultMutableTreeNode registerNode = new DefaultMutableTreeNode("Register");
		parentNode = registerNode;
		myParent.add(registerNode);
		
		n.f0.accept(this);
		
		parentNode.add(new DefaultMutableTreeNode(n.f0.choice.toString()));
		parentNode = myParent;
	}

}


