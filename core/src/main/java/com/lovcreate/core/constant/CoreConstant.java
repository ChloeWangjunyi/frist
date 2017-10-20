package com.lovcreate.core.constant;

import android.content.Context;
import android.content.SharedPreferences;

import com.lovcreate.core.bean.UserBean;

/**
 * Core静态变量
 * Created by Albert.Ma on 2017/8/3 0003.
 */

public class CoreConstant {
    /**
     * 网络请求返回状态
     */
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILURE = "FAILURE";
    public static final String TOKEN = "TOKEN";

    /**
     * 分页数:每页条目数
     */
    public static final String PAGE_SIZE = "10";

    /**
     * 当前登录人信息
     */
    public static UserBean loginUser = new UserBean();

    //角色: 0.超级管理员，1.管理员，2.普通用户
    public static final String ROLE_SYSTEM = "-1";
    public static final String ROLE_SUPER_MANAGER = "0";
    public static final String ROLE_MANAGER = "1";
    public static final String ROLE_USER = "2";

    /**
     * 定位服务:发送坐标与步数的循环周期 单位秒
     */
    public static final int SEND_LOCATION_STEP_PERIOD = 30;//30秒

    /**
     * 计步服务:将步数存入到SQLLite中的周期 单位ms
     */
    public static final int STEP_SAVE_PERIOD = 1000 * 20;//10s

    /**
     * 计步服务:息屏状态下,将步数存入到SQLLite中的周期 单位ms
     */
    public static final int STEP_SAVE_PERIOD_SCREEN_OFF = 1000 * 10;//10s

    /**
     * 高德地图: 设置定位间隔 单位ms
     */
    public static final int AMAP_LOCATION_INTERVAL = 1000 * 10;//10s
    /**
     * 高德地图: 请求超时时间 单位ms
     */
    public static final int AMAP_HTTP_TIME_OUT = 30000;//5分钟

    public static String getToken(Context context) {
        SharedPreferences loginSharedPreference = context.getSharedPreferences("users", Context.MODE_PRIVATE);
        return loginSharedPreference.getString("token", loginSharedPreference.getString("token", ""));
    }

    public static String getRole(Context context) {
        SharedPreferences loginSharedPreference = context.getSharedPreferences("users", Context.MODE_PRIVATE);
        return loginSharedPreference.getString("role", loginSharedPreference.getString("role", ""));
    }
}
