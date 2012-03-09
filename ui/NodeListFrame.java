/**
 * A basic frame that 
 * 
 * It depends on:
 * NodeListPanel
 * NodeCountPanel
 */
package ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

/**
 * @author mgrabau
 *
 */
public class NodeListFrame extends JFrame 
{
  private NodeListPanel listPanel;
  private NodeCountPanel countPanel;
  
  private JPanel buttonPanel;
  private JButton closeButton;
  
  public NodeListFrame(String title) 
  {
    super(title);
    init();
  }
  
  /**
   * Create and place the components onto the frame.
   */
  protected void init()
  {
    // create a layout
    
    BorderLayout layout = new BorderLayout();
    setLayout(layout);
    
    countPanel = new NodeCountPanel();
    add(countPanel, BorderLayout.NORTH);
    
    buttonPanel = new JPanel();
    buttonPanel.add(closeButton = new JButton("Close"));
    buttonPanel.setPreferredSize(buttonPanel.getMinimumSize());
    add(buttonPanel, BorderLayout.SOUTH);    
  }
  
  public void addNode()
  {
    // register the node with the dataset.
  }
  
  
  /**
   * Returns the panel to it's initial state.
   */
  public void reset()
  {
  }
}
