package com.esteeringbehaviors.entities;

import edu.moravian.math.Point2D;
import edu.moravian.math.Vector2D;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import javax.imageio.IIOException;
import com.me.steeringbehaviors.Settings;
import com.me.steeringbehaviors.RunnableSim;

/**
 *
 * @author James Moore (moore.work@live.com)
 */
public class EntityManager
  {

    private static LinkedList<RunnableSim> instance = null;

    private EntityManager()
      {
      }

    public static LinkedList<RunnableSim> getInstance() throws FileNotFoundException, IIOException
      {
        instance = new LinkedList<RunnableSim>();
    //    instance.add(new Ball(Point2D.zero, Vector2D.zero, 100, 100, Color.blue));
      //  instance.add(new Ball(new Point2D(100, 100), new Vector2D(10, 10), 100, 100, Color.blue));
        //instance.add(new Ball(new Point2D(400, 200), new Vector2D(-10, 10), 100, 100, Color.blue));
        instance.add(new Ball(new Point2D(400, 100), new Vector2D(-1, 0), 100, 100, Color.blue));

        Random rand = new Random();



//        for (int i = 0; i < 10; i++)
//          {
//            double  maxWidth =  Settings.getInstance().getWorldWidth();
//            double maxHeght =  Settings.getInstance().getWorldHeight();
//            instance.add(new Ball(new Point2D(rand.nextInt((int)maxWidth), rand.nextInt((int)maxHeght)), new Vector2D(rand.nextInt(30) - 15, rand.nextInt(30) - 15), rand.nextInt(100), rand.nextInt(100) + 50, Color.blue));
//          }


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
