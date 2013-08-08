package mandelbrot;

import Utility.ColorTool;
import Utility.Scaler;
import edu.moravian.math.Point2D;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import steeringbehaviors.RunnableSim;
import steeringbehaviors.WorldGraphics2D;

/**
 *
 * @author James Moore (moore.work@live.com)
 */
public class MandelBrot implements RunnableSim
{
    
    private int maxIter = 1000;
    private BufferedImage visual;
    private Dimension screenSize;
    private double redMutator;
    private double blueMutator;
    private double greenMutator;
    
    public MandelBrot()
    {
        //screenSize = Settings.getInstance().getResolution();
        screenSize = new Dimension(1200, 1200);
        Random rand = new Random();
        redMutator = rand.nextDouble();
        blueMutator = rand.nextDouble();
        greenMutator = rand.nextDouble();
        
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
                double xScaled = Scaler.scale((double) screenSize.width / 2, 0.0, 1.0, -1.0, x);
                double yScaled = Scaler.scale((double) screenSize.height / 2, 0.0, 1.0, -2.5, y);
                /// System.out.println(Scaler.scale((double) 1,  0.0, 1.0, -2.5, x));

                iterations = iterateOnAPixel(xScaled, yScaled, maxIter);
                
                buff.setRGB(x, y, ColorTool.getRGBColor(iterations, iterations / 2, iterations * 10));
                //buff.setRGB(x, y, ColorTool.getRGBColor((int) (iterations + redMutator), (int) (iterations / greenMutator), (int) (iterations * blueMutator)));
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
