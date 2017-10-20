package com.example.administrator.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.lovcreate.core.base.BaseActivity;

import java.io.File;

import butterknife.OnClick;

/**
 * Created by wangjunyi on 2017/9/22.
 */

public class PhotoActivity extends BaseActivity {

    private String photoPath;
    private String cameraUri = "/dinergate";
    private Uri mUri;
    private final int CAMERA_CODE = 1001;//相机状态码
    private final int PHOTO_CODE = 1002;//相册状态码
    private final int PHOTO_CUT_CODE = 1003;//图片剪裁状态码
    Dialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstancedState){
        super.onCreate(savedInstancedState);
        setContentView(R.layout.head_pic);
    }

    @OnClick({R.id.me_rl_portrait})
    public void onClick(View view){
        showChoosePicDialog();

    }
    /**
     * 选择图片方式弹框
     */
    protected void showChoosePicDialog() {
        //初始化自定义dialog
        View headDialog = LayoutInflater.from(this).inflate(R.layout.main_mine_head_dialog, null);
        TextView cameraTv = (TextView) headDialog.findViewById(R.id.camera_tv);
        TextView photoTv = (TextView) headDialog.findViewById(R.id.photo_tv);
        TextView cancelTv = (TextView) headDialog.findViewById(R.id.cancel_tv);
        dialog = new Dialog(this);

        //创建AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(headDialog);
        dialog = builder.create();
        dialog.show();

        //拍照点击事件
        cameraTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//启动系统相机
                File appDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + cameraUri);
                if (!appDir.exists()) {
                    appDir.mkdir();
                }
                photoPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + cameraUri + String.valueOf(System.currentTimeMillis()) + ".jpg";
                mUri = Uri.fromFile(new File(photoPath));//传递路径
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);//更改系统默认存储路径
                startActivityForResult(cameraIntent, CAMERA_CODE);//启动系统自带相机，用户可以用它来拍照
                dialog.dismiss();
            }
        });
        //图库选择点击事件
        photoTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent1, PHOTO_CODE);
                dialog.dismiss();
            }
        });
        //取消点击事件
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        //设置弹窗在屏幕底部
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        //设置弹窗宽度
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = window.getWindowManager().getDefaultDisplay().getWidth();
        window.setAttributes(lp);//设置宽度
    }
}
