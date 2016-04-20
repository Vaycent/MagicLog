package vaycent.customerControl;

import android.os.Environment;

import java.io.File;

/**
 * Created by Vaycent on 2016/4/16.
 */
public class CustomerControlHelper {
    public final static int PRINT_LOG_LEVEL=-10;

    public final static File LOG_FILE_PATH=new File(Environment.getExternalStorageDirectory().getPath() + "/TestLog");
    public final static String LOGFILE_FILTER_PRIORITY="i"; //v,d,i,w,e,s
    public final static String LOGFILE_FILTER_TAG="Use_Here_02";



}

