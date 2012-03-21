/**
 * DirectedDiffusion
 */
package dd.ui.plot;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.traces.Trace2DLtd;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dd.Node;

/**
 * The abstract class that gives the basis for implementing a 
 * frame that contains a plot.
 * 
 * The default panel implementation contains buttons for 
 * starting/stopping the trace.
 * 
 * @author mgrabau
 *
 */
public abstract class PlotFrame extends JFrame implements ActionListener
{
  protected JPanel titlePanel;
  protected JPanel plotPanel;
  protected Chart2D chart;
  protected NodeDataCollector collector;
  protected Trace2DLtd trace;
  
  protected JLabel titleLabel;
  protected boolean initialized;
  
  static final int DEFAULT_LATENCY = 500;  // pulse for the latency
  protected long latency;    // the amount of latency that is set
  private JButton startButton;
  private JButton stopButton;
  private JPanel buttonPanel;
  
  public PlotFrame(String title, Collection<Node> nodes)
  {
    super(title);
    
    init(nodes, "", DEFAULT_LATENCY);
    
  }
  
  public PlotFrame(String title, Collection<Node> nodes, String plotTitle)
  {
    super(title);
    init(nodes, plotTitle, DEFAULT_LATENCY);
  }
  
  public void setTraceName(String name)
  {
    trace.setName(name);
  }
  
  public String getTraceName()
  {
    return trace.getName();
  }
  
  /**
   * Sets the collector latency and the internal tracking parameter.
   * If the collector is not initialized, only the internal tracking
   * parameter will be changed.
   * 
   * @param ms the collector latency to set in milliseconds
   */
  public void setLatency(long ms)
  {
    if (collector != null)
      collector.setLatency(ms);
    // instead just store it for when the collector is started
    latency = ms;
  }
 
  /**
   * Retrieve the trace in order to 
   * @return
   */
  public Trace2DLtd getTrace()
  {
    return trace;
  }
  
  public boolean isCollectorRunning()
  {
    return collector.isRunning();
  }
  
  /**
   * This is a hook method that is called to change the collector that is 
   * used by the init method to configure the collector.
   * 
   * Use the latency field to get the amount of latency to set the collector
   * to initially.
   * 
   * @param nodes The collection of nodes to register with the component.
   * @param latency 
   */
  protected void setCollector(Collection<Node> nodes)
  {
    collector = new NodeDataCollector(trace, latency, nodes);
  }
  
  protected void init(Collection<Node> nodes, String plotTitle, long latency)
  {
    setLatency(latency);
    
    setLayout(new BorderLayout());
    
    if (plotTitle == null)
      plotTitle = "";
    createTitlePanel(plotTitle);
    add(titlePanel, BorderLayout.NORTH);
    
    titlePanel.setPreferredSize(new Dimension(titlePanel.getSize().width, 50));
    
    //plotPanel = new JPanel(new GridLayout(1, 1));
    
    //addTitlePanel();
    if (plotTitle != null && plotTitle.length() > 0)
    {
      addPlot(nodes, plotTitle);
      //chart.setSize(chart.getPreferredSize());
    }
    else
    {
      addPlot(nodes);
      
    }
    
    // TODO move this to a parameter instead
    boolean withButtons = true;
    if (withButtons)
    {
      buttonPanel = new JPanel();
      createButtonPanel("Start", "Stop");
      add(buttonPanel, BorderLayout.SOUTH);
    }  
    
    //chart.setSize(chart.getPreferredSize());
    //chart.setBounds(0,0,500, 500);
    setMinimumSize(new Dimension(500, 500));
    setPreferredSize(getMinimumSize());
    
    setCollector(nodes);
    
    //add(plotPanel, BorderLayout.SOUTH);
  }
  
  public void actionPerformed(ActionEvent e)
  {
    if (startButton != null && e.getSource() == startButton)
    {
      startButtonClicked(e);
    }
    else if (stopButton != null && e.getSource() == stopButton)
    {
      stopButtonClicked(e);
    }
    
    // This component doesn't know how to handle it, 
    // hopefully descendant class was smart enough to
    // override the method and handle whatever they needed to.
  }
  
  /**
   * Default implementation of the start button handler. Override to provide 
   * different function.
   * 
   * The default implementation starts the collector (if not running)
   * and toggles the button configuration.
   * @param e Event data for the click event (forwarded unchanged).
   */
  protected void startButtonClicked(ActionEvent e)
  {
    // TODO implement starting the trace/collector
    if (!collector.isRunning())
    {
      collector.start();
      stopButton.setEnabled(true);
      startButton.setEnabled(false);
    }
  }
  
  /**
   * Default implementation of the stop button handler. Override to provide 
   * different function.
   * 
   * The default implementation stop the collector (if running)
   * and toggles the button configuration.
   * @param e Event data for the click event (forwarded unchanged).
   */
  protected void stopButtonClicked(ActionEvent e)
  {
    // TODO implement stopping the trace/collector
    if (collector.isRunning())
    {
      collector.stop();
      stopButton.setEnabled(false);
      startButton.setEnabled(true);
    }
  }
  
  // the two hooks that are 
  protected void wireStartButton()
  {
    if (startButton != null)
    {
      startButton.addActionListener(this);
    }
  }
  
  protected void wireStopButton()
  {
    
  }
  
  protected void createButtonPanel(String startText, String stopText)
  {
    startButton = new JButton(startText);
    stopButton = new JButton(stopText);
    
    buttonPanel = new JPanel();
    
    buttonPanel.add(startButton);
    buttonPanel.add(stopButton);
    
    
  }
  
  protected void createTitlePanel(String title)
  {
    titlePanel = new JPanel(new GridLayout(1, 1));
    titleLabel = new JLabel("DirectedDiffusion Plot: " + title);
    titlePanel.add(titleLabel);
  }
  
  /**
   * Left abstract with the following constraints:
   * 
   * 1) Make sure to initialize the JPanel plotPanel
   * @param nodes
   */
  protected abstract void addPlot(Collection<Node> nodes);
  
  protected abstract void addPlot(Collection<Node> nodes, String plotTitle);
}
