package de.lmu.ifi.dbs.utilities;

/**
 * Class for overriding an object's toString method.
 * @author Franz
 */
public class ToStringFormatter<T> {

    /**
     * converts the given Object into a string.
     * @param o
     * @return
     */
    public String objectToString(T o) {
        return o.toString();
    }
}
