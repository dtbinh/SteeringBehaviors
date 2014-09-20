package com.me.steeringbehaviors;

import com.me.utility.VideoConfigurationException;
import java.io.FileNotFoundException;
import javax.imageio.IIOException;

/**
 * Mother freaking art
 *
 * @author James Moore (moore.work@live.com)
 */
public class Main
  {
    private static final int DBITDEPTH = 32;

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

        int defaultHeight;

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

