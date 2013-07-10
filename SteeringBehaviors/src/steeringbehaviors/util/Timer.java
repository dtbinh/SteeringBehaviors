package steeringbehaviors.util;

import steeringbehaviors.Settings;

/**
 * Timer class that handles time.  The external unit of this class is seconds.  
 * @author moore
 */
public class Timer
{

    private double beginTime;
    private static final double  fpsLen = Settings.getInstance().getDesiredFPS();

   /**
    * Creates a new Timer
    */
    public Timer()
    {
        beginTime = System.currentTimeMillis();
    }

    /**
     * Start new timer cycle.  
     */
    public void tick()
    {
        beginTime = System.currentTimeMillis();
    }

    /**
     * Get the ideal FPS for the game 
     * @return 
     */
    public double getFPS()
    {
        return 1000.0/fpsLen;
    }

   /**
    * Get time since last tick, in seconds 
    * @return  Delta in seconds 
    */
    public double getDelta()
    {
        return (System.currentTimeMillis() - beginTime) / 1000;
    }
}
