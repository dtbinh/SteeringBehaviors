package com.me.steeringbehaviors;

import com.me.utility.VideoConfigurationException;
import java.io.FileNotFoundException;
import javax.imageio.IIOException;

/**
 *
 * @author James Moore (moore.work@live.com)
 */
public class Main
  {
    
        //TODO clean here
    //TODO resolution manager class? 
    private static final int DBITDEPTH = 32;
    //private static final int DEFAULT_WIDTH = 800;
    //private static final int DEFAULT_HEIGHT = 600;
/**
 * No need to allow you cheeky bastards to instantiate this
 */
    private Main()
      {
      }
    


    /**
     * Runs the game!
     *
     * @param args
     * @throws FileNotFoundException
     * @throws IIOException
     */
    public static void main(final String[] args) throws FileNotFoundException, IIOException
      {

        int defaultWidth;
        //= DEFAULT_WIDTH;
        int defaultHeight;
        //= DEFAULT_HEIGHT;
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
//TODO framerate smoothing