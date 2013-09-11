package com.me.steeringbehaviors;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Settings class, get over it.  
 * @author moore
 */
public class Settings
{

    private final static Settings instance = new Settings();
    private final static Dimension worldSize = new Dimension(2000,1000);
    private static final int RADIUS_SCALE_CONST = 10;
    private Dimension res;

    private Settings()
    {
             Toolkit tk = Toolkit.getDefaultToolkit();
         res = tk.getScreenSize();

    }

    public static Settings getInstance()
    {
        return instance;
    }

    public Color getBackgroundColor()
    {
        return Color.gray;
    }

    public double getDesiredFPS()
    {
        return 60;
    }

    public double getElasticity()
    {
        return 0;
    }

    public double getWorldWidth()
    {
        return worldSize.getWidth();
    }

    public double getRadiusScale()
    {
        return RADIUS_SCALE_CONST;
    }

    public double getWorldHeight()
    {
        return worldSize.getHeight();
    }
 
    public String getBallFileName()
    {
        return "Balls.ini";
    }

    public void setResolution(Dimension resolution)
    {
        res = resolution;
    }

    public Dimension getResolution()
    {
        return res;
    }

    public Dimension getWorldSize()
    {
        return worldSize;
    }
}
