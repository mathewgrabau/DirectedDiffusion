package dd.ui;

import javax.swing.table.AbstractTableModel;
import dd.Node;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class NodeListTableModel extends AbstractTableModel // implements
// TableModelListener
{
  final String[] columnNames = { "ID", "Location", "Num Neighbors",
      "Num Interests", "Energy Used" };

  // These constants can be used in implementing various aspects of the table.
  public final int COL_ID = 0;
  public final int COL_LOCATION = 1;
  public final int COL_NUM_NEIGHBORS = 2;
  public final int COL_NUM_INTERESTS = 3;
  public final int COL_ENERGY_USED = 4;

  Collection<Node> sourceData;
  Vector<Vector<Object>> data;

  private Timer updateTimer;
  private boolean timerRunning;
  
  public NodeListTableModel(ArrayList<Node> nodes)
  {
    super();
    data = new Vector<Vector<Object>>();
    sourceData = nodes;
    if (nodes != null)
    {
      for (Node node : nodes)
      {
        insertNodeToData(node);
      }
    }
  }

  /**
   * Registers the node with a new collection
   * 
   * @param nodes
   */
  public NodeListTableModel(Vector<Node> nodes)
  {  
    super();
    data = new Vector<Vector<Object>>();
    sourceData = nodes;
    if (nodes != null)
    {
      for (Node node : nodes)
      {
        insertNodeToData(node);
      }
    }
  }

  public NodeListTableModel(Collection<Node> nodes)
  {
    super();
    data = new Vector<Vector<Object>>();
    sourceData = nodes;
    if (nodes != null)
    {
      for (Node node : nodes)
      {
        insertNodeToData(node);
      }
    }
  }
  
  /**
   * Returns the row data for a particular node.
   * 
   * @param node  Reference to the node
   * @return Reference to the applicable vector. null if nothing is found/null 
   * reference is passed in
   */
  private Vector<Object> getNodeRow(Node node)
  {
    if (node == null)
    {
      return null;
    }
    
    for (Vector<Object> curr : data)
    {
      int id = (Integer) curr.get(COL_ID);
      if (id == node.nodeID)
      {
        return curr;
      }
    }
    
    return null;
  }
  
  public boolean isTimerRunning()
  {
    return timerRunning;
  }
  
  public void startTimer()
  {
    
  }
  
  public void stopTimer()
  {
    if (timerRunning)
    {
      updateTimer.cancel();
      updateTimer = null;
      timerRunning = false;
    }
  }
  
  /**
   * Set the time between checking for the 
   * @param ms
   */
  public void setUpdatePeriod(int ms)
  {
    if (!timerRunning)
    {
      startTimer(ms);
    }
  }
  
  /**
   * Enumerate the nodes and update the model with the new data derived from them.
   */
  public void updateModel()
  {
    synchronized (this)
    {
      for (Node node : sourceData)
      {
        // look up the node data
        Vector<Object> row = getNodeRow(node);
        if (row != null)
        {
          // TODO Update more parameters
          row.set(COL_ENERGY_USED, node.nodeEnergyUsed);
        } else
        {
          System.err.println("updateModel--> Warning!!! Node " + node.nodeID
              + " could not be found...");
        }
      }
    }
    fireTableDataChanged();
  }
  
  private void startTimer(int period)
  {
    if (updateTimer == null)
    {
      updateTimer = new Timer();
    }
    
    // This is going to go through and poll the nodes for their status
    updateTimer.scheduleAtFixedRate(new TimerTask() {

      @Override
      public void run()
      {
        updateModel();
      }
    }, new Date(), period);

    timerRunning = true;
  }
  
  private void timerTick()
  {
    
  }

  /**
   * Perform an insert of data into the model. Typically an ordered insert.
   * 
   * @param node 
   */
  private void insertNodeToData(Node node)
  {
    Vector<Object> v = new Vector<Object>();
    v.add(node.nodeID);
    v.add(new Point(node.xCoord, node.yCoord));
    v.add(node.numNeighbors());
    // TODO calculate the number of interests (will require a public API of some sort)
    v.add(Integer.toString(1));
    v.add(new Integer(node.nodeEnergyUsed));
    
    synchronized (this)
    {
      // Go through and do an ordered insert
      boolean inserted = false;
      for (int i = 0; i < data.size() && !inserted; i++)
      {
        Vector<Object> curr = data.get(i);
        int id = (Integer) curr.get(COL_ID);
        if (node.nodeID < id)
        {
          data.add(i, v);
          inserted = true;
        }
      }

      if (!inserted)
      {
        data.add(v);
      }
    }
  }

  public int getColumnCount()
  {
    return columnNames.length;
  }

  public String getColumnName(int col)
  {
    return columnNames[col];
  }

  public int getRowCount()
  {
    int size;
    synchronized (this)
    {
      size = data.size();
    }
    return size;
  }

  public Object getValueAt(int row, int col)
  {
    // decode the value of the data now
    // Node inquired = sourceData.;
    Object value;
    
    // data.get(row).get(col);
    synchronized (this)
    {
      value = data.get(row).get(col);
    }
    
    return value;
  }
  
  public void addNode(Node n)
  {
    if (n != null)
    {
      synchronized(this) {
        boolean found = false;
        for (Node storedNode : sourceData)
        {
          // Since it has been found, then no worries here
          if (storedNode.nodeID == n.nodeID)
          {
            found = true;
            break;
          }
        }
      
        // Need to add in the new node to the collection
        if (!found)
        {
          insertNodeToData(n);
          sourceData.add(n);
        }
      }
      fireTableDataChanged();
    }
  }

  @Override
  public Class getColumnClass(int columnIndex)
  {
    return getValueAt(0, columnIndex).getClass();
  }
}

