package de.lmu.ifi.dbs.utilities.io;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Filename filter that can filter multiple file extensions.
 *
 * The filter is case insensitive.
 *
 * @Deprecated since 1.0.1, use org.apache.commons.io.filefilter.SuffixFileFilter instead
 */
@Deprecated
public class ExtensionFilter implements FilenameFilter {

    private final String[] extension;

    public ExtensionFilter(String extension) {
        this(new String[]{extension});
    }

    public ExtensionFilter(String... extensions) {
        this.extension = new String[extensions.length];
        for (int i = 0; i < extensions.length; i++) {
            this.extension[i] = extensions[i].trim().toLowerCase();
        }
    }

    @Override
    public boolean accept(File dir, String name) {
        name = name.toLowerCase();
        for (String ext : extension) {
            if (name.endsWith(ext)) {
                return true;
            }
        }
        return false;
    }
}
