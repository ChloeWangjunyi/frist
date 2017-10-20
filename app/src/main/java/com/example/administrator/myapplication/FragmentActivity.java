package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lovcreate.core.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import fragment.OneFragment;
import fragment.TwoFragment;

/**
 * Created by wangjunyi on 2017/9/19.
 */

public class FragmentActivity extends BaseActivity {


    private final static int ONE = 0;
    private final static int TWO = 1;
    private static int position = ONE;
    private List<Fragment> fragmentList;
    private Fragment fragment;
    /**
     * 视图注入
     */
    @Bind(R.id.radio)
    RadioGroup radioGroup;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment);
    }

    @Override
    public void onStart() {
        super.onStart();
        initFragment();
        setListener();
        initPosition();
    }

    private void initPosition() {
        switch (position) {
            case ONE:
                radioGroup.check(R.id.fragmentButtonOne);
                break;
            case TWO:
                radioGroup.check(R.id.fragmentButtonTwo);
                break;
        }
    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new OneFragment());
        fragmentList.add(new TwoFragment());
    }

    private void setListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.fragmentButtonOne:
                        position = ONE;
                        break;
                    case R.id.fragmentButtonTwo:
                        position = TWO;
                        break;
                    default:
                        position = ONE;
                        break;
                }
                Fragment to = fragmentList.get(position);
                switchFragment(fragment, to);
            }
        });
    }

    /**
     * 用于切换时，不会被重复加载
     *
     * @param from
     * @param to
     */
    private void switchFragment(Fragment from, Fragment to) {
        if (from != to) {
            fragment = to;//记录上一个fragment
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) {
                if (from != null) {
                    ft.hide(from);
                }
                if (to != null) {
                    ft.add(R.id.fragment, to).commit();
                }
            } else {
                if (from != null) {
                    ft.hide(from);
                }
                if (to != null) {
                    ft.show(to).commit();
                }
            }
        }
    }
}
