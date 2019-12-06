package com.ngyb.mobtech;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.mob.secverify.OperationCallback;
import com.mob.secverify.SecVerify;
import com.mob.secverify.exception.VerifyException;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/11/15 16:09
 */
public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initV();
    }

    private void initV() {
        //建议提前调用预登录接口，可以加快免密登录过程，提高用户体验
        SecVerify.preVerify(new OperationCallback<Void>() {
            @Override
            public void onComplete(Void data) {
                //TODO处理成功的结果
                Log.e(TAG, "onComplete: 成功");
            }

            @Override
            public void onFailure(VerifyException e) {
                //TODO处理失败的结果
                Log.e(TAG, "onFailure: " + new Gson().toJson(e));
            }
        });
    }
}
