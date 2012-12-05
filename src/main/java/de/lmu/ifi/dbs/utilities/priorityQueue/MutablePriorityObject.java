package de.lmu.ifi.dbs.utilities.priorityQueue;

public interface MutablePriorityObject<T> extends PriorityObject<T> {

    public void setPriority(double newPriority);
}
