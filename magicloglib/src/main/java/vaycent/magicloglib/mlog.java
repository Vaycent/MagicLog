package vaycent.magicloglib;

import android.content.Context;
import android.os.Environment;

/**
 * Created by Vaycent on 2016/4/14.
 */
public class mlog {

    private static boolean errorShow = true;
    private static boolean warningShow = true;
    private static boolean informationShow = true;
    private static boolean debugShow = true;
    private static boolean verboseShow = true;
    private static boolean xmlShow = true;
    private static boolean jsonShow = true;
    public static String logFilePath = Environment.getExternalStorageDirectory().getPath() + "/MagicLog_File";
    public static String logFilterPriority = "v";
    public static String logFilterTag="";

    private static String[] stackTraceMessages;

    public mlog(Builder builder){
        this.errorShow=builder.errorShow;
        this.warningShow=builder.warningShow;
        this.informationShow=builder.informationShow;
        this.debugShow=builder.debugShow;
        this.verboseShow=builder.verboseShow;
        this.xmlShow=builder.xmlShow;
        this.jsonShow=builder.jsonShow;
        this.logFilePath=builder.logFilePath;
        this.logFilterPriority=builder.logFilterPriority;
        this.logFilterTag=builder.logFilterTag;
    }

    @Override
    public String toString(){
        return "MlogObj [error ="+errorShow+",warning="+warningShow+",information="+informationShow+
                ",debug="+debugShow+",verbose="+verboseShow+",xml="+xmlShow+",json="+jsonShow+
                ",logFilePath="+logFilePath
                +",logFilterPriority="+logFilterPriority+",logFilterTag="+logFilterTag+"]";
    }

    public static void StartWriteLog(Context context){
        try{
            LogFile.getInstance(context).start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void StopWriteLog(Context context){
        try{
            LogFile.getInstance(context).stop();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public static void v(String tag,String message){
        if(verboseShow){
            intStackTraceMessages();
            chooseLogType(TextLog.VERBOSE, stackTraceMessages,tag,message);
        }
    }
    public static void v(String message){
        if(verboseShow){
            intStackTraceMessages();
            chooseLogType(TextLog.VERBOSE, stackTraceMessages,"",message);
        }
    }

    public static void d(String tag,String message){
        if(debugShow){
            intStackTraceMessages();
            chooseLogType(TextLog.DEBUG,stackTraceMessages, tag,message);
        }
    }
    public static void d(String message){
        if(debugShow){
            intStackTraceMessages();
            chooseLogType(TextLog.DEBUG,stackTraceMessages, "",message);
        }
    }

    public static void i(String tag,String message){
        if(informationShow){
            intStackTraceMessages();
            chooseLogType(TextLog.INFORMATION,stackTraceMessages, tag,message);
        }
    }
    public static void i(String message){
        if(informationShow){
            intStackTraceMessages();
            chooseLogType(TextLog.INFORMATION,stackTraceMessages, "",message);
        }
    }

    public static void w(String tag,String message){
        if(warningShow){
            intStackTraceMessages();
            chooseLogType(TextLog.WARNING,stackTraceMessages, tag,message);
        }
    }
    public static void w(String message){
        if(warningShow){
            intStackTraceMessages();
            chooseLogType(TextLog.WARNING,stackTraceMessages,"",message);
        }
    }

    public static void e(String tag,String message){
        if(errorShow){
            intStackTraceMessages();
            chooseLogType(TextLog.ERROR,stackTraceMessages,tag,message);
        }
    }
    public static void e(String message){
        if(errorShow){
            intStackTraceMessages();
            chooseLogType(TextLog.ERROR,stackTraceMessages,"",message);
        }
    }

    public static void xml(String tag,String xml){
        if(xmlShow){
            intStackTraceMessages();
            chooseLogType(XmlLog.XML,stackTraceMessages,tag,xml);
        }
    }
    public static void xml(String xml){
        if(xmlShow){
            intStackTraceMessages();
            chooseLogType(XmlLog.XML,stackTraceMessages,"",xml);
        }
    }

    public static void json(String tag,String json){
        if(jsonShow){
            intStackTraceMessages();
            chooseLogType(JSonLog.JSON,stackTraceMessages,tag,json);
        }
    }
    public static void json(String json){
        if(jsonShow){
            intStackTraceMessages();
            chooseLogType(JSonLog.JSON,stackTraceMessages,"",json);
        }
    }


    private static void intStackTraceMessages(){
        stackTraceMessages = StackTraceMessages.getTagInfo();
    }

    private static void chooseLogType(int logType,String[] stackTraceMessages,String tag, String message){
        switch(logType){
            case TextLog.VERBOSE:
            case TextLog.DEBUG:
            case TextLog.INFORMATION:
            case TextLog.WARNING:
            case TextLog.ERROR:
                TextLog.initTextLog(logType, stackTraceMessages, tag, message);
                break;
            case XmlLog.XML:
                XmlLog.initXmlLog(stackTraceMessages, tag, message);
                break;
            case JSonLog.JSON:
                JSonLog.initJSonLog(stackTraceMessages,tag, message);
                break;

        }
    }






    public static class Builder{
        private boolean errorShow;
        private boolean warningShow;
        private boolean informationShow;
        private boolean debugShow;
        private boolean verboseShow;
        private boolean xmlShow;
        private boolean jsonShow;

        private String logFilePath;
        private String logFilterPriority;
        private String logFilterTag;

        public Builder(){
        }

        public Builder error(boolean error){
            this.errorShow=error;
            return this;
        }
        public Builder warning(boolean warning){
            this.warningShow=warning;
            return this;
        }
        public Builder information(boolean information){
            this.informationShow=information;
            return this;
        }
        public Builder debug(boolean debug){
            this.debugShow=debug;
            return this;
        }
        public Builder verbose(boolean verbose){
            this.verboseShow=verbose;
            return this;
        }
        public Builder xml(boolean xml){
            this.xmlShow=xml;
            return this;
        }
        public Builder json(boolean json){
            this.jsonShow=json;
            return this;
        }

        public Builder logFilePath(String logFilePath){
            this.logFilePath=logFilePath;
            return this;
        }
        public Builder logFilterPriority(String logFilterPriority){
            this.logFilterPriority=logFilterPriority;
            return this;
        }
        public Builder logFilterTag(String logFilterTag){
            this.logFilterTag=logFilterTag;
            return this;
        }

        public mlog build(){
            return new mlog(this);
        }
    }

}

