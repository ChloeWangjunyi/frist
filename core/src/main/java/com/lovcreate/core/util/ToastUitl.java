package com.lovcreate.core.util;

import android.content.Context;
import android.widget.Toast;

/**
 * 作者：yuanYe创建于2016/10/28
 * QQ：962851730
 */

public class ToastUitl {
    public static void showToast(Context context, Object str) {
        Toast.makeText(context, str + "", Toast.LENGTH_SHORT).show();
    }
}
