package steeringbehaviors;



import java.awt.Color;
import java.awt.Dimension;

/**
 *
 * @author moore
 */
public class Settings
{

    public final static Settings instance = new Settings();
    private final static Dimension worldsize = new Dimension(1920,1080);
    private Dimension res;

    private Settings()
    {
        // Exists only to defeat instantiation.
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
