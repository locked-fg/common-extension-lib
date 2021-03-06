package de.lmu.ifi.dbs.utilities.priorityQueue;

import static de.lmu.ifi.dbs.utilities.priorityQueue.PriorityQueue.ASCENDING;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * A Queue which contains sorted key-value-pairs ordered by value. The values can be updated and the order will still
 * remain. Insertion of elements is O(log n)
 *
 * @param <T>
 */
public class UpdatablePriorityQueue<T extends MutablePriorityObject> {

    private final boolean DESCENDING = false;
    private final ArrayList<T> queue; // Heap-structure contains Objects
    private final TreeMap<Comparable<T>, Integer> direct; // Key-address-pair for fast
    private final boolean order; // order of objects in the heap

    /**
     * Standard constructor of the UpdatableQueue class. It creates a priority queue which is sorted with ascending or
     * descending priority.
     *
     * @param ascending
     */
    public UpdatablePriorityQueue(boolean ascending) {
        queue = new ArrayList<>();
        direct = new TreeMap<>();
        order = ascending;
    }

    /**
     * inserts an object to the queue at the appropriate position, or updates the priority and the position if the
     * object is already in the queue and it's new priority is higher than the old one.
     */
    public void insertIfBetter(T po) {
        int i = 0;
        if (direct.containsKey(po.getKey())) {
            i = direct.get(po.getKey());
            if (!hasHigherPriority(queue.get(i - 1).getPriority(), po.getPriority())) {
                return;
            } else {
                queue.set(i - 1, po);
            }
        } else {
            queue.add(po);
            i = queue.size();
        }
        sift_up(i);
    }

    /**
     * inserts an object to the queue at the appropriate position, or updates the priority (adding a value) and the
     * position if the object is already in the queue.
     *
     * @param po The priority object to be added
     */
    public void insertAdditive(T po) {
        int i = 0;
        if (direct.containsKey(po.getKey())) {
            i = direct.get(po.getKey());
            T x = queue.get(i - 1);
            double newPriority = x.getPriority() + po.getPriority();
            x.setPriority(newPriority);
            if (hasHigherPriority(x.getPriority(), newPriority)) {
                sift_up(i);
            } else {
                sift_down(i);
            }

        } else {
            queue.add(po);
            i = queue.size();
        }
        sift_up(i);
    }

    private double val(int key) {
        return queue.get(key).getPriority();
    }

    /**
     * Uppers a key in the heap
     */
    private void sift_up(int start) {
        int akt = start;
        int comp = akt / 2;
        T x = queue.get(akt - 1), y;
        if (order == ASCENDING) {
            while (comp > 0 && val(comp - 1) > x.getPriority()) {
                y = queue.get(comp - 1);
                queue.set(akt - 1, y);
                direct.put(y.getKey(), akt);
                akt = comp;
                comp = akt / 2;
            }
            queue.set(akt - 1, x);
            direct.put(x.getKey(), akt);
        } else {
            while (comp > 0 && val(comp - 1) < x.getPriority()) {
                y = queue.get(comp - 1);
                queue.set(akt - 1, y);
                direct.put(y.getKey(), akt);
                akt = comp;
                comp = akt / 2;
            }
            queue.set(akt - 1, x);
            direct.put(x.getKey(), akt);
        }
    }

    /**
     * Lowers a key in the heap
     */
    private void sift_down(int start) {
        int akt = start;
        int comp = 2 * akt;
        T x = queue.get(akt - 1), y;
        if (order == ASCENDING) {
            if ((comp < queue.size()) && (val(comp) < val(comp - 1))) {
                comp++;
            }
            while (comp <= queue.size() && val(comp - 1) < x.getPriority()) {
                y = queue.get(comp - 1);
                queue.set(akt - 1, y);
                direct.put(y.getKey(), akt);
                akt = comp;
                comp = 2 * akt;
                if (comp < queue.size() && val(comp) < val(comp - 1)) {
                    comp++;
                }
            }
            queue.set(akt - 1, x);
            direct.put(x.getKey(), akt);
        } else {
            if ((comp < queue.size()) && (val(comp) > val(comp - 1))) {
                comp++;
            }
            while (comp <= queue.size() && val(comp - 1) > x.getPriority()) {
                y = queue.get(comp - 1);
                queue.set(akt - 1, y);
                direct.put(y.getKey(), akt);
                akt = comp;
                comp = 2 * akt;
                if (comp < queue.size() && val(comp) > val(comp - 1)) {
                    comp++;
                }
            }
            queue.set(akt - 1, x);
            direct.put(x.getKey(), akt);
        }
    }

    /**
     * Test if an Object with a new Value should move up or down the heap
     */
    private boolean hasHigherPriority(double oldValue, double newValue) {
        if (order == ASCENDING) {
            if (newValue < oldValue) {
                return true;
            } else {
                return false;
            }
        } else {
            if (newValue > oldValue) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Returns the size of the priority queue.
     *
     * @return the number of elements in the priority queue.
     */
    public int size() {
        return queue.size();
    }

    /**
     * Returns the Value of the first object in the queue.
     */
    public double firstValue() {
        return queue.get(0).getPriority();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Removes the first object from the queue and returns that object to the caller.
     *
     * @return the first object in the queue.
     */
    public T removeFirst() {
        T po = queue.remove(0);
        direct.remove(po.getKey());
        if (queue.size() > 0) {
            queue.add(0, queue.remove(queue.size() - 1));
            sift_down(1);
        }
        return po;
    }

    @Override
    public UpdatablePriorityQueue<T> clone() {
        UpdatablePriorityQueue<T> result = new UpdatablePriorityQueue<>(order);
        for (int i = 0; i < queue.size(); i++) {
            result.insertIfBetter(queue.get(i));
        }
        return result;
    }

    public T contains(T name) {
        Integer i = direct.get(name);
        if (i == null) {
            return null;
        } else {
            return queue.get(i);
        }
    }

    public boolean getOrder() {
        return order;
    }
}
