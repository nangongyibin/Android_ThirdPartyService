package com.ngyb.mobtech;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

import java.util.logging.Logger;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/11/18 22:20
 */
public class NetWorkStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!isNetworkAvailable(context)) {
            Toast.makeText(context, "你现在处于一个没有网络的异世界", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "网络通畅", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 网络状态可用
     *
     * @param context
     * @return
     */
    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager netManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            NetworkInfo[] info = netManager.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
            return false;
        }

        return false;
    }

}
