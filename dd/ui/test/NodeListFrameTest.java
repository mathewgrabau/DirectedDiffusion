/**
 * 
 */
package dd.ui.test;

import java.util.ArrayList;

import dd.Node;
import dd.ui.NodeListFrame;
import javax.swing.JFrame;

/**
 * @author mgrabau
 *
 */
public class NodeListFrameTest
{
  static NodeListFrame underTest;
  static ArrayList<Node> nodes;
  
  static final int NUM_NODES = 3;  // just for placeholder, don't want to keep typing it out.
  
  private static void createTestObjects() {
    //ArrayList<Node> nodes = new ArrayList<Node>();
    nodes = new ArrayList<Node>();
    Node n =  new Node(1, 0, 0, 1, NUM_NODES);
    //underTest.addNode(n);
    nodes.add(n);
    n = new Node(2, 1, 1, 1, NUM_NODES);
    //underTest.addNode(n);
    nodes.add(n);
  }
  
  
  private static void doTests() throws Exception {
    // 1) Check the title got set correctly
    
    if (underTest.getTitle().equals("NodeListFrame Test"))
    {
      System.out.println("TEST PASSED: Correctly set the title");
    }
    else
    {
      throw new Exception("Test failed: title not properly set");
    }
    
    
    // 2) Register a new node
    
    Node inserted = new Node(3, 2, 2, 1, NUM_NODES);
    underTest.addNode(inserted);
    
    
    // Now there needs to be a new thing launched
  }
  
  private static void createAndShowGUI() {
    createTestObjects();
    
    underTest = new NodeListFrame("NodeListFrame Test", nodes);  
    underTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    underTest.pack();
    underTest.setVisible(true);
    
    
    // Then we need to actually launch the simulation now?
  }
  
  /**
   * Run a test to confirm that the node list window works properly.
   * @param args
   */
  public static void main(String[] args)
  {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
        
        try
        {
          doTests();
        } catch (Exception e)
        {
          System.err.println("TEST FAILED!!!! (review below for more information)");
          System.err.println(e.getMessage());
          e.printStackTrace();
        }
      }
    });

  }
 

}
