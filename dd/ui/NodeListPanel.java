/**
 * 
 */
package dd.ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;  // needed for supporting the Points for the node location.
import java.lang.reflect.Array;
import java.util.Collection;
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
  
  private NodeListTableModel tableModel;
  
  //public NodeListPanel(String
  
  public NodeListPanel()
  {
    super();
    initPanel(null);
  }
  
  /**
   * Constructor for the panel. Requires the passing of th e
   * @param nodes
   */
  public NodeListPanel(Collection<Node> nodes)
  {
    //super(new GridLayout(1, 0));
    super();
    initPanel(nodes);
     
    /*
    rowData = new Vector<Object>();
    Vector<Object> row = new Vector<Object>();
    row.add("1");
    rowData.add(row);
    
    Vector<Object> colData = new Vector<Object>();
    colData.add("ID");
    colData.add("Res Energy");
    */
    
    
    
    //JScrollPane scrollPane = new JScrollPane(table);
    
    //add(scrollPane);
    
  }
  
  /**
   * Initialize the panel. If passing null for the nodes parameter, then 
   * the panel will just appear empty.
   * @param nodes
   */
  private void initPanel(Collection<Node> nodes)
  {
    tableModel = new NodeListTableModel(nodes);
    table = new JTable(tableModel);
    table.setPreferredScrollableViewportSize(new Dimension(500,70));
    table.setFillsViewportHeight(true);
    
    // Register the rendering for the table
    table.setDefaultRenderer(java.awt.Point.class, new PointRenderer());
    
    add(new JScrollPane(table));
    
    setBounds(0, 0, 400, 400);
  }
  
  private void addToTable(Node n)
  {
    // 1) insert for the data 
    tableModel.addNode(n);
  }
  
  
  private Vector<Object> formatNodeForRow(Node n)
  {
   
    Vector<Object> rowData = new Vector<Object>();
    
    rowData.add(Integer.toString(n.nodeID));
    rowData.add(new Point(n.xCoord, n.yCoord));
    
    rowData.add(Integer.toString(n.nodeEnergyUsed));
    
    return rowData;
    
  }
  
  
  public void addNode(Node n) throws Exception
  {
    // probably just need to insert it into the table model...
    // if it isn't already there.
    
    tableModel.addNode(n);
    
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
  
  
  public int getNodeCount()
  {
    return tableModel.getRowCount();
  }
}

