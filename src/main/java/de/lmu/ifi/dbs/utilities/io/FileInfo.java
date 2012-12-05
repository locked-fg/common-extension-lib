package de.lmu.ifi.dbs.utilities.io;

import java.io.File;

/**
 * Class for printing common debug information which are used in case of
 * IOExceptions.
 *
 * 
 * @author graf
 */
public class FileInfo {

    public static String getInfo(File f) {
        String nl = System.getProperty("line.separator");

        StringBuilder sb = new StringBuilder(150);
        sb.append(nl);
        sb.append("File:     '").append(f).append("'").append(nl);
        sb.append("isNull:    ").append(f == null).append(nl);
        if (f != null) {
            sb.append("isFile:    ").append(f.isFile()).append(nl);
            sb.append("isDir:     ").append(f.isDirectory()).append(nl);
            sb.append("exists:    ").append(f.exists()).append(nl);
            sb.append("readable:  ").append(f.canRead()).append(nl);
            sb.append("writeable: ").append(f.canWrite()).append(nl);
            sb.append("exec:      ").append(f.canExecute()).append(nl);
            sb.append("size:      ").append(f.length()).append(nl);
        }
        return sb.toString();
    }
}
