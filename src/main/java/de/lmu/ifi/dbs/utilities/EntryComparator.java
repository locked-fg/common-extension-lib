package de.lmu.ifi.dbs.utilities;

import java.util.Comparator;
import java.util.Map.Entry;

/**
 * Class that provides Key and Value comparators for {@link Entry}-objects including the option to change the sort order
 * in ascending and descending
 *
 * @author graf
 */
public class EntryComparator {

    /**
     * Compares two {@link Entry}s by their values
     *
     * @param <T> generic type
     */
    public static class Value<T extends Comparable> implements Comparator<Entry<?, T>> {

        private int invert = 1;

        @Override
        public int compare(Entry<?, T> o1, Entry<?, T> o2) {
            return invert * o1.getValue().compareTo(o2.getValue());
        }

        public Value<T> asc() {
            invert = 1;
            return this;
        }

        public Value<T> desc() {
            invert = -1;
            return this;
        }
    }

    /**
     * Compares two {@link Entry}s by their keys
     *
     * @param <T> generic type
     */
    public static class Key<T extends Comparable> implements Comparator<Entry<T, ?>> {

        private int invert = 1;

        @Override
        public int compare(Entry<T, ?> o1, Entry<T, ?> o2) {
            return invert * o1.getKey().compareTo(o2.getKey());
        }

        public Key<T> asc() {
            invert = 1;
            return this;
        }

        public Key<T> desc() {
            invert = -1;
            return this;
        }
    }
}
