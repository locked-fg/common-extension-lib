package de.lmu.ifi.dbs.utilities;

import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.logging.Logger;

public class Statistics {

    private static final Logger log = Logger.getLogger(Statistics.class.getName());

    public static double mean(double... a) {
        return Arrays2.sum(a) / a.length;
    }

    public static double mean(byte... a) {
        return 1d * Arrays2.sum(a) / a.length;
    }

    public static double mean(float... a) {
        return Arrays2.sum(a) / a.length;
    }

    public static double mean(int... a) {
        return Arrays2.sum(a) / a.length;
    }

    /**
     * Get the average value of <code>arr</code> within <code>[a,...,b]</code>.
     *
     * @param arr the input array
     * @param a start index inclusive
     * @param b end index inclusive
     * @return Average value of the elements between <code>[a,...,b]</code>
     */
    public static double mean(double[] arr, int a, int b) {
        if (b < a) {
            throw new IllegalArgumentException("a>b not allowed: " + a + ">"
                    + b);
        }
        if (a < 0 || b >= arr.length) {
            throw new IllegalArgumentException("[a,b] must be in [0,"
                    + (arr.length - 1) + "]; is [" + a + "," + b + "]");
        }
        return Arrays2.sum(arr, a, b) / (b - a + 1);
    }

    /**
     * Get the average value of <code>arr</code> within <code>[a,...,b]</code>.
     *
     * @param arr the input array
     * @param a start index inclusive
     * @param b end index inclusive
     * @return Average value of the elements between <code>[a,...,b]</code>
     */
    public static double mean(float[] arr, int a, int b) {
        if (b < a) {
            throw new IllegalArgumentException("a>b not allowed: " + a + ">"
                    + b);
        }
        if (a < 0 || b >= arr.length) {
            throw new IllegalArgumentException("[a,b] must be in [0,"
                    + (arr.length - 1) + "]; is [" + a + "," + b + "]");
        }
        return Arrays2.sum(arr, a, b) / (b - a + 1);
    }

    /**
     * Get the average value of <code>arr</code> within <code>[a,...,b]</code>.
     *
     * @param arr the input array
     * @param a start index inclusive
     * @param b end index inclusive
     * @return Average value of the elements between <code>[a,...,b]</code>
     */
    public static double mean(int[] arr, int a, int b) {
        if (b < a) {
            throw new IllegalArgumentException("a>b not allowed: " + a + ">"
                    + b);
        }
        if (a < 0 || b >= arr.length) {
            throw new IllegalArgumentException("[a,b] must be in [0,"
                    + (arr.length - 1) + "]; is [" + a + "," + b + "]");
        }
        return Arrays2.sum(arr, a, b) / (b - a + 1);
    }

    /**
     * Computes the median of an array which is already sorted.<br>
     * <em>Warning:</em> If <code>a</code> is not sorted, this method will just
     * return some arbitrary value of <code>a</code> (impair number of elements)
     * or the average value of two arbitrary values of <code>a</code>.
     * 
     * @param a A <em>sorted</em> array.
     * @return The median value of the sorted array <code>a</code>.
     */
    public static double median(double[] a) {
        if (a.length < 1) {
            throw new IllegalArgumentException("array size must be >= 2 but was "
                    + a.length);
        }

        if (a.length % 2 == 0) {
            int m = a.length / 2;
            return 0.5 * (a[m - 1] + a[m]);
        } else {
            return a[a.length / 2];
        }
    }

    /**
     * Computes the maximum likelihood estimate (assuming a normal distribution) and NOT the empirical variance.
     *
     * @param a
     * @return <code>1 / n * sum<sub>i=1</sub><sup>n</sup>(a<sub>i</sub> - avg(a))</code>
     * ,<br>
     * computed as
     * <code>1 / n * sum<sub>i=1</sub><sup>n</sup>(a<sub>i</sub><sup>2</sup>) - avg(a)<sup>2</sup></code>
     */
    public static double var(double[] a) {
        // VAR(X) = E(X^2) - E(X)^2
        if (a.length == 0 || a.length == 1) {
            return 0;
        }
        double ex = 0, ex2 = 0; // E(X), E(X^2)
        for (int i = 0; i < a.length; i++) {
            ex += a[i];
            ex2 += a[i] * a[i];
        }
        ex /= a.length;
        ex2 /= a.length;
        return ex2 - ex * ex;
    }

    /**
     * Computes the maximum likelihood estimate (assuming a normal distribution) and not the empirical variance.
     *
     * @param arr
     * @param a from index
     * @param b to index (inclusive)
     * @return <code>1 / n * sum<sub>i=1</sub><sup>n</sup>(a<sub>i</sub> - avg(a))</code>
     * ,<br>
     * computed as
     * <code>1 / n * sum<sub>i=1</sub><sup>n</sup>(a<sub>i</sub><sup>2</sup>) - avg(a)<sup>2</sup></code>
     */
    public static double var(double[] arr, int a, int b) {
        // var X = E(X^2) - (E(X))^2
        int length = b - a + 1;
        if (length == 0 || length == 1) {
            return 0;
        }
        double ex = 0, ex2 = 0; // E(X), E(X^2)
        for (int i = a; i <= b; i++) {
            ex += arr[i];
            ex2 += arr[i] * arr[i];
        }
        ex /= length;
        ex2 /= length;
        return ex2 - ex * ex;
    }

    /**
     * Computes the standard deviation of a sample based on the result of {@link #var(double[])}.
     *
     * @param a
     * @return <code>sqrt(1 / n * sum<sub>i=1</sub><sup>n</sup>(a<sub>i</sub> - avg(a)))</code>
     * ,<br>
     * computed as
     * <code>sqrt(1 / n * sum<sub>i=1</sub><sup>n</sup>(a<sub>i</sub><sup>2</sup>) - avg(a)<sup>2</sup>)</code>
     */
    public static double stdev(double[] a) {
        return Math.sqrt(var(a));
    }

    public static double covariance(double[] a, double[] b) {
        return covariance(a, 0, b, 0, a.length);
    }

    public static double covariance(double[] a, int a1, double[] b, int b1,
            int w) {
        // Cov(X,Y) = E(X*Y) - E(X)*E(Y)
        double eXY = 0;
        for (int i = 0; i < w; i++) {
            eXY += a[a1 + i] * b[b1 + i];
        }
        eXY /= w;

        // In case of autocovariance, a and b are equal and a1 = b1,
        // so we don't need to compute the same mean twice
        double mean2, mean1 = mean(a, a1, a1 + w - 1);
        if (a == b && a1 == b1) {
            mean2 = mean1;
        } else {
            mean2 = mean(b, b1, b1 + w - 1);
        }

        return eXY - mean1 * mean2;
    }

    /**
     * Autocorrelation between 2 arrays a, b. Indices a1,a2,b1,b2 are all
     * INCLUSIVE. 
     * 
     * @param a array 1
     * @param a1 start index INCLUSIVE
     * @param b array 2
     * @param b1 start index INCLUSIVE
     * @param w with of the compare window
     * @return autocorrelation between a, b
     * @see <a href="http://de.wikipedia.org/wiki/Autokorrelation#Autokorrelation_in_der_Statistik">Wikipedia</a>
     */
    public static double autoCorrelation(double[] a, int a1, double[] b,
            int b1, int w) {
        // log.info("Array lengths: " + a.length + ", " + b.length +
        // "; indeces: " + a1 + "," + b1 + "; window: " + w);
        if (a1 + w > a.length || b1 + w > b.length) {
            throw new IndexOutOfBoundsException("Window too large. Array lengths: "
                    + a.length
                    + ", "
                    + b.length
                    + "; indeces: "
                    + a1
                    + ","
                    + b1 + "; window: " + w);
        }
        return covariance(a, a1, b, b1, w)
                / Math.sqrt(var(a, a1, a1 + w - 1) * var(b, b1, b1 + w - 1));
    }

    /**
     * Autocorrelation between 2 arrays a, b.
     * 
     * @param a array 1
     * @param b array 2
     * @return autocorrelation between a, b
     * @see <a href="http://de.wikipedia.org/wiki/Autokorrelation#Autokorrelation_in_der_Statistik">Wikipedia</a>
     */
    public static double autoCorrelation(double[] a, double[] b) {
        double root;
        if (a == b) {
            // sqrt(a*a) = a
            root = var(a);
        } else {
            root = Math.sqrt(var(a) * var(b));
        }
        return covariance(a, b) / root;
    }

    /**
     * @see http://en.wikipedia.org/wiki/Mean_square_weighted_deviation
     * @param values
     * @param weightFunction that receives the index of the array as input and outputs the accorind weight
     * @return standard deviation
     */
    public static double weightedStdev(double[] values, IntToDoubleFunction weightFunction) {
        double wx2 = 0, wx = 0, w = 0; // required terms

        double _weight, _x, _wx; // temporary values
        for (int i = 0; i < values.length; i++) {
            _weight = weightFunction.applyAsDouble(i);
            _x = values[i];
            _wx = _weight * _x;

            w += _weight;
            wx += _wx;
            wx2 += _wx * _x;
        }

        double sigma2 = (wx2 * w - (wx * wx)) / (w * w);
        return Math.sqrt(sigma2);
    }
}
