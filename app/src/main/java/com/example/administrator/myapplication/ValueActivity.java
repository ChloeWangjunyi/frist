package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.lovcreate.core.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by wangjunyi on 2017/9/22.
 */

public class ValueActivity extends BaseActivity {

    @Bind(R.id.value)
    EditText value;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.value);
        setText();
    }

    @OnClick({R.id.valueButton})
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("value", value.getText().toString());
        setResult(1, intent);
        finish();
    }

    private void setText() {
        if (!getIntent().getStringExtra("value").isEmpty()) {
            value.setText(getIntent().getStringExtra("value"));
        }
    }
}
