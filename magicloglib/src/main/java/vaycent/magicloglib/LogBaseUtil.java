package vaycent.magicloglib;

import android.os.Environment;
import android.util.Log;

/**
 * Created by Vaycent on 2016/4/15.
 */
public class LogBaseUtil {
    public final static int TABLE_TOP=0;
    public final static int TABLE_BOTTOM=1;

    public static boolean isNull(Object obj){
        if(obj==null)
            return true;
        return false;
    }

    public static boolean hasStorage(){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable())
            return true;
        return  false;
    }

    public static void printTable(int tableFram,String[] stackTraceMessages,String tag,String xml) {
        String[] tableElements = initTable(tableFram, stackTraceMessages, tag,xml);

        String tagStr;
        if(null!=tag&&!"".equals(tag))
            tagStr = tag;
        else
            tagStr = tableElements[0];

        Log.v(tagStr, tableElements[1]);
    }

    private static String[] initTable(int tableFram,String[] stackTraceMessages,String tag,String xml) {
        String tagMethod=stackTraceMessages[0];
        String framLine="";

        if(tableFram==TABLE_TOP){
            framLine="╔═════════════════════════════";
        }else if(tableFram==TABLE_BOTTOM){
            framLine="╚═════════════════════════════";
        }

        String[] tableElements=new String[]{tagMethod, framLine};
        return tableElements;
    }





}
