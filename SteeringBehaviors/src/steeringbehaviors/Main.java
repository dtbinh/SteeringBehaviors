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
    //Default resolutions 

    private static int DWIDTH = 800;
    private static int DHEIGHT = 600;
    private static int DBITDEPTH = 32;

    public static void main(String[] args) throws FileNotFoundException, IIOException
      {

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension resolution = tk.getScreenSize();

        DWIDTH = resolution.width;
        DHEIGHT = resolution.height;
        Settings.getInstance().setResolution(new Dimension(DWIDTH, DHEIGHT));

        // Create our game with a world size equal to
        // the screen size
        SteeringBehaviors g = new SteeringBehaviors(DWIDTH, DHEIGHT);


        try
          {
            // Create the video controller.  This will throw if something
            // goes wrong
            UserInterfaceController video = new UserInterfaceController(DWIDTH, DHEIGHT, DBITDEPTH, g);

            // And run the game
            new Thread(video).start();
          }
        catch (VideoConfigurationException ex)
          {
            System.out.println("Unable to display " + DWIDTH + "x" + DHEIGHT
                    + "x" + DBITDEPTH + " in full screen mode");
          }
      }
  }
