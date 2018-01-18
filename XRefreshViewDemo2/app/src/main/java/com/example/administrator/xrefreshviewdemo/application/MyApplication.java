package com.example.administrator.xrefreshviewdemo.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.example.administrator.xrefreshviewdemo.R;
import com.example.administrator.xrefreshviewdemo.util.PreferencesUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.RxRetrofitApp;

import java.util.Stack;

/**
 * Description:
 * Date：2017/03/15 10:30
 * Author: wangyong
 */

public class MyApplication extends Application {
    private static Stack<Activity> mActivityStack;
    private static MyApplication myApp;

    public static PreferencesUtils getPreferences() {
        return preferencesUtils;
    }

    public static PreferencesUtils preferencesUtils;

    /*
         * 关键代码，需要在布局生成之前设置，建议代码放在 Application 中
         */
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context).setEnableLastTime(false);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    public static MyApplication getInstance() {
        if (myApp == null) {
            myApp = new MyApplication();
        }
        return myApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RxRetrofitApp.init(this);
    }


    /**
     * get current activity
     */
    public Activity getCurActivity() {
        return mActivityStack.lastElement();
    }


    // 移除activity
    public void removeActivity(Activity activity) {
        if (activity != null) {
            mActivityStack.remove(activity);
        }
    }

    // 将当前Activity推入栈中
    public void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<Activity>();
        }
        mActivityStack.add(activity);
    }

    // 退出栈中所有Activity
    public void clearAllActivity() {
        while (!mActivityStack.isEmpty()) {
            Activity activity = mActivityStack.pop();
            if (activity != null) {
                activity.finish();
            }
        }
    }

}
