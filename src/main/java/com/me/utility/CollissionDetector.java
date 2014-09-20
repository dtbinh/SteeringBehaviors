package com.me.utility;

import edu.moravian.math.Vector2D;
import steeringbehaviors.entities.Ball;

/**
 *
 * @author James Moore (moore.work@live.com)
 */
public final class CollissionDetector
  {
/**
 * Utility class, no need to allow instatiation 
 */
    private CollissionDetector()
      {
      }

    /**
     * Static method that determines if two balls are going to collide
     *
     * @param ballA First ball
     * @param ballB Second ball
     * @return Are the balls colliding now or will they between frames?
     */
    public static boolean twoSpheresColliding(final Ball ballA, final Ball ballB)
      {
        //double distance = Math.sqrt(Math.pow(ballA.getCenter().getX() - ballB.getCenter().getX(), 2)    + Math.pow(ballA.getCenter().getY() - ballB.getCenter().getY(), 2));
        double distanceSQ = Math.pow(ballA.getCenter().getX() - ballB.getCenter().getX(), 2)
                + Math.pow(ballA.getCenter().getY() - ballB.getCenter().getY(), 2);
        //Get the distance between the two centers 

        //Sum the radii 
        double sumRadii = (ballB.getRadius() + ballA.getRadius());

        Vector2D movecAtoB = ballA.getVelocity();

        //If the movement vectors is smaller than the distance between the two bodies..
        if (movecAtoB.magnitude() < (distanceSQ - (sumRadii * sumRadii)))
          {
            //We ain't colliding
            return false;
          }

        //Get a vector from B to A 
        Vector2D cVar = ballB.getCenter().minus(ballA.getCenter());

        //Compare that vector to a vector from A to B 
        double dpOfDirections = movecAtoB.getNormalized().dot(cVar);
        if (dpOfDirections < 0)
          {
            //If this is negative, A is not moving toward B 
            return false;
          }


        //Get the distance between the two bodies
        Double distanceFromB = cVar.magnitudeSq() - (dpOfDirections * dpOfDirections);


        //If that is bigger
        if (distanceFromB >= (sumRadii * sumRadii))
          {
            //We ain't colliding 
            return false;
          }

        Double tVar = (sumRadii * sumRadii) - distanceFromB;

        //If we have gone past the second circle 
        if (tVar < 0)
          {
            return false;
          }

        Double travelDistance = dpOfDirections - Math.sqrt(tVar);

        //If we can't reach
        if (movecAtoB.magnitude() < travelDistance)
          {
            return false;
          }

        return true;
      }
  }
