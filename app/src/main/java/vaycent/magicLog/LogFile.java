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

import vaycent.customerControl.CustomerControlHelper;

/**
 * Created by Vaycent on 2016/4/19.
 */

public class LogFile {

    private static LogFile INSTANCE = null;
    private LogThread mLogThread = null;

    private int mPId;
    private File logFileFolder= CustomerControlHelper.LOG_FILE_PATH;
    private File logFile=new File(logFileFolder +  getFileName() + ".log");



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

        if(!mHasStorage&&mIsEnviroment){
            /* TODO use to change the logfile save path when it has no storage --By Vaycent*/
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

        private boolean mRunning = true;

        private Process logcatProc;
        private BufferedReader mReader = null;

        String cmds = null;


        private LogThread(String pid) {

            mPID=pid;

            /**
             *
             * 日志等级：*:v , *:d , *:w , *:e , *:f , *:s
             *
             * 显示当前mPID程序的 E和W等级的日志.
             *
             * */

            // cmds = "logcat *:e *:w | grep \"(" + mPID + ")\"";
            // cmds = "logcat  | grep \"(" + mPID + ")\"";//打印所有日志信息
            // cmds = "logcat -s way";//打印标签过滤信息
//            cmds = "logcat *:e *:i | grep \"(" + mPID + ")\"";

            cmds = "logcat *:v | grep \"(" + mPId + ")\"" ;

        }

        public void stopLogs() {
            mRunning = false;
        }

        @Override
        public void run() {

            try {
                logcatProc = Runtime.getRuntime().exec(cmds);
                mReader = new BufferedReader(new InputStreamReader(
                        logcatProc.getInputStream()), 1024);


                String line = null;

                while (mRunning && ((line = mReader.readLine()) != null)) {
                    if (!mRunning) {
                        break;
                    }
                    if (line.length() == 0) {
                        continue;
                    }
                    if ( line.contains(mPID)) {  //out != null &&
//                        out.write((getDateEN() + "  " + line + "\n")
//                                .getBytes());


                        String text=getDateEN() + "  " + line;
                        try {
                            // BufferedWriter for performance, true to set append to file flag

                            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
                            buf.append(text);
                            buf.newLine();
                            buf.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }



                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
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

    }




    private static String getFileName() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date(System.currentTimeMillis()));
        return date;
    }

    private static String getDateEN() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1 = format1.format(new Date(System.currentTimeMillis()));
        return date1;
    }


}
