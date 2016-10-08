# MagicLog

This is the logcat tool for Android in debug mode


## How to use

First, import this dependence

```gradle
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}

dependencies {
    compile 'com.github.Vaycent:MagicLog:1.2.0'
}

```

Debug the log in these function instead of Logcat

```java
mlog.v
mlog.d
mlog.i
mlog.w
mlog.e
mlog.xml
mlog.json
```

You can use these function to change the MagicLog feature

```java

	/**
     * To choose the log level to print out in logcat <p>
     * level: v,d,i,w,e,xml,json, from 4 to -2 <br>
     * @param level It will print out the log which above this level
     */
public void setPrintLogLevel(int level)

    /**
     * Setup the log file in this path <p>
     * Initalise an empty file if no this file in the system <br>
     * @param path The log file path
     */
public void setLogFilePath(String path)

    /**
     * To choose the log level to show in log file <p>
     * You can choose v,d,i,w,e, and "" means all <br>
     * @param level String to choose---v,d,i,w,e,""
     */
public void setLogfileFilterPriority(String level)

    /**
     * To choose the logcat tag to show in log file <p>
     * @param tag Tag filter
     */
public void setLogfileFilterTag(String tag)

```



## LICENSE
The source code is licensed under GPL v3. License is available [here](./LICENSE.txt)