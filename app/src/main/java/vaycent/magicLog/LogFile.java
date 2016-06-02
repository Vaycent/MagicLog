package vaycent.magicLog;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import vaycent.customerControl.customerControlHelper;

/**
 * Created by Vaycent on 2016/4/19.
 *
 * Warnning!!!!
 * Please! Do not print any logs in this class
 */

public class LogFile {

    private static LogFile INSTANCE = null;
    private LogThread mLogThread = null;

    private int mPId;
    private File logFileFolder= customerControlHelper.LOG_FILE_PATH;
    private File logFile=new File(logFileFolder +"/"+  getFileName() + ".log");



    public static LogFile getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LogFile(context);
        }
        return INSTANCE;
    }

    private LogFile(Context context) {
        mPId = android.os.Process.myPid();

        logFileFolder=checkFloderPath(context,logFileFolder);

        initPath(context, logFileFolder,logFile);


    }



    private File checkFloderPath(Context context,File path){
        boolean mHasStorage=LogBaseUtil.hasStorage();

        boolean mIsEnviroment=path.toString().contains(Environment.MEDIA_MOUNTED)?true:false;

        String lastPath=path.toString().substring(path.toString().lastIndexOf("/") + 1);

        if(!mHasStorage&&mIsEnviroment){ //!mHasStorage&&mIsEnviroment
            path = new File(context.getFilesDir().getAbsolutePath()+"/"+lastPath);
            logFile=new File(path +"/"+  getFileName() + ".log");
        }


        return path;
    }


    public void start(){
        startLogFileThread();
    }

    public void stop(){
        stopLogFileThread();
    }


    private void startLogFileThread() {

        if (mLogThread == null)
            mLogThread = new LogThread(String.valueOf(mPId));

        mLogThread.start();
    }

    private void stopLogFileThread() {
        if (mLogThread != null) {
            mLogThread.stopLogs();
            mLogThread = null;
        }
    }



    private void initPath(Context context,File folderPath,File filePath) {
        if (!folderPath.exists()){
            try {
                folderPath.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(!filePath.exists()){
            try {

                filePath.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    private class LogThread extends Thread {
        private String mPID;
        private String logCommand = null;

        private boolean printLogIsRunning = true;

        private LogThread(String pid) {
            mPID=pid;
            logCommand=getLogcatCommand(mPID);
        }

        public void stopLogs() {
            printLogIsRunning = false;
        }

        @Override
        public void run() {
            //Reduce More Resources
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

            initNewAppDivideLine(logFile);

            printLog(mPID,logCommand,printLogIsRunning);
        }

    }




    private  String getFileName() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date(System.currentTimeMillis()));
        return date;
    }

    private  String getDateEN() {
        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss"); //yyyy-MM-dd HH:mm:ss
        String date1 = format1.format(new Date(System.currentTimeMillis()));
        return date1;
    }

    private  void initNewAppDivideLine(File logFile){
        try {
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.newLine();
            buf.newLine();
            buf.newLine();
            buf.append("******************** I Am The Divide Line (Means reopen the app) ********************");
            buf.newLine();
            buf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private  String getLogcatCommand(String mPID){
        //Test by Vaycent on 2016/04/20

        //filter priority: *:v , *:d , *:w , *:e , *:f , *:s
        //filter pid: grep"(pid)"
        //filter tag: -s tagName
        //use | to divide the parameter

        String mCommand="logcat *:";
        mCommand+= customerControlHelper.LOGFILE_FILTER_PRIORITY;
        mCommand+=" | grep \"("+mPID+")\"";
        if(!customerControlHelper.LOGFILE_FILTER_TAG.equals(""))
            mCommand+=" -s "+ customerControlHelper.LOGFILE_FILTER_TAG;

//        System.out.println("mCommand:"+mCommand);

        return mCommand;
    }


    private void printLog(String mPID,String logCommand,boolean printLogIsRunning){
        Process logcatProc=null;
        BufferedReader mReader = null;

        try{
            logcatProc = Runtime.getRuntime().exec(logCommand);
            mReader = new BufferedReader(new InputStreamReader(
                    logcatProc.getInputStream()), 1024);

            loopWriteLogToFile(mReader,mPID,printLogIsRunning);

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (logcatProc != null) {
                logcatProc.destroy();
                logcatProc = null;
            }
            if (mReader != null) {
                try {
                    mReader.close();
                    mReader = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void loopWriteLogToFile(BufferedReader mReader,String mPID,boolean printLogIsRunning){
        String line = null;

        try{
            while (printLogIsRunning && ((line = mReader.readLine()) != null)) {
                if (!printLogIsRunning) {
                    break;
                }
                if (line.length() == 0) {
                    continue;
                }
                if ( line.contains(mPID)) {
                    line=line.replaceAll(mPID,"");
                    String text=getDateEN() + "  " + line;

                    BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
                    buf.append(text);
                    buf.newLine();
                    buf.close();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }



}
