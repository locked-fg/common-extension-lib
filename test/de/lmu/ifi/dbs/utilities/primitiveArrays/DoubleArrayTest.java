package de.lmu.ifi.dbs.utilities.primitiveArrays;

import java.util.List;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author graf
 */
public class DoubleArrayTest {

    public DoubleArrayTest() {
    }

    @Test
    public void addAllDouble() {
        DoubleArray doubleArray = new DoubleArray();
        doubleArray.add(0);
        doubleArray.add(1);
        
        List<Double> l = new ArrayList<>();
        l.add(2d);
        l.add(3d);
        
        doubleArray.addAll(l);

        assertEquals(4, doubleArray.size());
        assertEquals(0, doubleArray.get(0), 0.001);
        assertEquals(1, doubleArray.get(1), 0.001);
        assertEquals(2, doubleArray.get(2), 0.001);
        assertEquals(3, doubleArray.get(3), 0.001);
    }
    
//    @BeforeClass
//    public static void setUpClass() throws Exception {
//    }
//
//    @AfterClass
//    public static void tearDownClass() throws Exception {
//    }
}
