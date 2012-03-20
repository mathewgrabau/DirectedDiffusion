/**
 * DirectedDiffusion
 */
package dd.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;

import dd.Node;

/**
 * @author mgrabau
 *
 */
public class TimedNodeList extends NodeListFrame implements ActionListener
{
  protected JButton pauseButton;
  protected JButton resumeButton;
  
  protected int refreshInterval;
  public final int DEFAULT_INTERVAL = 500; // default updates every half a second
  
  public TimedNodeList(String title, Collection<Node> nodes, int refreshInterval)
  {
    super(title, nodes);
    
  }
  
  public TimedNodeList(String title, Collection<Node> nodes)
  {
    super(title, nodes);
  }
  
  public TimedNodeList()
  {
    super();
  }
  
  /* (non-Javadoc)
   * @see dd.ui.NodeListFrame#createButtonPanel()
   */
  @Override
  protected void createButtonPanel()
  {
    buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    pauseButton = new JButton("Pause");
    resumeButton = new JButton("Resume");
    
    buttonPanel.add(pauseButton);
    buttonPanel.add(resumeButton);
    pauseButton.setEnabled(false);
    pauseButton.addActionListener(this);
    resumeButton.addActionListener(this);
    
    add(buttonPanel, BorderLayout.SOUTH);
  }
  
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == pauseButton)
    {
      // TODO implement pausing
    }
    else if (e.getSource() == resumeButton)
    {
      // TODO implement resuming
    }
  }
}
