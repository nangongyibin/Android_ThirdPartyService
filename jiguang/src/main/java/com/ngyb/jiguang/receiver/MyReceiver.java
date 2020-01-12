package com.ngyb.jiguang.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import cn.jpush.android.api.JPushInterface;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/1/12 18:12
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "onReceive: " + intent.getAction());
        String action = intent.getAction();
        Bundle bundle = intent.getExtras();
        if (action.equals(JPushInterface.ACTION_REGISTRATION_ID)) {
            String title = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.e(TAG, "onReceive-title: " + title);
        }
        if (action.equals(JPushInterface.ACTION_MESSAGE_RECEIVED)) {
            String title = bundle.getString(JPushInterface.EXTRA_TITLE);
            Log.e(TAG, "onReceive-title: " + title);
            String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            Log.e(TAG, "onReceive-message: " + message);
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            Log.e(TAG, "onReceive-extra: " + extras);
            String file = bundle.getString(JPushInterface.EXTRA_MSG_ID);
            Log.e(TAG, "onReceive-msgid: " + file);
        }
        if (action.equals(JPushInterface.ACTION_NOTIFICATION_RECEIVED)) {
            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            Log.e(TAG, "onReceive-title: " + title);
            String alert = bundle.getString(JPushInterface.EXTRA_ALERT);
            Log.e(TAG, "onReceive-alert: " + alert);
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            Log.e(TAG, "onReceive-extras: " + extras);
            String notificationId = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_ID);
            Log.e(TAG, "onReceive-notificationId: "+notificationId );
            String fileHtml = bundle.getString(JPushInterface.EXTRA_RICHPUSH_HTML_PATH);
            Log.e(TAG, "onReceive-filehtml: "+fileHtml );
            String filestr = bundle.getString(JPushInterface.EXTRA_RICHPUSH_HTML_RES);
            Log.e(TAG, "onReceive-filestr: "+filestr );
            String file = bundle.getString(JPushInterface.EXTRA_MSG_ID);
            Log.e(TAG, "onReceive-file: "+file );
            String bigText = bundle.getString(JPushInterface.EXTRA_BIG_TEXT);
            Log.e(TAG, "onReceive-bigText: "+bigText );
            String bigPicPath = bundle.getString(JPushInterface.EXTRA_BIG_PIC_PATH);
            Log.e(TAG, "onReceive-bigPicPath: "+bigPicPath );
            String inboxJson = bundle.getString(JPushInterface.EXTRA_INBOX);
            Log.e(TAG, "onReceive-inboxJson: "+inboxJson );
            String prio = bundle.getString(JPushInterface.EXTRA_NOTI_PRIORITY);
            Log.e(TAG, "onReceive-prio: "+prio );
            String category = bundle.getString(JPushInterface.EXTRA_NOTI_CATEGORY);
            Log.e(TAG, "onReceive-category: "+category);
        }
        if (action.equals(JPushInterface.ACTION_NOTIFICATION_OPENED)){
            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            Log.e(TAG, "onReceive-title: " + title);
            String alert = bundle.getString(JPushInterface.EXTRA_ALERT);
            Log.e(TAG, "onReceive-alert: " + alert);
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            Log.e(TAG, "onReceive-extras: " + extras);
            String notificationId = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_ID);
            String file = bundle.getString(JPushInterface.EXTRA_MSG_ID);
            Log.e(TAG, "onReceive-file: "+file );
        }
        if (action.equals(JPushInterface.ACTION_NOTIFICATION_CLICK_ACTION)){
            String nActionExtra = intent.getExtras().getString(JPushInterface.EXTRA_NOTIFICATION_ACTION_EXTRA);
            Log.e(TAG, "onReceive-nActionExtra: "+nActionExtra );
        }
        if (action.equals(JPushInterface.ACTION_CONNECTION_CHANGE)){
            boolean connected = bundle.getBoolean(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            Log.e(TAG, "onReceive-connected: "+connected );
        }
    }
}
