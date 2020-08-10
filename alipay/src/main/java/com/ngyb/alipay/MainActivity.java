package com.ngyb.alipay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final int SDK_PAY_FLAG = 1;
    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID = "2021000116688286";
    /**
     *  pkcs8 格式的商户私钥。
     *
     * 	如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个，如果两个都设置了，本 Demo 将优先
     * 	使用 RSA2_PRIVATE。RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议商户使用
     * 	RSA2_PRIVATE。
     *
     * 	建议使用支付宝提供的公私钥生成工具生成和获取 RSA2_PRIVATE。
     * 	工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCJB8P8IxOBUBkN5A5j25UEOtX23kqZgEnCweS7Z3Sc9qFJdqJlj8WBr/yBhR5GaOOB2PuciTrRJn9JlIdxkYWKgeuM0sP/IKtZoJ3MREkhxWzaGLn6EXUHY5JurTEQKtDPhuV9GcV8adiVxBKxznhfOt8w+UfD3bawuUkpPuYZRzJG+f0s58S0F9Mo9oCY6aSNHwg++dDbNSPaythELZb3ZsdSGbRdSi7RQUHC9P7nttvFUbZK2WYtlNuiXCr5irQafpqcSoeHYSsW2znq2KAzv0VPQMx899O1Ekjx+2xtQz7P0muzrV7j1peL5BYslZt9pYKlzwvnf8UJ/RwjLzxLAgMBAAECggEAcDfB0ixPfui/e9a1yiU65dng+2iTox2iSjJLRQW2RAzdjLHw0EdrMYeTu+XuIy8mkWHGvCzN1BY4y2MtgF3yBLnc0tiP1dFFQgL89xk84sExIXJ6a0X8lu5+LtD6iwjlM+5+5XJDfl2YFx4ZSS+S+DnYKd0bXlWllRhHx9VQzoWAS4jfVqlH57jNsyhPwXadaQ0y2y8pyp8rwaxApz7vU17+p6tleBis/fpkBf6+NTrkTTb4wU7VlhvV3WEFIl6ClVFiPjzMe/CHLyxoqPhAteW/+NEKu2w6obmASl8ntCXkKIQZgZnSzQE453TCrRm9g20l/lgeSYppggXXhXdr8QKBgQDVeNPWKSmejU8mOzvVSRlNPh1JEt/xtWuKMQfSTMlM5KyqwiihZai8nxZsPpLm0NspNsTiLmQp45AVSEPLBq6qgdGNULSXANG2i1eLH3MQferdLWtWctq+7RrNwFEEAiQB3dS+UUwoChhiKTHh195GJEl+J3qNS8J79YtrDe2ExwKBgQCkVGCxoT3Yh/Pkuhv/zBFU1uN3yzcHtjcWJMlp1MiMueQbBfBhvVxpObda4aPyPE5eLU4YWQ584OSdyHJ5v0Ukad1L9E+SGOyk3YoTQsrRcErEdwnO2kbMYkqGYoCfj8t8o/RMsMV005lp/oLM9WIVPucIs/PfZo1B2LzbmuoAXQKBgBmV/NXrYxik2Tq82MQMmeK/MiVjBMJkuzn5/HUBTRNRSTzCPrpxg9LvwnFA4ryVhd8fuGNfqhO3ObrDSZTY/TBklOg6Jxr+NySI+Posz87HONI/smDPLVa69n54sX0ogIZUvz38YoNeCmQhieAGzpZbnQ9ozfCHhwzydj5jkfdbAoGBAKNENgGlusWcZaRViCYAQSPykHNf0t/1mQuGJxrfzxWKR2jV4LurINpm/pXr4zBz3ETTbGjianOGGX3uPlZZw79m8vhY1onH+63dciaq0Ml/cLl7r6H12iuMaJDV4wclHb5LQxjC9Jua9CyMMraiAf4rze0mpWDLCgNm7FSfuGf9AoGAX6zUpxHvyGwwT9p2qaBXgyvdpOn3G03Yt6ZMXKhIh7zIuujnvNZpDBigsIquRmGOBpfhASCJqgN8Tw5xNfeUa9WjIiA4rSnByDsBpGT39CR1y3cPoYjlJmY31+8Qrq+QoRwDp9eNkCJ/N7H0vYUYsa67awKnTaLx4TVlRoWQS/U=";
    public static final String RSA_PRIVATE = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        showAlert(MainActivity.this, getString(R.string.pay_success) + payResult);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        showAlert(MainActivity.this, getString(R.string.pay_failed) + payResult);
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };

    public void pay(View view) {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            showAlert(this, getString(R.string.error_missing_appid_rsa_private));
            return;
        }

        /*
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo 的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;
        final Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(MainActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.e("msp", result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private static void showAlert(Context ctx, String info) {
        showAlert(ctx, info, null);
    }

    private static void showAlert(Context ctx, String info, DialogInterface.OnDismissListener onDismiss) {
        new AlertDialog.Builder(ctx)
                .setMessage(info)
                .setPositiveButton(R.string.confirm, null)
                .setOnDismissListener(onDismiss)
                .show();
    }
}
