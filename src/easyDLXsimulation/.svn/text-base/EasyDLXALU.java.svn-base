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
public class EasyDLXALU {
	//public static boolean isZero=true;
	//public static boolean isPositive=true;

	public static String sub(String rs1, String rs2,boolean signed ){
		long  rs1Value=convertToInt(rs1,signed);
		long  rs2Value=convertToInt(rs2,signed);

		long result= rs1Value-rs2Value;
		String resultString = ignoreOverflow(result);
		
		return resultString;
	}
	
	public static String add(String rs1, String rs2,boolean signed ){
		long  rs1Value=convertToInt(rs1,signed);
		long  rs2Value=convertToInt(rs2,signed);
		long result= rs1Value+rs2Value; 
		
		String resultString = ignoreOverflow(result);
		return resultString;
	}
	
	public static String mul(String rs1, String rs2,boolean signed ){
		long  rs1Value=convertToInt(rs1,signed);
		long  rs2Value=convertToInt(rs2,signed);
		long result= rs1Value*rs2Value; 
		
		String resultString = ignoreOverflow(result);
		return resultString;
	}

	public static String div(String rs1, String rs2,boolean signed ){
		long  rs1Value=convertToInt(rs1,signed);
		long  rs2Value=convertToInt(rs2,signed);
		long result= rs1Value/rs2Value; 
	
		String resultString = ignoreOverflow(result);
		return resultString;
	}
	
	public static String and(String rs1, String rs2){
		String result="";
		if (rs1.length()<32){
			rs1=EasyDLXMemoryManager.signExtentionTo32bits(rs1);
		}
		if (rs2.length()<32){
			rs2=EasyDLXMemoryManager.signExtentionTo32bits(rs2);
		}

		for (int i=0;i<=31;i++){
			if ((rs1.charAt(i)==rs2.charAt(i))&&(rs1.charAt(i)=='1')){
				result=result+"1";
			}else{
				result=result+"0";
			}
		}		
		return result;
	}

	public static String or(String rs1, String rs2){
		String result="";
		if (rs1.length()<32){
			rs1=EasyDLXMemoryManager.signExtentionTo32bits(rs1);
		}
		if (rs2.length()<32){
			rs2=EasyDLXMemoryManager.signExtentionTo32bits(rs2);
		}
		
		for (int i=0;i<=31;i++){
			if ((rs1.charAt(i)==rs2.charAt(i))&&(rs1.charAt(i)=='0')){
				result=result+"0";
			}else{
				result=result+"1";
			}
		}
		return result;
	}
	
	public static String xor(String rs1, String rs2){
		String result="";	
		if (rs1.length()<32){
			rs1=EasyDLXMemoryManager.signExtentionTo32bits(rs1);
		}
		if (rs2.length()<32){
			rs2=EasyDLXMemoryManager.signExtentionTo32bits(rs2);
		}
		
		for (int i=0;i<=31;i++){
			if (rs1.charAt(i)==rs2.charAt(i)){
				result=result+"0";
			}else{
				result=result+"1";
			}
		}
		return result;
	}

	public static String shift(String rs1,String rs2, boolean shiftLeft){
		if (rs1.length()<32){
			rs1=EasyDLXMemoryManager.signExtentionTo32bits(rs1);
		}
		if (rs2.length()<32){
			rs2=EasyDLXMemoryManager.signExtentionTo32bits(rs2);
		}
		
		long times=convertToInt(rs2,true);
		String result=rs1;
		for (int i=0; i<times; i++){
			result=shiftOnePosition(result, shiftLeft);
		}
		return result;
	}

	public static String setInstr(String rs1, String rs2, String nameOp){
		
		if (rs1.length()<32){
			rs1=EasyDLXMemoryManager.signExtentionTo32bits(rs1);
		}
		if (rs2.length()<32){
			rs2=EasyDLXMemoryManager.signExtentionTo32bits(rs2);
		}
		
		long  rs1Value=convertToInt(rs1,true);
		long  rs2Value=convertToInt(rs2,true);
		String op= nameOp.trim();

		if(op.compareTo("SLT")==0){
			if (rs1Value<rs2Value){
				return EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(1));
			}else{
				return EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(0));
			}
		}
		if(op.compareTo("SGT")==0){
			if (rs1Value>rs2Value){
				return EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(1));
			}else{
				return EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(0));
			}
		}
		if(op.compareTo("SLE")==0){
			if (rs1Value<=rs2Value){
				return EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(1));
			}else{
				return EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(0));
			}
		}

		if(op.compareTo("SGE")==0){
			if (rs1Value>=rs2Value){
				return EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(1));
			}else{
				return EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(0));
			}
		}
		if(op.compareTo("SEQ")==0){
			if (rs1Value==rs2Value){
				return EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(1));
			}else{
				return EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(0));
			}
		}

		if(op.compareTo("SNE")==0){
			if (rs1Value!=rs2Value){
				return EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(1));
			}else{
				return EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(0));
			}
		}
		return null;
	}

	public static String shiftOnePosition(String rs1,boolean shiftLeft){
		String result="";
		if (shiftLeft){
			for (int i=1;i<=31;i++){
				result=result+rs1.charAt(i);
			}
			result=result+"0";
		}else{
			result="0";
			for (int i=0;i<=30;i++){
				result=result+rs1.charAt(i);
			}
		}
		return result;
	}

	public static long convertToInt(String binaryString,boolean signed){
		long result=0;
		
		if (binaryString.length()<32){
			binaryString= EasyDLXMemoryManager.signExtentionTo32bits(binaryString);
		}
		if (binaryString.length()>32){
			binaryString= binaryString.substring(binaryString.length()-32, binaryString.length());
		}
		
		int bit=Integer.parseInt(""+binaryString.charAt(binaryString.length()-32));

		if(signed){
			result= -(bit)*((long)Math.pow(2, 31));
		}else{
			result= (bit)*((long)Math.pow(2, 31));
		}
		for (int i=1; i<=31; i++){
			bit=Integer.parseInt(""+binaryString.charAt(binaryString.length()-32+i));
			result=result+ bit*((long)Math.pow(2, 31-i));
		}
		return result;
	}
	
	public static String ignoreOverflow(long result) {
		String resultString= Long.toBinaryString(result);
		if(resultString.length()<32){
			resultString=EasyDLXMemoryManager.signExtentionTo32bits(resultString);
		}
		resultString=resultString.substring(resultString.length()-32, resultString.length());
		return resultString;
	}
}
