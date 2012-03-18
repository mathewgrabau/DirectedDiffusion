/**
 * DirectedDiffusion
 */
package dd.ui.log;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * The panel used to hold the table that is going to show that 
 * 
 * @author mgrabau
 *
 */
public class LogMessagesPanel extends JPanel
{
  private LogTableModel tableModel;
  private JTable table;

  public LogMessagesPanel()
  {
    super(new GridLayout(1, 1));
    init();
  }
  
  void init()
  {
    tableModel = new LogTableModel();
    table = new JTable(tableModel);
    
    table.setPreferredScrollableViewportSize(new Dimension(500, 50));
    
    JScrollPane jsp = new JScrollPane(table);
    add(jsp);
  }
  
  public int getDisplayCount()
  {
    return tableModel.getDisplayedCount();
  }
  
  
  public int getTotalCount()
  {
    return tableModel.getMessageCount();
  }
}
