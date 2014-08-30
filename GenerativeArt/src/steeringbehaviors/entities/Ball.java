package steeringbehaviors.entities;

import com.me.steeringbehaviors.RunnableSim;

import edu.moravian.math.Point2D;
import edu.moravian.math.Vector2D;

import java.awt.Color;

import utility.CollissionDetector;

import com.me.steeringbehaviors.WorldGraphics2D;

/**
 * This is an entity that responds to other entities and its own behavior.
 *
 * @author James Moore (moore.work@live.com)
 */
public class Ball implements RunnableSim
{

    private Point2D ballLocation;
    private Vector2D velocity;
    private double ballMass;
    private double ballRadius;
    private Color ballColor;
    public static final int PERFECTLY_ELASTIC = 1;
    public static final int PERFECTLY_INELASTIC = 0;
    private double elasticity;

    /**
     * This constructs an entity with the given parameters, with a SYSTEM
     * SPECIFIED elasticity. One can also specify it individually with an
     * additional parameter
     *
     * @param locationIn The location (preferably within the world) for the upper
     * left corner of the entity
     * @param velocityIn The initial velocity of the entity
     * @param mass The mass of the entity
     * @param radius the radius in world units of the entity
     * @param color the color of the entity. the ball will be solid
     */
    public Ball(final Point2D locationIn, final Vector2D velocityIn,  final double mass, final double radius, final Color color)
    {
        this.ballLocation = locationIn;
        this.velocity = velocityIn;
        this.ballMass = mass;
        this.ballRadius = radius;
        this.ballColor = color;

        elasticity = 1;
    }

    /**
     * The entities handle their own drawing for now. Colors are safe
     *
     * @param g2d
     */
    @Override
    public final void draw(WorldGraphics2D g2d)
    {
        Color old = g2d.getColor();
        g2d.setColor(ballColor);
        
        g2d.drawArrow(this.getCenter(), velocity, 1000);

      //  g2d.fillOval((int) location.getX(), (int) location.getY(), (int) radius * 2, (int) radius * 2);
        g2d.drawPixel(ballLocation, Color.yellow);

        g2d.setColor(old);
    }

    /**
     * Updates the position of the ball within the world
     */
    @Override
    public void update()
    {
        this.ballLocation.set(this.ballLocation.scalePlus(1, velocity));
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
        return ballMass;
    }

    public double getRadius()
    {
        return ballRadius;
    }

    public Color getColor()
    {
        return ballColor;
    }

    public void setLocation(Point2D location)
    {
        this.ballLocation = location;
    }

    public Point2D getLocation()
    {
        return ballLocation;
    }

    public Point2D getCenter()
    {
        return new Point2D(ballLocation.getX() + (ballRadius), ballLocation.getY() + (ballRadius));
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
