package Utility;
public class ColorTool {
    public static int getRGBColor(int red, int green, int blue) {
        return red << 16 | green << 8 | blue;
    }
}


