package com.me.utility;

/**
 * Class with one static method to scale a number between a range
 *
 * @author James Moore (moore.work@live.com)
 */
public class Scaler
{

    /**
     * Scales a number between a range.
     *
     * @param ubData The upper bound of the unscaled range
     * @param lbData the lower bound of the unscaled range
     * @param ubScaled the upper bound of the scaled range
     * @param lbScaled the lower bound of the scaled range
     * @param numToBeScaled The number you actually want scaled
     * @return The scaled number
     */
    public static double scale(double ubData, double lbData, double ubScaled, double lbScaled, double numToBeScaled)
    {
        return ((ubScaled - lbScaled) * (numToBeScaled - lbData)) / (ubData - lbData) + lbScaled;
    }


}
