package steeringbehaviors;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Settings class, get over it.  
 * @author moore
 */
public class Settings
{

    public final static Settings instance = new Settings();
    private final static Dimension worldsize = new Dimension(2000,1000);
    private Dimension res;

    private Settings()
    {
             Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension resolution = tk.getScreenSize();

        Settings.getInstance().setResolution(new Dimension(resolution.width,  resolution.height));
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
        return worldsize.getWidth();
    }

    public double getRadiusScale()
    {
        return 10;
    }

    public double getWorldHeight()
    {
        return worldsize.getHeight();
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
        return worldsize;
    }
}
