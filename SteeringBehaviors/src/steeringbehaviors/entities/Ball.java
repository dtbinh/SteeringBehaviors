package steeringbehaviors.entities;

import com.me.steeringbehaviors.RunnableSim;
import edu.moravian.math.Point2D;
import edu.moravian.math.Vector2D;
import java.awt.Color;
import com.me.utility.CollissionDetector;
import com.me.steeringbehaviors.WorldGraphics2D;

/**
 * This is an entity that responds to other entities and its own behavior.
 *
 * @author James Moore (moore.work@live.com)
 */
public class Ball implements RunnableSim
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
    public Ball(Point2D location, Vector2D velocity, double mass, double radius, Color color)
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
        
        g2d.drawArrow(this.getCenter(), velocity, 1000);

      //  g2d.fillOval((int) location.getX(), (int) location.getY(), (int) radius * 2, (int) radius * 2);
        g2d.drawPixel(location, Color.yellow);

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

    /**
     * Responds to a collision between this ball and the input ball; BOTH ARE
     * MODIFIED.
     *
     * @param other
     * @return
     */
    public void respondColission(Ball other)
    {
        if (CollissionDetector.twoSpheresColliding(this, other))
        {

            Vector2D collissionNormal = this.getCenter().minus(other.getCenter());

            //The distance between the two centers
            double distance = collissionNormal.magnitude();

            //Once we have the distance, we want to normalize the vector so it is true to its name
            collissionNormal.normalize();
            double radiusSum = this.getRadius() + other.getRadius();

            /*
             * On a colission event the bodies will not be exactly touching
             * this is the distance between an exact colission of the two 
             * bodies
             */
            double penetration = radiusSum - distance;

            //"Push" the balls out so they touch 
            this.setLocation(this.getLocation().plus(collissionNormal.times(penetration * .5)));
            other.setLocation(other.getLocation().plus(collissionNormal.times(penetration * -.5)));

            //Get the relative velocity 
            Vector2D new_Velocity = this.getVelocity().minus(other.getVelocity());


            double vDotN = new_Velocity.dot(collissionNormal);
            //Determine the direction of the new  velocity and the old velocity 

            //Compute the impulse factor
            double num = vDotN * -(1 + elasticity);

            double denominator = (collissionNormal.dot(collissionNormal));

            denominator *= (1 / this.getMass() + 1 / other.getMass());

            double impulse_factor = num / denominator;

            //Modify the velocities 
            this.setVelocity(this.getVelocity().plus(collissionNormal.times(impulse_factor / this.getMass())));
            other.setVelocity(other.getVelocity().minus(collissionNormal.times(impulse_factor / other.getMass())));
        }
    }
}
