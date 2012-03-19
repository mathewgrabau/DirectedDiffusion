/**
 * DirectedDiffusion
 */
package dd.ui.log;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;



/**
 * Displays the filter for events, in a JPanel.
 * 
 * Uses 2 combo boxes for the implementation.
 * 
 * Important: the initial event for Filter change will not be captured by
 * listeners since they cannot register for the events yet. 
 * @author Mathew Grabau
 *
 */
public class LogFilterComponent extends JPanel implements ActionListener
{
  private JComboBox comboSetting;
  private JComboBox comboLevel;
  
  
  // used for event management
  private FilterSetting currentSetting;
  private LogLevel currentLevel;
  
  private EventListenerList listenerList;
  
  /**
   * Default constructor, will set the values to the ALL and NONE values.
   * 
   */
  public LogFilterComponent() 
  {
    super();
    
    init(FilterSetting.ALL, LogLevel.NONE);
  }
  
  /**
   * Constructor to set the values of the components initially.
   * @param setting initial setting value
   * @param level initial log level to show
   */
  public LogFilterComponent(FilterSetting setting, LogLevel level)
  {
    super();
    init(setting, level);
  }
  
  /**
   * Register an listener for FilterEvents, when the setting of the filter
   * changes.
   * @param l an instance of the FilterListener to register.
   */
  public void addListener(FilterListener l)
  {
    listenerList.add(FilterListener.class, l);
  }
  
  public void removeListener(FilterListener l)
  {
    listenerList.remove(FilterListener.class, l);
  }
  
  /**
   * Get current FilterSetting
   * @return current FilterSetting
   */
  public FilterSetting getSetting()
  {
    return currentSetting;
  }
  
  /**
   * Get current LogLevel.
   * @return the current LogLevel.
   */
  public LogLevel getLevel()
  {
    return currentLevel;
  }
  
  private void configControls()
  {
    // if all is enabled, then the log gets handled to the 
    Object v = comboSetting.getSelectedItem();
    String s;
    FilterSetting setting;
    LogLevel level;
    if (v != null && v instanceof FilterSetting)
    {
      setting = (FilterSetting)v;
    }
    else
    {      
      System.err.println("Invalid type pulled from JComboBox");
      return;
      //throw new Exception("Invalid type pulled from JComboBox");
    }
    
    FilterEvent eventToRaise;
    boolean settingChanged;
    boolean levelChanged;
    
    // Under the show all, no filter is required.
    if (setting == FilterSetting.ALL)
    {
      comboLevel.setEnabled(false);
    }
    else
    {
      comboLevel.setEnabled(true);
    }
    
    
    if (currentSetting == null)
    {
      settingChanged = true;
      currentSetting = setting;
    } else
    {
      settingChanged = setting != currentSetting;
      currentSetting = setting;
    }

    //comboLevel.setEnabled(true);

    v = comboLevel.getSelectedItem();
    if (v instanceof LogLevel)
    {
      level = (LogLevel) v;
      
      if (currentLevel == null)
      {
        levelChanged = true;
      }
      else
      {
        levelChanged = currentLevel != level;
      }
      
      currentLevel = level;
    } 
    else
    {
      System.err.println("Invalid type pulled from JComboBox");
      return;
    }
    eventToRaise = new FilterEvent(
        this, currentSetting, currentLevel, settingChanged, levelChanged);
    raiseFilterEvent(eventToRaise);
  }
  
  private void raiseFilterEvent(FilterEvent e)
  {
    for (FilterListener el : listenerList.getListeners(FilterListener.class))
    {
      el.filterChanged(e);
    }
  }
  
  private void init(FilterSetting initialSetting, LogLevel initalLevel) 
  {
    listenerList = new EventListenerList();
    /*
    showAllRadio = new JRadioButton("All");
    showOnlyRadio = new JRadioButton("Only");
    showAboveRadio = new JRadioButton("Above");
    showBelowRadio = new JRadioButton("Below");
    
    // create the grid for them
    JPanel p = new JPanel(new GridLayout(0, 1));
    p.add(showAllRadio);
    p.add(showOnlyRadio);
    p.add(showAboveRadio);
    p.add(showBelowRadio);
    */
    
    add(new JLabel("Show "));
    //String[] items = {"All", "Only", "Above", "Below"};
    //comboSetting = new JComboBox(items);
    comboSetting = new JComboBox();
    comboSetting.addItem(FilterSetting.ALL);
    comboSetting.addItem(FilterSetting.ONLY);
    comboSetting.addItem(FilterSetting.ABOVE);
    comboSetting.addItem(FilterSetting.BELOW);
    comboSetting.addActionListener(this);
    
    add(comboSetting);
    
    add(new JLabel("Category "));
    
    comboLevel = new JComboBox();
    comboLevel.addItem(LogLevel.ERROR);
    comboLevel.addItem(LogLevel.WARN);
    comboLevel.addItem(LogLevel.INFO);
    comboLevel.addItem(LogLevel.DEBUG);
    comboLevel.addActionListener(this);
    add(comboLevel);
    
    comboSetting.setSelectedIndex(0);
    
  }

  public void actionPerformed(ActionEvent e)
  {
    // TODO Auto-generated method stub
    configControls();
  }
}
