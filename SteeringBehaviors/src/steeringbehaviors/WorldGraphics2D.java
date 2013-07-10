package steeringbehaviors;

import steeringbehaviors.util.CoordinateTranslator;
import edu.moravian.math.Point2D;
import edu.moravian.math.Vector2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class encapsulates the interaction with the screen
 *
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

    /**
     *  Draw an image.
     * @param apearance
     * @param location
     * @param object  This can be null
     */
    public void drawImage(BufferedImage apearance, Point2D location, Object object)
      {
        g2d.drawImage(apearance, (int) location.getX(), (int) location.getY(), null);
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

    /**
     * Draw a triangle at a location, x and y must be of length 3
     *
     * @param location
     * @param x
     * @param y
     * @param col
     */
    public void drawTrangle(Point2D location, Vector2D direction)
      {
        double angle = direction.angle();
        g2d.rotate(angle);
        
        BufferedImage img;

        try
          {
            img = ImageIO.read(new File("traingle.png"));
            this.drawImage(img, location, null);
          }
        catch (IOException e)
          {
              System.out.println("Cannot find a triangle");
          }
        
        g2d.rotate(-1 * angle);
        
      }

    public void drawArrow(Point2D location, Vector2D velocity, int i)
    {
        Color col = g2d.getColor();
        velocity = velocity.getNormalized();
        g2d.setColor(Color.white);
        //Point2D realPoint = trans.worldtoScreen(location);
        Point2D realPoint = location;
        g2d.drawLine((int)realPoint.getX(), (int)realPoint.getY(), (int)realPoint.scalePlus(i, velocity).getX(),(int)realPoint.scalePlus(i,velocity).getY());
        g2d.setColor(col);
        
    }
    

  }
