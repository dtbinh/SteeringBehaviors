package edu.moravian.GOL;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import steeringbehaviors.RunnableSim;
import steeringbehaviors.Settings;
import steeringbehaviors.WorldGraphics2D;

/**
 *
 * @author James Moore (moore.work@live.com)
 */
public class GameOfLife implements RunnableSim 
  {

    private static final int BOARD_WIDTH = 5;
    private static final int BOARD_HEIGHT = 5;
    private static final int STARVE_THRESHOLD = 2;
    private static final int RESURRECTION_AMOUNT = 3;
    private static final int OVERCROWDING_THRESHOLD = 3;
    private static final int ITERATION_COUNT = 1000;
    GraphicsEnvironment env;
    GraphicsDevice device;
    GraphicsConfiguration config;
    BufferedImage buff;

    public GameOfLife()
      {
                Dimension screenSize = Settings.getInstance().getResolution();
                env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        device = env.getDefaultScreenDevice();
        config = device.getDefaultConfiguration();
        buff = config.createCompatibleImage(screenSize.width, screenSize.height, BufferedImage.TYPE_INT_RGB);
      }

    
    
    
    public static void main(String[] args)
      {
        boolean[][] world;
        double spawnThreshhold = .4;
        world = new boolean[BOARD_WIDTH][BOARD_HEIGHT];
        for (int x = 0; x < world.length; x++)
          {
            for (int y = 0; y < world[x].length; y++)
              {
                if (Math.random() > spawnThreshhold)
                  {
                    world[x][y] = true;
                  }
              }
          }

        outputBoard(world);

        int iters = runGame(world, true);
      }

    private static void outputBoard(boolean[][] world)
      {
        char rep;
        for (int i = 0; i < world.length; i++)
          {
            for (int j = 0; j < world[i].length; j++)
              {
                if (world[i][j] == true)
                  {
                    rep = '*';
                  }
                else
                  {
                    rep = ' ';
                  }
                System.out.printf("%-2c", rep);
              }
            System.out.println("");
          }
          System.out.println("-----------------------------------");
      }

    private static int runGame(boolean[][] world_in, boolean b)
      {
        int iterations = 0;
        boolean[][] world = world_in.clone();
        boolean[][] nextWorld = world_in.clone();
        boolean output = true;
        while (isBoardDead(world) == false && iterations < ITERATION_COUNT)
          {
            world = nextWorld.clone();

            for (int x = 0; x < world.length; x++)
              {
                for (int y = 0; y < world[x].length; y++)
                  {
                    int ne = countNeighbors(world, x, y);

                    if (world[x][y] == true)
                      {
                        if (ne < STARVE_THRESHOLD)
                          {
                            nextWorld[x][y] = false;
                          }

                        if (ne > OVERCROWDING_THRESHOLD)
                          {
                            nextWorld[x][y] = false;
                          }
                      }
                    else
                      {
                        if (ne == RESURRECTION_AMOUNT)
                          {
                            nextWorld[x][y] = true;
                          }
                      }
                  }
              }
            if (output == true)
              {
                outputBoard(world);
              }
            iterations += 1;
          }
        return iterations;
      }

    private static int countNeighbors(boolean[][] world, int x, int y)
      {

        int count = 0;
        int xL = x - 1;
        int xR = x + 1;
        int yD = y - 1;
        int yU = y + 1;
        if (withinXBoundries(xL))
          {
            count += accountFor(world[x][y]);
            if (withinYBoundries(yU))
              {
                count += accountFor(world[x][yU]);
              }
            if (withinYBoundries(yD))
              {
                count += accountFor(world[x][yD]);
              }
          }

        if (withinXBoundries(xR))
          {
            count += accountFor(world[x][y]);
            if (withinYBoundries(yU))
              {
                count += accountFor(world[x][yU]);
              }
            if (withinYBoundries(yD))
              {
                count += accountFor(world[x][yD]);
              }
          }

        if (withinYBoundries(yU))
          {
            count += accountFor(world[x][yU]);
          }

        if (withinYBoundries(yD))
          {
            count += accountFor(world[x][yD]);
          }

        return count;
      }

    private static boolean withinXBoundries(int x)
      {
        boolean withinXLeft = x == Math.abs(x);
        boolean withinYRight = x < BOARD_HEIGHT;

        return withinXLeft && withinYRight;
      }

    private static boolean withinYBoundries(int y)
      {
        boolean withinYLeft = y == Math.abs(y);
        boolean withinYRight = y < BOARD_HEIGHT;

        return withinYLeft && withinYRight;
      }

    private static int accountFor(boolean board)
      {
        if (board == true)
          {
            return 1;
          }
        else
          {
            return 0;
          }
      }

    private static boolean isBoardDead(boolean[][] world)
      {
        boolean status = true;
        for (int x = 0; x < world.length; x++)
          {
            for (int y = 0; y < world[x].length; y++)
              {
                if (world[x][y] == true)
                  {
                    status = false;
                    break;
                  }
              }
          }
        return status;
      }

    @Override
    public void draw(WorldGraphics2D w2d)
      {
      
     }

    @Override
    public void update()
      {
        throw new UnsupportedOperationException("Not supported yet.");
      }
  }
