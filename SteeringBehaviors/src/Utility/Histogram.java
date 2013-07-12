package Utility;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author James Moore (moore.work@live.com)
 */
public class Histogram
{

    HashMap<Integer, Integer> histogram;

    public Histogram()
    {
        this.histogram = new HashMap<Integer, Integer>();
    }

    public void add(int number)
    {
        int count = 0;
        Integer foo = new Integer(number);
        if (histogram.get(foo) != null)
        {
            count += histogram.get(foo).intValue();
        }

        histogram.put(new Integer(number), new Integer(count + 1));
    }

    public String contents()
    {
//for(int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++){
//    if(histogram.)
//}
        
        
        return histogram.toString();
    }
}
