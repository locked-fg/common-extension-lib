/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.lmu.ifi.dbs.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Franz
 */
public class CountMapTest {

    public CountMapTest() {
    }

    /**
     * Test of addAll method, of class CountMap.
     */
    @Test
    public void testAddAll_Collection() {
        System.out.println("addAll");
        List<String> l = Arrays.asList(new String[]{"a", "b", "a"});
        CountMap map = new CountMap();
        map.addAll(l);

        assertEquals(2, map.get("a"), 0.01);
        assertEquals(1, map.get("b"), 0.01);
    }

    /**
     * Test of addAll method, of class CountMap.
     */
    @Test
    public void testAddAll_Collection_double() {
        System.out.println("addAll");
        List<String> l = Arrays.asList(new String[]{"a", "b", "a"});
        CountMap map = new CountMap();
        map.addAll(l, 2);

        assertEquals(4, map.get("a"), 0.01);
        assertEquals(2, map.get("b"), 0.01);
    }

    /**
     * Test of add method, of class CountMap.
     */
    @Test
    public void testAdd_CountMap() {
        System.out.println("add");
        CountMap map = new CountMap();
        map.add("foo");

        assertEquals(1, map.get("foo"), 0.001);
        assertEquals(0, map.get("bar"), 0.001);
    }

    /**
     * Test of add method, of class CountMap.
     */
    @Test
    public void testAdd_GenericType() {
        System.out.println("add");
        Object t = "X";
        CountMap instance = new CountMap();

        Double result = instance.add(t);
        assertEquals(1d, result, 0.001);

        result = instance.add(t);
        assertEquals(2d, result, 0.001);
    }

    /**
     * Test of add method, of class CountMap.
     */
    @Test
    public void testAdd_GenericType_double() {
        System.out.println("add");
        Object t = "X";
        double inc = 3.0;
        CountMap instance = new CountMap();
        Double result = instance.add(t, inc);
        assertEquals(3d, result, 0.001);
    }

    /**
     * Test of iterator method, of class CountMap.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        CountMap map = new CountMap();

        Iterator result = map.iterator();
        assertFalse(result.hasNext());

        map.add("x");

        result = map.iterator();
        assertTrue(result.hasNext());
    }

    /**
     * Test of get method, of class CountMap.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        Object label = "X";

        CountMap map = new CountMap();
        Double result = map.get(label);
        assertEquals(0d, result, 0.01);

        map.add(label, 1);
        result = map.get(label);
        assertEquals(1d, result, 0.01);
    }

    /**
     * Test of values method, of class CountMap.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        CountMap map = new CountMap();
        assertTrue(map.values().isEmpty());

        map.add("x");
        assertFalse(map.values().isEmpty());
        assertEquals(1, map.values().size());
        assertEquals(1d, (Double) map.values().iterator().next(), 0.001);
    }

    /**
     * Test of keySet method, of class CountMap.
     */
    @Test
    public void testKeySet() {
        System.out.println("keySet");
        CountMap map = new CountMap();
        assertTrue(map.keySet().isEmpty());
        
        map.add("x");
        assertFalse(map.keySet().isEmpty());
        assertEquals("x", map.keySet().iterator().next());
    }

    /**
     * Test of getSum method, of class CountMap.
     */
    @Test
    public void testGetSum() {
        System.out.println("getSum");
        CountMap map = new CountMap();
        assertEquals(0d, map.getSum(), 0.01);
        
        map.add("x");
        assertEquals(1d, map.getSum(), 0.01);
        
        map.add("y", 5);
        assertEquals(6d, map.getSum(), 0.01);
    }

    /**
     * Test of size method, of class CountMap.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        CountMap map = new CountMap();
        assertEquals(0, map.size());
        map.add("x");
        assertEquals(1, map.size());
        map.add("x");
        assertEquals(1, map.size());
        map.add("y");
        assertEquals(2, map.size());
    }
}
