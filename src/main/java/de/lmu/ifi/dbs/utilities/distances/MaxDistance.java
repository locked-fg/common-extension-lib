package de.lmu.ifi.dbs.utilities.distances;

/**
 * Implementation of the maximum distance
 *
 * @author Franz
 */
public class MaxDistance implements Distance {

    @Override
    public double dist(double[] a, double[] b) {
        double max = 0;
        double tmp;
        for (int i = 0; i < b.length; i++) {
            tmp = Math.abs(a[i] - b[i]);
            max = Math.max(max, tmp);
        }
        return max;
    }

    @Override
    public short dist(short[] a, short[] b) {
        int max = 0;
        int tmp;
        for (int i = 0; i < b.length; i++) {
            tmp = Math.abs(a[i] - b[i]);
            max = Math.max(max, tmp);
        }
        return (short) max;
    }

    @Override
    public float dist(float[] a, float[] b) {
        float max = 0;
        float tmp;
        for (int i = 0; i < b.length; i++) {
            tmp = Math.abs(a[i] - b[i]);
            max = Math.max(max, tmp);
        }
        return max;
    }

    @Override
    public long dist(long[] a, long[] b) {
        long max = 0;
        long tmp;
        for (int i = 0; i < b.length; i++) {
            tmp = Math.abs(a[i] - b[i]);
            max = Math.max(max, tmp);
        }
        return max;
    }

    @Override
    public int dist(int[] a, int[] b) {
        int max = 0;
        int tmp;
        for (int i = 0; i < b.length; i++) {
            tmp = Math.abs(a[i] - b[i]);
            max = Math.max(max, tmp);
        }
        return max;
    }

    @Override
    public byte dist(byte[] a, byte[] b) {
        int max = 0;
        int tmp;
        for (int i = 0; i < b.length; i++) {
            tmp = Math.abs(a[i] - b[i]);
            max = Math.max(max, tmp);
        }
        return (byte) max;
    }
}