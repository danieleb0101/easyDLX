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
package easyDLXsimulation;

import java.util.Enumeration;
import java.util.Hashtable;
/**
 * 
 * @author Daniele Biagi  biagi.daniele@gmail.com
 *
 */
public class EasyDLXRegistersManager {
	private Hashtable<String, String> registers;
	private String currentPC;
	private String initialPC;

	public EasyDLXRegistersManager(String initPC) {	
		init(initPC);
	}


	private void init(String initPC) {
		if (initPC.length()>32){
			initPC= initPC.substring(initPC.length()-32, initPC.length());
		}
		initPC= EasyDLXMemoryManager.signExtentionTo32bits(initPC);
		registers= new Hashtable<String, String>();
		for(int i=0; i<=31;i++){
			registers.put("R"+i, "00000000000000000000000000000000");
		}
		initialPC=initPC;
		currentPC=initPC;

	}

	public void setInitialPCAndEraseRegisters(String initPC) {
		registers.clear();
		init(initPC);
	}

	public boolean updateRegister(String regName,String value){
		regName= regName.trim();
		value= EasyDLXMemoryManager.signExtentionTo32bits(value);
		if (!registers.containsKey(regName)){
			return false;
		}
		registers.put(regName, value);
		return true;
	}

	public String getRegisterValue(String regName){
		regName= regName.trim();
		return registers.get(regName);
	}

	public String getCurrentPC(){
		return currentPC;
	}

	public void incCurrentPC(){
		currentPC=EasyDLXALU.add(currentPC, EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(4)), false);	
	}

	public String getInitialPC(){
		return initialPC;
	}

	public void setCurrentPC(String pc){
		pc= EasyDLXMemoryManager.signExtentionTo32bits(pc);
		
		currentPC= pc;
	}


	@Override
	public String toString() {
		String result="";
		@SuppressWarnings("unused")
		Enumeration<String> e = registers.keys();		
		result="PC: 0x"+ Long.toHexString(EasyDLXALU.convertToInt(currentPC, false))+"\n";
		String space= "   ";
		for (int i=0; i<=31; i++){
			String key="R"+i;
			if (i>9){
				space= " ";
			}
			result =result+ key+":"+space+registers.get(key)+"\n";
		}
		return result;
	}

}
