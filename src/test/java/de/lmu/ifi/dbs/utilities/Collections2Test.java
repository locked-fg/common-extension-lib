package de.lmu.ifi.dbs.utilities;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author graf
 */
public class Collections2Test {

    public Collections2Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

//    @Test
//    public void testRandomSample_Collection_int() {
//        System.out.println("randomSample");
//        Collection<T> in = null;
//        int size = 0;
//        List expResult = null;
//        List result = Collections2.randomSample(in, size);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    @Test
//    public void testRandomSample_Collection_double() {
//        System.out.println("randomSample");
//        Collection<T> in = null;
//        double ratio = 0.0;
//        List expResult = null;
//        List result = Collections2.randomSample(in, ratio);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    @Test
//    public void testRandomSample_3args_1() {
//        System.out.println("randomSample");
//        Collection<T> in = null;
//        double ratio = 0.0;
//        Random rnd = null;
//        List expResult = null;
//        List result = Collections2.randomSample(in, ratio, rnd);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testRandomSample_3args_2() {
//        System.out.println("randomSample");
//        Collection<T> in = null;
//        int size = 0;
//        Random rnd = null;
//        List expResult = null;
//        List result = Collections2.randomSample(in, size, rnd);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testTrimToSize() {
//        System.out.println("trimToSize");
//        Collection in = null;
//        int size = 0;
//        Collections2.trimToSize(in, size);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testUnique() {
//        System.out.println("unique");
//        Collection<T> src = null;
//        List expResult = null;
//        List result = Collections2.unique(src);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testIndexOf() {
//        System.out.println("indexOf");
//        List list = null;
//        Object o = null;
//        int expResult = 0;
//        int result = Collections2.indexOf(list, o);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testJoinToString() {
//        System.out.println("joinToString");
//        List in = null;
//        String glue = "";
//        String expResult = "";
//        String result = Collections2.joinToString(in, glue);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    @Test
    public void testJoinFormatted() {
        Collection<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        Formatter<Integer> f = new Formatter<>();

        String result = Collections2.join(list, f, "|");
        String expected = "1|2";
        assertEquals(expected, result);
    }

    @Test
    public void testMax() {
        Collection<Integer> in = new ArrayList<>();
        in.add(new Integer(1));
        in.add(new Integer(2));
        in.add(new Integer(3));
        int expResult = 2;
        int result = Collections2.max(in);
        assertEquals(expResult, result);
    }

    @Test
    public void testMin() {
        Collection<Integer> in = new ArrayList<>();
        in.add(new Integer(1));
        in.add(new Integer(2));
        in.add(new Integer(3));
        int expResult = 0;
        int result = Collections2.min(in);
        assertEquals(expResult, result);
    }
}
