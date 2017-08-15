package vaycent.magicloglib;

import android.util.Log;

/**
 * Created by Vaycent on 2016/4/14.
 */
public class TextLog {
    public final static int VERBOSE=0;
    public final static int DEBUG=1;
    public final static int INFORMATION=2;
    public final static int WARNING=3;
    public final static int ERROR=4;
    private final static int MAXLEN = 5000;

    public static void initTextLog(int logType,String[] stackTraceMessages,String tag, String msg){

        String tagMethod;
        if(null!=tag&&!"".equals(tag))
            tagMethod = tag;
        else
            tagMethod=stackTraceMessages[0];

        if(MAXLEN<msg.length()){
            initTextLog(logType, stackTraceMessages,tag, msg.substring(0,MAXLEN));
            initTextLog(logType, stackTraceMessages,tag, msg.substring(MAXLEN));
        }else{
            String message=stackTraceMessages[1]+", "+msg;
            textSystemLog(logType, tagMethod, message);
        }
    }

    private static void textSystemLog(int logType,String tagInfo,String message){
        switch (logType){
            case VERBOSE:
                Log.v(tagInfo, message);
                break;
            case DEBUG:
                Log.d(tagInfo, message);
                break;
            case INFORMATION:
                Log.i(tagInfo, message);
                break;
            case WARNING:
                Log.w(tagInfo, message);
                break;
            case ERROR:
                Log.e(tagInfo, message);
                break;
        }
    }
}
