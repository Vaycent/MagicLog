package vaycent.magicLog;

import android.os.Environment;

import java.io.File;

/**
 * Created by Vaycent on 2016/4/16.
 */
public class customerControlHelper {
    public static int PRINT_LOG_LEVEL=-10;
    public static File LOG_FILE_PATH=new File(Environment.getExternalStorageDirectory().getPath() + "/TestLog");
    public static String LOGFILE_FILTER_PRIORITY="i"; //v,d,i,w,e,s
    public static String LOGFILE_FILTER_TAG="Use_Here_02"; // ""for do not filter the tag


    public static void setPrintLogLevel(int level){
        PRINT_LOG_LEVEL=level;
    }

    public static void setLogFilePath(String path){
        LOG_FILE_PATH=new File(path);
    }

    public static void setLogfileFilterPriority(String priority){
        LOGFILE_FILTER_PRIORITY= priority;
    }

    public static void setLogfileFilterTag(String tag){
        LOGFILE_FILTER_TAG= tag;
    }



}

