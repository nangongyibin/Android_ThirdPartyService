package com.ngyb.bugly;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/23 10:31
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //设置开发设备。
        CrashReport.setIsDevelopmentDevice(getApplicationContext(), true);
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 设置bugly初始化延迟
        strategy.setAppReportDelay(1000);
        // 设置crash回调
        strategy.setCrashHandleCallback(new CrashReport.CrashHandleCallback() {
            public Map<String, String> onCrashHandleStart(int crashType, String errorType,
                                                          String errorMessage, String errorStack) {
                LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
                map.put("Key", "Value");
                return map;
            }

            @Override
            public byte[] onCrashHandleStart2GetExtraDatas(int crashType, String errorType,
                                                           String errorMessage, String errorStack) {
                try {
                    return "Extra data.".getBytes("UTF-8");
                } catch (Exception e) {
                    return null;
                }
            }
        });
        // 初始化Bugly
        CrashReport.initCrashReport(getApplicationContext(), "b40dc030f9", true, strategy);
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 设置Crash回调
     */
    public abstract static class CrashHandleCallback {

        public static final int CRASHTYPE_JAVA_CRASH = 0; // Java crash
        public static final int CRASHTYPE_JAVA_CATCH = 1; // Java caught exception
        public static final int CRASHTYPE_NATIVE = 2; // Native crash
        public static final int CRASHTYPE_U3D = 3; // Unity error
        public static final int CRASHTYPE_ANR = 4; // ANR
        public static final int CRASHTYPE_COCOS2DX_JS = 5; // Cocos JS error
        public static final int CRASHTYPE_COCOS2DX_LUA = 6; // Cocos Lua error

        /**
         * Crash处理.
         *
         * @param crashType    错误类型：CRASHTYPE_JAVA，CRASHTYPE_NATIVE，CRASHTYPE_U3D ,CRASHTYPE_ANR
         * @param errorType    错误的类型名
         * @param errorMessage 错误的消息
         * @param errorStack   错误的堆栈
         * @return 返回额外的自定义信息上报
         */
        public abstract Map<String, String> onCrashHandleStart(int crashType, String errorType,
                                                               String errorMessage, String errorStack);

        /**
         * Crash处理.
         *
         * @param crashType    错误类型：CRASHTYPE_JAVA，CRASHTYPE_NATIVE，CRASHTYPE_U3D ,CRASHTYPE_ANR
         * @param errorType    错误的类型名
         * @param errorMessage 错误的消息
         * @param errorStack   错误的堆栈
         * @return byte[] 额外的2进制内容进行上报
         */
        public abstract byte[] onCrashHandleStart2GetExtraDatas(int crashType, String errorType,
                                                                String errorMessage, String errorStack);

    }
}
