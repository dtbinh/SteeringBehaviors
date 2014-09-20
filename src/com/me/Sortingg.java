/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me;

import com.me.steeringbehaviors.Settings;
import com.me.steeringbehaviors.WorldGraphics2D;
import com.me.utility.Utility;
import edu.moravian.math.Point2D;
import java.awt.Color;
import java.awt.Dimension;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bryan
 */
public class Sortingg    implements com.me.steeringbehaviors.RunnableSim {
    //TODO Reorganize sort adding system 

    private static List<Integer> elements;
    private static List<Integer> elements2;
    private static List<Integer> elements3;
    private static List<Integer> elements4;
    private static Utility ut;
    private static final int NUM_ELEMENTS = 80;
    private static int index = 0;
    private static Utility util;

    public Sortingg() {
        util = new Utility<Integer>();
        elements = new ArrayList<Integer>();
        elements2 = new ArrayList<Integer>();
        elements3 = new ArrayList<Integer>();
        elements4 = new ArrayList<Integer>();

        ut = new Utility();

        Random r = new Random();
        for (int i = 0; i < NUM_ELEMENTS; i++) {
            int val = r.nextInt(150) + 20;
            elements.add(val);
            elements2.add(val);
            elements3.add(val);
            elements4.add(val);
        }

        index = elements.size() - 1;
    }

    @Override
    public void draw(WorldGraphics2D w2d) {
        Dimension res = Settings.getInstance().getResolution();
        int offset = 10;
        for (int i = 0; i < NUM_ELEMENTS; i++) {

            w2d.drawRectangle(new Point2D(i * offset, 600), new Dimension(10, elements.get(i)), Color.blue);
            w2d.drawRectangle(new Point2D(i * offset, 300), new Dimension(10, elements2.get(i)), Color.red);
            w2d.drawRectangle(new Point2D(i * offset, 50), new Dimension(10, elements3.get(i)), Color.yellow);

        }

    }

    @Override
    public void update() {
        try {

            this.runOneStepSelectionSort(elements, index);
            this.runOneBubbleSort(elements2);
            elements3 = this.runOneStepInsertionSort(elements3, index);
            index = index - 1;
            Thread.sleep(100);

            if (this.isUnSorted(elements) && this.isUnSorted(elements2) && this.isUnSorted(elements3)) {
                System.exit(0);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Sortingg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Integer> selectionSortComplete(List<Integer> sortee) {
        int index = sortee.size() - 1;
        while (isUnSorted(sortee)) {
            runOneStepSelectionSort(sortee, index);
            index -= 1;
        }
        return sortee;
    }

    public List<Integer> bubbleSortComplete(List<Integer> sortee) {
        while (isUnSorted(sortee)) {
            runOneBubbleSort(sortee);
        }
        return sortee;
    }

    private boolean isUnSorted(List<Integer> sortee) {
        if (sortee.size() < 2) {
            return true;
        }

        for (int i = 1; i < sortee.size(); i++) {
            if (sortee.get(i - 1) > sortee.get(i)) {
                return false;
            }

        }
        return true;
    }

    protected void runOneBubbleSort(List<Integer> sortee) {


        if (sortee.size() < 1) {
            System.out.println("Sortee is rather small");
        }
        for (int i = 1; i < sortee.size(); i++) {
            if (sortee.get(i) < sortee.get(i - 1)) {
                Collections.swap(sortee, i, i - 1);
            }
        }


    }

    public static List<Integer> getElements() {
        return elements;
    }

    protected void runOneStepSelectionSort(List<Integer> sortee, int index) {
        int currMaxIndex = 0;

        if (sortee.size() < 1) {
            System.out.println("Sortee is rather small");
        }

        for (int i = 0; i <= index; i++) {
            if (sortee.get(i) > sortee.get(currMaxIndex)) {
                currMaxIndex = i;
            }
        }
        Collections.swap(sortee, currMaxIndex, index);
    }

    //TODO wrong method name
    protected List<Integer> runFullInsertionSort(List<Integer> sortee) {
        int index = sortee.size() - 1;
        while (isUnSorted(sortee) == false) {
            sortee = runOneStepInsertionSort(sortee, index);
            index -= 1;
        }
        return sortee;
    }

    protected List<Integer> runOneStepInsertionSort(List<Integer> sortee, int index) {
        int currMaxIndex = 0;

        if (sortee.size() < 1) {
            System.out.println("Sortee is rather small");
        }

        int val = sortee.get(index);
        for (int i = sortee.size() - 1; i > index; i--) {
            if (val >= sortee.get(i)) {
                if (val == 3) {
                    System.out.println("foo");
                }

                sortee = insertAt(sortee, i + 1, val);
                sortee.remove(index);
                return sortee;
            }
        }

        return sortee;
    }

    public static List<Integer> runFullMergeSort(List<Integer> sortee) {
        if (sortee.size() == 2) {
           List<Integer> foo = new LinkedList<Integer>();
           foo.add(Math.min(sortee.get(0), sortee.get(1)));
           foo.add(Math.max(sortee.get(0), sortee.get(1)));
           return foo;
        }
        if (sortee.size() == 1) {
            return sortee;
        }

        List<Integer> left = new LinkedList<Integer>();
        List<Integer> right = new LinkedList<Integer>();
        int middle = sortee.size() / 2;

        for (int i = 0; i < middle; i++) {
            left.add(sortee.get(i));
        }
        for (int i = middle; i < sortee.size(); i++) {
            right.add(sortee.get(i));
        }

        left = runFullMergeSort(left);
        right = runFullMergeSort(right);
Sortingg what = new Sortingg();
        
        return  what.runFullInsertionSort(util.concat(left, right));
    }

    public static List<Integer> insertAt(List<Integer> listToInsert, int index, Integer val) {
        List<Integer> ret = new LinkedList<Integer>();
        for (int i = 0; i < index; i++) {
            ret.add(listToInsert.get(i));
        }

        ret.add(val);

        for (int i = index; i < listToInsert.size(); i++) {
            ret.add(listToInsert.get(i));
        }
        return ret;
    }
}
