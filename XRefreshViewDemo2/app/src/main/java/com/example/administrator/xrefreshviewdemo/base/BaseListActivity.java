package com.example.administrator.xrefreshviewdemo.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.example.administrator.xrefreshviewdemo.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;


public abstract class BaseListActivity extends BaseActivity {
    @BindView(R.id.refreshLayout)
    protected SmartRefreshLayout refreshLayout;
    protected int countTotal;
    protected int pageIndex;
    protected int pageSize;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setAdapter();
        setRefresh();
    }

    public abstract void setAdapter();

    private void setRefresh() {
        refreshLayout.setEnableAutoLoadmore(true);//开启自动加载功能（非必须）
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                addRefreshMoreData();
                refreshlayout.finishRefresh(2000, true);
                refreshlayout.resetNoMoreData();
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {

                addLoadMoreData();
                refreshlayout.finishLoadmore();


            }
        });

        //触发自动刷新
        refreshLayout.autoRefresh();
    }

    public abstract void addRefreshMoreData();

    public abstract void addLoadMoreData();

}
