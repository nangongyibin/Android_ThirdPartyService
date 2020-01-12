package com.ngyb.mobtech.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ngyb.mobtech.R;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/1/12 10:37
 */
public class SMSSDKActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etPhone;
    private EditText etCode;
    private Button inputCode;
    private Button vertify;
    public static final String country = "86";
    private String phone;
    private EventHandler eh;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.arg2 == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (msg.arg1 == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    Toast.makeText(SMSSDKActivity.this, "验证码验证成功", Toast.LENGTH_SHORT).show();
                    //提交验证码成功
                } else if (msg.arg1 == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    Toast.makeText(SMSSDKActivity.this, "验证码发送成功", Toast.LENGTH_SHORT).show();
                    //获取验证码成功
                } else if (msg.arg1 == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                }
            } else if (msg.arg2 == SMSSDK.RESULT_ERROR) {
                //回调完成
                if (msg.arg1 == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    Toast.makeText(SMSSDKActivity.this, "验证码验证失败", Toast.LENGTH_SHORT).show();
                    //提交验证码成功
                } else if (msg.arg1 == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    Toast.makeText(SMSSDKActivity.this, "验证码发送失败", Toast.LENGTH_SHORT).show();
                    //获取验证码成功
                } else if (msg.arg1 == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                }
            } else {
                ((Throwable) msg.obj).printStackTrace();
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smssdk);
        init();
    }

    private void init() {
        initView();
        initListener();
        initSmsSdk();
    }

    private void initSmsSdk() {
        eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    private void initListener() {
        inputCode.setOnClickListener(this);
        vertify.setOnClickListener(this);
    }

    private void initView() {
        etPhone = findViewById(R.id.etPhone);
        etCode = findViewById(R.id.etCode);
        inputCode = findViewById(R.id.inputCode);
        vertify = findViewById(R.id.vertify);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.inputCode:
                phone = etPhone.getText().toString().trim();
                SMSSDK.getVerificationCode(country, phone);
                break;
            case R.id.vertify:
                String code = etCode.getText().toString().trim();
                SMSSDK.submitVerificationCode(country, phone, code);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }
}
