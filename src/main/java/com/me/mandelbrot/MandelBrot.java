package com.me.mandelbrot;

import com.me.utility.ColorTool;
import com.me.utility.Scaler;
import edu.moravian.math.Point2D;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import com.me.steeringbehaviors.RunnableSim;
import com.me.steeringbehaviors.Settings;
import com.me.steeringbehaviors.WorldGraphics2D;

/**
 *
 * @author James Moore (moore.work@live.com)
 */
public class MandelBrot implements RunnableSim
  {

    private int maxIter = 1000;
    private Dimension screenSize;
    private double redMutator;
    private double blueMutator;
    private double greenMutator;
    private int time;
    GraphicsEnvironment env;
    GraphicsDevice device;
    GraphicsConfiguration config;
    BufferedImage buff;

    public MandelBrot()
      {
        screenSize = Settings.getInstance().getResolution();
        time = 0;
        Random rand = new Random();
        redMutator = rand.nextDouble();
        blueMutator = rand.nextDouble();
        greenMutator = rand.nextDouble();
        env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        device = env.getDefaultScreenDevice();
        config = device.getDefaultConfiguration();
        buff = config.createCompatibleImage(screenSize.width, screenSize.height, BufferedImage.TYPE_INT_RGB);

      }

//    public static void main(String[] args) throws IOException
//    {
//        MandelBrot man = new MandelBrot();
//        BufferedImage out = man.render();
//        File f = new File("saved2.png");
//        ImageIO.write(out, "png", f);
//    }
    @Override
    public final void draw(WorldGraphics2D w2d)
      {
        w2d.drawImage(this.render(), Point2D.ZERO, w2d);
      }

    @Override
    public void update()
      {
        //throw new UnsupportedOperationException("Not supported yet.");
      }

    public BufferedImage render()
      {

        int iterations;
        CyclicBarrier cb = new CyclicBarrier(screenSize.height);

        Thread[] threadz = new pixelIterator[screenSize.height];

        for (int y = 0; y < screenSize.height; y++)
          {

            //TODO get rid of these magic numbers

            threadz[y] = new pixelIterator(y, maxIter, time, buff);
            threadz[y].start();

            // iterations = iterateOnAPixel(xScaled, yScaled, maxIter);

            //buff.setRGB(x, y, ColorTool.getRGBColor(iterations, iterations / 2, iterations * time));
            //buff.setRGB(x, y, ColorTool.getRGBColor((int) (iterations + redMutator), (int) (iterations / greenMutator), (int) (iterations * blueMutator)));

          }
        time += 1;
        return buff;
      }

    private int iterateOnAPixel(double x0, double y0, int max_iteration)
      {
        int iteration = 0;
        double x, y;
        x = 0;
        y = 0;

        while ((x * x + y * y) < 4 && iteration < max_iteration)
          {
            double xtemp = x * x - y * y + x0;
            y = 2 * x * y + y0;

            x = xtemp;

            iteration = iteration + 1;
          }

        return iteration;
      }
  }

//TODO redo thread structore
//TODO find better way of communicating to the pic 
class pixelIterator extends Thread
  {

    private static int WIDTH;
    private int yIndex;
    private double x_0;
    private double y_0;
    private int maxIter;
    private int time;
    private Dimension picLoc;
    private BufferedImage img;
    private final Dimension screenSize = Settings.getInstance().getResolution();
    private static final double MBROT_X_HIGH_BOUND = 1.0;
    private static final double MBROT_X_LOW_BOUND = -1.0;
    private static final double MROT_Y_HIGH_BOUND = 1.0;
    private static final double MBROT_Y_LOW_BOUND = -2.5;
    private CyclicBarrier cb;

    public pixelIterator(int yIndex, int max_iter, int time, BufferedImage img)
      {
        this.yIndex = yIndex;
        this.maxIter = max_iter;
        this.time = time;

        this.img = img;
        WIDTH = img.getWidth();
      }

    @Override
    public void start()
      {

        for (int i = 0; i < WIDTH; i++)
          {

            double xScaled = Scaler.scale((double) screenSize.width, 0.0, MBROT_X_HIGH_BOUND, MBROT_X_LOW_BOUND, i);
            double yScaled = Scaler.scale((double) screenSize.height, 0.0, MROT_Y_HIGH_BOUND, MBROT_Y_LOW_BOUND, yIndex);
            int iteration = 0;

            double x, y;
            x = y = 0;
            while ((x * x + y * y) < 4 && iteration < maxIter)
              {
                double xtemp = x * x - y * y + xScaled;
                y = 2 * x * y + yScaled;

                x = xtemp;

                iteration = iteration + 1;
              }

            img.setRGB(i, yIndex, ColorTool.getRGBColor(iteration, iteration / 2, iteration * time));
          }
      }
    //TODO thread creation seems to take a long time? 
  }
