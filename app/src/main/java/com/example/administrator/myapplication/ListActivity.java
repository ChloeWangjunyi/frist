package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lovcreate.core.base.BaseActivity;
import com.lovcreate.core.util.click.AntiShake;

import java.util.ArrayList;
import java.util.List;

import adapter.ListAdapter;
import butterknife.Bind;

/**
 * 列表activity
 * Created by wangjunyi on 2017/9/18.
 */

public class ListActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    ListAdapter adapter;
    List<Bean> list = new ArrayList<>();
    @Bind(R.id.listItem)
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
    }

    @Override
    public void onStart() {
        super.onStart();
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    public void initView() {
        getList();
        adapter = new ListAdapter(this, list);
        listView.setAdapter(adapter);
    }

    /**
     * 列表的item点击事件
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        if (AntiShake.check(view)) {
            return;
        }
        Intent intent = new Intent(this, ListInfoActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


    public void getList() {
        Bean bean = new Bean();
        bean.setAge("12");
        bean.setName("Chloe");
        bean.setIsSee(1);
        list.add(bean);
        Bean bean1 = new Bean();
        bean1.setAge("12");
        bean1.setName("Chloe1");
        bean1.setIsSee(0);
        list.add(bean1);
    }

}
