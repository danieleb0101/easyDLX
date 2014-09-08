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

import easyDLXsimulation.EasyDLXALU;
import easyDLXsimulation.EasyDLXRegistersManager;
/**
 * 
 * @author Daniele Biagi  biagi.daniele@gmail.com
 *
 */
public class TestRegistersManager {
private EasyDLXRegistersManager rm;
private long initPCLong;
	@Before
	public void setUp() throws Exception {
		Random generator= new Random();
		initPCLong= generator.nextLong();
		rm = new EasyDLXRegistersManager(Long.toBinaryString(initPCLong));
		rm.updateRegister("R23","0000000111");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRegistersManager() {
		String result=rm.getCurrentPC();
		assertEquals(result.compareTo(EasyDLXALU.ignoreOverflow(initPCLong)), 0);
		for (int i=0; i<=31;i++){
			result= rm.getRegisterValue("R"+i);
			if (i==23){
				assertEquals(result.compareTo(EasyDLXALU.ignoreOverflow(7)), 0);
			}else{
			assertEquals(result.compareTo(EasyDLXALU.ignoreOverflow(0)), 0);
			}
		}
	}

	@Test
	public void testSetInitialPCAndEraseRegisters() {
		boolean res_bool=rm.updateRegister("R21","00000001");
		assertTrue(res_bool);
		assertEquals(rm.getRegisterValue("R21").compareTo(EasyDLXALU.ignoreOverflow(1)), 0);
		rm.setInitialPCAndEraseRegisters("010101");
		assertEquals(rm.getRegisterValue("R21").compareTo(EasyDLXALU.ignoreOverflow(0)), 0);
		
	}

	@Test
	public void testUpdateRegister() {
		boolean res_bool=rm.updateRegister("R21","00000001");
		assertTrue(res_bool);
		res_bool=rm.updateRegister("R21","00000011");
		assertTrue(res_bool);
		res_bool=rm.updateRegister("R45","00000011");
		assertFalse(res_bool);
		
		assertEquals(rm.getRegisterValue("R21").compareTo(EasyDLXALU.ignoreOverflow(3)), 0);
	}

	@Test
	public void testGetRegisterValue() {
		assertEquals(rm.getRegisterValue("R23").compareTo(EasyDLXALU.ignoreOverflow(7)), 0);
		assertEquals(rm.getRegisterValue("R2").compareTo(EasyDLXALU.ignoreOverflow(0)), 0);
	}

	@Test
	public void testGetCurrentPC() {
		String currentPCString=rm.getCurrentPC();
		assertEquals(currentPCString.compareTo(EasyDLXALU.ignoreOverflow(initPCLong)), 0);
		
	}

	@Test
	public void testIncCurrentPC() {
		String currentPCString=rm.getCurrentPC();
		assertEquals(currentPCString.compareTo(EasyDLXALU.ignoreOverflow(initPCLong)), 0);
		rm.incCurrentPC();
		currentPCString=rm.getCurrentPC();
		assertEquals(currentPCString.compareTo(EasyDLXALU.ignoreOverflow(initPCLong+4)), 0);
		rm.incCurrentPC();
		currentPCString=rm.getCurrentPC();
		assertEquals(currentPCString.compareTo(EasyDLXALU.ignoreOverflow(initPCLong+8)), 0);
		rm.incCurrentPC();
		currentPCString=rm.getCurrentPC();
		assertEquals(currentPCString.compareTo(EasyDLXALU.ignoreOverflow(initPCLong+12)), 0);
		
	}

	@Test
	public void testGetInitialPC() {
		String res= rm.getInitialPC();
		rm.incCurrentPC();
		rm.incCurrentPC();
		rm.incCurrentPC();
		rm.incCurrentPC();
		assertEquals(res.compareTo(EasyDLXALU.ignoreOverflow(initPCLong)), 0);
	}

	@Test
	public void testSetCurrentPC() {
		String res= rm.getCurrentPC();
		assertEquals(res.compareTo(EasyDLXALU.ignoreOverflow(initPCLong)), 0);
		Random generator= new Random();
		long newPCLong= generator.nextLong();
		rm.setCurrentPC(Long.toBinaryString(newPCLong));
		res= rm.getCurrentPC();
		//System.out.println(Long.toBinaryString(newPCLong)+"\n"+res);
		assertEquals(res.compareTo(EasyDLXALU.ignoreOverflow(newPCLong)), 0);
		
	}

}
