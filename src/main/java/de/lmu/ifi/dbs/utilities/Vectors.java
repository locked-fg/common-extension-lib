package de.lmu.ifi.dbs.utilities;

public final class Vectors {

    public static double length(double[] in) {
        return Math.sqrt(dot(in, in));
    }

    /**
     * Normalizes the length of the vector.
     *
     * @param in
     */
    public static void normalize(double[] in) {
        double length = length(in);
        if (length != 0) {
            Arrays2.div(in, length);
        }
    }

    public static double cosinus(double[] a, double[] b) {
        return dot(a, b) / (length(a) * length(b));
    }

    /**
     * computes the dot product
     *
     * @param o1
     * @param o2
     * @return dot product
     * @throws IllegalArgumentException if o1 and o2 have different dimensions
     */
    public static double dot(double[] o1, double[] o2) {
        double result = 0;
        if (o1.length != o2.length) {
            throw new IllegalArgumentException(
                    "dimensions must be equal but were: " + o1.length + " <-> " + o2.length);
        }
        for (int x = 0; x < o1.length; x++) {
            result += o1[x] * o2[x];
        }
        return result;
    }
}
