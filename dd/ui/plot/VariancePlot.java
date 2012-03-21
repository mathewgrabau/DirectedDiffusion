/**
 * DirectedDiffusion
 */
package dd.ui.plot;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.traces.Trace2DLtdReplacing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Collection;

import javax.swing.JPanel;

import dd.Node;

/**
 * 
 * @author mgrabau
 *
 */
public class VariancePlot extends PlotFrame
{
  public VariancePlot(String title)
  {
    super(title, null, "Variance Plot");
  } 

  /* (non-Javadoc)
   * @see dd.ui.plot.PlotFrame#addPlot(java.util.Collection)
   */
  @Override
  protected void addPlot(Collection<Node> nodes)
  {
    //plotPanel = new JPanel(new GridLayout(1, 1));
    
    trace = new Trace2DLtdReplacing();
    trace.setName("Node Energy Variance");
    
    // here is the time to create the chart
    chart = new Chart2D();
    chart.addTrace(trace);
    
    //plotPanel.add(chart);
    add(chart, BorderLayout.CENTER);
  }

  /* (non-Javadoc)
   * @see dd.ui.plot.PlotFrame#addPlot(java.util.Collection, java.lang.String)
   */
  @Override
  protected void addPlot(Collection<Node> nodes, String plotTitle)
  {
    trace = new Trace2DLtdReplacing();
    trace.setName(plotTitle);
    
    chart = new Chart2D();
    chart.addTrace(trace);
    
    add(chart, BorderLayout.CENTER);
  }
  
  @Override
  protected void setCollector(Collection<Node> nodes)
  {
   
    //super.setCollector(nodes);
  }
}
