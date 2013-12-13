package com.me.steeringbehaviors;

import com.me.Sorting;
import edu.moravian.GOL.GameOfLife;
import edu.moravian.math.Point2D;
import edu.moravian.math.Vector2D;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import javax.imageio.IIOException;
import javax.swing.JFrame;
import mandelbrot.MandelBrot;
import steeringbehaviors.entities.Ball;
import steeringbehaviors.entities.EntityManager;
import steeringbehaviors.entities.TestBars;

/**
 *
 * @author moore
 */
public class Driver extends JFrame implements Simulation, KeyListener
{
    
    private int worldWidth;
    private int worldHeight;
    private int worldWidthCenter;
    private int worldHeightCenter;
    private LinkedList<RunnableSim> firstSet;
    private boolean endgameMet;
    private int lives;
    private Color background;
    private Settings set;
    
    public Driver(int worldWidth, int worldHeight) throws FileNotFoundException, FileNotFoundException, IIOException
    {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        worldWidthCenter = worldWidth / 2;
        worldHeightCenter = worldHeight / 2;
        set = Settings.getInstance();
        background = set.getBackgroundColor();
        
        firstSet = new LinkedList<RunnableSim>();
        //EntityManager.getInstance();
        //firstSet.add(new MandelBrot());
        firstSet.add(new GameOfLife());
     //   firstSet.add(new Sorting());
        set = Settings.getInstance();
        
        endgameMet = false;
    }
    
    @Override
    public void update()
    {
        for (RunnableSim b : firstSet)
        {
            b.update();
            //for (Ball a : balls)
            {
//                if (a != b)
//                {
//                    a.respondColission(b);
//                }
//            }
//
//            if (isOutOfBoundsX(b))
//            {
//                if (b.getLocation().getX() > worldWidthCenter && b.getVelocity().getX() > 0)
//                {
//                    b.setVelocity(new Vector2D(b.getVelocity().getX() * -1, b.getVelocity().getY()));
//                }
//                else if (b.getLocation().getX() < worldWidthCenter && b.getVelocity().getX() < 0)
//                {
//                    b.setVelocity(new Vector2D(b.getVelocity().getX() * -1, b.getVelocity().getY()));
//                }
//
//            }
//            if (isOutofBoundsY(b))
//            {
//                if (b.getLocation().getY() > worldHeightCenter && b.getVelocity().getY() > 0)
//                {
//                    b.setVelocity(new Vector2D(b.getVelocity().getX(), b.getVelocity().getY() * -1));
//                }
//                else if (b.getLocation().getY() < worldHeightCenter && b.getVelocity().getY() < 0)
//                {
//                    b.setVelocity(new Vector2D(b.getVelocity().getX(), b.getVelocity().getY() * -1));
//                }
            }
            
        }
        
        
    }
    
    @Override
    public void draw(WorldGraphics2D g2d)
    {
        g2d.setColor(background);
        g2d.fillRect(0, 0, worldWidth, worldHeight);
        
        for (RunnableSim a : firstSet)
        {
            a.draw(g2d);
            g2d.render();
        }
        //g2d.drawTrangle(new Point2D(100,100), new Vector2D(1,0));
        //  System.out.println(new Vector2D(1,0).angle());
    }
    
    @Override
    public boolean done()
    {
        return endgameMet;
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
            endgameMet = true;
        }
    }
    
    public void keyReleased(KeyEvent ke)
    {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Determines if a ball has fallen outside the sides
     *
     * @param b
     * @return
     */
    private boolean isOutOfBoundsX(Ball b)
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
     * Determines if a ball has fallen outside the vertical bounds
     *
     * @param b
     * @return
     */
    private boolean isOutofBoundsY(Ball b)
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
