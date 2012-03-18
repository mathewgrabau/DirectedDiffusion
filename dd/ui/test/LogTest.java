/**
 * 
 */
package dd.ui.test;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import dd.ui.log.LogFrame;

/**
 * @author Mathew Grabau
 *
 */
public class LogTest
{
  static void createAndShowGUI()
  {
    LogFrame lf = new LogFrame("LogFrame Demo");
    lf.pack();
    lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    lf.setVisible(true);
    
  }

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      System.err.println("setLookAndFeelFailed");
    }
    
    SwingUtilities.invokeLater(new Runnable() {
      
      public void run()
      {
        
        
        createAndShowGUI();
      }
    });
  }

}
