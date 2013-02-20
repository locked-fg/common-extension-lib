package de.lmu.ifi.dbs.utilities;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VectorsTest {

    @Test
    public void testLength() {
        double[] in = new double[]{1d, 0d, 0d};
        double expResult = 1;
        double result = Vectors.length(in);
        assertEquals(expResult, result, 0.0001);
    }

    @Test
    public void testNormalizeZero() {
        double[] in = new double[]{0, 0};
        Vectors.normalize(in);
        assertEquals(0, in[0], 0.00001);
        assertEquals(0, in[1], 0.00001);
    }

    @Test
    public void testNormalize() {
        double[] exp = new double[]{Math.sqrt(.5), Math.sqrt(.5)};
        double[] in = new double[]{3, 3};
        Vectors.normalize(in);
        for (int i = 0; i < in.length; i++) {
            assertEquals(exp[i], in[i], 0.0001);
        }

        exp = new double[]{0, 1};
        in = new double[]{0, 3};
        Vectors.normalize(in);
        for (int i = 0; i < in.length; i++) {
            assertEquals(exp[i], in[i], 0.0001);
        }
    }
}
