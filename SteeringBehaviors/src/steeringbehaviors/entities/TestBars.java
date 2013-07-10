
package steeringbehaviors.entities;

import java.awt.Color;
import steeringbehaviors.SingleEntity;
import steeringbehaviors.WorldGraphics2D;

/**
 *
 * @author James Moore (moore.work@live.com)
 */
public class TestBars implements SingleEntity {

    @Override
    public void draw(WorldGraphics2D w2d)
    {
        w2d.setColor(Color.blue);
      //w2d.setColor(Color.darkGray);
        w2d.fillOval(10, 19, 20, 20);
    }

    @Override
    public void update()
    {
       // throw new UnsupportedOperationException("Not supported yet.");
    }

}
