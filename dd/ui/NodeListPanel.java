/**
 * 
 */
package dd.ui;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.Point;  // needed for supporting the Points for the node location.
import java.lang.reflect.Array;
import java.util.Vector;

import dd.Node;

/**
 * @author mgrabau
 *
 */
public class NodeListPanel extends JPanel {
  
  private String[] columnNames = {
    "ID",
    "Location"
  };
  
  private Vector<Object> rowData;
  
  private JTable table;
  
  int currNode; // current node that is in the array
  Node[] registeredNodes;
  
  //public NodeListPanel(String
  
  public NodeListPanel()
  {
    super();
    
    rowData = new Vector<Object>();
    Vector<Object> row = new Vector<Object>();
    row.add("1");
    rowData.add(row);
    
    Vector<Object> colData = new Vector<Object>();
    colData.add("ID");
    colData.add("Res Energy");
        
    table = new JTable(rowData, colData); 
    
    add(table);
    
    registeredNodes = new Node[5];
    currNode = 0;
  }
  
  
  public void addNode(Node n)
  {
    if (currNode > registeredNodes.length)
    {
      // Resize the storage
      Node[] temp = new Node[2 * registeredNodes.length];
      System.arraycopy(registeredNodes, 0, temp, 0, registeredNodes.length);
      
      if (temp[0] != registeredNodes[0])
      {
        
      }
      }
    }
  }
  
  
  public void removeNode(Node n)
  {
    
  }
  
  
  public void reset()
  {
    
  }
}
