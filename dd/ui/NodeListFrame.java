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
import java.util.Collection;

/**
 * Shows a basic window that contains the information about the nodes that are 
 * running in the simulation.
 * 
 * It is a basic grid that displays the node information.
 * 
 * @author mgrabau
 *
 */
public class NodeListFrame extends JFrame 
{
  private NodeListPanel listPanel;
  private NodeCountPanel countPanel;
  
  private JPanel buttonPanel;
  private JButton closeButton;
  
  public NodeListFrame(String title, Collection<Node> nodes)
  {
    super(title);
    init(nodes);
  }
  
  public NodeListFrame(String title) 
  {
    super(title);
    init(null);
  }
  
  /**
   * Create and place the components onto the frame.
   * @param nodes The nodes that the information should be displayed for.
   */
  protected void init(Collection<Node> nodes)
  {
    // create a layout
    
    BorderLayout layout = new BorderLayout();
    setLayout(layout);
    
    countPanel = new NodeCountPanel();
    add(countPanel, BorderLayout.NORTH);
    countPanel.setCount(nodes == null ? 0 : nodes.size());
    
    listPanel = new NodeListPanel(nodes);
    add(listPanel, BorderLayout.CENTER);
    
    buttonPanel = new JPanel();
    
    buttonPanel.add(closeButton = new JButton("Close"));
    buttonPanel.setPreferredSize(buttonPanel.getMinimumSize());
    add(buttonPanel, BorderLayout.SOUTH);    
  }
  
  public void addNode(Node node) throws Exception
  {
    listPanel.addNode(node);
    countPanel.setCount(listPanel.getNodeCount());
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
