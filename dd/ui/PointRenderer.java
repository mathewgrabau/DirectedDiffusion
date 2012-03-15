/**
 * DirectedDiffusion
 * 
 */
package dd.ui;

import javax.swing.table.DefaultTableCellRenderer;

/**
 * Implements a way for displaying the information in a java.awt.Point.
 * 
 * Attach using JTable.setDefaultRenderer
 * 
 * @author Mathew Grabau
 *
 */
public class PointRenderer extends DefaultTableCellRenderer 
{
  public PointRenderer()
  {
    super();
  }
  
  public void setValue(Object value) 
  {
    
    String v = "";
    System.out.println("setValue is called");
    if (value != null && value instanceof java.awt.Point)
    {
      java.awt.Point p = (java.awt.Point)value;
      v = String.format("(%d, %d)", p.x, p.y);
    }
    
    setText(v);
  }
}