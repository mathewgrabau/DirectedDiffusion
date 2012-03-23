/**
 * DirectedDiffusion
 */
package dd.ui.plot;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.io.FileFilterExtensions;
import info.monitorenter.gui.chart.traces.Trace2DLtd;
import info.monitorenter.gui.chart.traces.Trace2DSimple;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
public abstract class StaticPlotFrame extends JFrame implements ActionListener
{
  protected JPanel titlePanel;
  protected JPanel plotPanel;
  protected Chart2D chart;
  protected StaticNodeDataCollector collector;
  protected Trace2DSimple trace;
  
  protected JLabel titleLabel;
  protected boolean initialized;
  
  protected JButton saveImageButton;  
  protected JPanel buttonPanel;
  protected JButton exportCSVButton;

  public StaticPlotFrame(String title, Collection<Node> nodes)
  {
    super(title);
    
    init(nodes, "");
    
  }
  
  public StaticPlotFrame(String title, Collection<Node> nodes, String plotTitle)
  {
    super(title);
    init(nodes, plotTitle);
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
   * Retrieve the trace in order to 
   * @return
   */
  public Trace2DSimple getTrace()
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
   * @param latency 
   */
  protected void setCollector(Collection<Node> nodes)
  {
    collector = new StaticNodeDataCollector(trace, nodes);
  }
  
  protected void init(Collection<Node> nodes, String plotTitle)
  {
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
      createButtonPanel("Save As Image", "Export as CSV");
      add(buttonPanel, BorderLayout.SOUTH);
    }  
    
    //chart.setSize(chart.getPreferredSize());
    //chart.setBounds(0,0,500, 500);
    setMinimumSize(new Dimension(500, 500));
    setPreferredSize(getMinimumSize());
    
    setCollector(nodes);
    
    //add(plotPanel, BorderLayout.SOUTH);
  }
  
  public BufferedImage takeSnapShot()
  {
    return chart.snapShot();
  }
  
  public void takeSample()
  {
    collector.collectData();
  }
  
  public void actionPerformed(ActionEvent e)
  {
    if (saveImageButton != null && e.getSource() == saveImageButton)
    {
      saveButtonClicked(e);
    }
    else if (exportCSVButton != null && e.getSource() == exportCSVButton)
    {
      exportCSVButtonClicked(e);
    }
    
    // This component doesn't know how to handle it, 
    // hopefully descendant class was smart enough to
    // override the method and handle whatever they needed to.
  }
  
  protected void saveButtonClicked(ActionEvent e)
  {
    // want to capture the snapshot right when the button is clicked
    BufferedImage imgToSave = takeSnapShot();
   
    boolean done = false;
    
    JFileChooser fileChooser = new JFileChooser();
    ImageFilter temp;
    
    temp = new JPEGFilter();
    if (temp.isSupported())
      fileChooser.addChoosableFileFilter(temp);
    temp = new PNGFilter();
    if (temp.isSupported())
      fileChooser.addChoosableFileFilter(temp);
    temp = new BitmapFilter();
    if (temp.isSupported())
      fileChooser.addChoosableFileFilter(temp);
    temp = new GIFFilter();
    if (temp.isSupported())
      fileChooser.addChoosableFileFilter(temp);
    
    fileChooser.setAcceptAllFileFilterUsed(false);
    
    
    //fileChooser.setFileFilter(new FileFilterExtensions(ImageIO.getWriterFileSuffixes()));
    //fileChooser.setFileFilter(new ImageFilter());
    
    while (!done)
    {
      int fcResult = fileChooser.showSaveDialog(this);
      if (fcResult == JFileChooser.APPROVE_OPTION)
      {
        File outfile = fileChooser.getSelectedFile();
        ImageFilter selectedFileType = null;
        if (fileChooser.getFileFilter() == null)
        {
          // assume its the first one
          selectedFileType = (ImageFilter) fileChooser.getChoosableFileFilters()[0];
        }
        else
        {
          selectedFileType = (ImageFilter) fileChooser.getFileFilter();
        }
        
        String outputFileName = outfile.getPath();
        String extension = ImageFilter.getExtension(outfile);
        if (extension == null)
        {
          outputFileName += "." + selectedFileType.extensionToCheck;
        }
        
        // if it exists, confirm the overwrite 
        if (outfile.exists())
        {
          int result = JOptionPane.showConfirmDialog(this, "Sorry, that file already exists! Try again?", "Save", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
          if (result != JOptionPane.YES_OPTION)
          {
            done = true;
          }
        }
        else
        {
          outfile = new File(outputFileName);
          
          try
          {
            ImageIO.write(imgToSave, selectedFileType.getExtensionToCheck(), outfile);
            done = true;
          } 
          catch (IOException e1)
          {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
        }
      }
      else
      {
        done = true;  // since the dialog was cancelled
      }
    }
  }
    
  protected void exportCSVButtonClicked(ActionEvent e)
  {
    // TODO implement this method
  }
  
  protected void wireSaveButton()
  {
    if (saveImageButton != null)
    {
      saveImageButton.addActionListener(this);
    }
  }
  
  protected void wireExportButton()
  {
    if (exportCSVButton != null)
    {
      exportCSVButton.addActionListener(this);
    }
  }
  
  protected void createButtonPanel(String saveText, String exportText)
  {
    buttonPanel = new JPanel();
    
    if (saveText != null)
    {
      saveImageButton = new JButton(saveText);
      buttonPanel.add(saveImageButton);
      wireSaveButton();
    }
    if (exportText != null)
    {
      exportCSVButton = new JButton(exportText);
      buttonPanel.add(exportCSVButton);
      wireExportButton();
    }
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
