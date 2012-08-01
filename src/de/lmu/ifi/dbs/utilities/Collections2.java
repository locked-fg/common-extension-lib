package de.lmu.ifi.dbs.utilities;

import java.util.*;

public class Collections2 {

    private static final int REVERSE_THRESHOLD = 18; // Collections

    /**
     * returns a random sample from the input list
     *
     * @param <T>
     * @param in source list
     * @param size size of sample
     * @return UNSORTED random sample from the list
     * @throws NullPointerException if in = null
     * @throws IllegalArgumentException if size > in.size()
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
        return randomSample(in, ratio, new Random());
    }

    /**
     * returns a random sample from the input list
     *
     * @param <T>
     * @param in source list
     * @param ratio percentage of returned elements = Math.round(in.size() * ratio
     * @param rnd random seed
     * @return UNSORTED random sample from the list
     * @throws NullPointerException if in = null
     * @throws IllegalArgumentException if ratio <= 0 || ratio > 1
     */
    public static <T> List<T> randomSample(final Collection<T> in, double ratio, Random rnd) {
        if (in == null) {
            throw new NullPointerException("in must not be null");
        }
        if (ratio <= 0 || ratio > 1) {
            throw new IllegalArgumentException("ratio must be in ]0,1] but was " + ratio);
        }
        return randomSample(in, (int) Math.round(in.size() * ratio), rnd);
    }

    /**
     * returns a random sample from the input list
     *
     * @param <T>
     * @param in source list
     * @param size size of sample
     * @return rnd Random seed
     * @return UNSORTED random sample from the list
     * @throws NullPointerException if in = null
     * @throws IllegalArgumentException if size > in.size()
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
            return new ArrayList<>(0);
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
     * @param src
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

    public static String joinToString(List in, String glue) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < in.size(); i++) {
            sb.append(in.get(i));
            if (i < in.size() - 1) {
                sb.append(glue);
            }
        }
        return sb.toString();
    }

    /**
     * Find and return the index with the greatest value. If there are >1 maxima with the same value, only the first
     * index will be returned.
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
     * Find and return the object the is the max value
     *
     * @param <T>
     * @param in
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
     * Find and return the index with the greatest value. If there are >1 maxima with the same value, only the first
     * index will be returned.
     *
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
     * Find and return the index with the smallest value. If there are >1 maxima with the same value, only the first
     * index will be returned.
     *
     * @param a
     * @return max array index of max value
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
     * @param <T>
     * @param in
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
     * Find and return the index with the greatest value. If there are >1 maxima with the same value, only the first
     * index will be returned.
     *
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

    public static double sum(Collection<? extends Number> list) {
        double sum = 0;
        for (Number d : list) {
            sum += d.doubleValue();
        }
        return sum;
    }

    public static double mean(Collection<? extends Number> list) {
        return sum(list) / list.size();
    }

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

    public static <T> String join(final Collection<T> c, final ToStringFormatter<T> f, final String glue) {
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
     * adds b to a: a[i] += b[i] <br> Afterwards a has the size of max(a.size(),b.size())
     *
     * @param a
     * @param b
     */
    public static <S extends Number, T extends Number> void addAsInteger(List<T> a, List<S> b) {
        while (a.size() < b.size()) {
            a.add((T) Integer.valueOf(0));
        }
        Integer ia, ib;
        for (int i = 0; i < b.size(); i++) {
            ia = a.get(i).intValue();
            ib = b.get(i).intValue();
            a.set(i, (T) Integer.valueOf(ia + ib));
        }
    }

    public static <T> T last(List<T> list) {
        return list.get(list.size() - 1);
    }

    public static <T> T last(Collection<T> collection) {
        int size = collection.size();
        T last = null;

        // logic copied from Collections.reverse
        if (size < REVERSE_THRESHOLD || collection instanceof RandomAccess) {
            last = ((List<T>) collection).get(size - 1);
        } else {
            for (T tmp : collection) {
                last = tmp;
            }
        }
        return last;
    }
}
