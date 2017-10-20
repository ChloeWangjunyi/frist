package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.lovcreate.core.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created by wangjunyi on 2017/9/22.
 */

public class CheckActivity extends BaseActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check);
    }

    @OnClick({R.id.buttonTrue})
    public void onClick(View view) {
        Toast.makeText(this, "true", Toast.LENGTH_SHORT).show();
    }
}
