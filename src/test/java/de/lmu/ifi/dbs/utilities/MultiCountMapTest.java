package de.lmu.ifi.dbs.utilities;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Franz
 */
public class MultiCountMapTest {
    
    public MultiCountMapTest() {
    }

    @Test
    public void testAdd_List() {
        List<String> list1 = new ArrayList<String>();
        list1.add("a");
        List<String> list2 = new ArrayList<String>();
        list1.add("a");
        list1.add("b");
        
        MultiCountMap map = new MultiCountMap();
        assertEquals(1, map.add(list1), 0.00001);
        assertEquals(2, map.add(list1), 0.00001);
        
        assertEquals(1, map.add(list2), 0.00001);
    }

    @Test
    public void testAdd_1args_1() {
        MultiCountMap map = new MultiCountMap();
        assertEquals(1, map.add("A"), 0.00001);
        assertEquals(2, map.add("A"), 0.00001);
        assertEquals(1, map.add("B"), 0.00001);
    }

    @Test
    public void testAdd_1args_2() {
        String[] ts1 = {"A"};
        String[] ts2 = {"A", "B"};
        
        MultiCountMap map = new MultiCountMap();
        assertEquals(1, map.add(ts1), 0.00001);
        assertEquals(2, map.add(ts1), 0.00001);
        
        assertEquals(1, map.add(ts2), 0.00001);
    }

    @Test
    public void testAdd_List_double() {
        List<String> list1 = new ArrayList<String>();
        list1.add("a");
        List<String> list2 = new ArrayList<String>();
        list1.add("a");
        list1.add("b");
        
        MultiCountMap map = new MultiCountMap();
        assertEquals(2, map.add(list1, 2), 0.00001);
        assertEquals(5, map.add(list1, 3), 0.00001);
        
        assertEquals(4, map.add(list2, 4), 0.00001);
    }

    @Test
    public void testAdd_2args_1() {
        MultiCountMap map = new MultiCountMap();
        assertEquals(2, map.add("A", 2), 0.00001);
        assertEquals(5, map.add("A", 3), 0.00001);
        assertEquals(4, map.add("B", 4), 0.00001);
    }

    @Test
    public void testAdd_2args_2() {
        String[] ts1 = {"A"};
        String[] ts2 = {"A", "B"};
        
        MultiCountMap map = new MultiCountMap();
        assertEquals(2, map.add(ts1, 2), 0.00001);
        assertEquals(5, map.add(ts1, 3), 0.00001);
        
        assertEquals(4, map.add(ts2, 4), 0.00001);
    }

    @Test
    public void testSet_List_double() {
        List<String> list1 = new ArrayList<String>();
        list1.add("a");
        List<String> list2 = new ArrayList<String>();
        list1.add("a");
        list1.add("b");
        
        MultiCountMap map = new MultiCountMap();
        assertEquals(0, map.set(list1, 2), 0.00001);
        assertEquals(2, map.set(list1, 3), 0.00001);
        
        assertEquals(0, map.set(list2, 4), 0.00001);
        assertEquals(4, map.set(list2, 5), 0.00001);
    }

    @Test
    public void testSet_2args_1() {
        MultiCountMap map = new MultiCountMap();
        assertEquals(0, map.set("A", 2), 0.00001);
        assertEquals(2, map.set("A", 3), 0.00001);
        assertEquals(0, map.set("B", 4), 0.00001);
        assertEquals(4, map.set("B", 5), 0.00001);
    }

    @Test
    public void testSet_2args_2() {
        String[] ts1 = {"A"};
        String[] ts2 = {"A", "B"};
        
        MultiCountMap map = new MultiCountMap();
        assertEquals(0, map.set(ts1, 2), 0.00001);
        assertEquals(2, map.set(ts1, 3), 0.00001);
        
        assertEquals(0, map.set(ts2, 4), 0.00001);
        assertEquals(4, map.set(ts2, 5), 0.00001);
    }

    @Test
    public void testGet_List_double() {
        List<String> list1 = new ArrayList<String>();
        list1.add("a");
        List<String> list2 = new ArrayList<String>();
        list1.add("a");
        list1.add("b");
        
        MultiCountMap map = new MultiCountMap();
        assertEquals(0, map.get(list1), 0.00001);
        assertEquals(1, map.add(list1, 1), 0.00001);
        assertEquals(1, map.get(list1), 0.00001);
        
        assertEquals(3, map.add(list1, 2), 0.00001);
        assertEquals(3, map.get(list1), 0.00001);
        
        assertEquals(4, map.add(list2, 4), 0.00001);
        assertEquals(4, map.get(list2), 0.00001);
    }

    @Test
    public void testGet_2args_1() {
        MultiCountMap map = new MultiCountMap();
        assertEquals(0, map.get("A"), 0.00001);
        assertEquals(2, map.add("A", 2), 0.00001);
        assertEquals(2, map.get("A"), 0.00001);
        
        assertEquals(5, map.add("A", 3), 0.00001);
        assertEquals(5, map.get("A"), 0.00001);
        
        assertEquals(0, map.get("B"), 0.00001);
        assertEquals(4, map.add("B", 4), 0.00001);
        assertEquals(4, map.get("B"), 0.00001);
    }

    @Test
    public void testGet_2args_2() {
        String[] ts1 = {"A"};
        String[] ts2 = {"A", "B"};
        
        MultiCountMap map = new MultiCountMap();
        assertEquals(0, map.get(ts1), 0.00001);
        assertEquals(2, map.add(ts1, 2), 0.00001);
        assertEquals(2, map.get(ts1), 0.00001);
        
        assertEquals(5, map.add(ts1, 3), 0.00001);
        assertEquals(5, map.get(ts1), 0.00001);
        
        assertEquals(0, map.get(ts2), 0.00001);
        assertEquals(4, map.add(ts2, 4), 0.00001);
        assertEquals(4, map.get(ts2), 0.00001);
        
        assertEquals(5, map.add(ts2), 0.00001);
        assertEquals(5, map.get(ts2), 0.00001);
    }
    
    @Test
    public void testClear(){
        MultiCountMap map = new MultiCountMap();
        assertTrue(map.isEmpty());
        map.add("A");
        assertFalse(map.isEmpty());
        map.clear();
        assertTrue(map.isEmpty());
    }
}
