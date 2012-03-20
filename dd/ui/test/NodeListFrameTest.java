/**
 * DirectedDiffusion
 */
package dd.ui.test;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
  
  static final int NUM_NODES = 4;  // just for placeholder, don't want to keep typing it out.
  
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
    
    // let's try starting the update
    underTest.startUpdateTimer(200);
    
    // 2) Register a new node
    
    final Node nodeOne = new Node(3, 2, 2, 1, NUM_NODES);
    underTest.addNode(nodeOne);
    
    final Timer t = new Timer();
    int n = 10;
    
    t.scheduleAtFixedRate(new TimerTask() {

      @Override
      public void run()
      {
        nodeOne.nodeEnergyUsed++;
      }
    }, 1000, 250);
    
    final Node nodeTwo = new Node(4, 3, 3, 1, NUM_NODES);
    underTest.addNode(nodeTwo);
    
    t.scheduleAtFixedRate(new TimerTask() {

      @Override
      public void run()
      {
        nodeTwo.nodeEnergyUsed++;
      }
      
    }, 1000, 500);
    
    final Node nodeThree = new Node(5, 4, 4, 1, NUM_NODES);
    underTest.addNode(nodeThree);
    // Now there needs to be a new thing launched
    
    t.scheduleAtFixedRate(new TimerTask(){

      @Override
      public void run()
      {
        nodeThree.nodeEnergyUsed++;
        if (decrementCounter())
        {
          t.cancel();
        }
        
      }
      
    }, 1000, 750);
    
  }
  
  static int n = 10;
  private static boolean decrementCounter()
  {
    n--;
    System.out.println(n + " times remaining");
    return n==0;
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
