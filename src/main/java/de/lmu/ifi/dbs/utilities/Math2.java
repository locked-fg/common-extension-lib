package de.lmu.ifi.dbs.utilities;

import java.awt.geom.Point2D;

/**
 * Additional math stuff which is not found in the {@link Math} class
 */
public class Math2 {

    /**
     * performs a division a/b with a fallback if a/b would result in a division by zero.
     *
     * @param a dividend
     * @param b divisor 
     * @param c fallback if b = 0
     * @return a/b or c if b = 0;
     */
    public static double div(double a, double b, double c) {
        return b != 0 ? a / b : c;
    }

    /**
     * Rotate a point around the origin
     *
     * @param x coordinate
     * @param y coordinate
     * @param deg in degrees
     * @return translated point
     */
    public static Point2D rotate(int x, int y, double deg) {
        double length = Math.sqrt(x * x + y * y);
        double theta = Math.atan2(y, x);
        theta += Math.toRadians(deg);

        double newX = length * Math.cos(theta);
        double newY = length * Math.sin(theta);

        return new Point2D.Double(newX, newY);
    }

    /**
     * calculate the gaussian derivation in 2d
     *
     * @param x coordinate
     * @param y coordinate
     * @param sigma standard deviation
     * @return the value of the gaussian
     */
    public static double gauss(double x, double y, double sigma) {
        // calculate gauss. mu = 0, sigma1=sigma2=sigma
        double sig2 = 2 * sigma * sigma;
        double g = Math.exp(-(x * x + y * y) / sig2) / (Math.PI * sig2);
        return g;
    }

    /**
     * calculate the 1d gaussian derivation with mu = 0
     *
     * @param x coordinate
     * @param sigma standard deviation
     * @return the value of the gaussian
     */
    public static double gauss(double x, double sigma) {
        double g = (1 / (sigma * Math.sqrt(2 * Math.PI)))
                * Math.exp(-(x * x) / (2 * sigma * sigma));
        return g;
    }

    /**
     * return a normalized gaussian kernel for the specified array size
     *
     * @param size size of the resulting array
     * @return array with gaussian values with the array sum normalized to 1
     */
    public static double[] getGauss(int size) {
        if (size <= 0 || size % 2 == 0) {
            throw new IllegalArgumentException("size must be > 0 and odd but was " + size);
        }
        if (size == 1) {
            return new double[]{1};
        }
        double[] k = new double[size];

        int offset = k.length / 2;
        double sum = 0;
        for (int i = 0; i < k.length; i++) {
            k[i] = Math2.gauss(i - offset, 1);
            sum += k[i];
        }

        // normalize
        for (int i = 0; i < k.length; i++) {
            k[i] /= sum;
        }
        return k;
    }

    /**
     * Forces an input value to be in [min, max]. If it is greater than max or less than min, the according min/max value is returned
     * 
     * @param min min value (inclusive)
     * @param value the value to check
     * @param max max value (inclusive)
     * @return bound value between min and max (inclusive) [min, value, max]
     */
    public static double bind(double min, double value, double max) {
        return Math.max(min, Math.min(max, value));
    }

    /**
     * checks if a value is in [min; max]
     * @param min lower bound
     * @param v the value to check
     * @param max upper bound
     * @return true, if v is in [min; max]
     */
    public static boolean isIn(int min, int v, int max) {
        return v >= min && v <= max;
    }

    /**
     * checks if a value is in [min; max]
     * @param min lower bound
     * @param v the value to check
     * @param max upper bound
     * @return true, if v is in [min; max]
     */
    public static boolean isIn(double min, double v, double max) {
        return v >= min && v <= max;
    }

    /**
     * Transforms the given point from karthesian to polar coordinates.
     *
     * x is then the length to 0,0; y is the angle in the range -Pi;Pi
     *
     * @param p the karthesian point
     */
    public static void toPolar(Point2D p) {
        double x = p.getX();
        double y = p.getY();
        p.setLocation(Math.sqrt(x * x + y * y), Math.atan2(y, x));
    }

    /**
     * Transforms the given point from polar to Karthesian coordinates.
     *
     * @param p polar point
     */
    public static void toKarthesian(Point2D p) {
        double x = p.getX() * Math.cos(p.getY());
        double y = p.getX() * Math.sin(p.getY());
        p.setLocation(x, y);
    }

    /**
     * Method that wraps {@link Math#pow(double, double)} and linearizes the calculation of the exponent up to power=64
     * which was faster on my machine in all tests.
     *
     * @param a
     * @param b
     * @return
     */
    public static double pow(double a, double b) {
        // on my machine, power=64 marks a limit where Math.pow becomes faster
        // b >> 6 == 0 if b == 64 ; 2^6
        if (b == (int) b && ((int) b >> 6) == 0) {
            if (b == 0) {
                return 1;
            } else if (b == 1) {
                return a;
            } else {
                double result = a;
                for (int i = 1; i < b; i++) {
                    result *= a;
                }
                return result;
            }
        }
        return Math.pow(a, b);
    }
}
