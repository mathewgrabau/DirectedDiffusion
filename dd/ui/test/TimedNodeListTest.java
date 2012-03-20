/**
 * DirectedDiffusion
 */
package dd.ui.test;

import java.util.Collection;
import java.util.Vector;

import javax.swing.JFrame;

import dd.Node;
import dd.ui.TimedNodeList;

/**
 * @author mgrabau
 *
 */
public class TimedNodeListTest
{
  public static void createAndShowGUI()
  {
    
   
    Vector<Node> nodes = new Vector<Node>();
    
    TimedNodeList tl = new TimedNodeList("TimedNodeListDemo", (Collection<Node>)nodes);
    tl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    tl.pack();
    tl.setVisible(true);
  }
  
  public static void doTests()
  {
    
  }
  
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
