/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.utility;

import com.me.utility.Scaler;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author James Moore (moore.work@live.com)
 */
public class ScalerTest
  {

    public ScalerTest()
      {
      }

    @Test
    public void testScale()
      {
        double old = 0.0;
        double temp = 0.0;

        for (int i = 0; i < 5000; i++)
          {
            temp = Scaler.scale(0, 5000, 1, 5, i);
            assertTrue(temp <= 5 && temp >= 1 && temp > old);
            old = temp;

          }
      }
  }