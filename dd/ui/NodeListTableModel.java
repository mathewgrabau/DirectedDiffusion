package dd.ui;

import javax.swing.table.AbstractTableModel;
import dd.Node;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
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

  public NodeListTableModel(ArrayList<Node> nodes)
  {
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
   * The implementation
   * 
   * @param nodes
   */
  public NodeListTableModel(Vector<Node> nodes)
  {  
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

  private void insertNodeToData(Node node)
  {
    Vector<Object> v = new Vector<Object>();
    v.add(node.nodeID);
    v.add(new Point(node.xCoord, node.yCoord));
    v.add(node.numNeighbors());
    // TODO calculate the number of interests
    v.add(Integer.toString(1));
    v.add(new Integer(node.nodeEnergyUsed));
    
    data.add(v);
    
  }

  public int getColumnCount()
  {
    // TODO Auto-generated method stub
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

