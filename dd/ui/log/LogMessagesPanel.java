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
 * The panel used to hold the table that shows the messages.
 * The majority of the class is forwarding/relaying between the table model
 * and the outside component.
 * 
 * @see LogMessagesPanel.getTableModel for the accessor to table model (heed note).
 * 
 * @author Mathew Grabau (mgrabau)
 *
 */
public class LogMessagesPanel extends JPanel
{
  private LogTableModel tableModel;
  private JTable table;
  private JScrollPane scrollPane;

  public LogMessagesPanel()
  {
    super(new GridLayout(1, 1));
    init();
  }
  
  protected void init()
  {
    tableModel = new LogTableModel();
    table = new JTable(tableModel);
    
    table.setPreferredScrollableViewportSize(new Dimension(500, 50));
    
    scrollPane = new JScrollPane(table);
    add(scrollPane);
  }
  
  public int getDisplayCount()
  {
    return tableModel.getDisplayedCount();
  }
  
  public int getTotalCount()
  {
    return tableModel.getMessageCount();
  }
  
  public void clear()
  {
    tableModel.removeAll();
  }
  
  public void addMessage(String text)
  {
    tableModel.addMessage(text);
  }
  
  
  public void addMessage(LogLevel level, String text)
  {
    tableModel.addMessage(level, text);
  }
  
  public void setFilterSetting(FilterSetting setting)
  {
    tableModel.setFilterSetting(setting);
  }
  
  public FilterSetting getFilterSetting()
  {
    return tableModel.getSetting();
  }
  
  public void setLogLevel(LogLevel level)
  {
    tableModel.setLogLevel(level);
  }
  
  public LogLevel getLogLevel()
  {
    return tableModel.getLevel();
  }
  
  /**
   * Enable/disable the messages from the logger model itself.
   * @param enable Enable/disable the messages.
   */
  public void setModelMessagesEnabled(boolean enable)
  {
    tableModel.setLogModelMessages(enable);
  }
  
  /**
   * Get the logger model messages enabled status.
   * @return Status for enabled.
   */
  public boolean getModelMessagesEnabled()
  {
    return tableModel.getLogModelMessages();
  }
  
  /**
   * Returns the reference to the LogTableModel that is registered with this 
   * panel/JTable so that it can be accessed directly as required.
   * 
   * NOTE: Use this method only if there is not already an accessor/mutator 
   * available. Take care if using this method, results are undefined when
   * the model is directly change.
   * @return Reference to the LogTableModel instance
   */
  public LogTableModel getTableModel()
  {
    return tableModel;
  }
}
