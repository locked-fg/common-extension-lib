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
 *
 * You probably want to use com.google.common.collect.Multiset instead if you only need integer counts
 * @see com.google.common.collect.Multiset
 * http://docs.guava-libraries.googlecode.com/git/javadoc/com/google/common/collect/Multiset.html
 */
public class CountMap<T> implements Iterable<Entry<T, Double>> {

    private final HashMap<T, Double> map = new HashMap<T, Double>();

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
        for (Entry<? extends T, Double> entry : other) {
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
     * @param value
     */
    public void addAll(Collection<T> list, double value) {
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
        Iterator<Entry<T, Double>> it = other.iterator();
        while (it.hasNext()) {
            Entry<T, Double> e = it.next();
            add(e.getKey(), e.getValue());
        }
    }

    /**
     * adds T to the map and returns the number of occurences that are now logged
     *
     * @param t
     * @return new value for t
     */
    public Double add(T t) {
        return add(t, 1);
    }

    /**
     * increase the counter of the map by 'inc'.
     *
     * @param t
     * @param inc
     * @return new amount
     */
    public Double add(T t, double inc) {
        Double i = get(t) + inc;
        map.put(t, i);
        return i;
    }

    @Override
    public Iterator<Entry<T, Double>> iterator() {
        return map.entrySet().iterator();
    }

    /**
     * Returns the current value for the given label. If the label was never added before, 0 is returned.
     *
     * @param label
     * @return
     */
    public Double get(T label) {
        Double value = map.get(label);
        if (value == null) {
            value = 0d;
        }
        return value;
    }

    public Collection<Double> values() {
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
    public double getSum() {
        return Collections2.sum(map.values());
    }

    public int size() {
        return map.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CountMap: ");
        for (Entry<T, Double> e : map.entrySet()) {
            sb.append("[");
            sb.append(e.getKey());
            sb.append("=");
            sb.append(e.getValue());
            sb.append("]");
        }
        return sb.toString();
    }
}
