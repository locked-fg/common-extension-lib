package de.lmu.ifi.dbs.utilities.primitiveArrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author graf
 */
public class BoundedSortedDoubleArrayTest {
    
    public BoundedSortedDoubleArrayTest() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void add1() {
        BoundedSortedDoubleArray arr = new BoundedSortedDoubleArray();
        final int k = 5;
        arr.setMaxLength(k);
        for (int i = k*2; i >=0; i--) {
            arr.add(i);
        }
        
        assertEquals(k, arr.size());
        for (int i = 0; i < arr.size(); i++) {
            assertEquals(i, arr.get(i), 0.0001);
        }
    }

    @Test
    public void addIndexCheck() {
        BoundedSortedDoubleArray arr = new BoundedSortedDoubleArray(3); // limit initially
        arr.setMaxLength(3);
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4); // must not fail
    }
    
    @Test
    public void setMaxlengthUp() {
        BoundedSortedDoubleArray arr = new BoundedSortedDoubleArray(2); // limit initially
        arr.setMaxLength(2);
        arr.add(1);
        arr.add(2);
        arr.add(3); // must not fail
        arr.setMaxLength(3);
        arr.add(5);
        arr.add(4);
        
        double exp[] = {1,2,4};
        for (int i = 0; i < arr.size(); i++) {
            assertEquals("failed at index "+i, exp[i], arr.get(i), 0.001);
        }
    }
    
    @Test
    public void setMaxlengthDown() {
        BoundedSortedDoubleArray arr = new BoundedSortedDoubleArray(3); // limit initially
        arr.setMaxLength(2);
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.setMaxLength(1);
        double exp[] = {1};
        for (int i = 0; i < arr.size(); i++) {
            assertEquals("failed at index "+i, exp[i], arr.get(i), 0.001);
        }
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }
    
    @AfterClass
    public static void tearDownClass() throws Exception {
    }
}
