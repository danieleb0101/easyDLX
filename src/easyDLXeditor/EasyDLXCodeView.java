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
package easyDLXeditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.PlainDocument;
import javax.swing.text.PlainView;
import javax.swing.text.Segment;
import javax.swing.text.Utilities;
/**
 * 
 * @author Daniele Biagi  biagi.daniele@gmail.com
 *
 */
public class EasyDLXCodeView extends PlainView {

    private static HashMap<Pattern, Color> patternColors;

    private static String CAMPI = "\\b(R0|R1|R2|R3|R4|R5|R6|R7|R8|R9|R10|R11|R12|" +
            "R13|R14|R15|R16|R17|R18|R19|R20|R21|R22|R23|R24|R25|R26|R27|R28|R29|R30|R31)\\b";
    private static String ELEMENTS = "\\b(ADD|ADDU|SUB|SUBU|MULT|MULTU|DIV|DIVU|AND|OR|XOR|SLL|SRL|SLT|SGT|SLE|SGE|SEQ|SNE|" +
	"ADDI|ADDUI|SUBI|SUBUI|ANDI|ORI|XORI|LHI|SLLI|SRLI|SLTI|SGTI|SLEI|SGEI|SEQI|SNEI|" +
	"LB|LBU|LH|LHU|LW|SB|SH|SW|BEQZ|BNEZ|JR|JALR|J|JAL|NOP)\\b";
    
    private static String SYMBOLS = "(\\(|\\)|,|;|:)";

    public EasyDLXCodeView(Element elem) {
        super(elem);
        getDocument().putProperty(PlainDocument.tabSizeAttribute, 3);
        
        patternColors = new HashMap<Pattern, Color>();
        patternColors.put(Pattern.compile(CAMPI), Color.BLUE);
        patternColors.put(Pattern.compile(ELEMENTS), Color.MAGENTA);
        patternColors.put(Pattern.compile(SYMBOLS), new Color(205, 50, 50));
    }

    @Override
    protected int drawUnselectedText(Graphics graphics, int x, int y, int p0, int p1) throws BadLocationException {
        Document doc = getDocument();
        String text = doc.getText(p0, p1 - p0);

        Segment segment = getLineBuffer();

        SortedMap<Integer, Integer> startMap = new TreeMap<Integer, Integer>();
        SortedMap<Integer, Color> colorMap = new TreeMap<Integer, Color>();

        // Match all regexes on this snippet, store positions
        for (Map.Entry<Pattern, Color> entry : patternColors.entrySet()) {

            Matcher matcher = entry.getKey().matcher(text);

            while (matcher.find()) {
                startMap.put(matcher.start(), matcher.end());
                colorMap.put(matcher.start(), entry.getValue());
            }
        }

        // TODO: check the map for overlapping parts

        int i = 0;

        // Colour the parts
        for (Map.Entry<Integer, Integer> entry : startMap.entrySet()) {
            int start = entry.getKey();
            int end = entry.getValue();

            if (i < start) {
                graphics.setColor(Color.black);
                doc.getText(p0 + i, start - i, segment);
                x = Utilities.drawTabbedText(segment, x, y, graphics, this, i);
            }


            Font currentFont = graphics.getFont();

            graphics.setColor(colorMap.get(start));
            
           
            
            i = end;
            doc.getText(p0 + start, i - start, segment);
            x = Utilities.drawTabbedText(segment, x, y, graphics, this, start);
            graphics.setFont(currentFont);

        }

        // Paint possible remaining text black
        if (i < text.length()) {
            graphics.setColor(Color.black);
            doc.getText(p0 + i, text.length() - i, segment);
            x = Utilities.drawTabbedText(segment, x, y, graphics, this, i);
        }
        
        return x;
    }}
