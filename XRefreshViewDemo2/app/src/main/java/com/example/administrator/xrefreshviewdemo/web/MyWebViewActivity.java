package com.example.administrator.xrefreshviewdemo.web;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.example.administrator.xrefreshviewdemo.R;


public class MyWebViewActivity extends AppCompatActivity {


    public static final String KEY_URL = "url";
    public static final String KEY_TITLE = "title";
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        //地址获取
        String url = getIntent().getStringExtra(KEY_URL);
        //标题获取
        String title = getIntent().getStringExtra(KEY_TITLE);
        mFragmentManager = this.getSupportFragmentManager();
        openFragment(url, title);
    }

    public static void startActivity(Context context, String url, String title) {
        Intent starter = new Intent(context, MyWebViewActivity.class);
        starter.putExtra(KEY_URL, url);
        starter.putExtra(KEY_TITLE, title);
        context.startActivity(starter);
    }

    public static void startActivity(Context context, String url) {
        Intent starter = new Intent(context, MyWebViewActivity.class);
        starter.putExtra(KEY_URL, url);
        context.startActivity(starter);
    }

    private MyWebViewFragment mAgentWebFragment;

    private void openFragment(String url, String title) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        //传输url数据
        Bundle mBundle = new Bundle();
        mBundle.putString(MyWebViewFragment.KEY_URL, url);
        mBundle.putString(MyWebViewFragment.KEY_TITLE, title);
        //获取fragment实例
        mAgentWebFragment = MyWebViewFragment.getInstance(mBundle);
        ft.add(R.id.container_framelayout, mAgentWebFragment, MyWebViewFragment.class.getName());
        ft.commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //一定要保证 mAentWebFragemnt 回调
        mAgentWebFragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        MyWebViewFragment mAgentWebFragment = this.mAgentWebFragment;
        if (mAgentWebFragment != null) {
            FragmentKeyDown mFragmentKeyDown = mAgentWebFragment;
            if (mFragmentKeyDown.onFragmentKeyDown(keyCode, event))
                return true;
            else
                return super.onKeyDown(keyCode, event);
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
