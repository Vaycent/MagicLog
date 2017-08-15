package vaycent.example;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import vaycent.magicloglib.mlog;

public class MainActivity extends AppCompatActivity {

    private final String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><employee><firstName>John</firstName><lastName>Doe</lastName><employeeNumber>123</employeeNumber><title>Accountant</title></employee>";

    private final String jsonStr = "{employee :{firstName: \"John\",lastName : \"Doe\",employeeNumber : 123,title : \"Accountant\"}}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mlog builder = new mlog.Builder()
                .error(true)
                .warning(true)
                .information(true)
                .debug(true)
                .verbose(true)
                .xml(true)
                .json(true)
                .logFilePath(Environment.getExternalStorageDirectory().getPath() + "/MagicLog_File")
                .logFilterPriority("v")
                .logFilterTag("")
                .build();
        System.out.println(builder.toString());;


        mlog.e("11","error");
        mlog.w("22","warning");
        mlog.i("33","information");
        mlog.d("44","debug");
        mlog.v("verbose");
        mlog.xml("55",xmlStr);
        mlog.json(jsonStr);

    }
}
