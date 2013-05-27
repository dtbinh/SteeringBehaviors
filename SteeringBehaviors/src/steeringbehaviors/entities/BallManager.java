package steeringbehaviors.entities;



import edu.moravian.math.Point2D;
import edu.moravian.math.Vector2D;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import javax.imageio.IIOException;

/**
 *
 * @author James Moore (moore.work@live.com)
 */
public class BallManager
  {

    private static LinkedList<Ball> instance = null;

    private BallManager()
      {
      }

    public static LinkedList<Ball> getInstance() throws FileNotFoundException, IIOException
      {
        instance = new LinkedList<>();
        instance.add(new Ball(Point2D.zero, Vector2D.zero, 10, 10, Color.yellow));
        instance.add(new Ball(new Point2D(10,10), new Vector2D(10,10), 10, 10, Color.yellow));
        /*
        if (instance == null)
          {
            synchronized (BallManager.class)
              {
                if (instance == null)
                  {
                    instance = new LinkedList<Ball>();
                    String filename = Settings.getInstance().getBallFileName();
                    File ini_file = new File(filename);
                    Scanner scan = new Scanner(ini_file);

                    LinkedList<String> lines = new LinkedList<String>();

                    while (scan.hasNext())
                      {
                        String line = scan.nextLine();
                        if (line.startsWith("#") == false)
                          {
                            lines.add(line.trim());
                          }
                      }

                 

                    for (String str : lines)
                      {
                        String[] attributes = str.split(",");
                        if (attributes.length != 7)
                          {
                            throw new IIOException("Balls must have the correct # of attributes");
                          }
                        try
                          {
                            Point2D position = new Point2D(Double.parseDouble(attributes[0]), Double.parseDouble(attributes[1]));
                            Vector2D velo = new Vector2D(Double.parseDouble(attributes[2]), Double.parseDouble(attributes[3]));

                            double mass = Double.parseDouble(attributes[4]);
                            double radius = mass * Settings.getInstance().getRadiusScale();
                            double elasticity = Double.parseDouble(attributes[5]);

                            Color color = new Color((int) Double.parseDouble(attributes[6]));
                            Ball add = new Ball(position, velo, mass, radius, color, elasticity);
                            instance.add(add);
                          }
                        catch (NumberFormatException e)
                          {
                            throw e;
                          }
                      }

                  }
              }
          }
          */
        return instance;
      }
  }
//TODO Implement the positions as percentages within the world 