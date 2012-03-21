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
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dd.Node;

/**
 * The abstract class that 
 * 
 * @author mgrabau
 *
 */
public abstract class PlotFrame extends JFrame
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
  
  public void setLatency(long ms)
  {
    collector.setLatency(ms);
  }
 
  /**
   * Retrieve the trace in order to 
   * @return
   */
  public Trace2DLtd getTrace()
  {
    return trace;
  }
  
  /**
   * This is a hook method that is called to change the collector that is 
   * used by the init method to configure the collector.
   * 
   * Use the latency field to get the amount of latency to set the collector
   * to initially.
   * 
   * @param nodes The collection of nodes to register with the component.
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
    //chart.setSize(chart.getPreferredSize());
    //chart.setBounds(0,0,500, 500);
    setMinimumSize(new Dimension(500, 500));
    setPreferredSize(getMinimumSize());
    
    setCollector(nodes);
    
    //add(plotPanel, BorderLayout.SOUTH);
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
