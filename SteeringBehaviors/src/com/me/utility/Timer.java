package com.me.utility;

import com.me.steeringbehaviors.Settings;

/**
 * Timer class that handles time.  The external unit of this class is seconds.  
 * @author moore
 */
public class Timer
{
    private static final double MILLIS_DIVISOR = 1000.0;
    private static final int MILLIS_DIVISOR_INT = 1000;
    private double beginTime;
    private static final double  FPS_LEN = Settings.getInstance().getDesiredFPS();

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
        return MILLIS_DIVISOR / FPS_LEN;
    }

   /**
    * Get time since last tick, in seconds 
    * @return  Delta in seconds 
    */
    public double getDelta()
    {
        return (System.currentTimeMillis() - beginTime) / MILLIS_DIVISOR_INT;
    }
}
