package steeringbehaviors;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import org.junit.After;
import org.junit.Before;
import Utility.Timer;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author moore
 */
public class TimerTest
{

    public TimerTest()
    {
    }

    @Before
    public void setUp()
    {
    }

    @Test
    public void testTick() throws InterruptedException
    {
     Timer t = new Timer();
        t.tick();
       Thread.sleep(5000);
             
        assertEquals(true, t.getDelta() > 4.9);
    }

}
