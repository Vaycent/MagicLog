package vaycent.magicLog;

import android.util.Log;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Created by Vaycent on 2016/4/15.
 */
public class XmlLog {
    public final static int XML=-1;




    public static void initXmlLog(String[] stackTraceMessages,String xml){
        initTableLog(stackTraceMessages,xml);
    }

    private static void initTableLog(String[] stackTraceMessages,String xml){
        LogBaseUtil.printTable(LogBaseUtil.TABLE_TOP, stackTraceMessages, xml);

        printXmlText(stackTraceMessages, xml);

        LogBaseUtil.printTable(LogBaseUtil.TABLE_BOTTOM, stackTraceMessages, xml);
    }

    private static void printXmlText(String[] stackTraceMessages,String xmlContext){
        String outputXml="";
        String tagMethod=stackTraceMessages[0];
        String messageHelper=stackTraceMessages[1];

        outputXml=formatXmlHeader(outputXml, messageHelper);

        outputXml=formatXmlBody(outputXml,xmlContext);


        Log.v(tagMethod, outputXml);
    }

    private static String formatXmlHeader(String formatXml,String inputMessageHelper){
        String outputFormat=formatXml+"║ "+inputMessageHelper + "\n";
        return outputFormat;
    }


    private static String formatXmlBody(String formatXml,String xmlContext) {
        try {
            String outputFormat=formatXml;
            Source xmlContextSource = new StreamSource(new StringReader(xmlContext));
            StreamResult outputStream = new StreamResult(new StringWriter());

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlContextSource, outputStream);

            String InsertFrame=outputStream.getWriter().toString().replaceFirst(">", ">\n");
            InsertFrame=insertVerticalFrame(InsertFrame);

            outputFormat=outputFormat+InsertFrame;

            return outputFormat;
        } catch (Exception e) {
            e.printStackTrace();
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
