package de.lmu.ifi.dbs.utilities;

import java.awt.geom.Point2D;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author graf
 */
public class Math2Test {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
//
//    @Test
//    public void testRotate() {
//        System.out.println("rotate");
//        int x = 0;
//        int y = 0;
//        double deg = 0.0;
//        Point2D expResult = null;
//        Point2D result = Math2.rotate(x, y, deg);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGauss_3args() {
//        System.out.println("gauss");
//        double x = 0.0;
//        double y = 0.0;
//        double sigma = 0.0;
//        double expResult = 0.0;
//        double result = Math2.gauss(x, y, sigma);
//        assertEquals(expResult, result, 0.0);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGauss_double_double() {
//        System.out.println("gauss");
//        double x = 0.0;
//        double sigma = 0.0;
//        double expResult = 0.0;
//        double result = Math2.gauss(x, sigma);
//        assertEquals(expResult, result, 0.0);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetGauss() {
//        System.out.println("getGauss");
//        int size = 0;
//        double[] expResult = null;
//        double[] result = Math2.getGauss(size);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testBind() {
//        System.out.println("bind");
//        double min = 0.0;
//        double value = 0.0;
//        double max = 0.0;
//        double expResult = 0.0;
//        double result = Math2.bind(min, value, max);
//        assertEquals(expResult, result, 0.0);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testIsIn_3args_1() {
//        System.out.println("isIn");
//        int min = 0;
//        int v = 0;
//        int max = 0;
//        boolean expResult = false;
//        boolean result = Math2.isIn(min, v, max);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testIsIn_3args_2() {
//        System.out.println("isIn");
//        double min = 0.0;
//        double v = 0.0;
//        double max = 0.0;
//        boolean expResult = false;
//        boolean result = Math2.isIn(min, v, max);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testToPolar() {
//        System.out.println("toPolar");
//        Point2D p = null;
//        Math2.toPolar(p);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testToKarthesian() {
//        System.out.println("toKarthesian");
//        Point2D p = null;
//        Math2.toKarthesian(p);
//        fail("The test case is a prototype.");
//    }

    @Test
    public void testCosinus() {
        double[] a = {0, 1};
        double[] b = {0, 1};
        double expResult = 1.0;
        double result = Math2.cosinus(a, b);
        assertEquals(expResult, result, 0.0);

        a = new double[]{0, 1};
        b = new double[]{1, 0};
        expResult = .0;
        result = Math2.cosinus(a, b);
        assertEquals(expResult, result, 0.0);

        a = new double[]{0, 1};
        b = new double[]{.5, .5};
        expResult = .5 * Math.sqrt(2);
        result = Math2.cosinus(a, b);
        assertEquals(expResult, result, Math.ulp(expResult));
    }
}
