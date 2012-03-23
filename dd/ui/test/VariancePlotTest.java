/**
 * DirectedDiffusion
 */
package dd.ui.test;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dd.ui.plot.DynamicVariancePlot;

/**
 * Tests out the variance plots.
 * 
 * @author mgrabau
 *
 */
public class VariancePlotTest
{
  static int PLOT_LATENCY = 500;
  static DynamicVariancePlot plot;
  public static void createAndShowUI()
  {
    plot = new DynamicVariancePlot("Variance Plot Test");
    plot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    plot.pack();
    plot.setVisible(true);
  }
  
  public static void doTests()
  {
    // test setting the trace title once it has been configured
    // uncomment it to see it if desired
    //plot.setTraceName("Test change the plot name");
  }
  
  public static void main(String[] args)
  {
      try
      {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (ClassNotFoundException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (InstantiationException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IllegalAccessException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (UnsupportedLookAndFeelException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    
    
    SwingUtilities.invokeLater(new Runnable() {
      
      public void run()
      {
        createAndShowUI();
        doTests();
      }
    });
  }
}
