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

    @Test
    public void testDiv(){
        double div = Math2.div(1, 2, 3);
        assertEquals(0.5, div, 0.001);
        
        div = Math2.div(0, 2, 3);
        assertEquals(0, div, 0.001);
        
        div = Math2.div(1, 0, 3);
        assertEquals(3, div, 0.001);
    }

}
