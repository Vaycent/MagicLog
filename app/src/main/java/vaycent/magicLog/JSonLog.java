package vaycent.magicLog;

import android.util.Log;

/**
 * Created by Vaycent on 2016/4/15.
 */
public class JSonLog {
    public final static int JSON=-2;

    public void JSonLog(){

    }

    public static void initJSonLog(String[] stackTraceMessages,String xml){
        initTableLog(stackTraceMessages,xml);
    }

    private static void initTableLog(String[] stackTraceMessages,String xml){
        LogBaseUtil.printTable(LogBaseUtil.TABLE_TOP, stackTraceMessages, xml);

        printJSonText(stackTraceMessages, xml);

        LogBaseUtil.printTable(LogBaseUtil.TABLE_BOTTOM, stackTraceMessages, xml);
    }

    private static void printJSonText(String[] stackTraceMessages,String xmlContext){
        String outputXml="";
        String tagMethod=stackTraceMessages[0];
        String messageHelper=stackTraceMessages[1];

        outputXml=formatJSonHeader(outputXml, messageHelper);

        outputXml=formatJSonBody(outputXml,xmlContext);


        Log.v(tagMethod, outputXml);
    }

    private static String formatJSonHeader(String formatXml,String inputMessageHelper){
        String outputFormat=formatXml+"║ "+inputMessageHelper + "\n";
        return outputFormat;
    }

    //TODO Have no idea, how to write now --By Vaycnet
    private static String formatJSonBody(String formatXml,String xmlContext) {
        try {
            String outputFormat=formatXml;

            return outputFormat;
        } catch (Exception e) {
            e.printStackTrace();
            return formatXml;
        }
    }
}
