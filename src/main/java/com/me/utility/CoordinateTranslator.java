package com.me.utility;

import edu.moravian.math.Point2D;

/**
 * This class translates between screen coordinates and world coordinates
 *
 * @author James Moore (moore.work@live.com)
 */
public class CoordinateTranslator
  {

    //Remember, screen's 0,0 is upper left 
    //World's 0,0 is lower right 
    private int screenWidth;
    private int screenHeight;
    private double worldWidth;
    private double worldHeight;

    /**
     * @param screenWidth Screen width in pixels
     * @param screenHeight Screen Height in pixels
     * @param worldWidth world width in w/e
     * @param worldHeight world height in w/e
     */
    public CoordinateTranslator(int screenWidth, int screenHeight, double worldWidth, double worldHeight)
      {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
      }

    public Point2D screenToWorld(Point2D screenPoint)
      {
        return new Point2D((screenPoint.getX() / screenWidth) * worldWidth,
                (1 - screenPoint.getY() / screenHeight) * worldHeight);
      }

    public Point2D worldtoScreen(Point2D worldPoint)
      {
        return new Point2D(worldPoint.getX() / worldWidth * screenWidth,
                (1 - worldPoint.getY() / worldHeight) * screenHeight);
      }
  }
