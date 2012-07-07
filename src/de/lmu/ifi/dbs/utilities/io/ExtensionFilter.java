package de.lmu.ifi.dbs.utilities.io;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Filename filter that can filter multiple file extensions
 */
public class ExtensionFilter implements FilenameFilter {

    private final String[] extension;

    public ExtensionFilter(String extension) {
        this.extension = new String[]{extension};
    }

    public ExtensionFilter(String... extensions) {
        this.extension = extensions;
    }

    @Override
    public boolean accept(File dir, String name) {
        for (String ext : extension) {
            if (name.endsWith(ext)) {
                return true;
            }
        }
        return false;
    }
}
