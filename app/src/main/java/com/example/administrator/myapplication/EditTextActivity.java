package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lovcreate.core.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by wangjunyi on 2017/9/22.
 */

public class EditTextActivity extends BaseActivity {

    private boolean buttonStatus = false;

    @Bind(R.id.showText)
    TextView textView;
    @Bind(R.id.editText1)
    EditText editText;
    @Bind(R.id.nextButton)
    Button button;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_test);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (buttonStatus) {
            button.setEnabled(true);
        } else {
            button.setEnabled(false);
        }
        editText.addTextChangedListener(buttonWatcher);
    }

    TextWatcher buttonWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (String.valueOf(s).trim().isEmpty()) {
                buttonStatus = false;
            } else {
                buttonStatus = true;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (buttonStatus) {
                button.setEnabled(true);
            } else {
                button.setEnabled(false);
            }

        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (data != null) {
                    textView.setText(data.getStringExtra("value"));
                }
                break;
        }
    }

    @OnClick({R.id.nextButton})
    public void onClick(View view) {
        Intent intent = new Intent(EditTextActivity.this, ValueActivity.class);
        intent.putExtra("value", textView.getText().toString());
        startActivityForResult(intent, 1);
    }


}
