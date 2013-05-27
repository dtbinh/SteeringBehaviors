package steeringbehaviors;

import edu.moravian.math.Point2D;
import edu.moravian.math.Vector2D;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import javax.imageio.IIOException;
import javax.swing.JFrame;
import steeringbehaviors.entities.Entity;
import steeringbehaviors.entities.EntityManager;

/**
 *
 * @author moore
 */
public class SteeringBehaviors extends JFrame implements Game, KeyListener
{

    private int worldWidth;
    private int worldHeight;
    private int worldWidthCenter;
    private int worldHeightCenter;
    private LinkedList<Entity> balls;
    private boolean endgame_met;
    private int lives;
    private Color background;
    private Settings set;

    public SteeringBehaviors(int worldWidth, int worldHeight) throws FileNotFoundException, FileNotFoundException, IIOException
    {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        worldWidthCenter = worldWidth / 2;
        worldHeightCenter = worldHeight / 2;
        set = Settings.getInstance();
        background = set.getBackgroundColor();

        balls = EntityManager.getInstance();

        set = Settings.getInstance();

        endgame_met = false;
    }

    @Override
    public void update()
    {
        for (Entity b : balls)
        {
            b.update();

            for (Entity a : balls)
            {
                if (a != b)
                {
                    a.respondColission(b);
                }
            }
//TODO ball bouncing
            if (isOutOfBoundsX(b))
            {
                if (b.getLocation().getX() > worldWidthCenter && b.getVelocity().getX() > 0)
                {
                    b.setVelocity(new Vector2D(b.getVelocity().getX() * -1, b.getVelocity().getY()));
                }
                else if (b.getLocation().getX() < worldWidthCenter && b.getVelocity().getX() < 0)
                {
                    b.setVelocity(new Vector2D(b.getVelocity().getX() * -1, b.getVelocity().getY()));
                }

            }
            if (isOutofBoundsY(b))
            {
                if (b.getLocation().getY() > worldHeightCenter && b.getVelocity().getY() > 0)
                {
                    b.setVelocity(new Vector2D(b.getVelocity().getX(), b.getVelocity().getY() * -1));
                }
                else if (b.getLocation().getY() < worldHeightCenter && b.getVelocity().getY() < 0)
                {
                    b.setVelocity(new Vector2D(b.getVelocity().getX(), b.getVelocity().getY() * -1));
                }
            }

        }


    }

    @Override
    public void draw(WorldGraphics2D g2d)
    {
        g2d.setColor(background);
        g2d.fillRect(0, 0, worldWidth, worldHeight);

        for (Entity a : balls)
        {
            a.draw(g2d);
        }
    }

    @Override
    public boolean done()
    {
        return endgame_met;
    }

    public void keyTyped(KeyEvent ke)
    {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void keyPressed(KeyEvent ke)
    {
        //throw new UnsupportedOperationException("Not supported yet.");
        if (ke.getKeyChar() == KeyEvent.VK_SPACE)
        {
            endgame_met = true;
        }
    }

    public void keyReleased(KeyEvent ke)
    {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Determines if a ball has fallen outside the sides
     * @param b
     * @return 
     */
    private boolean isOutOfBoundsX(Entity b)
    {
        //TODO calculating out of bounds with velocity, not bounds 
        Vector2D dir;
        if (b.getLocation().getX() > worldWidthCenter)
        {
            dir = new Vector2D(1, 0);
        }
        else
        {
            dir = new Vector2D(-1, 0);
        }

        Point2D newLoc = b.getCenter().scalePlus(b.getRadius(), dir);
        return (newLoc.getX() < 0 || newLoc.getX() > worldWidth);

    }

    /**
     * Determines if a ball has fallen outside the veritcal bounds 
     * @param b
     * @return 
     */
    private boolean isOutofBoundsY(Entity b)
    {
        Vector2D dir;
        if (b.getLocation().getY() > worldHeightCenter)
        {
            dir = new Vector2D(0, 1);
        }
        else
        {
            dir = new Vector2D(0, -1);
        }

        Point2D newLoc = b.getCenter().scalePlus(b.getRadius(), dir);

        return (newLoc.getY() < 0 || newLoc.getY() > worldHeight);
    }
}
