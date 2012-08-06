package de.lmu.ifi.dbs.utilities;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Class used to count the amount of certain objects. For example: put(Foo) -> 1 but(Foo) -> 2
 *
 * @author graf
 * @param <T>
 */
public class CountMap<T> implements Iterable<Entry<T, Integer>> {

    private final HashMap<T, Integer> map = new HashMap<>();

    public CountMap() {
    }

    public CountMap(Collection<? extends T> values) {
        for (T t : values) {
            add(t);
        }
    }

    /**
     * Initialize the countmap with the content of another count map. The content of 'other' is copied over to this.
     *
     * @param other
     */
    public CountMap(CountMap<? extends T> other) {
        for (Entry<? extends T, Integer> entry : other) {
            map.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Adds all elements of this collection with a value of 1
     *
     * @param list
     * @see #addAll(java.util.Collection, int)
     */
    public void addAll(Collection<T> list) {
        addAll(list, 1);
    }

    /**
     * Adds all elements of this collection with the given value
     *
     * @param list
     */
    public void addAll(Collection<T> list, int value) {
        for (T t : list) {
            add(t, value);
        }
    }

    /**
     * Adds a complete keymap to this key map.
     *
     * @param other Keymap whose elements will be added
     */
    public void add(CountMap<T> other) {
        Iterator<Entry<T, Integer>> it = other.iterator();
        while (it.hasNext()) {
            Entry<T, Integer> e = it.next();
            add(e.getKey(), e.getValue());
        }
    }

    /**
     * adds T to the map and returns the number of occurences that are now logged
     *
     * @param t
     * @return number of times 't' was put into the map until now
     */
    public Integer add(T t) {
        return add(t, 1);
    }

    /**
     * increase the counter of the map by 'inc'.
     *
     * @param t
     * @param inc
     * @return new amount
     */
    public Integer add(T t, int inc) {
        Integer i = map.get(t);
        if (i == null) {
            i = inc;
        } else {
            i = i + inc;
        }

        map.put(t, i);
        return i;
    }

    @Override
    public Iterator<Entry<T, Integer>> iterator() {
        return map.entrySet().iterator();
    }

    public Integer get(T label) {
        return map.get(label);
    }

    public Collection<Integer> values() {
        return map.values();
    }

    public Set<T> keySet() {
        return map.keySet();
    }

    /**
     * returns the sum of all values - NOT the size of the map!
     *
     * @return
     */
    public int getSum() {
        int sum = 0;
        for (Integer value : map.values()) {
            sum += value;
        }
        return sum;
    }

    public int size() {
        return map.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CountMap: ");
        for (Entry<T, Integer> e : map.entrySet()) {
            sb.append("[");
            sb.append(e.getKey());
            sb.append("=");
            sb.append(e.getValue());
            sb.append("]");
        }
        return sb.toString();
    }
}
