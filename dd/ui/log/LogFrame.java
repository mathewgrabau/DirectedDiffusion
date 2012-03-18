/**
 * 
 */
package dd.ui.log;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * @author Mathew Grabau
 *
 */
public class LogFrame extends JFrame implements ActionListener, FilterListener
{
  private LogFilterComponent filter;
  private JPanel buttonPanel;
  private JButton clearButton;
  private JPanel tablePanel;
  private LogTableModel tableModel;
  private JTable messagesTable;
  
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
    
    // add in the table model for events now
    messagesTable = new JTable(tableModel = new LogTableModel());
    messagesTable.setPreferredScrollableViewportSize(new Dimension(500, 50));
    messagesTable.setFillsViewportHeight(true);
    JScrollPane pane = new JScrollPane();
    JPanel p = new JPanel(new GridLayout(1, 1));
    p.add(pane);
    add(p, BorderLayout.CENTER);
    
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
