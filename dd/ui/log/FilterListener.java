/**
 * 
 */
package dd.ui.log;

import java.util.EventListener;

/**
 * @author Mathew Grabau
 *
 */
public interface FilterListener extends EventListener
{
  void filterChanged(FilterEvent e);
}
