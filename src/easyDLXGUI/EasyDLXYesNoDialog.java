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
package easyDLXGUI;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * 
 * @author Daniele Biagi  biagi.daniele@gmail.com
 *
 */
public class EasyDLXYesNoDialog  extends JDialog
	{
	 
	private static final long serialVersionUID = 6504025820108159277L;
		private String name;

	    public EasyDLXYesNoDialog(String nome)
	    {
	        name = nome;
	    }

	    public int setVisible()
	    {
	        JOptionPane pane = new JOptionPane(
	        name+ " already exists. \nDo you want to replace it?");
	        Object[] options = new String[] { "Yes", "No" };
	        pane.setOptions(options);
	        JDialog dialog = pane.createDialog(new JFrame(), "Confirm");
	        dialog.setVisible(true);
	        Object obj = pane.getValue();
	        int result = -1;
	        
	        for (int k = 0; k < options.length; k++)
	          if (options[k].equals(obj))
	          {
	            result = k;
	            break;
	          }

	        return result;
	    }
	}



