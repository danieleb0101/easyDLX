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

import java.util.Random;

import junit.framework.TestCase;

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
public class TestALU extends TestCase{
	

	
	@Before
	public void setUp() throws Exception {
	
	}

	@After
	public void tearDown() throws Exception {
	}

	
	public void testSub() {
		
		long longRandom1= (long) ((Math.random()*Math.pow(2, 32))-(Math.pow(2, 31)));
		long longRandom2= (long) ((Math.random()*Math.pow(2, 32))-(Math.pow(2, 31)));
		
		long longResult=longRandom1-longRandom2;
		String stringResult= Long.toBinaryString(longResult);
		stringResult=EasyDLXMemoryManager.signExtentionTo32bits(stringResult);
		stringResult= stringResult.substring(stringResult.length()-32, stringResult.length());
		
		String aluResult=EasyDLXALU.sub(Long.toBinaryString(longRandom1),Long.toBinaryString(longRandom2), false);
		assertEquals(aluResult.compareTo(stringResult), 0);	
		
		aluResult=EasyDLXALU.sub(Long.toBinaryString(longRandom1),Long.toBinaryString(longRandom2), true);
		assertEquals(aluResult.compareTo(stringResult), 0);
		
		
	}

	@Test
	public void testAdd() {
		
		long longRandom1= (long) ((Math.random()*Math.pow(2, 32))-(Math.pow(2, 31)));
		long longRandom2= (long) ((Math.random()*Math.pow(2, 32))-(Math.pow(2, 31)));
		
		long longResult=longRandom1+longRandom2;
		String stringResult= Long.toBinaryString(longResult);
		stringResult=EasyDLXMemoryManager.signExtentionTo32bits(stringResult);
		stringResult= stringResult.substring(stringResult.length()-32, stringResult.length());
		
		String aluResult=EasyDLXALU.add(Long.toBinaryString(longRandom1),Long.toBinaryString(longRandom2), false);
		assertEquals(aluResult.compareTo(stringResult), 0);	
		
		aluResult=EasyDLXALU.add(Long.toBinaryString(longRandom1),Long.toBinaryString(longRandom2), true);
		assertEquals(aluResult.compareTo(stringResult), 0);
	}

	
	
	@Test
	public void testMul() {
		
		long longRandom1= (long) ((Math.random()*Math.pow(2, 32))-(Math.pow(2, 31)));
		long longRandom2= (long) ((Math.random()*Math.pow(2, 32))-(Math.pow(2, 31)));
		
		long longResult=longRandom1*longRandom2;
		String stringResult= Long.toBinaryString(longResult);
		stringResult=EasyDLXMemoryManager.signExtentionTo32bits(stringResult);
		stringResult= stringResult.substring(stringResult.length()-32, stringResult.length());
		
		String aluResult=EasyDLXALU.mul(Long.toBinaryString(longRandom1),Long.toBinaryString(longRandom2), false);
		assertEquals(aluResult.compareTo(stringResult), 0);	
		
		aluResult=EasyDLXALU.mul(Long.toBinaryString(longRandom1),Long.toBinaryString(longRandom2), true);
		assertEquals(aluResult.compareTo(stringResult), 0);
		
	}

	@Test
	public void testDiv() {
		long longRandom1= (long) ((Math.random()*Math.pow(2, 32))-(Math.pow(2, 31)));
		long longRandom2= (long) ((Math.random()*Math.pow(2, 28))-(Math.pow(2, 27)));
		
		long longResult=(int)longRandom1/longRandom2;
		
		String stringResult= Long.toBinaryString(longResult);
		stringResult=EasyDLXMemoryManager.signExtentionTo32bits(stringResult);
		stringResult= stringResult.substring(stringResult.length()-32, stringResult.length());
		
		String aluResult=EasyDLXALU.div(Long.toBinaryString(longRandom1),Long.toBinaryString(longRandom2), true);
		assertEquals(aluResult.compareTo(stringResult), 0);	
		
		
		longRandom1=EasyDLXALU.convertToInt(Long.toBinaryString(longRandom1), false);
		longRandom2=EasyDLXALU.convertToInt(Long.toBinaryString(longRandom2), false);
		longResult=longRandom1/longRandom2;
		
		stringResult= Long.toBinaryString(longResult);
		stringResult=EasyDLXMemoryManager.signExtentionTo32bits(stringResult);
		stringResult= stringResult.substring(stringResult.length()-32, stringResult.length());
		
		aluResult=EasyDLXALU.div(Long.toBinaryString(longRandom1),Long.toBinaryString(longRandom2), false);
		assertEquals(aluResult.compareTo(stringResult), 0);

	}

	@Test
	public void testAnd() {
		
		
		double random1=Math.random()*Math.pow(2, 32);
		long longRandom1= (long)random1;
		double random2=Math.random()*Math.pow(2, 32);
		long longRandom2= (long)random2;
		long l3=longRandom1&longRandom2;
		
		String s=EasyDLXALU.and(Long.toBinaryString(longRandom1), Long.toBinaryString(longRandom2)); 
		
		String s1= Long.toBinaryString(l3);
		s1= EasyDLXMemoryManager.signExtentionTo32bits(s1);
		s1= s1.substring(s1.length()-32, s1.length());
		assertEquals(s1.compareTo(s), 0);
		
		
		
		
	}

	@Test
	public void testOr() {
		double random1=Math.random()*Math.pow(2, 32);
		long longRandom1= (long)random1;
		double random2=Math.random()*Math.pow(2, 32);
		long longRandom2= (long)random2;
		long l3=longRandom1|longRandom2;
		
		String s=EasyDLXALU.or(Long.toBinaryString(longRandom1), Long.toBinaryString(longRandom2)); 
		
		String s1= Long.toBinaryString(l3);
		s1= EasyDLXMemoryManager.signExtentionTo32bits(s1);
		s1= s1.substring(s1.length()-32, s1.length());
		assertEquals(s1.compareTo(s), 0);
		
		
	}

	@Test
	public void testXor() {
		double random1=Math.random()*Math.pow(2, 32);
		long longRandom1= (long)random1;
		double random2=Math.random()*Math.pow(2, 32);
		long longRandom2= (long)random2;
		long l3=longRandom1^longRandom2;
		
		String s=EasyDLXALU.xor(Long.toBinaryString(longRandom1), Long.toBinaryString(longRandom2)); 
		
		String s1= Long.toBinaryString(l3);
		s1= EasyDLXMemoryManager.signExtentionTo32bits(s1);
		s1= s1.substring(s1.length()-32, s1.length());
		assertEquals(s1.compareTo(s), 0);
		
	}

	@Test
	public void testShift() {
		double random1=Math.random()*Math.pow(2, 32);
		long longRandom1= (long)random1;
		double random2=Math.random()*32;
		long longRandom2= (long)random2;
		long l3=longRandom1<<longRandom2;
		
		String s=EasyDLXALU.shift(Long.toBinaryString(longRandom1),Long.toBinaryString(longRandom2),true);
				
		
		String s1= Long.toBinaryString(l3);
		s1= EasyDLXMemoryManager.signExtentionTo32bits(s1);
		s1= s1.substring(s1.length()-32, s1.length());
		assertEquals(s1.compareTo(s), 0);
		
		
		l3=longRandom1>>longRandom2;
		s=EasyDLXALU.shift(Long.toBinaryString(longRandom1),Long.toBinaryString(longRandom2),false);
		
		s1= Long.toBinaryString(l3);
		s1= EasyDLXMemoryManager.signExtentionTo32bits(s1);
		s1= s1.substring(s1.length()-32, s1.length());
		assertEquals(s1.compareTo(s), 0);
		
	}

	
	@Test
	public void testSetInstr() {
		
		int i=0;
		while(i++<=9){
		Random generator= new Random();
		long longRandom1= (long)generator.nextInt();
		
		long longRandom2= (long)generator.nextInt();
		
		
		String res="";
		if (longRandom1>=longRandom2){
			res=EasyDLXALU.setInstr(Long.toBinaryString(longRandom1), Long.toBinaryString(longRandom2), "SGE");
			assertEquals(res.compareTo(EasyDLXMemoryManager.signExtentionTo32bits("1")), 0);
		}else{
			res=EasyDLXALU.setInstr(Long.toBinaryString(longRandom1), Long.toBinaryString(longRandom2), "SGE");
			assertEquals(res.compareTo(EasyDLXMemoryManager.signExtentionTo32bits("0")), 0);
		}
		
		if (longRandom1>longRandom2){
			res=EasyDLXALU.setInstr(Long.toBinaryString(longRandom1), Long.toBinaryString(longRandom2), "SGT");
			assertEquals(res.compareTo(EasyDLXMemoryManager.signExtentionTo32bits("1")), 0);
		}else{
			res=EasyDLXALU.setInstr(Long.toBinaryString(longRandom1), Long.toBinaryString(longRandom2), "SGT");
			assertEquals(res.compareTo(EasyDLXMemoryManager.signExtentionTo32bits("0")), 0);
		}
		
		if (longRandom1<=longRandom2){
			res=EasyDLXALU.setInstr(Long.toBinaryString(longRandom1), Long.toBinaryString(longRandom2), "SLE");
			assertEquals(res.compareTo(EasyDLXMemoryManager.signExtentionTo32bits("1")), 0);
		}else{
			res=EasyDLXALU.setInstr(Long.toBinaryString(longRandom1), Long.toBinaryString(longRandom2), "SLE");
			assertEquals(res.compareTo(EasyDLXMemoryManager.signExtentionTo32bits("0")), 0);
		}
		
		if (longRandom1<longRandom2){
			res=EasyDLXALU.setInstr(Long.toBinaryString(longRandom1), Long.toBinaryString(longRandom2), "SLT");
			assertEquals(res.compareTo(EasyDLXMemoryManager.signExtentionTo32bits("1")), 0);
		}else{
			res=EasyDLXALU.setInstr(Long.toBinaryString(longRandom1), Long.toBinaryString(longRandom2), "SLT");
			assertEquals(res.compareTo(EasyDLXMemoryManager.signExtentionTo32bits("0")), 0);
		}
		
		if (longRandom1!=longRandom2){
			
			res=EasyDLXALU.setInstr(Long.toBinaryString(longRandom1), Long.toBinaryString(longRandom2), "SEQ");
			assertEquals(res.compareTo(EasyDLXMemoryManager.signExtentionTo32bits("0")), 0);
		}
		res=EasyDLXALU.setInstr(Long.toBinaryString(longRandom1), Long.toBinaryString(longRandom1), "SEQ");
		assertEquals(res.compareTo(EasyDLXMemoryManager.signExtentionTo32bits("1")), 0);
		
		if (longRandom1!=longRandom2){
			res=EasyDLXALU.setInstr(Long.toBinaryString(longRandom1), Long.toBinaryString(longRandom2), "SNE");
			assertEquals(res.compareTo(EasyDLXMemoryManager.signExtentionTo32bits("1")), 0);
		}
		res=EasyDLXALU.setInstr(Long.toBinaryString(longRandom1), Long.toBinaryString(longRandom1), "SEQ");
		assertEquals(res.compareTo(EasyDLXMemoryManager.signExtentionTo32bits("1")), 0);
		res=EasyDLXALU.setInstr(Long.toBinaryString(longRandom1), Long.toBinaryString(longRandom1), "SNE");
		assertEquals(res.compareTo(EasyDLXMemoryManager.signExtentionTo32bits("0")), 0);
		
		}
		String r=EasyDLXALU.setInstr(Long.toBinaryString(4), Long.toBinaryString(2), "xx");
		assertNull(r);
	}
}
