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
    int valOne = one.ordinal();
    int valTwo = two.ordinal();
    
    return valOne - valTwo;
  }
  
  public static boolean equals(LogLevel one, LogLevel two)
  {
    return compare(one, two) == 0;
  }
}
