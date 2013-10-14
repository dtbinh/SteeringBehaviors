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

    private static List<Integer> elements;
    private Sorting sort;

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
        elements =  new ArrayList<Integer>(Arrays.asList(els)) ;
    }

    @Test
    public void testInsertionSort()
    {
        Integer[] els =
        {
            6, 5, 3, 1, 8, 7, 4
        };
 
        elements =sort.runFullInsertionSort(elements);
      
    }
}