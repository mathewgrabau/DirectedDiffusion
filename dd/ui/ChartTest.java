package dd.ui;

import info.monitorenter.gui.chart.*;
import info.monitorenter.gui.chart.traces.Trace2DSimple;

import javax.swing.SwingUtilities;
import javax.swing.*;

// this is a basic class just intended for experimenting with the chart workings


public class ChartTest
{
  
  private ChartTest() {
    super();
  }
  
  static void runTest()
  {
    Chart2D chart = new Chart2D();
    ITrace2D trace = new  Trace2DSimple();
    
    chart.addTrace(trace);
    
    for (int i = 0; i < 100; i++) {
      trace.addPoint(i, 0.1 * i);
    }
    
    JFrame frame = new JFrame("Charting evaluation");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(chart);
    frame.setSize(500, 500);
    frame.setVisible(true);
  }

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    // TODO Auto-generated method stub
    SwingUtilities.invokeLater(new Runnable() {

      @Override
      public void run()
      {
        // TODO Auto-generated method stub
        runTest();
      }
      
    });
  }

}
