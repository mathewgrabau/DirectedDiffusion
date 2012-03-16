package dd.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import info.monitorenter.gui.chart.*;
import info.monitorenter.gui.chart.io.ADataCollector;
import info.monitorenter.gui.chart.io.RandomDataCollectorOffset;
import info.monitorenter.gui.chart.traces.Trace2DLtd;
import info.monitorenter.gui.chart.traces.Trace2DSimple;

import javax.swing.*;

// this is a basic class just intended for experimenting with the chart workings


public class ChartTest
{
  
  static ADataCollector collector;
  static JButton startButton;
  static JButton stopButton;
  
  private ChartTest() {
    super();
  }
  
  static void startClicked() 
  {
    stopButton.setEnabled(true);
    startButton.setEnabled(false);
    
    collector.start();
  }
  
  static void stopClicked() 
  {
    stopButton.setEnabled(false);
    startButton.setEnabled(true);
    
    collector.stop();
  }
  
  static void runTest()
  {
    Chart2D chart = new Chart2D();
    //ITrace2D trace = new  Trace2DSimple();
    ITrace2D trace = new Trace2DLtd(50, "Evaluation Data");
    
           
    chart.addTrace(trace);
    
    //for (int i = 0; i < 100; i++) {
      //trace.addPoint(i, 0.1 * i);
    //}
    
    JFrame frame = new JFrame("Charting evaluation");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    frame.setLayout(new BorderLayout());
    frame.getContentPane().add(chart, BorderLayout.CENTER);
    
    // add some buttons to make things more interesting
    
    startButton = new JButton("Start");
    startButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        if (e.getSource() == startButton)
          startClicked();
      }
      
    });
    startButton.setEnabled(true);
    
    stopButton = new JButton("Stop");
    stopButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        if (e.getSource() == stopButton)
          stopClicked();
      }
      
    });
    stopButton.setEnabled(false);
    
    JPanel p = new JPanel(new GridLayout(1, 0));
    //p.add(new JLabel("Here is some text"));
    p.add(startButton);
    p.add(stopButton);
    frame.getContentPane().add(p, BorderLayout.SOUTH);
    
    frame.setSize(500, 500);
    frame.setVisible(true);
    
    // Set a timer that can periodically add a point
    // the latency is set in milliseconds
    collector = new RandomDataCollectorOffset(trace, 1000);
    //collector.start();    
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
