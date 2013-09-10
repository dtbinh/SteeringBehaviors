package steeringbehaviors;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import javax.imageio.IIOException;

/**
 *
 * @author James Moore (moore.work@live.com)
 */
public class Main
  {
    //TODO resolution manager class? 

    private static int DBITDEPTH = 32;

    public static void main(String[] args) throws FileNotFoundException, IIOException
      {

        int defaultWidth = 800;
        int defaultHeight = 600;
        // Create our game with a world size equal to
        // the screen size

        defaultWidth = Settings.getInstance().getResolution().width;
        defaultHeight = Settings.getInstance().getResolution().height;

        Driver g = new Driver(defaultWidth, defaultHeight);


        try
          {
            // Create the video controller.  This will throw if something
            // goes wrong
            UserInterfaceController video = new UserInterfaceController(defaultWidth, defaultHeight, DBITDEPTH, g);

            // And run the game
            new Thread(video).start();
          }
        catch (VideoConfigurationException ex)
          {
            System.out.println("Unable to display " + defaultWidth + "x" + defaultHeight
                    + "x" + DBITDEPTH + " in full screen mode");
          }
      }
  }
