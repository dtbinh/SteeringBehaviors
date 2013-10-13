package com.me.utility;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author James Moore (moore.work@live.com)
 */
public class Utility<T>
  {

    public List<T> insertAt(List<T> listToInsert, int index, T val)
      {
        List<T> ret = new LinkedList<T>();
        for (int i = 0; i < index; i++)
          {
            ret.add(listToInsert.get(i));
          }

        ret.add(val);

        for (int i = index; i < listToInsert.size(); i++)
          {
            ret.add(listToInsert.get(i));
          }

        return ret;
      }
  }
