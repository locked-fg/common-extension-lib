package de.lmu.ifi.dbs.utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.RandomAccess;

/**
 * Keep in mind that alot of these fucntions can be achived by Streams as well!
 */
public class Collections2 {

    private static final int REVERSE_THRESHOLD = 18; // Collections

    /**
     * returns a random sample from the input list
     *
     * @param <T> generic type param
     * @param in source list
     * @param size size of sample
     * @return UNSORTED random sample from the list
     * @throws NullPointerException if in = null
     * @throws IllegalArgumentException if size &gt; in.size()
     */
    public static <T> List<T> randomSample(final Collection<T> in, int size) {
        if (in == null) {
            throw new NullPointerException("in must not be null");
        }
        return randomSample(in, size, new Random());
    }

    public static <T> List<T> randomSample(final Collection<T> in, double ratio) {
        if (in == null) {
            throw new NullPointerException("in must not be null");
        }
        return randomSample(in, (int) Math.round(in.size() * ratio), new Random());
    }

    /**
     * returns a random sample from the input list
     *
     * @param <T> generic type param
     * @param in source list
     * @param size size of sample
     * @param rnd Radnom generator
     * @return UNSORTED random sample from the list
     * @throws NullPointerException if in = null
     * @throws IllegalArgumentException if size &gt; in.size()
     */
    public static <T> List<T> randomSample(final Collection<T> in, int size, Random rnd) {
        if (in == null) {
            throw new NullPointerException("in must not be null");
        }
        if (!Math2.isIn(0, size, in.size())) {
            throw new IllegalArgumentException("size must be in [0, " + in.size() + "]");
        }
        // sampling = 0, nothing
        if (size == 0) {
            return Collections.EMPTY_LIST;
        }
        // sampling = 100% -> all
        ArrayList<T> out = new ArrayList<>(in);
        if (size == in.size()) {
            return out;
        }

        Collections.shuffle(out, rnd);
        return out.subList(0, size);
    }

    public static void trimToSize(Collection in, int size) {
        while (in.size() > size) {
            in.remove(in.size() - 1);
        }
    }

    /**
     * Returns a new List with unique elements from the source list.
     *
     * @param <T> type param
     * @param src input collection
     * @return list with unique elements from src
     */
    public static <T> List<T> unique(Collection<T> src) {
        List<T> dst = new ArrayList<>(src.size());
        for (T elem : src) {
            if (!dst.contains(elem)) {
                dst.add(elem);
            }
        }
        return dst;
    }

    public static int indexOf(List list, Object o) {
        for (int i = 0; i < list.size(); i++) {
            Object lo = list.get(i);
            if (o == null && lo == null) {
                return i;
            } else if (o != null && o.equals(lo)) {
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @param in
     * @param glue
     * @return
     * @deprecated since 2.5.0
     * @see #join(java.util.Collection, java.lang.String)
     */
    public static String joinToString(List in, String glue) {
        return join(in, glue);
    }

    /**
     * Find and return the index with the greatest value. If there are &gt;1 maxima with the same value, only
     * the first index will be returned.
     *
     * @param in the collection of objects
     * @return max array index of max value
     */
    public static int max(Collection<? extends Comparable> in) {
        int pos = -1;
        Comparable o = null;
        int i = 0;
        for (Comparable cand : in) {
            if (o == null || o.compareTo(cand) < 0) {
                pos = i;
                o = cand;
            }
            i++;
        }
        return pos;
    }

    /**
     * Find and return the object which is the max value
     *
     * @param <T> type param
     * @param in input data
     * @return max value object
     * @see #max(java.util.Collection)
     */
    public static <T extends Comparable> T maxValue(Collection<T> in) {
        // don't use max() here to determine the index because the collection
        // might not support access to an element in O(1)
        T o = null;
        for (T cand : in) {
            if (o == null || o.compareTo(cand) < 0) {
                o = cand;
            }
        }
        return o;
    }

    /**
     * Find and return the index with the greatest value. If there are &gt;1 maxima with the same value, only the
     * first index will be returned.
     *
     * @param <T> type param
     * @param in the collection of objects
     * @param comparator which does the comparisons
     * @return max array index of max value
     */
    public static <T> int max(Collection<T> in, Comparator<T> comparator) {
        int pos = -1;
        T o = null;
        int i = 0;
        for (T cand : in) {
            if (o == null || comparator.compare(o, cand) < 0) {
                pos = i;
                o = cand;
            }
            i++;
        }
        return pos;
    }

    /**
     * Find and return the index with the smallest value. If there are &gt;1 maxima with the same value, only the
     * first index will be returned.
     *
     * @param in input data
     * @return max  index of max value
     */
    public static int min(Collection<? extends Comparable> in) {
        int pos = -1;
        Comparable o = null;
        int i = 0;
        for (Comparable cand : in) {
            if (o == null || o.compareTo(cand) > 0) {
                pos = i;
                o = cand;
            }
            i++;
        }
        return pos;
    }

    /**
     * Find and return the object the is the max value
     *
     * @param <T> type param
     * @param in input data
     * @return min  index of max value
     * @see #max(java.util.Collection)
     */
    public static <T extends Comparable> T minValue(Collection<T> in) {
        // don't use max() here to determine the index because the collection
        // might not support access to an element in O(1)
        T o = null;
        for (T cand : in) {
            if (o == null || o.compareTo(cand) > 0) {
                o = cand;
            }
        }
        return o;
    }

    /**
     * Find and return the index with the greatest value. If there are &gt;1 maxima with the same value, only the
     * first index will be returned.
     *
     * @param <T> type param
     * @param in the collection of objects
     * @param comparator which does the comparisons
     * @return max array index of max value
     */
    public static <T> int min(Collection<T> in, Comparator<T> comparator) {
        int pos = -1;
        T o = null;
        int i = 0;
        for (T cand : in) {
            if (o == null || comparator.compare(o, cand) > 0) {
                pos = i;
                o = cand;
            }
            i++;
        }
        return pos;
    }

    /**
     * calculates the sum of all values in the list
     *
     * @param list input data
     * @return sum over all elements
     */
    public static double sum(Collection<? extends Number> list) {
        double sum = 0;
        for (Number d : list) {
            sum += d.doubleValue();
        }
        return sum;
    }

    /**
     * calculates the mean of all elements in the list
     *
     * @param list input data
     * @return mean value
     */
    public static double mean(Collection<? extends Number> list) {
        return sum(list) / list.size();
    }

    /**
     * calculates the variance of the elements in the list
     *
     * @param list input data
     * @return variance
     */
    public static double variance(Collection<? extends Number> list) {
        double mean = mean(list);
        double sum = 0;
        double t;
        for (Number d : list) {
            t = d.doubleValue() - mean;
            sum += t * t;
        }
        return sum;
    }

    public static <T> String join(final Collection<T> c, final Formatter<T> f, final String glue) {
        StringBuilder sb = new StringBuilder();
        final int size = c.size();
        int i = 0;
        for (T o : c) {
            i++;
            sb.append(f.objectToString(o));
            if (i < size) {
                sb.append(glue);
            }
        }
        return sb.toString();
    }

    public static String join(Collection c, Character glue) {
        return Arrays2.join(c.toArray(), glue.toString());
    }

    public static String join(Collection c, String glue) {
        return Arrays2.join(c.toArray(), glue);
    }

    public static void fill(List in, Object fill) {
        for (int i = 0; i < in.size(); i++) {
            in.set(i, fill);
        }
    }

    /**
     * returns the last element of the list.
     *
     * @param <T> type param
     * @param list input data
     * @return last element from the list
     */
    public static <T> T last(List<T> list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    /**
     * returns the last element of the collection.
     *
     * @param <T> type param
     * @param collection input data
     * @return last element from the collection
     */
    public static <T> T last(Collection<T> collection) {
        int size = collection.size();

        T last = null;

        // logic copied from Collections.reverse
        if (size != 0) {
            if (size < REVERSE_THRESHOLD || collection instanceof RandomAccess) {
                last = ((List<T>) collection).get(size - 1);
            } else {
                for (T tmp : collection) {
                    last = tmp;
                }
            }
        }
        return last;
    }

    /**
     * Gets the specific indexed element.
     *
     * In contrast to regular get() methods, this methods also supports negative numbers to index from the end
     * of the list like -1 (last element), -2 last but one
     *
     * @param <T> type param
     * @param collection input data
     * @param i index of the element (can also be negative, then it's addressed from the end)
     * @return the element at the specified position (negative numbers indicate indexing from last element)
     */
    public static <T> T get(Collection<T> collection, int i) {
        int size = collection.size();
        if (i < 0) {
            i = size + i;
        }
        T last = null;

        // logic copied from Collections.reverse
        if (size < REVERSE_THRESHOLD || collection instanceof RandomAccess) {
            last = ((List<T>) collection).get(i);
        } else {
            int j = 0;
            for (T tmp : collection) {
                if (i == j++) {
                    last = tmp;
                    break;
                }
            }
        }
        return last;
    }
}
