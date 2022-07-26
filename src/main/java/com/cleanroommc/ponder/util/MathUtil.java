package com.cleanroommc.ponder.util;

public class MathUtil {

    /**
     * Method for linear interpolation of floats
     * @param pDelta A value usually between 0 and 1 that indicates the percentage of the lerp. (0 will give the start
     * value and 1 will give the end value)
     * @param pStart Start value for the lerp
     * @param pEnd End value for the lerp
     */
    public static float lerp(float pDelta, float pStart, float pEnd) {
        return pStart + pDelta * (pEnd - pStart);
    }

    /**
     * Method for linear interpolation of doubles
     * @param pDelta A value usually between 0 and 1 that indicates the percentage of the lerp. (0 will give the start
     * value and 1 will give the end value)
     * @param pStart Start value for the lerp
     * @param pEnd End value for the lerp
     */
    public static double lerp(double pDelta, double pStart, double pEnd) {
        return pStart + pDelta * (pEnd - pStart);
    }

    /**
     * Returns the given value if between the lower and the upper bound. If the value is less than the lower bound,
     * returns the lower bound. If the value is greater than the upper bound, returns the upper bound.
     * @param pValue The value that is clamped.
     * @param pMin The lower bound for the clamp.
     * @param pMax The upper bound for the clamp.
     */
    public static float clamp(float pValue, float pMin, float pMax) {
        if (pValue < pMin) {
            return pMin;
        } else {
            return pValue > pMax ? pMax : pValue;
        }
    }

    /**
     * Returns the given value if between the lower and the upper bound. If the value is less than the lower bound,
     * returns the lower bound. If the value is greater than the upper bound, returns the upper bound.
     * @param pValue The value that is clamped.
     * @param pMin The lower bound for the clamp.
     * @param pMax The upper bound for the clamp.
     */
    public static double clamp(double pValue, double pMin, double pMax) {
        if (pValue < pMin) {
            return pMin;
        } else {
            return pValue > pMax ? pMax : pValue;
        }
    }

    public static boolean equal(float pX, float pY) {
        return Math.abs(pY - pX) < 1.0E-5F;
    }

    public static boolean equal(double pX, double pY) {
        return Math.abs(pY - pX) < (double) 1.0E-5F;
    }

}
