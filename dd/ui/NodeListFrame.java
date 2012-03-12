/**
 * A basic frame that 
 * 
 * It depends on:
 * NodeListPanel
 * NodeCountPanel
 */
package dd.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dd.Node;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
    
    
    listPanel = new NodeListPanel();
    add(listPanel, BorderLayout.CENTER);
    
    buttonPanel = new JPanel();
    
    buttonPanel.add(closeButton = new JButton("Close"));
    buttonPanel.setPreferredSize(buttonPanel.getMinimumSize());
    add(buttonPanel, BorderLayout.SOUTH);    
  }
  
  public void addNode(Node node)
  {
    //listPanel.addNode(node);
  }
  
  
  /**
   * Returns the panel to it's initial state.
   */
  public void reset()
  {
  }
  
  
  public void setCount(int count) {
    countPanel.setCount(count);
  }
}
