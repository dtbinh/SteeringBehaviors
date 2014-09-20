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
public class UtilityTest {

    List<Integer> vals;
    List<Integer> vals2;
    Utility ut;

    public UtilityTest() {
    }

    @Before
    public void setUp() {
        Integer[] els = {
            1, 2, 4
        };
        Integer[] els2 = {5, 1000};
        vals = Arrays.asList(els);
        vals2 = Arrays.asList(els2);
        ut = new Utility();
    }

    @Test
    public void testInsertAtMiddle() {
        
        List<Integer> foo = ut.insertAt(vals, 2, new Integer(3));
       
        assertEquals((Integer) 1, foo.get(0));
        assertEquals((Integer) 2, foo.get(1));
        assertEquals((Integer) 3, foo.get(2));
        assertEquals((Integer) 4, foo.get(3));
    }
    
    
    @Test
    public void testInsertTooBig() {
       
        try{
            
        List<Integer> foo = ut.insertAt(vals, vals.size() + 1, new Integer(3));
       fail("Should throw exception");
        }catch(IllegalArgumentException e){
            //Good path
        }
    }

    /**
     * Test of concat method, of class Utility.
     */
    @Test
    public void testConcat() {
        Utility util = new Utility();

        List<Integer> foo = new LinkedList<Integer>();

        foo = util.concat(vals, vals2);
        // System.out.println(foo);
        assertEquals((Integer) 1, foo.get(0));
        assertEquals((Integer) 2, foo.get(1));
        assertEquals((Integer) 4, foo.get(2));
        assertEquals((Integer) 5, foo.get(3));
        assertEquals((Integer) 1000, foo.get(4));
    }
}
