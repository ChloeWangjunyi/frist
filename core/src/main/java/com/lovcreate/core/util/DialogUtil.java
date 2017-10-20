package com.lovcreate.core.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.lovcreate.core.R;

/**
 * Created by Li on 2017/2/7.
 */

public class DialogUtil {
    private static AlertDialog.Builder mDialogBuilder;
    private static AlertDialog alert;

    public static Dialog showCustomDialog(final Context context, final View view) {
        /* 加载dialog布局 */
        mDialogBuilder = new AlertDialog.Builder(context, R.style.dialog);
        mDialogBuilder.setView(view);
        mDialogBuilder.setCancelable(true);
        alert = mDialogBuilder.create();
        Window dialogWindow = alert.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        alert.setCanceledOnTouchOutside(false);
        alert.setCancelable(false);
        // WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        // lp.y = 20;
        // dialogWindow.setAttributes(lp);
        return alert;
    }

    public static Dialog showBottomDialog(final Context context, final View view) {
        /* 加载dialog布局 */
        mDialogBuilder = new AlertDialog.Builder(context, R.style.dialog);
        mDialogBuilder.setView(view);
        mDialogBuilder.setCancelable(true);
        alert = mDialogBuilder.create();
        Window dialogWindow = alert.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        alert.setCanceledOnTouchOutside(false);
        alert.setCancelable(false);
        alert.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;
        dialogWindow.setAttributes(lp);
        return alert;
    }

    public static Dialog number(final Context context, final View view) {
        /* 加载dialog布局 */
        mDialogBuilder = new AlertDialog.Builder(context, R.style.DialogTheme);
        mDialogBuilder.setView(view);
        mDialogBuilder.setCancelable(true);
        alert = mDialogBuilder.create();
        Window dialogWindow = alert.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        alert.setCanceledOnTouchOutside(false);
        alert.setCancelable(false);
        alert.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;
        dialogWindow.setAttributes(lp);
        return alert;
    }

    public static Dialog showCustomAddDialog(final Context context, final View view) {
        /* 加载dialog布局 */
        mDialogBuilder = new AlertDialog.Builder(context, R.style.dialog);
        mDialogBuilder.setView(view);
        mDialogBuilder.setCancelable(true);
        alert = mDialogBuilder.create();
        Window dialogWindow = alert.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        alert.setCanceledOnTouchOutside(true);
        alert.setCancelable(true);
        // WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        // lp.y = 20;
        // dialogWindow.setAttributes(lp);
        return alert;
    }

    public static Dialog showCustomAddDialog_01(final Context context, final View view) {
        /* 加载dialog布局 */
        mDialogBuilder = new AlertDialog.Builder(context, R.style.dialog);
        mDialogBuilder.setView(view);
        mDialogBuilder.setCancelable(true);
        alert = mDialogBuilder.create();
        Window dialogWindow = alert.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        alert.setCanceledOnTouchOutside(true);
        alert.setCancelable(true);
        // WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        // lp.y = 20;
        // dialogWindow.setAttributes(lp);
        return alert;
    }
}
