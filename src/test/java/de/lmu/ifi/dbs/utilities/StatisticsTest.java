package de.lmu.ifi.dbs.utilities;

import java.lang.annotation.Annotation;
import java.util.function.Function;
import java.util.function.IntToDoubleFunction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {

    public StatisticsTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testMean_doubleArr() {
        double[] a = {1, 2, 3, 4, 5};
        double expResult = 3;
        double result = Statistics.mean(a);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testMean_floatArr() {
        double[] a = {1, 2, 3, 4, 5};
        double expResult = 3;
        double result = Statistics.mean(a);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testMean_intArr() {
        double[] a = {1, 2, 3, 4, 5};
        double expResult = 3;
        double result = Statistics.mean(a);
        assertEquals(expResult, result, 0.0);

        a = new double[]{1, 2};
        expResult = 1.5;
        result = Statistics.mean(a);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testMean_3args_double() {
        double[] arr = {0, 2, 3, 4, 0};
        int a = 1;
        int b = 3;
        double expResult = 3;
        double result = Statistics.mean(arr, a, b);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testMean_3args_float() {
        float[] arr = {0, 2, 3, 4, 0};
        int a = 1;
        int b = 3;
        double expResult = 3;
        double result = Statistics.mean(arr, a, b);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testMean_3args_int() {
        int[] arr = {0, 2, 3, 4, 0};
        int a = 1;
        int b = 3;
        double expResult = 3;
        double result = Statistics.mean(arr, a, b);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testMedian() {
        double[] a = {1, 1.1, 1.2, 2, 3};
        double expResult = 1.2;
        double result = Statistics.median(a);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testVar_doubleArr() {
        double[] a = {1, 2, 3, 4, 5};
        double expResult = 2;
        double result = Statistics.var(a);
        assertEquals(expResult, result, 0.0001);
    }

    @Test
    public void testVar_3args() {
        double[] a = {100, 1, 2, 3, 4, 5, 100};
        double expResult = 2;
        double result = Statistics.var(a, 1, 5);
        assertEquals(expResult, result, 0.0001);
    }

    @Test
    public void testCovariance_2args() {
        double[] a = {1, 2, 3, 5, 10, 100};
        double expResult = 46192d / 36;
        double result = Statistics.covariance(a, a);
        assertEquals(expResult, result, 0.05);
    }

    @Test
    public void testCovariance_5args() {
        double[] a = {-1000, 1, 2, 3, 5, 10, 100, -1000};
        double expResult = 46192d / 36;
        double result = Statistics.covariance(a, 1, a, 1, 6);
        assertEquals(expResult, result, 0.05);
    }

    @Test
    public void testAutoCorrelation_2args() {
        double[] a = {1, 2, 3, 3.3, 4.4, 5, 7, 9, 10};
        double expResult = 1;
        double result = Statistics.autoCorrelation(a, a);
        assertEquals(expResult, result, 0.0);

        double[] b = a.clone();
        Arrays2.mul(b, -1);
        expResult = -1;
        result = Statistics.autoCorrelation(a, b);
        assertEquals(expResult, result, 0.0);

        b = a.clone();
        a[0] += .1;
        result = Statistics.autoCorrelation(a, b);
        assertTrue(Math2.isIn(0.9, result, 1));
    }

    @Test
    public void testAutoCorrelation_5args() {
        double[] a = {-1000, 1, 2, 3, 3.3, 4.4, 5, 7, 9, 10};
        double[] b = {1, 2, 3, 3.3, 4.4, 5, 7, 9, 10, 1000};
        double expResult = 1;
        double result = Statistics.autoCorrelation(a, 1, b, 0, 9);
        assertEquals(expResult, result, 0.0);

        double[] c = b.clone();
        Arrays2.mul(c, -1);
        expResult = -1;
        result = Statistics.autoCorrelation(a, 1, c, 0, 9);
        assertEquals(expResult, result, 0.0);

        c = b.clone();
        c[0] += .1;
        result = Statistics.autoCorrelation(a, 1, c, 0, 9);
        assertTrue("was " + result, Math2.isIn(0.9, result, 1));
    }

    @Test
    public void testWeightedStdev_weight1() {
        IntToDoubleFunction f = v -> 0.2d;

        double[] v1 = new double[]{1, 1, 1, 1, 1};
        double res1 = Statistics.weightedStdev(v1, f);
        assertEquals(Statistics.stdev(v1), res1, 0.0001);

        double[] v2 = new double[]{1, 2, 3, 4, 5};
        double res2 = Statistics.weightedStdev(v2, f);
        assertEquals(Statistics.stdev(v2), res2, 0.0001);
    }

    @Test
    public void testWeightedStdev_weightAsc() {
        IntToDoubleFunction f = v -> v * 1d;

        double[] v1 = new double[]{1, 1, 1, 1, 1};
        double res1 = Statistics.weightedStdev(v1, f);
        assertEquals(Statistics.stdev(v1), res1, 0.0001);

        double[] v2 = new double[]{10, 1, 10, 1, 10, 5, 5, 5, 5, 5, 5, 5, 5};
        double res2 = Statistics.weightedStdev(v2, f);
        double stdev = Statistics.stdev(v2);
        assertTrue(res2 + " should be < than " + stdev, res2 < stdev);
    }
}
