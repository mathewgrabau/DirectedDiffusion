/**
 * 
 */
package dd.ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;  // needed for supporting the Points for the node location.
import java.lang.reflect.Array;
import java.util.Vector;

import dd.Node;

/**
 * 
 * @author mgrabau
 *
 */
public class NodeListPanel extends JPanel {
   
  
  private Vector<Object> rowData;
  
  private JTable table;
  
  int currNode; // current node that is in the array
  Node[] registeredNodes;
  
  //public NodeListPanel(String
  
  public NodeListPanel()
  {
    //super(new GridLayout(1, 0));
    super();
     
    /*
    rowData = new Vector<Object>();
    Vector<Object> row = new Vector<Object>();
    row.add("1");
    rowData.add(row);
    
    Vector<Object> colData = new Vector<Object>();
    colData.add("ID");
    colData.add("Res Energy");
    */
    
    table = new JTable(new NodeListTableModel());
    table.setPreferredScrollableViewportSize(new Dimension(500,70));
    table.setFillsViewportHeight(true);
    
    //JScrollPane scrollPane = new JScrollPane(table);
    
    //add(scrollPane);
    add(new JScrollPane(table));
    
    
    setBounds(0, 0, 400, 400);
    
    registeredNodes = new Node[1];
    currNode = 0;
  }
  
  private void addToTable(Node n)
  {
    // 1) insert for the data 
    
  }
  
  
  private Vector<Object> formatNodeForRow(Node n)
  {
    /*
    Vector<Object> rowData = new Vector<Object>();
    
    rowData.add(Integer.toString(n.nodeID));
    
    return rowData;
    */
    
    return null;
  }
  
  
  public void addNode(Node n) throws Exception
  {
    /*
    if (currNode >= registeredNodes.length)
    {
      // Resize the storage
      Node[] temp = new Node[2 * registeredNodes.length];
      System.arraycopy(registeredNodes, 0, temp, 0, registeredNodes.length);
      
      if (temp[0] != registeredNodes[0])
      {
        throw new Exception("Apparently not just copying the reference");
      }
      else
      {
        System.out.println("addNode, comparison worked properly");
      }
      
    }
    else
    {
      if (n == null)
      {
        throw new NullPointerException();
      }
      else
      {
        System.out.println("addNode, adding the new node");
        
        // add the reference to the one tracked.
        registeredNodes[currNode++] = n;
        
        Vector<Object> rd = formatNodeForRow(n);
        rowData.add(rd);
        
      }
    }
    */
  }
  
  
  public void removeNode(Node n)
  {
    
  }
  
  
  public void reset()
  {
    
  }
}
