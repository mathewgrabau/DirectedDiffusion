/**
 * DirectedDiffusion
 * Graphical extensions
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
 * A custom implementation of the TestDataCollector that is used
 * to implement a reader for the nodes.
 * 
 * The basic implementation just reads energy. Override it to implement
 * a different pulse.
 */
public class NodeDataCollector extends ADataCollector
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
	 */
	protected int currentPoint; 

	public NodeDataCollector(ITrace2D trace, long latency, Collection<Node> nodes)
	{
		super(trace, latency);
		registerNodes(nodes);
		currentPoint = 0;
	}
	
	public NodeDataCollector(ITrace2D trace, long latency)
	{
		super(trace, latency);
		registeredNodes = new Vector<Node>();
		currentPoint = 0;
	}
	
	public void registerNodes(Collection<Node> toMonitor)
	{
		
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
	  updateCurrentPoint();
	  return new TracePoint2D(currentNode.nodeID, currentNode.nodeEnergyUsed);
	}

	@Override
	public ITracePoint2D collectData()
	{
	  
	}
}