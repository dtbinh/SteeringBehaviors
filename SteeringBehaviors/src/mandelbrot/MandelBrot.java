package mandelbrot;

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

    @Override
    public void draw(WorldGraphics2D w2d)
      {
        throw new UnsupportedOperationException("Not supported yet.");
      }

    @Override
    public void update()
      {
        throw new UnsupportedOperationException("Not supported yet.");
      }
  }
