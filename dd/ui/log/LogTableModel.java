/**
 * 
 */
package dd.ui.log;

import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/**
 * 
 * The basic model used for controlling the events in the model.
 * @author Mathew Grabau
 *
 */
public class LogTableModel extends AbstractTableModel
{
  final String[] columnNames = {"ID", "Time", "Level", "Message"};
  private FilterSetting setting;    // the currently set filtering model
  private LogLevel level;   // the currently set logging level
  private LogLevel defaultLevel;  // when a message is added without a level, they will use this level
  //private ArrayList<ArrayList<Object>> rowData;   // this is the data that actually gets shown
  Vector<Vector<Object>> data;
  private Vector<Vector<Object>> messages;   // this is the collection that has been accumulated
  private boolean logModelMessages;   // if the model should inject some messges about itself
  
  private static int ID_GEN = 0; 
  
  // indexes correspond to the various columns
  final int COL_ID = 0;
  final int COL_TIME = 1;
  final int COL_LEVEL = 2;
  final int COL_MSG = 3;
  
  public LogTableModel() 
  {
    super();
    //columnNames = new String[4];
    //columnNames[0] = "ID";
    //columnNames[1] = "Time";
    //columnNames[2] = "Level";
    //columnNames[3] = "Message";
    
    defaultLevel = level = LogLevel.INFO;
    setting = FilterSetting.ALL;
    
    logModelMessages = true;
    
    messages = new Vector<Vector<Object>>();
    //rowData = new ArrayList<ArrayList<Object>>();
    data = new Vector<Vector<Object>>();
    
    addMessage(this.getClass().getName() + ": log initialized.");
  }
 
  /**
   * Get the next id value for logging into the the message list.
   * @return the id to assign to the next added message
   */
  private static int nextId()
  {
    return ++ID_GEN;
  }
  
  public void setLogModelMessages(boolean enable)
  {
    logModelMessages = enable;
  }
  
  public boolean getLogModelMessages()
  {
    return logModelMessages;
  }
  
  /**
   * Clean out the storage of the current element completely.
   */
  public void removeAll()
  {
    messages.clear();
    data.clear();
  }
  
  /**
   * Set the default level that the logged messages are entered under.
   * @param level the log level that is currently being used.
   */
  public void setDefaultLevel(LogLevel level)
  {
    defaultLevel = level;
    if (logModelMessages)
      addMessage(LogLevel.INFO, this.getClass().getName() + ": default log level set to " + level);
    updateRowData();
  }
  
  public void setFilterSetting(FilterSetting setting)
  {
    this.setting = setting;
    if (logModelMessages)
      addMessage(LogLevel.INFO, this.getClass().getName() + ": filter setting changed to " + setting);
    updateRowData();
  }
  
  /**
   * Set the level used to control the outputted messages (in conjunction with the FilterSetting).
   * @param level new level to assign
   */
  public void setLogLevel(LogLevel level)
  {
    this.level = level;
    if (logModelMessages)
      addMessage(LogLevel.INFO, this.getClass().getName() + ": log level changed to " + level);
    updateRowData();
  }
  
  /**
   * Register a message using the currently configured default logging
   * level. 
   * @param msg String with the message in it
   * 
   * @see LogLevel
   */
  public void addMessage(String msg)
  {
    addMessage(defaultLevel, msg);
  }
  
  /**
   * This allows the user to specify the level of information
   * that their logging implies.
   * @param level The logging level to attach to it
   * @param msg String containing the message
   */
  public void addMessage(LogLevel level, String msg)
  {
    // put it into the messages that have been queued
    Vector<Object> messageStore = new Vector<Object>();
    int id = nextId();
    messageStore.add(new Integer(id));
    messageStore.add(new Date());
    messageStore.add(level);
    messageStore.add(msg);
    
    // insert it into the global store for messages
    addMessage(messageStore);
    
    // update the contents of the actual message now
    updateRowData();
  }
  
  /**
   * Worker method for insertion (ordered) of a new message onto the list.
   * @param messageParts
   */
  private void addMessage(Vector<Object> messageParts)
  {
    int currId = (Integer)messageParts.get(COL_ID);
    boolean inserted = false;
    
    // ordered insert by the row id
    for (int i = 0; i < messages.size(); i++)
    {
      int compareId = (Integer) messages.get(i).get(COL_ID);
      if (currId <= compareId) {
        //messages.add(i, messageParts);
        messages.insertElementAt(messageParts, i);
        inserted = true;
      }
    }
    
    if (!inserted)
    {
      messages.add(messageParts);
    }
  }
  
  /**
   * Build the row data according to the current settings
   * and log level. To start, everything gets flushed out.
   */
  private void updateRowData()
  {
    // clear the data first
    data.clear();
    
    // easiest case, transfer the data out
    if (setting == setting.ALL)
    {
        // TODO do we need better cloning?
        data = (Vector<Vector<Object>>) messages.clone();
    }
    else
    {
      // copy in the rows that are applicable.
      for (Vector<Object> msg : messages)
      {
        LogLevel msgLevel = (LogLevel) msg.get(COL_LEVEL);
        // what operation do we need to perform?
        
        // For only: include if they are the same
        if (setting == FilterSetting.ONLY && msgLevel == level)
        {
          
        }
        // for above: included if the message registers higher on the comparison (positive)
        // the above comparison has been made inclusive
        else if (setting == FilterSetting.ABOVE && LogLevelUtil.compare(level, msgLevel) >= 0)
        {
          data.add(msg);
        }
        // for below: included if the message registers lower on the comparison (negative)
        // the below one is also inclusive
        else if (setting == FilterSetting.BELOW && LogLevelUtil.compare(level, msgLevel) <= 0)
        {
          data.add(msg);
        }
      }
      
    }
  }
  
  /**
   * Return the currently set filtering operation
   * @return Current FilterSetting
   */
  public FilterSetting getSetting()
  {
    return setting;
  }
  
  /**
   * Return the currently set log level.
   * @return Current LogLevel.
   */
  public LogLevel getLevel()
  {
    return level;
  }
  
  public String getColumnName(int column)
  {
    System.out.println("getColumnName was called " + column + " " + columnNames[column]);
    return columnNames[column];
  }
  
  /* (non-Javadoc)
   * @see javax.swing.table.TableModel#getColumnCount()
   */
  public int getColumnCount()
  {
    System.out.println("getColumnCount was called " + columnNames.length);
    return columnNames.length;
  }

  /* (non-Javadoc)
   * @see javax.swing.table.TableModel#getRowCount()
   */
  public int getRowCount()
  {
    System.out.println("getRowCount called ");
    return data.size();
  }
  
  /**
   * Get the number of messages stored.
   * @return
   */
  public int getMessageCount()
  {
    return messages.size();
  }
  
  /**
   * Get the number of displayed messages.
   * @return
   */
  public int getDisplayedCount()
  {
    return getRowCount();
  }

  /* (non-Javadoc)
   * @see javax.swing.table.TableModel#getValueAt(int, int)
   */
  public Object getValueAt(int row, int col)
  {
    System.out.println("getValueAte called " + row + " " + col);
    return data.get(row).get(col);
  }
  
  public Class getColumnClass(int columnIndex)
  {
    switch (columnIndex)
    {
    case COL_ID:
      System.out.println("Returning the Integer.class");
      return Integer.class;
    case COL_TIME:
      System.out.println("Returning the Date.class");
      return Date.class;
    case COL_LEVEL:
      System.out.println("Returning the LogLevel.class");
      return LogLevel.class;
    case COL_MSG:
      System.out.println("Returning the String.class");
      return String.class;
    }
    
    return super.getColumnClass(columnIndex);
  }

}
