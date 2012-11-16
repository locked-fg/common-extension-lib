package de.lmu.ifi.dbs.utilities.primitiveArrays;

import java.util.Arrays;
import java.util.Random;
import javax.management.modelmbean.InvalidTargetObjectTypeException;

/**
 * An extension of DoubleArray that inserts values in a sorted way.
 * 
 * @author graf
 */
public class SortedDoubleArray extends DoubleArray {

    public SortedDoubleArray() {
    }

    public SortedDoubleArray(int size) {
        super(size);
    }

    public SortedDoubleArray(double[] data) {
        super(data.length);
        for (int i = 0; i < data.length; i++) {
            addAll(data);
        }
    }

    /**
     * adds the eleemnt in a sorted way
     * @param d 
     */
    @Override
    public void add(double d) {
        int pos = Arrays.binarySearch(data, 0, index, d);
        if (pos < 0) {
            pos = -pos - 1;
        }

        ensureCapacity(size() + 1);
        int length = index - pos;
        if (length > 0) {
            System.arraycopy(data, pos, data, pos + 1, length);
        }
        index++;
        data[pos] = d;
    }

    @Override
    public void addAll(double[] a) {
        ensureCapacity(index + a.length);
        for (int i = 0; i < a.length; i++) {
            add(a[i]);
        }
    }

    /**
     * @param i
     * @param a 
     * @throws UnsupportedOperationException if the sorting will be broken
     */
    @Override
    public void set(int i, double a) {
        if (size() == 0) { // empty list, just add 
            if (i == 0) {
                add(a);
                return;
            } else {
                throw new IllegalArgumentException("index out of bounds");
            }
        }

        if (i == 0) {  // start of the list
            if (get(0) > a) {
                throw new IllegalArgumentException("a larger than first element");
            } else {
                ensureCapacity(index);
                System.arraycopy(data, 0, data, 1, index);
                data[0] = a;
                index++;
                return;
            }
        } else if (i == index) { // end of list
            if (get(index - 1) > a) {
                throw new IllegalArgumentException("a less than last element in array");
            } else {
                add(a);
            }
        } else { // somewhere between
            if (get(i - 1) < a && a <= get(i)) {
                ensureCapacity(index);
                System.arraycopy(data, i, data, i + 1, index);
                data[i] = a;
                index++;
                return;
            } else {
                throw new IllegalArgumentException("a breaks sorting");
            }
        }
        throw new IllegalStateException("should never reach here");
    }
}
