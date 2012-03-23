/**
 * DirectedDiffusion
 */
package dd.ui.plot;

import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.ITracePoint2D;
import info.monitorenter.gui.chart.TracePoint2D;

import java.io.OutputStream;
import java.util.Collection;
import java.util.Vector;

import dd.Node;

/**
 * @author mgrabau
 *
 * Implements a collector that gathers the node energy data on pulses and
 * forwards it to the applicable item gathering point.
 * 
 * This particular variance collector is static, ie. must be told to collectData()
 */
public class StaticNodeVarianceCollector extends StaticNodeDataCollector
{

  /**
   * Create a NodeVarianceCollector with specified parameters.
   * This allows for the implementation
   *  
   * @param trace
   * @param nodes
   */
  public StaticNodeVarianceCollector(ITrace2D trace, 
      Collection<Node> nodes)
  {
    super(trace, nodes);
  }
  
  /**
   * 
   * @param trace
   */
  public StaticNodeVarianceCollector(ITrace2D trace)
  {
    super(trace);
  }

  /* (non-Javadoc)
   * @see dd.ui.plot.NodeDataCollector#collectData()
   */
  @Override
  public void collectData()
  {
    // The default data is good enough
    updateCurrentPoint();
    int x = currentPoint;
    double sum = 0;
    Vector<Double> samples = new Vector<Double>();
    
    for (Node currentNode : registeredNodes)
    {
        ITracePoint2D currentSample = getNodeData(currentNode);
        sum += currentSample.getY();
        samples.add(currentSample.getY());
    }
    
    // after the data has been collected compute the mean
    int n = samples.size();
    double mean = sum / n;
    double totalDelta = 0.0;
    
    // variance computation
    for (Double sample : samples)
    {
      totalDelta += Math.abs(sample - mean);
    }
    
    ITracePoint2D p = new TracePoint2D(x, totalDelta / n);
    m_trace.addPoint(p);
    
    //System.out.print(m_trace.getSize());
    //m_trace.firePointChanged(p,ITracePoint2D.STATE_ADDED);
  }
}
