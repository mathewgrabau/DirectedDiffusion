/**
 * 
 */
package dd.ui.nodelist;

import java.awt.Dimension;
import java.awt.FlowLayout;

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
    super();
    setupComponent("Count:", 0);
  }
  
  public NodeCountPanel(String label)
  {
    super();
    setupComponent(label, 0);
  }
  
  public NodeCountPanel(String label, int count)
  {
    super();
    setupComponent(label, count);
    
    // TODO add the layout in.
    
  }
  
  /**
   * Build and place the components that are 
   * @param label String used to label the text field for the count
   * @param count The initial value
   */
  protected void setupComponent(String label, int count)
  {
    FlowLayout fl = new FlowLayout();
    fl.setAlignment(FlowLayout.CENTER);
    
    countLabel = new JLabel(label);
    countField = new JTextField();
    countField.setHorizontalAlignment(JTextField.RIGHT);
    countField.setEditable(false);
    setCount(count);
    countField.setPreferredSize(new Dimension(
        countField.getPreferredSize().width * 5, 
        countField.getPreferredSize().height));
    setLayout(fl);
    
    add(countLabel);
    add(countField);
    
    //setPreferredSize(getMinimumSize());
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

