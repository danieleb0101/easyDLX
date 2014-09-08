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
package easyDLXtest;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import easyDLXsimulation.EasyDLXControlFlow;
import easyDLXsimulation.EasyDLXALU;
import easyDLXsimulation.EasyDLXLabelsManager;
import easyDLXsimulation.EasyDLXRegistersManager;
import easyDLXsimulation.EasyDLXMemoryManager;
/**
 * 
 * @author Daniele Biagi  biagi.daniele@gmail.com
 *
 */
public class TestControlFlow {
	private EasyDLXLabelsManager lm;
	private EasyDLXRegistersManager rm;

	@Before
	public void setUp() throws Exception {
		lm=new EasyDLXLabelsManager();
		lm.addLabel("LB1","00000000000000000011000001000000");
		lm.addLabel("LB2","00000000000000000011000000001000");
		lm.addLabel("LB3","00000000000000000011000000011000");
		lm.addLabel("LB3","00000000000000000011000000111000");

		rm= new EasyDLXRegistersManager("00000000000000000011000000000000");


	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBranch() {
		int value= 1;

		Random generator= new Random();
		int index=generator.nextInt(3)+1;

		String label= ("LB"+index).trim();
		


		String result=EasyDLXControlFlow.branch(Integer.toBinaryString(value),label,true,"00000000000000000011000000000000",lm);
		assertEquals(result.compareTo("00000000000000000011000000000100"), 0);
		value= -2;
		result=EasyDLXControlFlow.branch(Integer.toBinaryString(value),label,true,"00000000000000000011000000000000",lm);
		assertEquals(result.compareTo("00000000000000000011000000000100"), 0);

		value= 0;
		result=EasyDLXControlFlow.branch(Integer.toBinaryString(value),label,true,"00000000000000000011000000000000",lm);
		assertEquals(result.compareTo(lm.getStringAddress(label)), 0);

		result=EasyDLXControlFlow.branch(Integer.toBinaryString(value),label,false,"00000000000000000011000000000000",lm);
		assertEquals(result.compareTo("00000000000000000011000000000100"), 0);

		value=-1;
		result=EasyDLXControlFlow.branch(Integer.toBinaryString(value),label,false,"00000000000000000011000000000000",lm);
		assertEquals(result.compareTo(lm.getStringAddress(label)), 0);

	}

	@Test
	public void testJump() {
		Random generator= new Random();
		int index=generator.nextInt(3)+1;

		String currentPC=EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(generator.nextInt()));
		String label= ("LB"+index).trim();
		

		String result=EasyDLXControlFlow.jump(label, lm, true,currentPC , rm);
		assertEquals(result.compareTo(lm.getStringAddress(label)), 0);

		
		String savedPC=Long.toBinaryString((EasyDLXALU.convertToInt(currentPC, false)+4));
		savedPC=EasyDLXMemoryManager.signExtentionTo32bits(savedPC);
		savedPC= savedPC.substring(savedPC.length()-32, savedPC.length());

		assertEquals(rm.getRegisterValue("R31").compareTo(savedPC), 0);

		rm.updateRegister("R31", "00000000000000000000000000000000");
		
		result=EasyDLXControlFlow.jump(label, lm, false,currentPC , rm);
		assertEquals(result.compareTo(lm.getStringAddress(label)), 0);
		assertEquals(rm.getRegisterValue("R31").compareTo("00000000000000000000000000000000"), 0);
	}

}
