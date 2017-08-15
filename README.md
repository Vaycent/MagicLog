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
    compile 'com.github.Vaycent:MagicLog:2.1'
}

```
Setup mlog in your project to change its feature, for example, you can use this code in Application, it will show in logcat if this type is true.

```java
new mlog.Builder()
                .error(true)
                .warning(true)
                .information(false)
                .debug(true)
                .verbose(true)
                .xml(true)
                .json(true)
                .printStackTrace(true)
                .build();

```




Debug the log in these function instead of Logcat

```java
mlog.v()
mlog.d()
mlog.i()
mlog.w()
mlog.e()
mlog.xml()
mlog.json()
mlog.printStackTrace();
```




## LICENSE
The source code is licensed under GPL v3. License is available [here](./LICENSE.txt)
