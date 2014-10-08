/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.GOL;

import com.me.steeringbehaviors.RunnableSim;
import com.me.steeringbehaviors.WorldGraphics2D;
import java.util.Calendar;
import java.util.GregorianCalendar;
import sun.util.calendar.Gregorian;

/**
 *
 * @author devnull
 */
public class WeirdClock implements RunnableSim {

    public void draw(WorldGraphics2D w2d) {
    
        int xSize = w2d.getRes().width;
        int ySize = w2d.getRes().height;
        GregorianCalendar cal = new GregorianCalendar();
        
        double firstBoxCutoffHour = cal.get(Calendar.SECOND)/60.0;
        
        for(int i = 0; i < 100; i++){
            System.out.println(firstBoxCutoffHour);
        }
        
    }

    public void update() {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
