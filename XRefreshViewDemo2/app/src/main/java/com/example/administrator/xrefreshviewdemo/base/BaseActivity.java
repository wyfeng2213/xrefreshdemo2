package com.example.administrator.xrefreshviewdemo.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import com.example.administrator.xrefreshviewdemo.eventbus.Event;
import com.example.administrator.xrefreshviewdemo.eventbus.EventBusUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

public abstract class BaseActivity extends Activity {
    public static String TAG;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mContext = this;
        TAG = this.getClass().getSimpleName();
//        MyApplication.getInstance().addActivity(this);
        initContentView(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        initData();
        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }
    }

    /**
     * 是否注册事件分发
     *
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(Event event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    /**
     * 接收到分发到事件
     *
     * @param event 事件
     */
    protected void receiveEvent(Event event) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }
    }

    @Override
    protected void onResume() {
        /**
         * 设置为横屏
         */
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    abstract protected void initContentView(Bundle var1);


    /**
     * 初始化事件
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

}
