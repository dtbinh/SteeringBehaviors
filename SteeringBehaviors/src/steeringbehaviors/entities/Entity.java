package steeringbehaviors.entities;

import steeringbehaviors.RunnableSim;
import edu.moravian.math.Point2D;
import edu.moravian.math.Vector2D;
import java.awt.Color;
import Utility.CollissionDetector;
import steeringbehaviors.WorldGraphics2D;

/**
 * This is an entity that responds to other entities and its own behavior.
 *
 * @author James Moore (moore.work@live.com)
 */
public class Entity implements RunnableSim
{

    private Point2D location;
    private Vector2D velocity;
    private double mass;
    private double radius;
    private Color color;
    public static final int PERFECTLY_ELASTIC = 1;
    public static final int PERFECTLY_INELASTIC = 0;
    private double elasticity;

    /**
     * This constructs an entity with the given parameters, with a SYSTEM
     * SPECIFIED elasticity. One can also specify it individually with an
     * additional parameter
     *
     * @param location The location (preferably within the world) for the upper
     * left corner of the entity
     * @param velocity The initial velocity of the entity
     * @param mass The mass of the entity
     * @param radius the radius in world units of the entity
     * @param color the color of the entity. the ball will be solid
     */
    public Entity(Point2D location, Vector2D velocity, double mass, double radius, Color color)
    {
        this.location = location;
        this.velocity = velocity;
        this.mass = mass;
        this.radius = radius;
        this.color = color;

        elasticity = 1;
    }

    /**
     * The entities handle their own drawing for now. Colors are safe
     *
     * @param g2d
     */
    @Override
    public void draw(WorldGraphics2D g2d)
    {
        Color old = g2d.getColor();
        g2d.setColor(color);

        g2d.fillOval((int) location.getX(), (int) location.getY(), (int) radius * 2, (int) radius * 2);
        
        g2d.drawArrow(location, velocity, 1);

        g2d.setColor(old);
    }

    /**
     * Updates the position of the ball within the world
     */
    @Override
    public void update()
    {
        this.location.set(this.location.scalePlus(1, velocity));
    }

    public Vector2D getVelocity()
    {
        return velocity;
    }

    public void setVelocity(Vector2D velocity)
    {
        this.velocity = velocity;
    }

    public double getMass()
    {
        return mass;
    }

    public double getRadius()
    {
        return radius;
    }

    public Color getColor()
    {
        return color;
    }

    public void setLocation(Point2D location)
    {
        this.location = location;
    }

    public Point2D getLocation()
    {
        return location;
    }

    public Point2D getCenter()
    {
        return new Point2D(location.getX() + (radius), location.getY() + (radius));
    }


}
