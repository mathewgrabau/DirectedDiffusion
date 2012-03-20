/**
 * DirectedDiffusion
 */
package dd.ui.test;

import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
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
  // these constants control the demonstration/testing for the component
  final static int NUM_NODES = 100;
  final static int TABLE_REFRESH = 200;
  final static int NODES_PULSE = 100;
  
  public static void createAndShowGUI()
  {
       
    final Vector<Node> nodes = new Vector<Node>();
        
    for (int i = 0; i < NUM_NODES; i++)
    {
      nodes.add(new Node(i+1, i, i, i, NUM_NODES));
    }
    
    TimedNodeList tl = new TimedNodeList("TimedNodeListDemo", (Collection<Node>)nodes);
    tl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    tl.pack();
    tl.setVisible(true);
    
    tl.startUpdateTimer(TABLE_REFRESH);
    
    final Random rand = new Random();
    
    Timer t = new Timer();
    t.scheduleAtFixedRate(new TimerTask() {
      
      @Override
      public void run()
      {
        // For testing/demoing, change this code as desired.
        int todo = NUM_NODES;
        
        while (todo-- != 0)
        {
          // pick a random node
          int n = rand.nextInt(NUM_NODES);
          Node node = nodes.get(n);
          // update the energy by a random amount
          node.nodeEnergyUsed += rand.nextInt(5);
        }
      }
    }, new Date(), NODES_PULSE);
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
