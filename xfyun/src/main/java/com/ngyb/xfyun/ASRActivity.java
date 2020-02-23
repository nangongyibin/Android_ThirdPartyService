package com.ngyb.xfyun;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import java.util.List;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/2/22 16:49
 */
public class ASRActivity extends AppCompatActivity {
    private static final String TAG = "ASRActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asr);
        setTitle("语音识别");
    }

    public void ASR(View view) {
        final RecognizerDialog recognizerDialog = new RecognizerDialog(this, new InitListener() {
            @Override
            public void onInit(int i) {
                if (i != ErrorCode.SUCCESS) {
                    Toast.makeText(ASRActivity.this, "初始化失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ASRActivity.this, "初始化成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //设置语音输入语言，zh_cn为简体中文
        recognizerDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        //设置结果返回语言
        recognizerDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        recognizerDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {
                if (b) {
                    return;
                }
                String resultString = recognizerResult.getResultString();
                Log.e(TAG, "onResult: " + resultString);
                Bean bean = new Gson().fromJson(resultString, Bean.class);
                List<Bean.WsBean> ws = bean.getWs();
                if (ws != null && ws.size() > 0) {
                    for (int i = 0; i < ws.size(); i++) {
                        Bean.WsBean wsBean = ws.get(i);
                        List<Bean.WsBean.CwBean> cw = wsBean.getCw();
                        if (cw != null && cw.size() > 0) {
                            for (int i1 = 0; i1 < cw.size(); i1++) {
                                Bean.WsBean.CwBean cwBean = cw.get(i1);
                                String w = cwBean.getW();
                                Toast.makeText(ASRActivity.this, "" + w, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }

            @Override
            public void onError(SpeechError speechError) {
                Log.e(TAG, "onError: " + new Gson().toJson(speechError));
                Log.e(TAG, "onError: " + speechError.getErrorCode());
            }
        });
        recognizerDialog.show();
    }
}
