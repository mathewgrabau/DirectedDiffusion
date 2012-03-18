/**
 * 
 */
package dd.ui.log;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * @author Mathew Grabau
 *
 */
public class LogFrame extends JFrame implements ActionListener, FilterListener
{
  private LogFilterComponent filter;
  
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
