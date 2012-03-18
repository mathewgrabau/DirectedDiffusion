/**
 * 
 */
package dd.ui.log;

/**
 * @author Mathew Grabau
 *
 */
public final class LogLevelUtil
{
  // no instances being created of this one, cause it
  // cannot create an instance 
  private LogLevelUtil() 
  {
    throw new AssertionError();
  }
  
  public static int getValue(LogLevel ll)
  {
    switch (ll)
    {
    case NONE:
      return 0;
    case ERROR:
      return 1;
    case WARN:
      return 2;
    case INFO:
      return 3;
    case DEBUG:
      return 4;
    default:
        return -1;
    }
  }
  
  /**
   * Compares two log levels, returning a negative number if the first is less
   * than the second (less information should be shown), 0 if equal, positive
   * if the second is greater
   * @param one second level to compare
   * @param two first level to compare
   * @return
   */
  public static int compare(LogLevel one, LogLevel two)
  {
    // assign the according integer
    int valOne = getValue(one);
    int valTwo = getValue(two);
    
    return valOne - valTwo;
  }
  
  public static boolean equals(LogLevel one, LogLevel two)
  {
    return compare(one, two) == 0;
  }
}
