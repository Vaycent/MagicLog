package vaycent.customerControl;

import android.os.Environment;

import java.io.File;

/**
 * Created by Vaycent on 2016/4/16.
 */
public class CustomerControlHelper {
    public final static int PRINT_LOG_LEVEL=-10;

    public final static File LOG_FILE_PATH=new File(Environment.getExternalStorageDirectory().getPath() + "/TestLog");

}

