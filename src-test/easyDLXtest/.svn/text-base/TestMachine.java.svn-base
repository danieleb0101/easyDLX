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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import easyDLXsimulation.EasyDLXMachineGlobalState;
/**
 * 
 * @author Daniele Biagi  biagi.daniele@gmail.com
 *
 */
public class TestMachine {
private EasyDLXMachineGlobalState machine;
	/*private LabelsManager lm;
	private RegistersManager rm;
	private SimulatedMemory sm;*/
	
	@Before
	public void setUp() throws Exception {
		/*lm=new LabelsManager();
		rm= new RegistersManager("00000000000000000011000000000000");
		sm = new SimulatedMemory();*/
		machine= new EasyDLXMachineGlobalState("00000000000000000011000000000000");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMachineGlobalState() {
		
		Object res= machine.getRegistersManager();
		assertNotNull(res);
		res= machine.getLabelsManager();
		assertNotNull(res);
		res= machine.getSimulatedMemory();
		assertNotNull(res);
	}

	@Test
	public void testRestartMachine() {
		machine= new EasyDLXMachineGlobalState("00000000000000000011000000000000");
		machine.getRegistersManager().updateRegister("R2", "00000000000000000011000000000011");
		machine.getRegistersManager().incCurrentPC();
		String res= machine.getRegistersManager().getRegisterValue("R2");
		assertEquals(res.compareTo("00000000000000000011000000000011"), 0);
		res= machine.getRegistersManager().getCurrentPC();
		assertEquals(res.compareTo("00000000000000000011000000000100"), 0);
		
		machine.restartMachine("00000000000000000011000000000000");
		res= machine.getRegistersManager().getRegisterValue("R2");
		assertEquals(res.compareTo("00000000000000000000000000000000"), 0);
		res= machine.getRegistersManager().getCurrentPC();
		assertEquals(res.compareTo("00000000000000000011000000000000"), 0);
		assertNull(machine.getLabelsManager().getStringAddress("LB2"));
		
	}

	@Test
	public void testGetLabelsManager() {
		assertNotNull(machine.getLabelsManager());
		machine.restartMachine("100110");
		assertNotNull(machine.getLabelsManager());
	}

	

	@Test
	public void testGetRegistersManager() {
		assertNotNull(machine.getRegistersManager());
		machine.restartMachine("100110");
		assertNotNull(machine.getRegistersManager());
	}

	@Test
	public void testGetSimulatedMemory() {
		assertNotNull(machine.getSimulatedMemory());
		machine.restartMachine("100110");
		assertNotNull(machine.getSimulatedMemory());
	}


}
