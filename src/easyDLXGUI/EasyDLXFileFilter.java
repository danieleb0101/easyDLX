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

import java.io.File;

import javax.swing.filechooser.FileFilter;
/**
 * 
 * @author Daniele Biagi  biagi.daniele@gmail.com
 *
 */
public class EasyDLXFileFilter  extends FileFilter {

	   
	    protected static String ACCEPTED_EXT;
	    protected static String CONF_PROP_FILE_NAME;
	    
	     public EasyDLXFileFilter(String s)
	     {
	        ACCEPTED_EXT = s;
	        CONF_PROP_FILE_NAME = "Solo file " + ACCEPTED_EXT;
	    }
	    
	    
	    
	    @SuppressWarnings("static-access")
		public boolean accept(File f)
	    {
	        if (f.isDirectory()) {
	            return true;
	        }

	        String extension = EasyDLXUtilExtension.getExtension(f);
	        if (extension != null) {
	            if (extension.equals(this.ACCEPTED_EXT) ) {
	                    return true;
	            } else {
	                return false;
	            }
	        }

	        return false;
	    }

	    //The description of this filter
	    @SuppressWarnings("static-access")
		public String getDescription() {
	        return this.ACCEPTED_EXT;
	    }
	}

	