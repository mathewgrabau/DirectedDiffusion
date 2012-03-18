/**
 * 
 */
package dd.ui.log;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Mathew Grabau
 *
 */
public class LogFrame extends JFrame implements ActionListener, FilterListener
{
  private LogFilterComponent filter;
  private JPanel buttonPanel;
  private JButton clearButton;
  
  public LogFrame(String title)
  {
    super(title);
    init();
  }

  public LogFrame()
  {
    super("dd.ui.log.LogFrame");
    init();
  }

  protected void init()
  {
  
    setLayout(new BorderLayout());
    filter = new LogFilterComponent();
    filter.addListener(this);
    add(filter, BorderLayout.NORTH);
    
    
    // button for clearing the things
    buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    buttonPanel.add(clearButton = new JButton("Clear"));
    add(buttonPanel, BorderLayout.SOUTH);
    buttonPanel.setAlignmentX(RIGHT_ALIGNMENT);
    clearButton.setPreferredSize(clearButton.getMinimumSize());
  }

  /* (non-Javadoc)
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  public void actionPerformed(ActionEvent e)
  {
    // TODO Auto-generated method stub

  }

  public void filterChanged(FilterEvent e)
  {
    // TODO Auto-generated method stub
    System.out.println("filterChanged " + e.isSettingChanged() + " " + e.isLevelChanged());
  }

}
