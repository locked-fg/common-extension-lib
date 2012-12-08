package de.lmu.ifi.dbs.utilities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
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

    public boolean isEmpty(){
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
     * @param ts
     * @param inc
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
     * @param ts
     * @param inc
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
