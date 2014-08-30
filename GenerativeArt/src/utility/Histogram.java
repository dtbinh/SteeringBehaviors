package utility;

import java.util.HashMap;

/**
 *
 * @author James Moore (moore.work@live.com)
 */
public class Histogram
  {
//TODO calculate standard deviation, etc

    private HashMap<Integer, Integer> histogram;

    /**
     * Creates a new histogram
     */
    public Histogram()
      {
        this.histogram = new HashMap<Integer, Integer>();
      }

    /**
     * Adds a number to the histogram.
     *
     * @param number Any integer that you want accounted for
     */
    public void add(int number)
      {
        int count = 0;

        if (histogram.get(number) != null)
          {
            count += histogram.get(number).intValue();
          }

        histogram.put(new Integer(number), new Integer(count + 1));
      }

    public HashMap contents()
      {
        return histogram;
      }

    public String contentsAsFormattedString()
      {
        return histogram.toString();
      }
  }
