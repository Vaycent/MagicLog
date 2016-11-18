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





}

