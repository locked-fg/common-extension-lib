package de.lmu.ifi.dbs.utilities.primitiveArrays;

import java.util.Arrays;
import java.util.Random;

/**
 * An extension of SortedDoubleArray that can be limited in size.
 * This data structure is useful in cases where for example the k'th smallest 
 * distance has to be determined.
 * 
 * @author graf
 */
public class BoundedSortedDoubleArray extends DoubleArray {

    private int maxLength = Integer.MAX_VALUE;

    public BoundedSortedDoubleArray() {
    }

    public BoundedSortedDoubleArray(int size) {
        super(size);
    }

    public BoundedSortedDoubleArray(int size, int maxLength) {
        super(size);
        this.setMaxLength(maxLength);
    }

    public BoundedSortedDoubleArray(int maxLength, double[] data) {
        this(data.length, maxLength);
        addAll(data);
    }

    public BoundedSortedDoubleArray(double[] data) {
        super(data.length);
        addAll(data);
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize must be > 0 but was " + maxSize);
        }
        if (maxSize < index) { // update index pointer
            index = maxSize;
        }
        this.maxLength = maxSize;
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
        // d would be inserted AFTER the largest value in an already filled list
        if (pos >= maxLength) {
            return;
        }

        int length = index - pos; // length of the data to move one element further
        if (size() < maxLength) { // max length not yet reached
            ensureCapacity(size() + 1);
            index++;
        } else { // max length already reached
            length--;// skip last element
        }

        if (length > 0) {
            System.arraycopy(data, pos, data, pos + 1, length);
        }
        data[pos] = d;
    }

    @Override
    public void addAll(double[] a) {
        ensureCapacity(index + a.length);
        for (int i = 0; i < a.length; i++) {
            add(a[i]);
        }
    }
}
