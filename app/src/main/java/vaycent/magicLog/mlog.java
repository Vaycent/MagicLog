package vaycent.magicLog;

import vaycent.customerApp.UseHelper;

/**
 * Created by Vaycent on 2016/4/14.
 */
public class mlog {
    private final static int PRINT_LOG_LEVEL= UseHelper.PRINT_LOG_LEVEL;

    private static String[] stackTraceMessages;

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

