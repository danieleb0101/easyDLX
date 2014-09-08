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
/**
 * 
 * @author Daniele Biagi  biagi.daniele@gmail.com
 *
 */

public class EasyDLXControlFlow {

	/*
	 * it returns the new pc
	 */
	public static String branch(String value,String label,boolean isBranchEQZ,String currentPC,EasyDLXLabelsManager lm){
		long long_value=EasyDLXALU.convertToInt(value, true);
		String result;
		if (long_value==0){
			if (isBranchEQZ){
				//branch taken
				result=lm.getStringAddress(label.trim());
			}else{
				result=EasyDLXALU.add(currentPC, EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(4)),false);
			}
		}else{
			if (isBranchEQZ){
				//branch untaken
				result=EasyDLXALU.add(currentPC, EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(4)),false);
			}else{
				result=lm.getStringAddress(label.trim());
			}
			
		}
		return result;
	}
	
	/*
	 * it returns the new value of PC
	 */
	public static String jump(String label,EasyDLXLabelsManager lm,boolean isJAL, String currentPC, EasyDLXRegistersManager rm){
		if (isJAL){
			
			String result=EasyDLXALU.add(currentPC, EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(4)),false);
			rm.updateRegister("R31", result.substring(0, 32));
		}
		return lm.getStringAddress(label);
	}
}
