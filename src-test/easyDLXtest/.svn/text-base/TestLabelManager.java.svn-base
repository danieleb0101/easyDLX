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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import easyDLXsimulation.EasyDLXLabelsManager;
/**
 * 
 * @author Daniele Biagi  biagi.daniele@gmail.com
 *
 */
public class TestLabelManager {

	private EasyDLXLabelsManager lm;
	@Before
	public void setUp() throws Exception {
		lm= new EasyDLXLabelsManager();
		lm.addLabel("LB1","00000000000000000011000001000000");
		lm.addLabel("LB2","00000000000000000011000000001000");
		//lm.addLabel("LB3","00000000000000000011000000011000");
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddLabel() {
		String address="00000000000000000011000000111000";
		boolean res_bool=lm.addLabel("LB3",address);
		String res= lm.getStringAddress("LB3");
		assertTrue(res_bool);
		assertEquals(res.compareTo(address), 0);
		
		res_bool=lm.addLabel("LB3",address);
		assertFalse(res_bool);
		
	}

	@Test
	public void testGetStringAddress() {
		String expected="00000000000000000011000000001000";
		String res= lm.getStringAddress("LB2");
		assertEquals(res.compareTo(expected), 0);
		res= lm.getStringAddress("LB20");
		assertNull(res);
		
	}

	@Test
	public void testEraseLabels() {
		lm.eraseLabels();
		assertNull(lm.getStringAddress("LB2"));
	}

}
