package vaycent.magicLog;

import android.os.Environment;

import java.io.File;

/**
 * Created by Vaycent on 2016/4/16.
 */
public class customerControlHelper {
    public static int PRINT_LOG_LEVEL=-10;
    public static File LOG_FILE_PATH=new File(Environment.getExternalStorageDirectory().getPath() + "/MagicLog_File");
    public static String LOGFILE_FILTER_PRIORITY="v"; //v,d,i,w,e,s
    public static String LOGFILE_FILTER_TAG=""; // ""for do not filter the tag


    public static void SetLogLevel(int level){
        PRINT_LOG_LEVEL=level;
    }

    public static void SetLogPath(File file){
        LOG_FILE_PATH=file;
    }

    public static void SetFilterPriority(String priority){
        LOGFILE_FILTER_PRIORITY=priority;
    }

    public static void SetFilterTag(String tag){
        LOGFILE_FILTER_TAG=tag;
    }



}

