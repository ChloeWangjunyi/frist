package com.lovcreate.core.app;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.StrictMode;

import com.lovcreate.core.util.ScreenAdaptation;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/12/14.
 *
 * 应用主体, 初始化okhttp
 * 在这里做一些第三方初始化
 */
public class MainApplication extends Application {

    private static MainApplication instance;

    private List<Activity> activityList = new ArrayList<>();

    private static String deviceId = "";

    private static SharedPreferences loginSharedPreferences;
    private static SharedPreferences.Editor loginSharedPreferenceEditor;

    @Override
    public void onCreate() {
        super.onCreate();

        //针对Android 7 以上 FileUriExposedException : 置入一个不设防的VmPolicy,再用旧的方式直接把file://格式的URI发送出去   http://www.jianshu.com/p/68a4e8132fcd
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        loginSharedPreferences = getApplicationContext().getSharedPreferences("deviceId", MODE_PRIVATE);
        loginSharedPreferenceEditor = loginSharedPreferences.edit();
        initApp();

        // 初始化屏幕适配 需要传入ui设计给的大小
        new ScreenAdaptation(this, 750,1334).register();
    }

    public static String getDeviceId() {
        if ("".equals(deviceId)) {
            return loginSharedPreferences.getString("deviceId", "");
        } else {
            return deviceId;
        }
    }

    public static void setDeviceId(String deviceId) {
        MainApplication.deviceId = deviceId;
    }

    private void initApp() {
        // http请求
        try {
            OkHttpUtils.initClient(new OkHttpClient.Builder().readTimeout(10, java.util.concurrent.TimeUnit.SECONDS)
                .connectTimeout(10, java.util.concurrent.TimeUnit.SECONDS)
                .writeTimeout(10, java.util.concurrent.TimeUnit.SECONDS).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MainApplication getInstance() {
        if (instance == null) {
            instance = new MainApplication();
        }
        return instance;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }
}