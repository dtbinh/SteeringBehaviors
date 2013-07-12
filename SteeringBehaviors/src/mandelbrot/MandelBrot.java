package mandelbrot;

import Utility.ColorTool;
import Utility.Histogram;
import Utility.Scaler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import steeringbehaviors.Settings;
import steeringbehaviors.SingleEntity;
import steeringbehaviors.WorldGraphics2D;

/**
 *
 * @author James Moore (moore.work@live.com)
 */
public class MandelBrot implements SingleEntity
{

    private double xc = -.667;
    private double yc = -.36;
    private int res = 50000;
    private int size = 1000;
    private int maxIter = 1000;
    private BufferedImage visual;
    private Dimension screenSize;

    public MandelBrot()
    {
        //screenSize = Settings.getInstance().getResolution();
        screenSize = new Dimension(1200, 1200);

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
        if (visual == null)
        {
        }
    }

    @Override
    public void update()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BufferedImage render()
    {

        BufferedImage buff = new BufferedImage(screenSize.width, screenSize.height, BufferedImage.TYPE_INT_RGB);

        int iterations;

        for (int y = 0; y < screenSize.height; y++)
        {
            for (int x = 0; x < screenSize.width; x++)
            {
                //TODO get rid of these magic numbers
                double xScaled = Scaler.scale((double) screenSize.width, 0.0, 1.0, -1.0, x);
                double yScaled = Scaler.scale((double) screenSize.height, 0.0, 1.0, -2.5, y);

                iterations = iterateOnAPixel(xScaled, yScaled, maxIter);

//                if (iterations > 500)
//                {
//                    buff.setRGB(x, y, Color.CYAN.getRGB());
//                }
//                else
//                {
//                    buff.setRGB(x, y, Color.black.getRGB());
//                }
               buff.setRGB(x, y,  ColorTool.getRGBColor(iterations % 222 ,iterations*2,iterations));
                

            }
        }

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
