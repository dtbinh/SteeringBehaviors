package mandelbrot;

import Utility.ColorTool;
import Utility.Scaler;
import com.sun.jndi.toolkit.ctx.HeadTail;
import edu.moravian.math.Point2D;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import steeringbehaviors.RunnableSim;
import steeringbehaviors.Settings;
import steeringbehaviors.WorldGraphics2D;

/**
 *
 * @author James Moore (moore.work@live.com)
 */
public class MandelBrot implements RunnableSim
  {

    private static final double MBROT_X_HIGH_BOUND = 1.0;
    private static final double MBROT_X_LOW_BOUND = -1.0;
    private static final double MROT_Y_HIGH_BOUND = 1.0;
    private static final double MBROT_Y_LOW_BOUND = -2.5;
    private int maxIter = 1000;
    private BufferedImage visual;
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

      }

    public static void main(String[] args) throws IOException
      {
        MandelBrot man = new MandelBrot();
        BufferedImage out = man.render();
        File f = new File("saved2.png");
        ImageIO.write(out, "png", f);
      }

    @Override
    public void draw(WorldGraphics2D w2d)
      {
        w2d.drawImage(this.render(), Point2D.zero, w2d);
      }

    @Override
    public void update()
      {
        //throw new UnsupportedOperationException("Not supported yet.");
      }

    public BufferedImage render()
      {

        //BufferedImage buff2 = new BufferedImage(screenSize.width, screenSize.height, BufferedImage.TYPE_INT_RGB);
        buff = config.createCompatibleImage(screenSize.width, screenSize.height, BufferedImage.TYPE_INT_RGB);

        int iterations;

        Thread[] threadz = new pixelIterator[screenSize.width];

        for (int y = 0; y < screenSize.height; y++)
          {
            for (int x = 0; x < screenSize.width; x++)
              {
                //TODO get rid of these magic numbers
                double xScaled = Scaler.scale((double) screenSize.width, 0.0, MBROT_X_HIGH_BOUND, MBROT_X_LOW_BOUND, x);
                double yScaled = Scaler.scale((double) screenSize.height, 0.0, MROT_Y_HIGH_BOUND, MBROT_Y_LOW_BOUND, y);

                threadz[x] = new pixelIterator(xScaled, yScaled, maxIter, time, new Dimension(x, y), buff );
                threadz[x].run();

                // iterations = iterateOnAPixel(xScaled, yScaled, maxIter);

                //buff.setRGB(x, y, ColorTool.getRGBColor(iterations, iterations / 2, iterations * time));
                //buff.setRGB(x, y, ColorTool.getRGBColor((int) (iterations + redMutator), (int) (iterations / greenMutator), (int) (iterations * blueMutator)));
              }
          }
        time += 1;
        return buff;
      }

    private int iterateOnAPixel(double x0, double y0, int max_iteration)
      {
        int iteration = 0;
        double x, y;
        x = y = 0;

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
double x_0, y_0;
int max_iter, time;
Dimension picLoc;
BufferedImage img;

    public pixelIterator(double x_0, double y_0, int max_iter, int time, Dimension picLoc, BufferedImage img)
      {
        this.x_0 = x_0;
        this.y_0 = y_0;
        this.max_iter = max_iter;
        this.time = time;
        this.picLoc = picLoc;
        this.img = img;
      }
  


    @Override
    public void run()
      {
        int iteration = 0;

        double x, y;
        x = y = 0;
        while ((x * x + y * y) < 4 && iteration < max_iter)
          {
            double xtemp = x * x - y * y + x_0;
            y = 2 * x * y + y_0;

            x = xtemp;

            iteration = iteration + 1;
          }

        img.setRGB(picLoc.width, picLoc.height, ColorTool.getRGBColor(iteration, iteration / 2, iteration * time));
      }
  }


//TODO thread creation seems to take a long time? 