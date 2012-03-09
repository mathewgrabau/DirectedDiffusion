/**
 * 
 */
package ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A panel showing count of the nodes in the simulation.
 * 
 * It contains a field (not editable, set to the number) with 
 * a label for that field. The label's text field can be set
 * using the constructor.
 * 
 * @author mgrabau
 *
 */
public class NodeCountPanel extends JPanel
{
  private JLabel countLabel;
  private JTextField countField;
  
  public NodeCountPanel()
  {
    setupComponent("Count:", 0);
  }
  
  public NodeCountPanel(String label)
  {
    setupComponent(label, 0);
  }
  
  public NodeCountPanel(String label, int count)
  {
    setupComponent(label, count);
    
    // TODO add the layout in.
    
  }
  
  /**
   * Build and place the components that are 
   * @param label
   * @param count
   */
  protected void setupComponent(String label, int count)
  {
    countLabel = new JLabel(label);
    countField = new JTextField(count);
    countField.setEditable(false);
    
    add(countLabel);
    add(countField);
  }
  
  /**
   * Set the count of the nodes.
   * @param count
   */
  public void setCount(int count)
  {
    if (count < 0)
    {
      // TODO throw an exception here
    } else
    {
      countField.setText(Integer.toString(count));
    }
  }
}
