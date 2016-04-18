package vaycent.magicLog;

import android.util.Log;

/**
 * Created by Vaycent on 2016/4/15.
 */
public class LogUtil {
    public final static int TABLE_TOP=0;
    public final static int TABLE_BOTTOM=1;


    public static void printTable(int tableFram,String[] stackTraceMessages,String xml) {
        String[] tableElements=initTable(tableFram,stackTraceMessages, xml);

        Log.v(tableElements[0], tableElements[1]);
    }

    private static String[] initTable(int tableFram,String[] stackTraceMessages,String xml) {
        String tagMethod=stackTraceMessages[0];
        String framLine="";

        if(tableFram==TABLE_TOP){
            framLine="╔═════════════════════════════════════════════════════════════════════════════════════════════════";
        }else if(tableFram==TABLE_BOTTOM){
            framLine="╚═════════════════════════════════════════════════════════════════════════════════════════════════";
        }

        String[] tableElements=new String[]{tagMethod, framLine};
        return tableElements;
    }



}
