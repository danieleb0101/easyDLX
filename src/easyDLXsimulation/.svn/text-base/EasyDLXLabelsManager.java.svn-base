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
public class EasyDLXLabelsManager {
	// LABEL - ADDRESS  ex: "lp1"-1000 	
	private Hashtable<String, String> labels;
	//private Hashtable<String,Cursors> cursors;

	public EasyDLXLabelsManager() {
		labels= new Hashtable<String, String>();
		//cursors= new Hashtable<String, Cursors>();
	}

	public boolean addLabel(String label, String address){
		if ((label==null)||(label.compareTo("")==0)){
			return false;
		}
		label=label.trim();
		if (label.endsWith(":")){
			label= label.replaceFirst(":", "");
		}
		if (labels.containsKey(label)){
			return false;
		}else{
			address= EasyDLXMemoryManager.signExtentionTo32bits(address);
			labels.put(label,address);
			return true;
		}
	}

	public String getStringAddress(String label){
		String value= labels.get(label);
		if (value==null){
			return null;
		}else{
			return value;
		}
	}

	public void eraseLabels(){
		labels.clear();
		labels= new Hashtable<String, String>();
		
	}
	
	@Override
	public String toString() {
		String result="";
		Enumeration<String> e = labels.keys();
		
		while(e.hasMoreElements()){
			String key=e.nextElement();
			result =result+ "LABEL: "+key+" - ADDR: 0x"+Long.toHexString(EasyDLXALU.convertToInt(labels.get(key),false))+"\n";
		}	
		return result;
	}
}
