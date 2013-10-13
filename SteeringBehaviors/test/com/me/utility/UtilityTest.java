/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.utility;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author James Moore (moore.work@live.com)
 */
public class UtilityTest
  {

    List<Integer> vals;
    Utility ut; 

    public UtilityTest()
      {
      }

    @Before
    public void setUp()
      {
        Integer[] els =
          {
            1,2,4
          };
        vals = Arrays.asList(els);
        ut = new Utility();
      }

    @Test
    public void testInsertAt()
      {
          System.out.println(vals);
        List<Integer> foo = ut.insertAt(vals, 2, new Integer(3));
        System.out.println(foo);
      }
  }