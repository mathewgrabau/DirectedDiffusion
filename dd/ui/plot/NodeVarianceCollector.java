/**
 * DirectedDiffusion
 */
package dd.ui.plot;

import info.monitorenter.gui.chart.IPointPainter;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.ITracePoint2D;
import info.monitorenter.gui.chart.TracePoint2D;

import java.util.Collection;
import java.util.Set;
import java.util.Vector;

import dd.Node;

/**
 * @author mgrabau
 *
 */
public class NodeVarianceCollector extends NodeDataCollector
{

  /**
   * @param trace
   * @param latency
   * @param nodes
   */
  public NodeVarianceCollector(ITrace2D trace, long latency,
      Collection<Node> nodes)
  {
    super(trace, latency, nodes);
  }
  
  /**
   * 
   * @param trace
   * @param latency
   */
  public NodeVarianceCollector(ITrace2D trace, long latency)
  {
    super(trace, latency);
  }

  /* (non-Javadoc)
   * @see dd.ui.plot.NodeDataCollector#collectData()
   */
  @Override
  public ITracePoint2D collectData()
  {
    // The default data is good enough
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
    
    return new TracePoint2D(x, totalDelta / n);
  }
}
