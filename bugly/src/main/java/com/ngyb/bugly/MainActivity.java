package com.ngyb.bugly;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.BuglyLog;
import com.tencent.bugly.crashreport.CrashReport;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //需要先在异常配置中添加标签，然后在这里使用标签id即可。
        CrashReport.setUserSceneTag(this,163185);
        //bugly日志。
        BuglyLog.e(TAG,"异常");
        // 设置自定义Map参数
        CrashReport.putUserData(this,"南宫燚滨","囡囡");
        CrashReport.testJavaCrash();
    }
}
