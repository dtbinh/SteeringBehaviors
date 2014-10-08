/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.GOL;

import com.me.steeringbehaviors.RunnableSim;
import com.me.steeringbehaviors.WorldGraphics2D;
import edu.moravian.math.Point2D;
import java.awt.Color;
import java.awt.Dimension;
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
        int xQuadSize = (int) (xSize / 2.0);
        int yQuadSize = (int) (ySize / 2.0);
        int numPixels = xSize * ySize;
        int numPixelsPerQuad = (int) ((xSize * ySize) / 4.0);
        //System.out.println(numPixelsPerQuad + "" + numPixels);
        GregorianCalendar cal = new GregorianCalendar();

        double firstBoxCutoffHour = cal.get(Calendar.MINUTE) / 
                60.0;
        double firstBoxCutoffDayOfMonth = (cal.get(Calendar.DAY_OF_MONTH) / 
                (float) cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        double firstBoxCutoffHourOfDay = (cal.get(Calendar.HOUR_OF_DAY) / 
                (float) 24.0);
        double posInYear = (cal.get(Calendar.DAY_OF_YEAR) / 
                (float) cal.getActualMaximum(Calendar.DAY_OF_YEAR));


        for (int i = 0; i < xQuadSize; i++) {
            for (int j = 0; j < yQuadSize; j++) {
                Color col;
                if (i * j > firstBoxCutoffHour * numPixelsPerQuad) {
                    col = new Color(183, 110, 184) ;
                } else {
                    col =new Color(2, 132, 130);
                }
                w2d.drawPixel(new Point2D(i, j), col);
            }
        }

        for (int i = xQuadSize; i < xQuadSize * 2; i++) {
            for (int j = 0; j < yQuadSize; j++) {
                Color col;
                if (i * j > firstBoxCutoffHourOfDay * numPixelsPerQuad) {
                    col = new Color(0, 71, 171);
                } else {
                    col = new Color(220, 20, 60);
                }
                w2d.drawPixel(new Point2D(i, j), col);
            }
        }

        for (int i = 0; i < xQuadSize; i++) {
            for (int j = yQuadSize; j < yQuadSize * 2; j++) {
                Color col;
                if (i * j > firstBoxCutoffDayOfMonth * numPixelsPerQuad) {
                    col = Color.BLACK;
                } else {
                    col = Color.yellow;
                }
                w2d.drawPixel(new Point2D(i, j), col);
            }
        }

        for (int i = xQuadSize; i < xQuadSize * 2; i++) {
            for (int j = 0; j < yQuadSize ; j++) {
                Color col;
              //  System.out.println(i*j +"   " + firstBoxCutoffDayOfMonth * numPixelsPerQuad);
                if (i * j > firstBoxCutoffDayOfMonth * numPixelsPerQuad) {
                    col = Color.BLACK;
                } else {
                    col = Color.yellow;
                }
                w2d.drawPixel(new Point2D(i, j + yQuadSize), col);
            }
        }

    }

    public void update() {
        //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
