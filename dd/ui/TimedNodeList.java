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
 * 
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
    configureTimer(refreshInterval);
  }
  
  public TimedNodeList(String title, Collection<Node> nodes)
  {
    super(title, nodes);
    
    configureTimer(DEFAULT_INTERVAL);
  }
  
  public TimedNodeList()
  {
    super();
    configureTimer(DEFAULT_INTERVAL);
  }
  
  protected void configureTimer(int refreshInterval) 
  {
    this.refreshInterval = refreshInterval;
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
      pauseButton.setEnabled(false);
      resumeButton.setEnabled(true);
      stopUpdateTimer();
    }
    else if (e.getSource() == resumeButton)
    {
      pauseButton.setEnabled(true);
      resumeButton.setEnabled(false);
      startUpdateTimer();
    }
  }

  public void startUpdateTimer()
  {
    listPanel.startUpdateTimer(refreshInterval);
    
    // configure the UI appropriately (since this can be sourced from an outside call)
    pauseButton.setEnabled(true);
    resumeButton.setEnabled(false);
  }
  
  /**
   * Starts the timer on the table model, with the various
   * @param milliSecs Number of milliseconds to set for the update period
   */
  public void startUpdateTimer(int milliSecs)
  {
    refreshInterval = milliSecs;
    listPanel.startUpdateTimer(milliSecs);
    
  }
  
  public void stopUpdateTimer()
  {
    listPanel.stopUpdateTimer();
  }
}
