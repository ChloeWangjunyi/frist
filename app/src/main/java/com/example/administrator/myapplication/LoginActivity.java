package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.lovcreate.core.base.BaseActivity;
import com.lovcreate.core.util.click.AntiShake;
import com.rey.material.widget.EditText;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 登录
 * Created by wangjunyi on 2017/9/5.
 */

public class LoginActivity extends BaseActivity {
    private boolean accountStatus = false;
    private boolean passwordStatus = false;


    @Bind(R.id.accountEditText)
    EditText accountEditText;
    @Bind(R.id.passwordEditText)
    EditText passwordEditText;

    @Bind(R.id.forgetButton)
    Button forgetButton;
    @Bind(R.id.loginButton)
    Button loginButton;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
    }


    @Override
    public void onStart() {
        super.onStart();
        if (accountStatus) {
            forgetButton.setEnabled(true);
        } else {
            forgetButton.setEnabled(false);
        }
        if (accountStatus && passwordStatus) {
            loginButton.setEnabled(true);
        } else {
            loginButton.setEnabled(false);
        }
        accountEditText.addTextChangedListener(accountTextWatcher);
        passwordEditText.addTextChangedListener(passwordTextWatcher);
    }

    TextWatcher passwordTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (String.valueOf(s).trim().isEmpty()) {
                passwordStatus = false;
            } else {
                passwordStatus = true;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (accountStatus && passwordStatus) {
                loginButton.setEnabled(true);
            } else {
                loginButton.setEnabled(false);
            }
        }
    };


    TextWatcher accountTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // 进行输入则去除输入框的错误提示
            accountEditText.setError("");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (String.valueOf(s).trim().isEmpty()) {
                accountStatus = false;
            } else {
                accountStatus = true;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (accountStatus) {
                forgetButton.setEnabled(true);
            } else {
                forgetButton.setEnabled(false);
            }
            if (accountStatus && passwordStatus) {
                loginButton.setEnabled(true);
            } else {
                loginButton.setEnabled(false);
            }
        }
    };

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.loginButton, R.id.forgetButton})
    public void onClick(View view) {
        if (AntiShake.check(view.getId())) {
            return;
        }
        switch (view.getId()) {
            case R.id.loginButton:
                if (accountEditText.getText().toString().isEmpty()) {
                    accountEditText.setError("账号错误");
                    break;
                }
                startActivity(new Intent(this, ListActivity.class));
                break;
            case R.id.forgetButton:
                break;
        }
    }


}
