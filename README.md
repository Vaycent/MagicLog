# MagicLog

This is the logcat tool for Android in debug mode

## How to use
```gradle
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}

dependencies {
    compile 'com.github.Vaycent:MagicLog:1.1.7'
}

```
First, import this dependence

```java
setPrintLogLevel(int level) 
setLogFilePath(String path)
setLogfileFilterPriority(String priority)
setLogfileFilterTag(String tag)


mlog.v
mlog.d
mlog.i
mlog.w
mlog.e
mlog.xml
mlog.json
```



## LICENSE
The source code is licensed under GPL v3. License is available [here](./LICENSE.txt)