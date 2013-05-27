package steeringbehaviors;

import edu.moravian.math.Point2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * This class encapsulates the interaction with the screen
 * @author James Moore (moore.work@live.com)
 */
public class WorldGraphics2D
{

    Graphics2D g2d;
    CoordinateTranslator trans;
    Settings set = Settings.getInstance();

    public WorldGraphics2D(Graphics g)
    {
        this.g2d = (Graphics2D) g;
        trans = new CoordinateTranslator(
                set.getResolution().width,
                set.getResolution().height,
                set.getWorldSize().width,
                set.getWorldSize().height);
    }

    public void setColor(Color background)
    {
        g2d.setColor(background);
    }

    void fillRect(Point2D location, int worldWidth, int worldHeight)
    {
        Point2D lower_left = trans.worldtoScreen(location);
        g2d.fillRect((int) lower_left.getX(), (int) lower_left.getY(), worldWidth, worldHeight);
    }

    public Color getColor()
    {
        return g2d.getColor();
    }

    void drawString(String string, Point2D point, Color c)
    {
        Color old = g2d.getColor();
        g2d.setColor(c);
        g2d.drawString(string, (int) point.getX(), (int) point.getY());
        g2d.setColor(old);

    }

    void drawLine(Point2D start, Point2D end)
    {
        g2d.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
    }

    public void drawImage(BufferedImage apearance, Point2D location, Object object)
    {
        g2d.drawImage(apearance, (int)location.getX(), (int)location.getY(), null);
    }

    void fillRect(int i, int i0, int worldWidth, int worldHeight)
    {
        g2d.fillRect(i, i0, worldWidth, worldHeight);
    }

    void drawString(String string, int i, int i0)
    {
        g2d.drawString(string, i, i0);
    }

    public void fillOval(int i, int i0, int i1, int i2)
    {
        g2d.fillOval(i, i0, i2, i2);
    }
}
