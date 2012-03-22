/**
 * DirectedDiffusion
 */
package dd.ui.plot;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileFilter;

/**
 * @author mgrabau
 *
 */
public class ImageFilter extends FileFilter
{
  
  protected String extensionToCheck = "";
  protected String description = "";

  public boolean isSupported()
  {
    for (String s : ImageIO.getWriterFileSuffixes())
    {
      if (s.equalsIgnoreCase(extensionToCheck))
        return true;
    }
    
    return false;
  }
  
  /* (non-Javadoc)
   * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
   */
  @Override
  public boolean accept(File f)
  {
    if (f.isDirectory())
      return true;
    
    String extension = getExtension(f);
    System.out.println(extension);
    System.out.println(extension != null && extension.equalsIgnoreCase(extensionToCheck));
    return (extension != null && extension.equalsIgnoreCase(extensionToCheck));
  }
  
  public static String getExtension(File f)
  {
    String name = f.getName();
    
    System.out.println(name);
    
    // check for the dot at the end fo the file
    int extStart = name.lastIndexOf(".");
    if (extStart == -1)
      return null;
    
    System.out.println(extStart);
    
    if (name.length() > extStart)
    {
      System.out.println("FOund the extension");
      return name.substring(extStart + 1);
    }
    
    
    
    return null;
  }

  /* (non-Javadoc)
   * @see javax.swing.filechooser.FileFilter#getDescription()
   */
  @Override
  public String getDescription()
  {
    return description;
  }

}
