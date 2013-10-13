/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author James Moore (moore.work@live.com)
 */
public class SortingTest
  {

    private static List<Integer> elements; private Sorting sort;

    public SortingTest()
      {
        sort = new Sorting();
      }

    @Before
    public void setUp()
      {
        //65318724
        Integer[] els =
          {
            6, 5, 3, 1, 8, 7, 4
          };
        elements = Arrays.asList(els);
      }

    @Test
    public void testInsertionSort()
      {
        Integer[] els =
          {
            6, 5, 3, 1, 8, 7, 4
          };
       sort.runOneStepInsertionSort(elements, elements.size() - 1);

        for (int i = 0; i < els.length; i++)
          {
            assertEquals(els[i], elements.get(i));
          }
        
         sort.runOneStepInsertionSort(elements, elements.size() - 2);
         
          System.out.println(elements);
          
          sort.runOneStepInsertionSort(elements, elements.size() - 3);
          System.out.println(elements);
          fail("");
      }
  }