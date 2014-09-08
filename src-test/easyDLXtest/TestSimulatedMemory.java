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
import easyDLXsimulation.EasyDLXMemoryManager;
/**
 * 
 * @author Daniele Biagi  biagi.daniele@gmail.com
 *
 */
public class TestSimulatedMemory {

	private EasyDLXMemoryManager sm;
	private Random generator;
	@Before
	public void setUp() throws Exception {
		sm= new EasyDLXMemoryManager();
		generator=new Random();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEraseMemory() {
		sm.storeByte("0000001", "000011");
		String res=sm.loadByte(Long.toBinaryString(1), true);
		
		
		assertEquals(res.compareTo(EasyDLXALU.ignoreOverflow(3)), 0);
		sm.eraseMemory();
		res=sm.loadByte(Long.toBinaryString(1), true);
		assertNull(res);
	}

	@Test
	public void testStoreByte() {
		long addrlong= generator.nextLong();
		long data32bitLong=generator.nextLong();
		
		sm.storeByte(EasyDLXALU.ignoreOverflow(addrlong), Long.toBinaryString(data32bitLong));
		String res=sm.loadByte(EasyDLXALU.ignoreOverflow(addrlong), false);
		long load_res_long= EasyDLXALU.convertToInt(res, false);
		long load_res_long1= EasyDLXALU.convertToInt(EasyDLXALU.ignoreOverflow(data32bitLong).substring(24,32),false);
		assertEquals(load_res_long, load_res_long1);
	}

	@Test
	public void testStoreHalfWord() {
		
		long addrlong= generator.nextLong();
		long data32bitLong=generator.nextLong();
		
		sm.storeHalfWord(EasyDLXALU.ignoreOverflow(addrlong),Long.toBinaryString(data32bitLong));
		String res=sm.loadHalfWord(EasyDLXALU.ignoreOverflow(addrlong), false);
		long load_res_long= EasyDLXALU.convertToInt(res, false);
		long load_res_long1= EasyDLXALU.convertToInt(EasyDLXALU.ignoreOverflow(data32bitLong).substring(16,32),false);
		assertEquals(load_res_long, load_res_long1);
		
	}

	@Test
	public void testStoreWord() {
		long addrlong= generator.nextLong();
		long data32bitLong=generator.nextLong();
		
		sm.storeWord(EasyDLXALU.ignoreOverflow(addrlong), Long.toBinaryString(data32bitLong));
		String res=sm.loadWord(EasyDLXALU.ignoreOverflow(addrlong));
		//System.out.println(res+"\n"+EasyDLXALU.ignoreOverflow(data32bitLong));
		assertEquals(res.compareTo(EasyDLXALU.ignoreOverflow(data32bitLong)), 0);
	}

	@Test
	public void testLoadByte() {
		long addrlong= generator.nextLong();
		long data32bitLong=generator.nextLong();
		String binaryString=EasyDLXALU.ignoreOverflow(data32bitLong);
		
		long signed_value= EasyDLXALU.convertToInt(EasyDLXMemoryManager.signExtentionTo32bitsSigned(binaryString.substring(binaryString.length()-8, binaryString.length())), true);
		long unsigned_value=EasyDLXALU.convertToInt(EasyDLXMemoryManager.signExtentionTo32bits(binaryString.substring(binaryString.length()-8, binaryString.length())), false);
		
		sm.storeByte(Long.toBinaryString(addrlong), Long.toBinaryString(data32bitLong));
		
		long signed_value_loaded= EasyDLXALU.convertToInt(sm.loadByte(Long.toBinaryString(addrlong), true),true);
		long unsigned_value_loaded= EasyDLXALU.convertToInt(sm.loadByte(Long.toBinaryString(addrlong), false),false);
		
		assertEquals(unsigned_value, unsigned_value_loaded);
		assertEquals(signed_value, signed_value_loaded);
	}

	@Test
	public void testLoadHalfWord() {
		long addrlong= generator.nextLong();
		long data32bitLong=generator.nextLong();
		String binaryString=EasyDLXALU.ignoreOverflow(data32bitLong);
		
		long signed_value= EasyDLXALU.convertToInt(EasyDLXMemoryManager.signExtentionTo32bitsSigned(binaryString.substring(binaryString.length()-16, binaryString.length())), true);
		long unsigned_value=EasyDLXALU.convertToInt(EasyDLXMemoryManager.signExtentionTo32bits(binaryString.substring(binaryString.length()-16, binaryString.length())), false);
		
		sm.storeHalfWord(Long.toBinaryString(addrlong), Long.toBinaryString(data32bitLong));
		
		long signed_value_loaded= EasyDLXALU.convertToInt(sm.loadHalfWord(Long.toBinaryString(addrlong), true),true);
		long unsigned_value_loaded= EasyDLXALU.convertToInt(sm.loadHalfWord(Long.toBinaryString(addrlong), false),false);
		
		assertEquals(unsigned_value, unsigned_value_loaded);
		assertEquals(signed_value, signed_value_loaded);
	}

	@Test
	public void testLoadWord() {
		long addrlong= generator.nextLong();
		long data32bitLong=generator.nextLong();
		String binaryString=EasyDLXALU.ignoreOverflow(data32bitLong);
		
		long signed_value= EasyDLXALU.convertToInt(EasyDLXMemoryManager.signExtentionTo32bitsSigned(binaryString.substring(binaryString.length()-32, binaryString.length())), true);
		long unsigned_value=EasyDLXALU.convertToInt(EasyDLXMemoryManager.signExtentionTo32bits(binaryString.substring(binaryString.length()-32, binaryString.length())), false);
		
		sm.storeWord(Long.toBinaryString(addrlong), Long.toBinaryString(data32bitLong));
		
		long signed_value_loaded= EasyDLXALU.convertToInt(sm.loadWord(Long.toBinaryString(addrlong)),true);
		long unsigned_value_loaded= EasyDLXALU.convertToInt(sm.loadWord(Long.toBinaryString(addrlong)),false);
		
		assertEquals(unsigned_value, unsigned_value_loaded);
		assertEquals(signed_value, signed_value_loaded);
	}

	@Test
	public void testCheckAlignedAddress() {
		
		long addrlong= generator.nextLong();
		int alignedTo= generator.nextInt(8)+1;
		boolean result=EasyDLXMemoryManager.checkAlignedAddress(Long.toBinaryString(addrlong), alignedTo);
		long addrlong32bit=EasyDLXALU.convertToInt(Long.toBinaryString(addrlong), false);
		
		if ((addrlong32bit%alignedTo)==0){
			assertTrue(result);
		}else{
			assertFalse(result);
		}
		
	}

	@Test
	public void testSignExtentionTo32bits() {
		String res=EasyDLXMemoryManager.signExtentionTo32bits("100000000000000011111111");
		assertEquals(res.compareTo("00000000100000000000000011111111"), 0);
		res=EasyDLXMemoryManager.signExtentionTo32bits("1100000000100000000000000011111111");
		assertEquals(res.compareTo("00000000100000000000000011111111"), 0);
		res=EasyDLXMemoryManager.signExtentionTo32bits("10000000100000000000000011111111");
		assertEquals(res.compareTo("10000000100000000000000011111111"), 0);
		
	}

	@Test
	public void testSignExtentionTo32bitsSigned() {
		String res=EasyDLXMemoryManager.signExtentionTo32bitsSigned("100000000000000011111111");
		assertEquals(res.compareTo("11111111100000000000000011111111"), 0);
		res=EasyDLXMemoryManager.signExtentionTo32bitsSigned("1100000000100000000000000011111111");
		assertEquals(res.compareTo("00000000100000000000000011111111"), 0);
		res=EasyDLXMemoryManager.signExtentionTo32bitsSigned("0000000100000000000000011111111");
		assertEquals(res.compareTo("00000000100000000000000011111111"), 0);
	}

}
