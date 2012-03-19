/**
 * 
 */
package dd.ui.test;

import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import dd.ui.log.LogFrame;
import dd.ui.log.LogLevel;

/**
 * @author Mathew Grabau
 *
 */
public class LogTest
{
  static void createAndShowGUI()
  {
    final Random random = new Random();
    
    final LogFrame lf = new LogFrame("LogFrame Demo");
    lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    lf.pack();
    lf.setVisible(true);
    
    // 1) Set the timer to add an event every so often to the log
    Timer t = new Timer("loggingTimer");
    
    lf.addMessage(LogLevel.INFO, "Created the timer");
    
    // schedule the task to delay two seconds, and pulse every second
    // each event is issued with random taskings.
    // made it fairly slow due to the need to keep it workable.
    t.schedule(new TimerTask() {

      @Override
      public void run()
      {
        // generate an event
        int ordinal = random.nextInt(4);
        // don't want to get the NONE being posted
        LogLevel l = LogLevel.values()[ordinal + 1];
        
        lf.addMessage(l, "a timed test event was fired");
      }
      
    }, 2000, 2000);

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
