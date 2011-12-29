package de.lmu.ifi.dbs.utilities;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class Arrays2Test {

    public Arrays2Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testMedian() {
        double[] d;
        d = new double[]{1};
        assertEquals(1, Arrays2.median(d), 0.0001);

        d = new double[]{1, 2, 3};
        assertEquals(2, Arrays2.median(d), 0.0001);

        d = new double[]{1, 3};
        assertEquals(2, Arrays2.median(d), 0.0001);

        d = new double[]{1, 2, 3, 4};
        assertEquals(2.5, Arrays2.median(d), 0.0001);
    }

    @Test
    public void testUniqueDouble() {
        double[] in = {0d, 0d, 1, 2, 3, 2, 0, 0};
        double[] exp = {0d, 1, 2, 3};

        // equals only works on ints etc
        assertArrayEquals(exp, Arrays2.unique(in), 0.0001);
    }

    @Test
    public void testUniqueDoubleAcc() {
        double[] in = {0d, 0d, 1, 2, 40, 2, 2.41, 40.51, 0};
        double[] exp = {0d, 1, 2, 40, 40.51};

        // equals only works on ints etc
        assertArrayEquals(exp, Arrays2.unique(in, 0.5), 0.0001);
    }

    @Test
    public void testFloatSub() {
        float[] a = {1f, 2f};
        float[] b = {.5f, .5f};
        float[] exp = {.5f, 1.5f};

        assertArrayEquals(exp, Arrays2.sub(a, b, null), 0.0001f);
        Arrays2.sub(a, b);
        assertArrayEquals(exp, a, 0.0001f);
    }

    private void assertArrayEquals(double[] exp, double[] out, double accuracy) {
        assertEquals("lenghth error", exp.length, out.length);
        for (int i = 0; i < out.length; i++) {
            assertEquals(exp[i], out[i], accuracy);
        }
    }

    private void assertArrayEquals(float[] exp, float[] out, float accuracy) {
        assertEquals("lenghth error", exp.length, out.length);
        for (int i = 0; i < out.length; i++) {
            assertEquals(exp[i], out[i], accuracy);
        }
    }
}
