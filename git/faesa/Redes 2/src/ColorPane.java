import javax.swing.*;
import javax.swing.text.*;
import java.awt.Color;

public class ColorPane extends JTextPane {

  public void appendNaive(Color c, String s) { // naive implementation
    // bad: instiantiates a new AttributeSet object on each call
    SimpleAttributeSet aset = new SimpleAttributeSet();
    StyleConstants.setForeground(aset, c);
   
    int len = getText().length();
    setCaretPosition(len); // place caret at the end (with no selection)
    setCharacterAttributes(aset, false);
    replaceSelection(s); // there is no selection, so inserts at caret
  }

  public void append(Color c, String s) { // better implementation--uses StyleContext
    StyleContext sc = StyleContext.getDefaultStyleContext();
    AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
                                        StyleConstants.Foreground, c);
  
    int len = getDocument().getLength(); // same value as getText().length();
    setCaretPosition(len);  // place caret at the end (with no selection)
    setCharacterAttributes(aset, false);
    replaceSelection(s); // there is no selection, so inserts at caret
  }
}