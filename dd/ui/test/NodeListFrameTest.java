/**
 * 
 */
package dd.ui.test;

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
    
    
    // 2) Register a node
    Node n =  new Node(1, 0, 0, 1, 2);
    underTest.addNode(n);
    n = new Node(2, 1, 1, 1, 2);
    underTest.addNode(n);
  }
  
  private static void createAndShowGUI() {
    underTest = new NodeListFrame("NodeListFrame Test");  
    underTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    underTest.pack();
    underTest.setVisible(true);
    
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
