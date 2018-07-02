package com.example.administrator.xrefreshviewdemo.refresh;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.administrator.xrefreshviewdemo.R;
import com.example.administrator.xrefreshviewdemo.base.BaseListActivity;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

// oncreate设置adapter-->进界面加载数据-->接口请求成功之后刷新adapter-->下拉或者上拉
public class RefreshActivity3 extends BaseListActivity {
    @BindView(R.id.listview)
    protected ListView listview;
    List<String> mDatas = new ArrayList<>();
    int count = 0;
    CommonAdapter<String> adapter;


    public static void startActivity(Context context) {
        Intent starter = new Intent(context, RefreshActivity3.class);
        context.startActivity(starter);
    }

    @Override
    public void setAdapter() {
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

    @Override
    public void addRefreshMoreData() {
        count = 0;
        mDatas.clear();
        for (int i = 0; i < 10; i++) {
            count++;
            mDatas.add("第" + count + "项数据");
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addLoadMoreData() {
        addData();
        setAdapter();
    }

    @Override
    protected void initContentView(Bundle var1) {
        setContentView(R.layout.activity_refresh2);
    }

    @Override
    public void initView() {

    }

    //一开始加载数据
    @Override
    public void initData() {
        addData();
        setAdapter();
    }

    public void addData() {
        for (int i = 0; i < 10; i++) {
            count++;
            mDatas.add("第" + count + "项数据");
        }
    }

}
