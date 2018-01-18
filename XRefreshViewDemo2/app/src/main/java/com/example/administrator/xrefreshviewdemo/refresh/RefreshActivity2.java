package com.example.administrator.xrefreshviewdemo.refresh;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.administrator.xrefreshviewdemo.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RefreshActivity2 extends AppCompatActivity {

    private ListView listview;
    private SmartRefreshLayout refreshLayout;
    List<String> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh2);
        initView();
        setAdatper();
    }

    public static void startActivity(Context context) {
        Intent starter = new Intent(context, RefreshActivity2.class);
        context.startActivity(starter);
    }

    private void initView() {
        listview = (ListView) findViewById(R.id.listview);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        setRefresh();
    }

    private void setRefresh() {
        refreshLayout.setEnableAutoLoadmore(true);//开启自动加载功能（非必须）
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                initData();
                adapter.notifyDataSetChanged();
                refreshlayout.finishRefresh(2000, true);
                refreshlayout.resetNoMoreData();
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {

                addData();
                adapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore(1000, true);
                if (mDatas.size() >= 30) {
                    refreshlayout.setEnableLoadmore(false);
                }

            }
        });

        //触发自动刷新
        refreshLayout.autoRefresh();
    }

    CommonAdapter<String> adapter;
    int count = 0;

    public void initData() {
        count = 0;
        mDatas.clear();
        for (int i = 0; i < 10; i++) {
            count++;
            mDatas.add("第" + count + "项数据");
        }
    }

    public void addData() {
        for (int i = 0; i < 10; i++) {
            count++;
            mDatas.add("第" + count + "项数据");
        }
    }

    public void setAdatper() {
        addData();
        if (adapter == null) {
            adapter = new CommonAdapter<String>(this, R.layout.item_layout, mDatas) {
                @Override
                public void convert(ViewHolder holder, String item, int pos) {
                    holder.setText(R.id.tv_item, item);
                }

                @Override
                public void onViewHolderCreated(ViewHolder holder, View itemView) {
                    super.onViewHolderCreated(holder, itemView);
                }
            };
            listview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}
