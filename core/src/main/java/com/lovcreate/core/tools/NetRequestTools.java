package com.lovcreate.core.tools;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.lovcreate.core.app.MainApplication;
import com.lovcreate.core.base.BaseBean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 * 
 * 登陆超时工具类
 */
public class NetRequestTools {

    /**
     * 判断是否需要重新登陆
     */
    public static boolean net_check(BaseBean baseBean, Context context) {
        if (context == null) {
            return false;
        }
        if (baseBean.getCode().equals("3")) {
            Toast.makeText(context, baseBean.getMessage(), Toast.LENGTH_SHORT).show();
            List<Activity> list = ((MainApplication) context.getApplicationContext()).getActivityList();
            for (int i = 0; i < list.size(); i++) {
                list.get(i).finish();
            }
            SPTools.put(context, "token", "");
            // Intent intent = new Intent(context, LoginActivity.class);
            // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // context.startActivity(intent);
            return true;
        }
        return false;
    }

}
