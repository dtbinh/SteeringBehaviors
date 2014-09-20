package com.me.utility;

/**
 * Utility class to generate RGB colors
 * @author devnull
 */
public final class ColorTool
  {

    /**
     * We really don't need one of these, so why provide it? 
     */
    private ColorTool()
      {
      }
//the RGB color model is 4 8 bit color channels divided as such alpha, red, blue, green
    ///aaaaaaaarrrrrrrrggggggggbbbbbbbb
    /**
     * Utility to create an RGB color integer from three color ints
     * @param red
     * @param green
     * @param blue
     * @return 
     */
    public static int getRGBColor(final int red, final int green, final int blue)
      {
        return red << 16 | green << 8 | blue;
      }
  }
