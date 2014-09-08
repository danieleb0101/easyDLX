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
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

public class EasyDLXMemoryManager {
	//ADDRESS - VALUE  ex: 34d-"10010001" 	
	private Hashtable<String, String> memory;

	public EasyDLXMemoryManager() {
		memory= new Hashtable<String, String>();
	}

	public void eraseMemory(){
		memory.clear();
		memory= new Hashtable<String, String>();
	}

	public boolean storeByte(String address, String value){
		
		address=signExtentionTo32bits(address);
		int length=8;

		if (value.length()<(length)){
			length= value.length();
			//System.out.println("value:"+value.substring(0, length));
			memory.put(address, value.substring(0, length));
		}else{
			//System.out.println("value:"+value.substring(value.length()-8, value.length()));
			memory.put(address, value.substring(value.length()-8, value.length()));
		}
		return true;

	}
	public boolean storeHalfWord(String address, String value){
		//The address must be aligned to 2
		address=signExtentionTo32bits(address);
		value=signExtentionTo32bits(value);
		
		memory.put(address, value.substring(24,32));		
		memory.put(EasyDLXALU.add(address, EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(1)), false), value.substring(16,24));
		return true;
	}
	public boolean storeWord(String address, String value){
		//The address must be aligned to 4
		address=signExtentionTo32bits(address);
		value=signExtentionTo32bits(value);
		
		memory.put(address, value.substring(24,32));		
		memory.put(EasyDLXALU.add(address, EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(1)), false), value.substring(16,24));
		memory.put(EasyDLXALU.add(address, EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(2)), false), value.substring(8,16));
		memory.put(EasyDLXALU.add(address, EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(3)), false), value.substring(0,8));
		return true;
	}

	public String loadByte(String address, boolean signed){
		address=signExtentionTo32bits(address);
		String result=memory.get(address);
		if (result==null){
			return null;
		}
		if (signed){
			result=signExtentionTo32bitsSigned(result);
		}else{
			result=signExtentionTo32bits(result);
		}

		return result;
	}

	public String loadHalfWord(String address, boolean signed){
		address=signExtentionTo32bits(address);
		String result= memory.get(address);
		if (result==null){
			return null;
		}
		String addrh=EasyDLXALU.add(address, EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(1)), false);
		String temp=memory.get(addrh);
		//System.out.println("addresses: "+ address +"\n"+ addrh);
		//System.out.println("results: "+ result +"\n"+ temp);
		if (temp==null){
			return null;
		}
		result=temp+""+ result;
		if (signed){
			result=signExtentionTo32bitsSigned(result);
		}else{
			result=signExtentionTo32bits(result);
		}

		return result;
	}

	public String loadWord(String address){
		address=signExtentionTo32bits(address);
		String result=memory.get(address);
		
		if (result==null){
			return null;
		}
		
		String addrh=EasyDLXALU.add(address, EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(1)), false);
		String temp=memory.get(addrh);
		if (temp==null){
			return null;
		}
		result=temp+""+result;
		
		addrh=EasyDLXALU.add(address, EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(2)), false);
		temp=memory.get(addrh);
		if (temp==null){
			return null;
		}
		result=temp+""+result;		
		addrh=EasyDLXALU.add(address, EasyDLXMemoryManager.signExtentionTo32bits(Integer.toBinaryString(3)), false);
		temp=memory.get(addrh);
		if (temp==null){
			return null;
		}
		result=temp+""+result;
		return result;
	}


	public static  boolean checkAlignedAddress(String address, int alignedTo){
		address=signExtentionTo32bits(address);
		long addr=EasyDLXALU.convertToInt(address, false);
		if ((addr%alignedTo)!=0){
			return false;
		}
		return true;
	}

	public static String signExtentionTo32bits(String binaryString){
		
		String result=binaryString;
		while(result.length()<32){
			result="0"+result;
		}
		
		if (result.length()>32){
			result= result.substring(result.length()-32, result.length());
		}		
		return result;
	}

	
	public static String signExtentionTo32bitsSigned(String binaryString){
		String result=binaryString;
		while(result.length()<32){
			result=binaryString.charAt(0)+result;
		}
		if (result.length()>32){
			result= result.substring(result.length()-32, result.length());
		}
		return result;
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		String result="";

		@SuppressWarnings({ "rawtypes" })
		Vector v = new Vector(memory.keySet());
		Collections.sort(v);
		Iterator<String> it = v.iterator();
		while (it.hasNext()) {
			String key=it.next();
			if(memory.get(key).contains("CODE")){
				result =result+ "ADDR: 0x"+Long.toHexString(EasyDLXALU.convertToInt(key,false)) +" - "+memory.get(key)+"\n";
			}else{
				result =result+ "ADDR: 0x"+Long.toHexString(EasyDLXALU.convertToInt(key,false))+" - byte: 0b"+memory.get(key)+"\n";
			}
		}
		return result;
	}
}
