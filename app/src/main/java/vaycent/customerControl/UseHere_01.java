package vaycent.customerControl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vaycent.magicLog.LogFile;
import vaycent.magicLog.R;
import vaycent.magicLog.mlog;

public class UseHere_01 extends AppCompatActivity {
    private static final String XML = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><!--  Copyright w3school.com.cn --><note><to>George</to><from>John</from><heading>Reminder</heading><body>Don't forget the meeting!</body></note>";
    private static final String JSON="\"{\\\"menu\\\":[\\\"泰式柠檬肉片\\\",\\\"鸡柳汉堡\\\",\\\"蒸桂鱼卷 \\\"],\\\"tag\\\":\\\"其他\\\"}\"";

    private LogFile logFile=new LogFile();

    private Button go_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.use_here_01);


        mlog.v("Test mlog v Hello World!");
        mlog.d("Test mlog d Hello World!");
        mlog.i("Test mlog i Hello World!");
        mlog.w("Test mlog w Hello World!");
        mlog.e("Test mlog e Hello World!");



        mlog.xml(XML);

        mlog.json(JSON);


        logFile.start();


        go_btn=(Button)findViewById(R.id.go_btn);
        go_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(UseHere_01.this, UseHere_02.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy(){
        logFile.stop();
        super.onDestroy();
    }




}
