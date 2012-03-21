/**
 * DirectedDiffusion
 */
package dd.ui.log;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Mathew Grabau
 *
 */
public class LogFrame extends JFrame implements ActionListener, FilterListener
{
  private LogFilterComponent filter;
  private JPanel buttonPanel;
  private JButton clearButton;
  private LogMessagesPanel messagesPanel;
  private JPanel statusPanel;
  private JTextField statusField;
  private JPanel bottomPanel;
  
  public LogFrame(String title)
  {
    super(title);
    init();
  }

  public LogFrame()
  {
    super("dd.ui.log.LogFrame");
    init();
  }
  
  protected void init()
  {
  
    setLayout(new BorderLayout());
    filter = new LogFilterComponent();
    filter.addListener(this);
    add(filter, BorderLayout.NORTH);
    
    messagesPanel = new LogMessagesPanel();
    add(messagesPanel, BorderLayout.CENTER);
    
    // button for clearing the things
    bottomPanel = new JPanel(new GridLayout(1, 2));
    
    buildStatusPanel();
    bottomPanel.add(statusPanel);
    buildButtonPanel();    
    bottomPanel.add(buttonPanel);
       
    add(bottomPanel, BorderLayout.SOUTH);
  }
  
  private void buildButtonPanel()
  {
    buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    buttonPanel.add(clearButton = new JButton("Clear"));
    //add(buttonPanel, BorderLayout.SOUTH);
    buttonPanel.setAlignmentX(RIGHT_ALIGNMENT);
    clearButton.setPreferredSize(clearButton.getMinimumSize());
    clearButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e)
      {
        if (e.getSource() == clearButton)
        {
          messagesPanel.clear();
          updateStatusPanel();
        } 
      }
      
    });
  }
  
  private void buildStatusPanel()
  {
    statusPanel = new JPanel(new GridLayout(1, 1));
    statusPanel.add(statusField = new JTextField());
    statusField.setEditable(false);
    updateStatusPanel();
  }
  
  private void updateStatusPanel() 
  {
    statusField.setText("Showing " + messagesPanel.getDisplayCount() + " of " + messagesPanel.getTotalCount() + " events");
  }

  /* (non-Javadoc)
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  public void actionPerformed(ActionEvent e)
  {
    // TODO Auto-generated method stub

  } 

  public void filterChanged(FilterEvent e)
  {
    if (e.isSettingChanged())
    {
      messagesPanel.setFilterSetting(e.getSetting());
    }
    
    if (e.isLevelChanged())
    {
      messagesPanel.setLogLevel(e.getLevel());
    }
    
    updateStatusPanel();
  }

  /**
   * @param text  
   */
  public void addMessage(String text)
  {
    messagesPanel.addMessage(text);
    updateStatusPanel();
  }
  
  public void addMessage(LogLevel level, String text)
  {
    messagesPanel.addMessage(level, text);
    updateStatusPanel();
  } 
}
