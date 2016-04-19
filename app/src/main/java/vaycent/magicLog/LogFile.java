package vaycent.magicLog;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vaycent on 2016/4/19.
 */

public class LogFile {

    private LogDumper mLogDumper = null;
    private int mPId;


    public void start() {
        mPId =android.os.Process.myPid();
        System.out.println("mPId:"+mPId);

        if (mLogDumper == null)
            mLogDumper = new LogDumper(String.valueOf(mPId));

        mLogDumper.start();
    }

    public void stop() {
        if (mLogDumper != null) {
            mLogDumper.stopLogs();
            mLogDumper = null;
        }
    }

    private class LogDumper extends Thread {

        private Process logcatProc;
        private BufferedReader mReader = null;
        private boolean mRunning = true;
        String cmds = null;
        private String mPID;
        private FileOutputStream out = null;

        private File logFloderFile= new File(Environment.getExternalStorageDirectory().getPath() + "/TestLog");
        private File logFile;

        public LogDumper(String pid) {
            mPID = pid;

            logFile=new File(logFloderFile + "/TestLog"+  getFileName() + ".log");

            if(!logFloderFile.exists()){
                logFloderFile.mkdir();
            }
            if (!logFile.exists()) {
                try {
                    logFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }



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

            cmds = "logcat *:v *:i | grep \"(" + mPID + ")\" -s UseHere_01" ;
            System.out.println("cmds:"+cmds);
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
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    out = null;
                }

            }

        }

    }




    public static String getFileName() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date(System.currentTimeMillis()));
        return date;// 2012年10月03日 23:41:31
    }

    public static String getDateEN() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1 = format1.format(new Date(System.currentTimeMillis()));
        return date1;// 2012-10-03 23:41:31
    }


}
