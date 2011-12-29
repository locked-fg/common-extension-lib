package de.lmu.ifi.dbs.utilities.distances;

import de.lmu.ifi.dbs.utilities.Math2;

public class LpNorm extends DistanceAdapter {

    private final int p;

    public LpNorm(int p) {
        if (p <= 0) {
            throw new IllegalArgumentException("p must be > 0 but was " + p);
        }
        this.p = p;
    }

    @Override
    public double dist(double[] a, double[] b) {
        double tmp;
        double sum = 0;
        for (int i = 0; i < b.length; i++) {
            tmp = a[i] - b[i];
            sum += Math2.pow(Math.abs(tmp), p);
        }
        return Math2.pow(sum, 1d / p);
    }
}
