package de.lmu.ifi.dbs.utilities;

import java.util.Comparator;
import java.util.Map.Entry;

/**
 * Class that provides Key and Value comparators for {@link Entry}-objects.
 * 
 * @author graf
 */
public class EntryComparator {

    /**
     * Compares two {@link Entry}s by their values
     * @param <T> 
     */
    public static class Value<T extends Comparable> implements Comparator<Entry<?, T>> {

        @Override
        public int compare(Entry<?, T> o1, Entry<?, T> o2) {
            return o1.getValue().compareTo(o2.getValue());
        }
    }

    /**
     * Compares two {@link Entry}s by their keys
     * @param <T> 
     */
    public static class Key<T extends Comparable> implements Comparator<Entry<T, ?>> {

        @Override
        public int compare(Entry<T, ?> o1, Entry<T, ?> o2) {
            return o1.getKey().compareTo(o2.getKey());
        }
    }
}
