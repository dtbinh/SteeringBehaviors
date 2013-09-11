package com.me.utility;

//TODO replace this 

/**
 * This class represents a video configuration exception.
 * Used when the desired resolution is not avilable or
 * the system cannot go into fullscreen mode.
 * 
 * @author mebjc01
 */
public class VideoConfigurationException extends Exception
{
    /**
     * An exception to indicate that the video configuration is bad
     * @param msg 
     */
    public VideoConfigurationException(final String msg)
    {
        super(msg);
    }

    // There is nothing else to do.  This class inherits everything
    // it needs from Exception
}
