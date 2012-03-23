/**
 * DirectedDiffusion
 */
package dd.ui.plot;

import java.util.Collection;
import java.util.Vector;

import dd.Node;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.ITracePoint2D;
import info.monitorenter.gui.chart.TracePoint2D;
import info.monitorenter.gui.chart.io.ADataCollector;

/**
 * @author mgrabau
 *
 */
public class StaticNodeDataCollector extends info.monitorenter.gui.chart.io.AStaticDataCollector
{
  /**
   * This collection stores the references to the nodes that are monitored.
   * Either register with regsiterNode or otherwise
   */
  protected Vector<Node> registeredNodes;
  /**
   * The counter - basically the 'x' coordinate, depending on implementation it
   * can keep incrementing or roll over at a certain point.
   * 
   * The overall effect of the counter is also impacted by the settings on the 
   * trace.
   * 
   * It is useful in simulations that use the incrementing timer model that 
   * is user controlled.
   */
  protected int currentPoint; 

  public StaticNodeDataCollector(ITrace2D trace, Collection<Node> nodes)
  {
    super(trace);
    registeredNodes = new Vector<Node>();
    registerNodes(nodes);
    currentPoint = 0;
  }
  
  public StaticNodeDataCollector(ITrace2D trace)
  {
    super(trace);
    registeredNodes = new Vector<Node>();
    currentPoint = 0;
  }
  
  public void registerNodes(Collection<Node> toMonitor)
  {
    // if passed null then nothing to do
    if (toMonitor == null || toMonitor.isEmpty())
    {
      return;
    }
    
    // Easy case - nothing there just register the data
    if (registeredNodes.isEmpty())
    {
      for (Node node : toMonitor)
      {
        registeredNodes.add(node);
      }
    }
    else
    {
      // prevent duplicate registration
      for (Node pendingNode : toMonitor)
      {
        if (!isNodeRegistered(pendingNode))
        {
          registeredNodes.add(pendingNode);
        }
      }
      
    }
  }
  
  public boolean isNodeRegistered(Node node)
  {
    for (Node current : registeredNodes)
    {
      // already registered? Note that this _IS_ being done by 
      // reference.
      if (current == node)
      {
        return true;
      }
    }
    
    return false;
  }
  
  /**
   * Does the standard update (an increment) for the counter.
   * 
   * Override if you need the counter to have different functionality
   */
  protected void updateCurrentPoint()
  {
    currentPoint++;
  }
  
  /**
   * The default implementation gathers the current energy 
   * in the node, giving the x coordinate as the nodes' id.
   * @param currentNode
   * @return
   */
  protected ITracePoint2D getNodeData(Node currentNode)
  {
    return new TracePoint2D(currentNode.nodeID, currentNode.nodeEnergyUsed);
  }

  /* (non-Javadoc)
   * @see info.monitorenter.gui.chart.io.ADataCollector#collectData()
   */
  @Override
  public void collectData()
  {
    long sum = 0;
    for (Node node : registeredNodes)
    {
      ITracePoint2D tp = getNodeData(node);
      sum += Math.round(tp.getY());
    }
    
    // Update the current point now, since that is a sample that has been taken
    updateCurrentPoint();
    m_trace.addPoint(currentPoint, sum);
  }
  
}
