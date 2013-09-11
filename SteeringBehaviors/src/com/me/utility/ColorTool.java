package com.me.utility;

public class ColorTool
  {

    private ColorTool()
      {
      }
//TODO properly javadoc this 

    public static int getRGBColor(final int red, final int green, final int blue)
      {
        return red << 16 | green << 8 | blue;
      }
  }
