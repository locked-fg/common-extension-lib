package de.lmu.ifi.dbs.utilities;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Class used to count the amount of certain objects. For example: put(Foo) -&gt; 1 but(Foo) -&gt; 2
 *
 * You probably want to use com.google.common.collect.Multiset instead if you only need integer counts
 *
 * @author graf
 * @param <T> type param
 * @see <a href="http://docs.guava-libraries.googlecode.com/git/javadoc/com/google/common/collect/Multiset.html">Multiset</a>
 */
public class CountMap<T> implements Iterable<Entry<T, Double>> {

    private final HashMap<T, Double> map = new HashMap<>();

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
     * @param other other countMap
     */
    public CountMap(CountMap<? extends T> other) {
        for (Entry<? extends T, Double> entry : other) {
            map.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Adds all elements of this collection with a value of 1
     *
     * @param list elements to add to the map
     */
    public void add(Collection<T> list) {
        for (T t : list) {
            add(t, 1);
        }
    }

    /**
     * Adds all elements of this collection with the given value
     *
     * @param list elements to add to the map
     * @param value value used for each element
     */
    public void add(Collection<T> list, double value) {
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
     * @param e adds this element with a vlaue of 1
     * @return new value for t
     */
    public Double add(T e) {
        return add(e, 1);
    }

    /**
     * increase the counter of the map by 'inc'.
     *
     * @param e adds this element
     * @param inc amount to increase
     * @return new amount
     */
    public Double add(T e, double inc) {
        Double i = get(e) + inc;
        map.put(e, i);
        return i;
    }

    /**
     * Sets a specific value for this label, replacing any (posiibly) existing value
     *
     * @param e adds this element
     * @param value set this amount for e
     * @return previous value
     */
    public Double set(T e, double value) {
        Double prev = get(e);
        map.put(e, value);
        return prev;
    }

    /**
     * Removes the entry from the map and returns the most recent associated value
     *
     * @param e element to remove
     * @return the previously assigned value
     */
    public double remove(T e) {
        double d = get(e);
        map.remove(e);
        return d;
    }

    /**
     * clears the map
     */
    public void clear() {
        map.clear();
    }

    /**
     * @return iterator over the entry set
     */
    @Override
    public Iterator<Entry<T, Double>> iterator() {
        return map.entrySet().iterator();
    }

    /**
     * Returns the current value for the given label. If the label was never added before, 0 is returned.
     *
     * @param needle the object to get the value for
     * @return the current value for this label (0 if the label was never added)
     */
    public Double get(T needle) {
        Double value = map.get(needle);
        if (value == null) {
            value = 0d;
        }
        return value;
    }

    /**
     * @return all values stored in the map
     */
    public Collection<Double> values() {
        return map.values();
    }

    /**
     * @return set of keys that have been counted
     */
    public Set<T> keySet() {
        return map.keySet();
    }

    /**
     * @return the entry set of the count map
     */
    public Set<Entry<T, Double>> entrySet() {
        return map.entrySet();
    }

    /**
     * returns the sum of all values - NOT the size of the map!
     *
     * @return sum of all values
     */
    public double getSum() {
        return Collections2.sum(map.values());
    }

    /**
     * returns the amount of entries in the map
     *
     * @return size of map (number of entries)
     */
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
