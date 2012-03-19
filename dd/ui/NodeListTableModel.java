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
  
  private void startTimer(int period)
  {
    if (updateTimer == null)
    {
      updateTimer = new Timer();
      // This is going to go through and poll the nodes for their status
      updateTimer.scheduleAtFixedRate(new TimerTask() {
        
        @Override
        public void run()
        {
           // Then let's update the nodes now
        }
      }, new Date(), period);
    }
  }
  
  private void timerTick()
  {
    
  }

  private void insertNodeToData(Node node)
  {
    Vector<Object> v = new Vector<Object>();
    v.add(node.nodeID);
    v.add(new Point(node.xCoord, node.yCoord));
    v.add(node.numNeighbors());
    // TODO calculate the number of interests (will require a public API of some sort)
    v.add(Integer.toString(1));
    v.add(new Integer(node.nodeEnergyUsed));
    
    data.add(v);
    
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
    return data.size();
  }

  public Object getValueAt(int row, int col)
  {
    // decode the value of the data now
    // Node inquired = sourceData.;

    return data.get(row).get(col);
  }
  
  public void addNode(Node n)
  {
    if (n != null)
    {
      for (Node storedNode : sourceData)
      {
        // Since it has been found, then no worries here
        if (storedNode.nodeID == n.nodeID)
        {
          return;
        }
      }
      
      // Need to add in the new node to the collection
      insertNodeToData(n);
      sourceData.add(n);
      
      fireTableDataChanged();
    }
  }

  @Override
  public Class getColumnClass(int columnIndex)
  {
    //System.out.println("columnIndex: " + columnIndex);
    //System.out.println("The class type is " + getValueAt(0, columnIndex).getClass().getName());
    return getValueAt(0, columnIndex).getClass();
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex)
  {

    // TODO Add the code to update the various values.
    super.setValueAt(aValue, rowIndex, columnIndex);
  }
}

