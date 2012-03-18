/**
 * 
 */
package dd.ui.log;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.EventObject;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.EventListenerList;

enum FilterSetting {
  ALL,
  ONLY,
  ABOVE,
  BELOW
};


/**
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
  
  public LogFilterComponent() 
  {
    super();
    
    init(FilterSetting.ALL, LogLevel.NONE);
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
  
  public FilterSetting getSetting()
  {
    return currentSetting;
  }
  
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
      // TODO determine how to fire an event
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

    // TODO determine an event to fire, that should allow for the
    // make it a filter changed event

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
    comboLevel.addItem(LogLevel.NONE);
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

interface FilterListener extends EventListener
{
  void filterChanged(FilterEvent e);
}

class FilterEvent extends EventObject 
{
  private FilterSetting setting;
  private LogLevel level;
  private boolean settingChanged;
  private boolean levelChanged;
  
  public FilterEvent(Object source, FilterSetting setting, LogLevel level, boolean settingChanged, boolean levelChanged)
  {
    super(source);
    this.setting = setting;
    this.level = level;
    this.settingChanged = settingChanged;
    this.levelChanged = levelChanged;
  }
  
  public FilterSetting getSetting()
  {
    return setting;
  }
  
  public LogLevel getLevel()
  {
    return level;
  }
  
  public boolean isSettingChanged()
  {
    return settingChanged;
  }
  
  public boolean isLevelChanged()
  {
    return levelChanged;
  }
}