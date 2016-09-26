package vaycent.magicLog;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Vaycent on 2016/4/15.
 */
public class JSonLog {
    public final static int JSON=-2;

    /**
     * It is used for json pretty print
     */
    private static final int JSON_INDENT = 2;

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

    private static String formatJSonBody(String formatXml,String xmlContext) {
        String outputFormat=formatXml;
        String InsertFrame="";
        try {
            xmlContext = xmlContext.trim();
            if (xmlContext.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(xmlContext);
                InsertFrame = jsonObject.toString(JSON_INDENT);
            } else if (xmlContext.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(xmlContext);
                InsertFrame= jsonArray.toString(JSON_INDENT);
            }

            InsertFrame=insertVerticalFrame(InsertFrame);
            outputFormat=outputFormat+InsertFrame;
            return outputFormat;
        } catch (JSONException e) {
            return formatXml;
        }
    }



    private static String insertVerticalFrame(String inputXml){
        String outputXml="";
        String[] lines = inputXml.split(System.getProperty("line.separator"));
        for(int i=0;i<lines.length;i++){
            lines[i]="║ "+lines[i]+"\n";
            outputXml+=lines[i];
        }

        return outputXml;
    }
}
