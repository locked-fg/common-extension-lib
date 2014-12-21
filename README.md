# common-extension-lib

Just some classes that I use very frequently in most of my projects

```xml

<dependency>
  <groupId>de.lmu.ifi.dbs</groupId>
  <artifactId>commons-extension-lib</artifactId>
  <version>2.5.0</version>
</dependency>
```

# History
- 2.5.0: 
    - added weighted stdev
    - raised dependency to Java 8
- 2.4.0:
    - fixed several possible issues marked by findbugs
    - removed ExtensionFilter (use org.apache.commons.io.filefilter.SuffixFileFilter)
- 2.3.0:
    - added div by zero checks
- 2.2.0:
    - extended PropertyContainer
- 2.1.0: 
    - added max dist
    - added getDouble to PropertyContainer
    - removed de.lmu.ifi.dbs.utilities.formatter (Logging formatter)
    - removed de.lmu.ifi.dbs.utilities.primitiveArrays
- 2.0.0:
    - removed deprecated methods
    - added Math2 methods
    - introduced some more packages
    - enhanced the count map
    - added a multi count map
- 1.2.0: 
    - Improved CountMap (supports double values)
- 1.1.0: 
    - Java 6 Compatibility
- 1.0.0: 
    - maven release