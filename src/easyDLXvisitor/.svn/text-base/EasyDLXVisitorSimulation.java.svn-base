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

import easyDLXsimulation.EasyDLXControlFlow;
import easyDLXsimulation.EasyDLXALU;
import easyDLXsimulation.EasyDLXMachineGlobalState;
import easyDLXsimulation.EasyDLXSemanticError;
import easyDLXsimulation.EasyDLXMemoryManager;

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
public class EasyDLXVisitorSimulation extends DepthFirstVisitor {
	private Node temp;
	private String opName="";
	private String regName="";
	private String Rd="";
	private String Rs1="";
	private String Rs2="";
	private int immediate;
	private String binaryString="";
	private String label="";
	private EasyDLXMachineGlobalState machine;
	private EasyDLXSemanticError semanticError;

	public EasyDLXVisitorSimulation(EasyDLXMachineGlobalState machine,EasyDLXSemanticError semanticError) {
		this.machine= machine;
		this.semanticError= semanticError;
	}

	public void visit(NodeList n) {
		for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ){
			temp = e.nextElement();
			// debug
			//System.out.println("Node List: " + temp.toString());
			temp.accept(this);

		}
	}

	public void visit(NodeListOptional n) {
		if ( n.present() )
			for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ){
				temp = e.nextElement();
				// debug
				//System.out.println("Node ListOptional: " + temp.toString());
				temp.accept(this);

			}
	}

	public void visit(NodeOptional n) {
		if ( n.present() ){
			//  debug
			//System.out.println("Node Optional: " + n.node.toString());
			n.node.accept(this);

		}
	}

	public void visit(NodeSequence n) {
		for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ){
			temp = e.nextElement();
			// debug
			//System.out.println("Node Sequence: " + temp.toString());
			temp.accept(this);

		}
	}

	public void visit(NodeToken n) {
		//debug
		//System.out.println("visit " + n.tokenImage);
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
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
	}

	/**
	 * f0 -> <LABEL_COLON>
	 */
	public void visit(Label n) {
		n.f0.accept(this);
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
		n.f0.accept(this);
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
		n.f0.accept(this);
		n.f1.accept(this);

		Rd= regName;

		n.f2.accept(this);
		n.f3.accept(this);

		Rs1= regName;

		n.f4.accept(this);
		n.f5.accept(this);

		Rs2= regName;

		//check the name of the operation and execute
		//Rd= Rs1 op Rs2

		String op1= machine.getRegistersManager().getRegisterValue(Rs1);
		String op2= machine.getRegistersManager().getRegisterValue(Rs2);
		String res;
		res = performOperation(op1, op2, opName);
		if (!semanticError.isError()){
			machine.getRegistersManager().updateRegister(Rd, res);
			machine.getRegistersManager().incCurrentPC();
		}

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
		n.f0.accept(this);
		opName= n.f0.choice.toString().trim();
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
		n.f0.accept(this);
		n.f1.accept(this);

		Rd= regName;

		n.f2.accept(this);
		n.f3.accept(this);

		Rs1= regName;

		n.f4.accept(this);
		n.f5.accept(this);

		//check the name of the operation and execute
		// Rd= Rs1 op immediate

		String op1= machine.getRegistersManager().getRegisterValue(Rs1);
		String res;
		res = performOperation(op1, binaryString, opName.substring(0, opName.length()-1));
		if (!semanticError.isError()){
			machine.getRegistersManager().updateRegister(Rd, res);
			machine.getRegistersManager().incCurrentPC();
		}


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
		n.f0.accept(this);
		opName= n.f0.choice.toString().trim();
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
		n.f0.accept(this);
		n.f1.accept(this);

		Rd= regName;

		n.f2.accept(this);
		n.f3.accept(this);

		//immediate

		n.f4.accept(this);
		n.f5.accept(this);

		Rs1= regName;

		n.f6.accept(this);

		//load Rd<- mem[Rs1+immediate]


		String result=null;
		String addr=null;
		switch (opName) {

		case "LB":{

			addr= EasyDLXALU.add(machine.getRegistersManager().getRegisterValue(Rs1), binaryString, true);

			result=machine.getSimulatedMemory().loadByte(addr, true);
			break;
		}
		case "LBU":{

			addr= EasyDLXALU.add(machine.getRegistersManager().getRegisterValue(Rs1), binaryString, true);

			result=machine.getSimulatedMemory().loadByte(addr, false);
			break;
		}
		case "LH":{

			addr= EasyDLXALU.add(machine.getRegistersManager().getRegisterValue(Rs1), binaryString, true);

			if (!EasyDLXMemoryManager.checkAlignedAddress(addr, 2)){
				semanticError.setError(true, "The instruction LH uses only addresses multiple of 2, at line ");
			}else{
				result=machine.getSimulatedMemory().loadHalfWord(addr, true);
			}
			break;
		}
		case "LHU":{

			addr= EasyDLXALU.add(machine.getRegistersManager().getRegisterValue(Rs1), binaryString, true);

			if (!EasyDLXMemoryManager.checkAlignedAddress(addr, 2)){
				semanticError.setError(true, "The instruction LHU uses only addresses multiple of 2, at line ");
			}else{
				result=machine.getSimulatedMemory().loadHalfWord(addr, false);
			}

			break;
		}
		case "LW":{

			addr= EasyDLXALU.add(machine.getRegistersManager().getRegisterValue(Rs1), binaryString, true);

			if (!EasyDLXMemoryManager.checkAlignedAddress(addr, 4)){
				semanticError.setError(true, "The instruction LW uses only addresses multiple of 4, at line ");
			}else{
				result=machine.getSimulatedMemory().loadWord(addr);
			}
			break;
		}

		}

		if ((result==null)&&(!semanticError.isError())){
			semanticError.setError(true,"There is no data in this memory location 0x"+Long.toHexString(EasyDLXALU.convertToInt(addr,false))+",\n at line ");
			return;
		}
		if (result!=null){
			if ((result.contains("CODE")&&(!semanticError.isError()))){
				semanticError.setError(true,"There is no data in this memory location 0x"+Long.toHexString(EasyDLXALU.convertToInt(addr,false))+",\n there is an instruction, at line ");
				return;
			}
		}
		if (!semanticError.isError()){
			machine.getRegistersManager().updateRegister(Rd, result);
			machine.getRegistersManager().incCurrentPC();
		}

	}

	/**
	 * f0 -> "LB"
	 *       | "LBU"
	 *       | "LH"
	 *       | "LHU"
	 *       | "LW"
	 */
	public void visit(OpLoad n) {
		n.f0.accept(this);
		opName= n.f0.choice.toString().trim();
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
		n.f0.accept(this);
		n.f1.accept(this);

		//immed

		n.f2.accept(this);
		n.f3.accept(this);

		Rs1=regName;

		n.f4.accept(this);
		n.f5.accept(this);
		n.f6.accept(this);

		Rs2=regName;


		String addr=null;
		//store
		switch (opName) {


		case "SB":{


			addr= EasyDLXALU.add(machine.getRegistersManager().getRegisterValue(Rs1), binaryString, true);

			if (checkNoCode(addr)){
				machine.getSimulatedMemory().storeByte(addr, machine.getRegistersManager().getRegisterValue(Rs2));
			}

			break;
		}
		case "SH":{


			addr= EasyDLXALU.add(machine.getRegistersManager().getRegisterValue(Rs1), binaryString, true);

			String addrh=EasyDLXALU.add(addr, EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(1)), false);

			if (!EasyDLXMemoryManager.checkAlignedAddress(addr, 2)){
				semanticError.setError(true, "The instruction SH uses only addresses multiple of 2, at line ");
			}else{
				if ((checkNoCode(addr))&&(checkNoCode(addrh))){
					machine.getSimulatedMemory().storeHalfWord(addr, machine.getRegistersManager().getRegisterValue(Rs2));
				}
			}
			break;
		}
		case "SW":{

			addr= EasyDLXALU.add(machine.getRegistersManager().getRegisterValue(Rs1), binaryString, true);

			if (!EasyDLXMemoryManager.checkAlignedAddress(addr, 4)){
				semanticError.setError(true, "The instruction SW uses only addresses multiple of 4, at line ");
			}else{

				if ((checkNoCode(addr))){
					machine.getSimulatedMemory().storeWord(addr, machine.getRegistersManager().getRegisterValue(Rs2));
				}
			}
			break;
		}

		}
		if (!semanticError.isError()){
			machine.getRegistersManager().incCurrentPC();
		}
	}

	private boolean checkNoCode(String addr) {
		String result=machine.getSimulatedMemory().loadByte(addr, true);
		if (result!=null){
			if (result.contains("CODE")){
				semanticError.setError(true,"It is not possible to store data in this address 0x"+Long.toHexString(EasyDLXALU.convertToInt(addr,false))+",\n it is already used for instructions, at line ");
				return false;
			}
		}
		return true;
	}

	/**
	 * f0 -> "SB"
	 *       | "SH"
	 *       | "SW"
	 */
	public void visit(OpStore n) {
		n.f0.accept(this);
		opName= n.f0.choice.toString().trim();
	}

	/**
	 * f0 -> OpBranch()
	 * f1 -> Register()
	 * f2 -> <COMMA>
	 * f3 -> <LABELJ>
	 */
	public void visit(InstrBranch n) {
		n.f0.accept(this);
		n.f1.accept(this);

		Rs1= regName;

		n.f2.accept(this);
		n.f3.accept(this);

		label= n.f3.tokenImage.trim();

		String result= null;
		//dest label
		switch (opName) {
		case "BEQZ":{
			result= EasyDLXControlFlow.branch(machine.getRegistersManager().getRegisterValue(Rs1),label,true,machine.getRegistersManager().getCurrentPC(),machine.getLabelsManager());
			break;
		}
		case "BNEZ":{
			result= EasyDLXControlFlow.branch(machine.getRegistersManager().getRegisterValue(Rs1),label,false,machine.getRegistersManager().getCurrentPC(),machine.getLabelsManager());
			break;
		}
		}
		machine.getRegistersManager().setCurrentPC(result);


	}

	/**
	 * f0 -> "BEQZ"
	 *       | "BNEZ"
	 */
	public void visit(OpBranch n) {
		n.f0.accept(this);
		opName= n.f0.choice.toString().trim();
	}

	/**
	 * f0 -> OpJumpReg()
	 * f1 -> Register()
	 */
	public void visit(InstrJumpReg n) {
		n.f0.accept(this);
		n.f1.accept(this);

		Rs1= regName;
		String result=null;;
		switch (opName) {
		case "JR":{
			result=machine.getRegistersManager().getRegisterValue(Rs1);
			break;
		}
		case "JALR":{

			machine.getRegistersManager().incCurrentPC();
			String pc=machine.getRegistersManager().getCurrentPC();
			machine.getRegistersManager().updateRegister("R31", pc);
			result=machine.getRegistersManager().getRegisterValue(Rs1);

			break;
		}
		}
		machine.getRegistersManager().setCurrentPC(result);
	}

	/**
	 * f0 -> "JR"
	 *       | "JALR"
	 */
	public void visit(OpJumpReg n) {
		n.f0.accept(this);
		opName= n.f0.choice.toString().trim();
	}

	/**
	 * f0 -> OpJumpLab()
	 * f1 -> <LABELJ>
	 */
	public void visit(InstrJumpLab n) {
		n.f0.accept(this);
		n.f1.accept(this);

		label= n.f1.tokenImage.trim();

		//

		String result=null;;
		switch (opName) {
		case "J":{
			result=EasyDLXControlFlow.jump(label, machine.getLabelsManager(), false, machine.getRegistersManager().getCurrentPC(), machine.getRegistersManager());
			break;
		}
		case "JAL":{

			result=EasyDLXControlFlow.jump(label, machine.getLabelsManager(), true, machine.getRegistersManager().getCurrentPC(), machine.getRegistersManager());

			break;
		}
		}
		machine.getRegistersManager().setCurrentPC(result);
	}

	/**
	 * f0 -> "J"
	 *       | "JAL"
	 */
	public void visit(OpJumpLab n) {
		n.f0.accept(this);
		opName= n.f0.choice.toString().trim();
	}

	/**
	 * f0 -> "NOP"
	 */
	public void visit(Nop n) {
		n.f0.accept(this);
		opName= n.f0.tokenImage.trim();
		machine.getRegistersManager().incCurrentPC();
	}

	/**
	 * f0 -> <NUMBER>
	 */
	public void visit(Immed n) {
		n.f0.accept(this);
		try{
			immediate= Integer.parseInt(n.f0.tokenImage.trim());
			binaryString= Integer.toBinaryString(immediate);
			//System.out.println("binaryString:"+binaryString+"length:"+binaryString.length());
			binaryString=EasyDLXMemoryManager.signExtentionTo32bits(binaryString);
		}catch (Exception e) {
			e.printStackTrace();
		}

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
		n.f0.accept(this);
		regName= n.f0.choice.toString().trim();
	}

	private String performOperation(String op1, String op2, String string) {
		String res=null;
		switch (string) {

		case "ADD":{
			res= EasyDLXALU.add(op1, op2, true);
			break;
		}
		case "ADDU":{
			res= EasyDLXALU.add(op1, op2, false);
			break;
		}
		case "SUB":{
			res= EasyDLXALU.sub(op1, op2, true);
			break;
		}
		case "SUBU":{
			res= EasyDLXALU.sub(op1, op2, false);
			break;
		}
		case "MULT":{
			res= EasyDLXALU.mul(op1, op2, true);
			break;}
		case "MULTU":{
			res= EasyDLXALU.mul(op1, op2, false);
			break;
		}
		case "DIV":{
			long rs2Val=EasyDLXALU.convertToInt(op2,true);
			if (rs2Val==0){
				semanticError.setError(true, "Exception division by 0, at line ");
			}else{
				res= EasyDLXALU.div(op1, op2, true);
			}
			break;
		}
		case "DIVU":{
			long rs2Val=EasyDLXALU.convertToInt(op2,false);
			if (rs2Val==0){
				semanticError.setError(true, "Exception division by 0, at line ");
			}else{
				res= EasyDLXALU.div(op1, op2, false);
			}
			break;
		}
		case "AND":{
			res= EasyDLXALU.and(op1, op2);
			break;}
		case "OR":{
			res= EasyDLXALU.or(op1, op2);
			break;}
		case "XOR":{
			res= EasyDLXALU.xor(op1, op2);
			break;}
		case "SLL":{
			res= EasyDLXALU.shift(op1, op2, true);
			break;}
		case "SRL":{
			res= EasyDLXALU.shift(op1, op2, false);
			break;}
		default:{
			res= EasyDLXALU.setInstr(op1, op2, opName);
			break;}
		}
		return res;
	}

}
