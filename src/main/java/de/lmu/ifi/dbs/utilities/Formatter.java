package de.lmu.ifi.dbs.utilities;

/**
 * Class for overriding an object's toString method.
 * 
 * @author Franz
 * @param <T>  generic type
 */
public class Formatter<T> {

    /**
     * converts the given Object into a string.
     * @param o obect to format
     * @return toString
     */
    public String objectToString(T o) {
        return o.toString();
    }
}
