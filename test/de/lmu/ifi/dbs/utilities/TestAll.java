package de.lmu.ifi.dbs.utilities;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * no need to add classes by hand, just generate the test suite via gui
 * @author graf
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({de.lmu.ifi.dbs.utilities.StatisticsTest.class,
    de.lmu.ifi.dbs.utilities.Arrays2Test.class,
    de.lmu.ifi.dbs.utilities.Collections2Test.class,
    de.lmu.ifi.dbs.utilities.Math2Test.class})
public class TestAll {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
}
