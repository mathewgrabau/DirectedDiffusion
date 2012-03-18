/**
 * DirectedDiffusion
 */

package dd.ui.log;

import java.util.EventObject;

/**
 * The object that is passed to the events for Filter. 
 * @author Mathew Grabau (mgrabau)
 *
 */
public class FilterEvent extends EventObject 
{
  private FilterSetting setting;
  private LogLevel level;
  private boolean settingChanged;
  private boolean levelChanged;
  
  /**
   * FilterEvent constructor
   * 
   * @param source  the Component that generated the event
   * @param setting The FilterSetting value at the time that the event was generated. 
   * @param level The LogLevel at the time that the event was generated.
   * @param settingChanged  Whether the setting had changed
   * @param levelChanged  Whether the level had changed
   */
  public FilterEvent(Object source, FilterSetting setting, LogLevel level,
      boolean settingChanged, boolean levelChanged)
  {
    super(source);
    this.setting = setting;
    this.level = level;
    this.settingChanged = settingChanged;
    this.levelChanged = levelChanged;
  }
  
  /**
   * Return the setting of the filter at the time. 
   * @return FilterSetting in effect.
   */
  public FilterSetting getSetting()
  {
    return setting;
  }
  
  /**
   * Return the level of the log at the time.
   * @return LogLevel in effect.
   */
  public LogLevel getLevel()
  {
    return level;
  }
  
  /**
   * Return whether the setting changed.
   * @return change status
   */
  public boolean isSettingChanged()
  {
    return settingChanged;
  }
  
  /**
   * Return whether the level changed.
   * @return change status
   */
  public boolean isLevelChanged()
  {
    return levelChanged;
  }
}