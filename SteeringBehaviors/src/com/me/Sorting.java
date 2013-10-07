/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me;

import com.me.steeringbehaviors.Settings;
import com.me.steeringbehaviors.WorldGraphics2D;
import edu.moravian.math.Point2D;
import java.awt.Color;
import java.awt.Dimension;
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
public class Sorting implements com.me.steeringbehaviors.RunnableSim
{

    private static List<Integer> elements;
    private static List<Integer> elements2;
    private static final int NUM_ELEMENTS = 80;
    private static  int index = 0;

    public Sorting()
    {
        elements = new ArrayList<Integer>();
        elements2 = new ArrayList<Integer>();
        Random r = new Random();
        for (int i = 0; i < NUM_ELEMENTS; i++)
        {
            elements.add(r.nextInt(100));
            elements2.add(r.nextInt(100));
        }
        
        index = elements.size() - 1;
    }

    @Override
    public void draw(WorldGraphics2D w2d)
    {
        Dimension res = Settings.getInstance().getResolution();
        int offset = 10;
        for (int i = 0; i < NUM_ELEMENTS; i++)
        {
            w2d.drawRectangle(new Point2D(i * offset, 600), new Dimension(10, 2 * elements.get(i)), Color.blue);
            w2d.drawRectangle(new Point2D(i * offset, 300), new Dimension(10, 2 * elements2.get(i)), Color.red);
            
        }

    }

    @Override
    public void update()
    {
        try
        {

            this.runOneStepSelectionSortt(elements, index);
            this.runOneBubbleSort(elements2);
            index = index -1;
            Thread.sleep(100);

            if (this.isUnSorted(elements)&&this.isUnSorted(elements2))
            {
                System.exit(0);
            }
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(Sorting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Integer> selectionSortComplete(List<Integer> sortee)
    {
        int index = sortee.size() - 1;
        while (isUnSorted(sortee))
        {
            runOneStepSelectionSortt(sortee, index);
            index -= 1;
        }
        return sortee;
    }

    public List<Integer> bubbleSortComplete(List<Integer> sortee)
    {
        while (isUnSorted(sortee))
        {
            runOneBubbleSort(sortee);
        }
        return sortee;
    }

    private boolean isUnSorted(List<Integer> sortee)
    {
        if (sortee.size() < 2)
        {
            return true;
        }

        for (int i = 1; i < sortee.size(); i++)
        {
            if (sortee.get(i - 1) > sortee.get(i))
            {
                return false;
            }

        }
        return true;
    }

    private void runOneBubbleSort(List<Integer> sortee)
    {

        if (sortee.size() < 1)
        {
            System.out.println("Sortee is rather small");
        }
        for (int i = 1; i < sortee.size(); i++)
        {
            if (sortee.get(i) < sortee.get(i - 1))
            {

                Collections.swap(sortee, i, i - 1);
            }
        }


    }

    public static List<Integer> getElements()
    {
        return elements;
    }

    private void runOneStepSelectionSortt(List<Integer> sortee, int index)
    {
        int currMaxIndex = 0;

        if (sortee.size() < 1)
        {
            System.out.println("Sortee is rather small");
        }

        for (int i = 0; i <= index; i++)
        {
            if (sortee.get(i) > sortee.get(currMaxIndex))
            {
                currMaxIndex = i;
            }
        }
        Collections.swap(sortee, currMaxIndex, index);
    }
    
    
}
