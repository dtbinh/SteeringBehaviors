/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me;

import com.me.Sortingg;
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
public class SortingTest {

    private static List<Integer> elements;
    private Sortingg sort;

    public SortingTest() {
        sort = new Sortingg();
    }

    @Before
    public void setUp() {
        //65318724
        Integer[] els = {
            6, 5, 3, 1, 8, 7, 4
        };
        elements = new ArrayList<Integer>(Arrays.asList(els));
    }

    @Test
    public void testInsertionSort() {
        Integer[] els = {
            6, 5, 3, 1, 8, 7, 4
        };

        elements = sort.runOneStepInsertionSort(elements, els.length - 1);
        assertTrue(twoArraysSame(els, elements));

        Integer[] els2 = {
            6, 5, 3, 1, 8, 4, 7
        };

        elements = sort.runOneStepInsertionSort(elements, els.length - 2);
        assertTrue(twoArraysSame(els2, elements));

        Integer[] els3 = {
            6, 5, 3, 1, 4, 7, 8
        };

        elements = sort.runOneStepInsertionSort(elements, els.length - 3);
        assertTrue(twoArraysSame(els3, elements));


        Integer[] els4 = {
            6, 5, 3, 1, 4, 7, 8
        };

        elements = sort.runOneStepInsertionSort(elements, els.length - 4);
        assertTrue(twoArraysSame(els4, elements));



        Integer[] els5 = {
            6, 5, 1, 3, 4, 7, 8
        };

        elements = sort.runOneStepInsertionSort(elements, els.length - 5);

        assertTrue(twoArraysSame(els5, elements));


        Integer[] els6 = {
            6, 1, 3, 4, 5, 7, 8
        };

        elements = sort.runOneStepInsertionSort(elements, els.length - 6);
        assertTrue(twoArraysSame(els6, elements));

        Integer[] els7 = {
            1, 3, 4, 5, 6, 7, 8
        };

        elements = sort.runOneStepInsertionSort(elements, els.length - 7);
        assertTrue(twoArraysSame(els7, elements));
    }

    private static final boolean twoArraysSame(Integer[] one, List<Integer> two) {
        if (one.length != two.size()) {
            return false;
        }
        for (int i = 0; i < one.length; i++) {
            if (one[i] != two.get(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Test of runFullMergeSort method, of class Sorting.
     */
    @Test
    public void testRunFullMergeSort() {

        Integer[] els = {
            6, 5, 3, 1, 8, 7, 4
        };

        Integer[] elsCorrect = {1, 3, 4, 5, 6, 7, 8};
        elements = Sortingg.runFullMergeSort(elements);
      

        assertTrue(twoArraysSame(elsCorrect, elements));

    }
}