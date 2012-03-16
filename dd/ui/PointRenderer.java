/**
 * DirectedDiffusion
 *  
 *  Renders the java.awt.Point at (x, y) pairs.
 */
package dd.ui;

import javax.swing.table.DefaultTableCellRenderer;

/**
 * Implements a way for displaying the information in a java.awt.Point.
 * 
 * Attach using JTable.setDefaultRenderer
 * 
 * @author Mathew Grabau (mgrabau)
 *
 */
public class PointRenderer extends DefaultTableCellRenderer 
{
  public PointRenderer()
  {
    super();
  }
  
  /***
   * Sets the cell text for points.
   * 
   * @param value The value to convert (must be type java.awt.Point)
   * @throws IllegalArgumentException if the value is not an instance of java.awt.Point
   * 
   * Just returns a string with "null" if the argument is null
   */
  public void setValue(Object value) 
  {
    String v = "";
    if (value == null)
    {
      v = "null";
    }
    else if(value instanceof java.awt.Point)
    {
      java.awt.Point p = (java.awt.Point)value;
      v = String.format("(%d, %d)", p.x, p.y);    
    }
    else 
    {
      throw new IllegalArgumentException("Cannot render type " + value.getClass().getName());
    }
    
    setText(v);
  }
}