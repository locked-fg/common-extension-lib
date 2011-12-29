package de.lmu.ifi.dbs.utilities.primitiveArrays;

import java.util.Arrays;
import java.util.List;

/**
 * Wrapper for double arrays (double[]) so that the array size expands automatically 
 * as it is known from ArrayList.
 * In contrast to ArrayList, this data structure does no autoboxing.
 * 
 * @author graf
 * @Todo implement List interface?
 */
public class DoubleArray {

    protected double[] data;
    protected int index = 0;

    public DoubleArray() {
        this(10);
    }

    public DoubleArray(int size) {
        this.data = new double[size];
    }

    public DoubleArray(double[] data) {
        this.data = data.clone();
        index = this.data.length - 1;
    }

    /**
     * Returns the value at index i. Check for out of bounds.
     * 
     * @param i index
     * @see #getFast(int) for get without checks
     * @throws ArrayIndexOutOfBoundsException if i is not in [0, size()-1]
     */
    public double get(int i) {
        if (i < 0 || i >= index) {
            throw new IndexOutOfBoundsException("index " + i
                    + " out of bounds [0," + (index - 1) + "]");
        }
        return getFast(i);
    }

    /**
     * Accesses the data array without bounds check. 
     * if i is less than 0 an ArrayIndexOutOufBounds exception will occur. If
     * i is greater than the filled size (#size()), then the returned value 
     * is 0.
     * 
     * @param i
     * @return value at index i
     * @throws ArrayIndexOutOfBoundsException if i is less than 0
     */
    public double getFast(int i) {
        return data[i];
    }

    /**
     * add a value at the index and replaces the element at index i
     * 
     * @param i
     * @param a
     */
    public void set(int i, double a) {
        if (i < 0 || i >= index) {
            throw new IndexOutOfBoundsException("index " + i
                    + " out of bounds [0," + (index - 1) + "]");
        }
        data[i] = a;
    }

    /**
     * add a value at the end of the list
     * 
     * @param a
     */
    public void add(double a) {
        ensureCapacity(index + 1);
        data[index++] = a;
    }

    /**
     * adds the set of values at the end of the list
     * 
     * @param a
     */
    public void addAll(double[] a) {
        int numNew = a.length;
        ensureCapacity(index + numNew);
        System.arraycopy(a, 0, data, index, numNew);
        index += numNew;
    }

    /**
     * Adds a list of {@link Number}s
     * 
     * @param numberList 
     */
    public void addAll(List<? extends Number> numberList) {
        ensureCapacity(index + numberList.size());
        for (Number aNumber : numberList) {
            add(aNumber.doubleValue());
        }
    }

    /**
     * enlarges the array to the given capacity
     * 
     * @param minCapacity
     */
    protected void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 3) / 2 + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            // minCapacity is usually close to size, so this is a win:
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    /**
     * trims the data array to the size of the used data
     */
    public void trimToSize() {
        if (index < data.length) {
            data = Arrays.copyOf(data, index);
        }
    }

    /**
     * @return the amount of data being stored
     */
    public int size() {
        return index;
    }

    /**
     * Returns a reference(!) to the data array
     * @return 
     */
    public double[] getData() {
        return data;
    }
}
