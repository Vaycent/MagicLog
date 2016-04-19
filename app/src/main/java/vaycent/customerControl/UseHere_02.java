package vaycent.customerControl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vaycent.magicLog.R;
import vaycent.magicLog.mlog;

/**
 * Created by Vaycent on 2016/4/19.
 */
public class UseHere_02 extends AppCompatActivity {
    private static final String XML = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><!--  Copyright w3school.com.cn --><note><to>George</to><from>John</from><heading>Reminder</heading><body>Don't forget the meeting!</body></note>";
    private static final String JSON="\"{\\\"menu\\\":[\\\"泰式柠檬肉片\\\",\\\"鸡柳汉堡\\\",\\\"蒸桂鱼卷 \\\"],\\\"tag\\\":\\\"其他\\\"}\"";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.use_here_02);
        //

        mlog.v("Test mlog v Hello World!");
        mlog.d("Test mlog d Hello World!");
        mlog.i("Test mlog i Hello World!");
        mlog.w("Test mlog w Hello World!");
        mlog.e("Test mlog e Hello World!");
    }

    @Override
    protected  void onResume(){



        mlog.xml(XML);

        mlog.json(JSON);


        super.onResume();
    }
}
