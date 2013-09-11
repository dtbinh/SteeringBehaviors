
package com.me.steeringbehaviors;

/**
 *
 * @author moore
 */
public interface RunnableSim
{
    /**
     * Draws the entity 
     * @param w2d an instantiated world2D, feel free to draw to it
     */
      void draw(WorldGraphics2D w2d);
   
     /**
      * Updates the entity 
      */
      void update();
    
}
