package de.lmu.ifi.dbs.utilities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * This class can be used to cound combinations of occurences.
 *
 * For example if you want to know how often {"A"} occurs but also want to count {"A", "B"}, you would simply do a
 * <code>
 * String[] ts1 = {"A"};
 * String[] ts2 = {"A", "B"};
 *
 * MultiCountMap map = new MultiCountMap();
 * map.add(ts1);
 * map.add(ts1);
 * map.add(ts2, 4); // for some reason we know this occurs 4x
 * map.add(ts2);
 *
 * map.get(ts1); // returns 1.0
 * map.get(ts2); // returns 5.0
 * </code>
 *
 * In clean OOP you might have (or should!) create separate objects for the inputs and override hashcode and equals. Yet
 * this is sometimes just overkill and you simply want to count such occurences but do not want to concatenate the
 * strings with some magic seprator etc. In this case, this class is for you.
 *
 * @author Franz
 */
public class MultiCountMap<T> {

    private HashMap<Entry<T>, Double> map = new HashMap<Entry<T>, Double>();

    public MultiCountMap() {
    }

    /**
     * clears all entries
     */
    public void clear() {
        map.clear();
    }

    /**
     * Checks if the map is empty. Empty in this case means that there are no elements stored in the list. If an element
     * with a count of 0 is stored, this does NOT mean empty.
     *
     * @return true if there is no element contained in the map.
     */
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * returns the current counter value
     *
     * @param ts
     * @return value
     */
    public double get(List<T> ts) {
        return get((T[]) ts.toArray());
    }

    /**
     * returns the current counter value
     *
     * @param ts
     * @return value
     */
    public double get(T ts) {
        return get((T[]) new Object[]{ts});
    }

    /**
     * returns the current counter value
     *
     * @param ts
     * @return value
     */
    public double get(T[] ts) {
        Double v = map.get(new Entry<T>(ts));
        return v == null ? 0 : v;
    }

    /**
     * raises the counter vor the given combination
     *
     * @param ts
     * @return new count value
     */
    public double add(List<T> ts) {
        return add(ts, 1);
    }

    /**
     * raises the counter vor the given combination
     *
     * @param ts
     * @return new count value
     */
    public double add(T ts) {
        return add(ts, 1);
    }

    /**
     * raises the counter vor the given combination
     *
     * @param ts
     * @return new count value
     */
    public double add(T[] ts) {
        return add(ts, 1);
    }

    /**
     * raises the counter vor the given combination
     *
     * @param ts
     * @param inc
     * @return new count value
     */
    public double add(List<T> ts, double inc) {
        return addDirect((T[]) ts.toArray(), inc);
    }

    /**
     * raises the counter vor the given combination
     *
     * @param t object to be added
     * @param inc value to add to the counter
     * @return new count value
     */
    public double add(T t, double inc) {
        return addDirect((T[]) new Object[]{t}, inc);
    }

    /**
     * raises the counter vor the given combination
     *
     * @param ts
     * @param inc
     * @return new count value
     */
    public double add(T[] ts, double inc) {
        return addDirect(ts.clone(), inc);
    }

    /**
     * sets the counter vor the given combination
     *
     * @param ts
     * @param inc
     * @return previous count value
     */
    public double set(List<T> ts, double inc) {
        return setDirect((T[]) ts.toArray(), inc);
    }

    /**
     * sets the counter vor the given combination
     *
     * @param t object to be added
     * @param inc value to set for the counter
     * @return previous count value
     */
    public double set(T t, double inc) {
        return setDirect((T[]) new Object[]{t}, inc);
    }

    /**
     * sets the counter vor the given combination
     *
     * @param ts
     * @param inc
     * @return previous count value
     */
    public double set(T[] ts, double inc) {
        return setDirect(ts.clone(), inc);
    }

    private double setDirect(T[] ts, double value) {
        Double prevValue = map.put(new Entry<T>(ts), value);
        return prevValue == null ? 0 : prevValue;
    }

    private double addDirect(T[] ts, double inc) {
        Entry<T> entry = new Entry<T>(ts);
        Double value = map.get(entry);
        value = (value == null) ? inc : value + inc;
        map.put(entry, value);
        return value;
    }

    private static class Entry<T> {

        private final T[] key;

        public Entry(T[] key) {
            this.key = key;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 67 * hash + Arrays.deepHashCode(this.key);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Entry other = (Entry) obj;
            if (!Arrays.deepEquals(this.key, other.key)) {
                return false;
            }
            return true;
        }
    }
}
