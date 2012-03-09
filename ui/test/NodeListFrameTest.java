/**
 * 
 */
package ui.test;

import ui.NodeListFrame;
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
