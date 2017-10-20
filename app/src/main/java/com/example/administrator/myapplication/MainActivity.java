package com.example.administrator.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.lovcreate.core.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * gps_page
 * Created by wangjunyi on 2017/9/18.
 */

public class MainActivity extends BaseActivity {

    View dialogView;
    Dialog dialog;
    @Bind(R.id.tel)
    TextView telTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps);
    }

    @OnClick({R.id.listButton, R.id.fragmentButton, R.id.sex, R.id.editText, R.id.takePhoto, R.id.editTest, R.id.getOut, R.id.dialog, R.id.tel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.listButton:
                Intent intent = new Intent(this, ListActivity.class);
                startActivity(intent);
                break;
            case R.id.fragmentButton:
                startActivity(new Intent(this, FragmentActivity.class));
                break;
            case R.id.sex:
                startActivity(new Intent(this, SexActivity.class));
                break;
            case R.id.editText:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.takePhoto:
                startActivity(new Intent(this, PhotoActivity.class));
                break;
            case R.id.editTest:
                startActivity(new Intent(this, EditTextActivity.class));
                break;
            case R.id.getOut:
                startActivity(new Intent(this, CheckActivity.class));
                break;
            case R.id.dialog:
                getDialog();
                break;
            case R.id.tel:
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telTextView.getText()));//跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
                break;

        }
    }

    public void getDialog() {
        dialogView = LayoutInflater.from(this).inflate(R.layout.test_dialog, null);
        dialog = new MyDialog(this, dialogView);
        dialog.show();
        TextView cancel = (TextView) dialogView.findViewById(R.id.cancel);
        TextView submit = (TextView) dialogView.findViewById(R.id.ensure);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


}
