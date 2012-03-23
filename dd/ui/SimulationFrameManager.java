/**
 * DirectedDiffusion
 */
package dd.ui;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


/**
 * @author mgrabau
 *
 */
public class SimulationFrameManager extends WindowAdapter
{
  private Vector<ManagedFrameInfo> monitoringInfo;
  
  class ManagedFrameInfo {
    JFrame frame;
    boolean closed;
    
    public ManagedFrameInfo(JFrame f)
    {
      frame = f;
      closed = false;
      
      // create a last frame that will exit on close
    }
    
    public JFrame getFrame()
    {
      return frame;
    }
    
    public void setClosed()
    {
      closed = true;
    }
    
    public boolean isClosed()
    {
      return closed;
    }
  };
  
  public SimulationFrameManager()
  {
    monitoringInfo = new Vector<ManagedFrameInfo>();
    
  }
  
  public boolean isRegistered(JFrame f)
  {
    if (f == null)
      return false;
    
    for (ManagedFrameInfo info : monitoringInfo)
    {
      if (info.getFrame() == f)
      {
        return true;
      }
    }
    
    return false;
  }
  
  protected ManagedFrameInfo getInfo(JFrame f)
  {
    if (f == null)
      return null;
    
    for (ManagedFrameInfo info : monitoringInfo)
    {
      if (info.getFrame() == f)
      {
        return info;
      }
    }
    
    return null;
  }
  
  public void registerFrame(JFrame frameToWatch)
  {
    if (frameToWatch != null)
    {
      //System.out.println("Frame registered");
      frameToWatch.addWindowListener(this);
      monitoringInfo.add(new ManagedFrameInfo(frameToWatch));
      frameToWatch.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
  }
  
  public void checkAllClosed()
  {
     for (ManagedFrameInfo info : monitoringInfo)
     {
       if (!info.isClosed())
       {
         return;
       }
     }
     
     // dispose all the windows
     disposeAll();
     
     // exit the application
     // TODO find a better way to do it - this one is pretty crude.
     // don't know if there is a better way
     System.exit(0);
  }
  
  private void disposeAll()
  {
    for (ManagedFrameInfo info : monitoringInfo)
    {
      if (info.isClosed())
        info.getFrame().dispose();
    }
  }
  
  /* (non-Javadoc)
   * @see java.awt.event.WindowAdapter#windowClosed(java.awt.event.WindowEvent)
   */
  @Override
  public void windowClosing(WindowEvent e)
  {
    Window closed = e.getWindow();
    if (closed != null && closed instanceof JFrame)
    {
      JFrame closedFrame = (JFrame) closed;
      ManagedFrameInfo info = getInfo(closedFrame);
      if (info != null)
      {
        info.setClosed();
        closedFrame.setVisible(false);
      }
      
      checkAllClosed();
      
      return;
    }
    
    // otherwise just pass it on
    super.windowClosed(e);
  }
}
