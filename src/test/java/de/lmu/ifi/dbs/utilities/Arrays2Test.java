package de.lmu.ifi.dbs.utilities;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class Arrays2Test {
    
    @Test
    public void testDivZero(){
        double[] a = {1d,2d};
        Arrays2.div(a, 0);
        assertEquals(1, a[0], 0.0001);
        assertEquals(2, a[1], 0.0001);
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
    
    @Test
    public void testAbs() {
        double[] a = {1.2, -1.1};
        Arrays2.abs(a);
        double[] e = {1.2, 1.1};
        assertArrayEquals(e, a, 0.0001);
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
