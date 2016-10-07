package vaycent.magicLog;

import java.io.File;

/**
 * Created by Vaycent on 2016/4/14.
 */
public class mlog {
    private static int PRINT_LOG_LEVEL= customerControlHelper.PRINT_LOG_LEVEL;

    private static String[] stackTraceMessages;


    /**
     * To choose the log level to print out in logcat <p>
     * level: v,d,i,w,e,xml,json, from 4 to -2 <br>
     * @param level It will print out the log which above this level
     */
    public void setPrintLogLevel(int level){
        customerControlHelper.PRINT_LOG_LEVEL=level;
    }

    /**
     * Setup the log file in this path <p>
     * Initalise an empty file if no this file in the system <br>
     * @param path The log file path
     */
    public void setLogFilePath(String path){
        customerControlHelper.LOG_FILE_PATH=new File(path);
    }

    /**
     * To choose the log level to show in log file <p>
     * You can choose v,d,i,w,e, and "" means all <br>
     * @param level String to choose---v,d,i,w,e,""
     */
    public void setLogfileFilterPriority(String level){
        customerControlHelper.LOGFILE_FILTER_PRIORITY= level;
    }

    /**
     * To choose the logcat tag to show in log file <p>
     * @param tag Tag filter
     */
    public void setLogfileFilterTag(String tag){
        customerControlHelper.LOGFILE_FILTER_TAG= tag;
    }



    public static void v(String message){
        intStackTraceMessages();
        chooseLogType(TextLog.VERBOSE, stackTraceMessages,message);
    }

    public static void d(String message){
        intStackTraceMessages();
        chooseLogType(TextLog.DEBUG,stackTraceMessages, message);
    }

    public static void i(String message){
        intStackTraceMessages();
        chooseLogType(TextLog.INFORMATION,stackTraceMessages, message);
    }

    public static void w(String message){
        intStackTraceMessages();
        chooseLogType(TextLog.WARNING,stackTraceMessages, message);
    }

    public static void e(String message){
        intStackTraceMessages();
        chooseLogType(TextLog.ERROR,stackTraceMessages,message);
    }

    public static void xml(String xml){
        intStackTraceMessages();
        chooseLogType(XmlLog.XML,stackTraceMessages,xml);
    }

    public static void json(String json){
        intStackTraceMessages();
        chooseLogType(JSonLog.JSON,stackTraceMessages,json);
    }
    


    private static void intStackTraceMessages(){
        stackTraceMessages = StackTraceMessages.getTagInfo();
    }

    private static void chooseLogType(int logType,String[] stackTraceMessages,String message){
        if(logType<PRINT_LOG_LEVEL){
            return;
        }

        switch(logType){
            case TextLog.VERBOSE:
            case TextLog.DEBUG:
            case TextLog.INFORMATION:
            case TextLog.WARNING:
            case TextLog.ERROR:
                TextLog.initTextLog(logType, stackTraceMessages, message);
                break;
            case XmlLog.XML:
                XmlLog.initXmlLog(stackTraceMessages, message);
                break;
            case JSonLog.JSON:
                JSonLog.initJSonLog(stackTraceMessages, message);
                break;

        }
    }

}

