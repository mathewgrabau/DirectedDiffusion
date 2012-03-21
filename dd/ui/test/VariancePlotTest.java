/**
 * DirectedDiffusion
 */
package dd.ui.test;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dd.ui.plot.VariancePlot;

/**
 * Tests out the variance plots.
 * 
 * @author mgrabau
 *
 */
public class VariancePlotTest
{
  static int PLOT_LATENCY = 500;
  
  public static void createAndShowUI()
  {
    VariancePlot plot = new VariancePlot("Variance Plot Test");
    plot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    plot.pack();
    plot.setVisible(true);
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
      
      
      @Override
      public void run()
      {
        createAndShowUI();
      }
    });
  }
}
