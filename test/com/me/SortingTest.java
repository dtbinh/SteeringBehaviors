/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me;

import com.me.steeringbehaviors.WorldGraphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author James Moore (moore.work@live.com)
 */
public class SortingTest {

    private static List<Integer> elements;
    private Sorting sort;

    public SortingTest() {
        sort = new Sorting();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        //65318724
        Integer[] els = {
            6, 5, 3, 1, 8, 7, 4
        };
        elements = new ArrayList<Integer>(Arrays.asList(els));
    }

    @After
    public void tearDown() throws Exception {
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
        elements = Sorting.runFullMergeSort(elements);
      

        assertTrue(twoArraysSame(elsCorrect, elements));

    }

    /**
     * Test of draw method, of class Sorting.
     */
    @Test
    public void testDraw() {
        System.out.println("draw");
        WorldGraphics2D w2d = null;
        Sorting instance = new Sorting();
        instance.draw(w2d);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class Sorting.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Sorting instance = new Sorting();
        instance.update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectionSortComplete method, of class Sorting.
     */
    @Test
    public void testSelectionSortComplete() {
        System.out.println("selectionSortComplete");
        List<Integer> sortee = null;
        Sorting instance = new Sorting();
        List<Integer> expResult = null;
        List<Integer> result = instance.selectionSortComplete(sortee);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of bubbleSortComplete method, of class Sorting.
     */
    @Test
    public void testBubbleSortComplete() {
        System.out.println("bubbleSortComplete");
        List<Integer> sortee = null;
        Sorting instance = new Sorting();
        List<Integer> expResult = null;
        List<Integer> result = instance.bubbleSortComplete(sortee);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of runOneBubbleSort method, of class Sorting.
     */
    @Test
    public void testRunOneBubbleSort() {
        System.out.println("runOneBubbleSort");
        List<Integer> sortee = null;
        Sorting instance = new Sorting();
        instance.runOneBubbleSort(sortee);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getElements method, of class Sorting.
     */
    @Test
    public void testGetElements() {
        System.out.println("getElements");
        List<Integer> expResult = null;
        List<Integer> result = Sorting.getElements();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of runOneStepSelectionSort method, of class Sorting.
     */
    @Test
    public void testRunOneStepSelectionSort() {
        System.out.println("runOneStepSelectionSort");
        List<Integer> sortee = null;
        int index = 0;
        Sorting instance = new Sorting();
        instance.runOneStepSelectionSort(sortee, index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of runFullInsertionSort method, of class Sorting.
     */
    @Test
    public void testRunFullInsertionSort() {
        System.out.println("runFullInsertionSort");
        List<Integer> sortee = null;
        Sorting instance = new Sorting();
        List<Integer> expResult = null;
        List<Integer> result = instance.runFullInsertionSort(sortee);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of runOneStepInsertionSort method, of class Sorting.
     */
    @Test
    public void testRunOneStepInsertionSort() {
        System.out.println("runOneStepInsertionSort");
        List<Integer> sortee = null;
        int index = 0;
        Sorting instance = new Sorting();
        List<Integer> expResult = null;
        List<Integer> result = instance.runOneStepInsertionSort(sortee, index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertAt method, of class Sorting.
     */
    @Test
    public void testInsertAt() {
        System.out.println("insertAt");
        List<Integer> listToInsert = null;
        int index = 0;
        Integer val = null;
        List<Integer> expResult = null;
        List<Integer> result = Sorting.insertAt(listToInsert, index, val);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}