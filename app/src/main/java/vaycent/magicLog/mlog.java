package vaycent.magicLog;

import java.io.File;

/**
 * Created by Vaycent on 2016/4/14.
 */
public class mlog {
    private final static int PRINT_LOG_LEVEL= customerControlHelper.PRINT_LOG_LEVEL;

    private static String[] stackTraceMessages;



    public static void setPrintLogLevel(int level){
        customerControlHelper.PRINT_LOG_LEVEL=level;
    }

    public static void setLogFilePath(String path){
        customerControlHelper.LOG_FILE_PATH=new File(path);
    }

    public static void setLogfileFilterPriority(String priority){
        customerControlHelper.LOGFILE_FILTER_PRIORITY= priority;
    }

    public static void setLogfileFilterTag(String tag){
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


    //TODO We can overLoad more xml function here
    public static void xml(String xml){
        intStackTraceMessages();
        chooseLogType(XmlLog.XML,stackTraceMessages,xml);
    }

    //TODO We can overLoad more json function here
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

