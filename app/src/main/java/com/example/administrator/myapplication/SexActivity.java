package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.lovcreate.core.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * choose sex page
 * Created by wangjunyi on 2017/9/19.
 */

public class SexActivity extends BaseActivity {
    @Bind(R.id.sex)
    TextView sex;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sex_select);
    }

    @OnClick({R.id.chooseSex})
    public void onClick(View view) {
        showChooseSexDialog();

    }

    protected void showChooseSexDialog() {
        final List<String> sexList = new ArrayList<>();
        sexList.add("男");
        sexList.add("女");
        OptionsPickerView optionsPickerView = IOSPickerUtil.getOptionsPickerBuilder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                sex.setText(sexList.get(options1));
            }
        }, "性别")
                .setSelectOptions("男".equals(sex.getText().toString()) ? 0 : 1) // 设置默认值
                .build();
        optionsPickerView.setPicker(sexList);
        optionsPickerView.show();
    }
}

