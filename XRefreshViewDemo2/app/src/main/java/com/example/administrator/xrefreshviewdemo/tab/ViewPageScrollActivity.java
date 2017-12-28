package com.example.administrator.xrefreshviewdemo.tab;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmcc.healthlibrary.utils.ToastUtil;
import com.example.administrator.xrefreshviewdemo.R;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.shizhefei.view.viewpager.SViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ViewPageScrollActivity extends AppCompatActivity {
    private IndicatorViewPager indicatorViewPager;
    private View centerView;
    private FixedIndicatorView indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page_scroll);
        SViewPager viewPager = (SViewPager) findViewById(R.id.tabmain_viewPager);
        indicator = (FixedIndicatorView) findViewById(R.id.tabmain_indicator);
        //设置点击之后的文字切换
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(Color.RED, Color.GRAY));

        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        indicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        // 禁止viewpager的滑动事件
        viewPager.setCanScroll(false);
        // 设置viewpager保留界面不重新加载的页面数量
        viewPager.setOffscreenPageLimit(4);

        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserEvent(String event) {
        if ("fragment".equals(event)) {
            ToastUtil.show(this, "收到Fragment消息");
        }
    }

    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
        private String[] tabNames = {"主页", "消息", "发现", "我"};
        //设置tab的icon
//        private int[] tabIcons = {R.drawable.maintab_1_selector, R.drawable.maintab_2_selector, R.drawable.maintab_3_selector,
//                R.drawable.maintab_4_selector};

        private int[] tabIcons = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
        private LayoutInflater inflater;

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            inflater = LayoutInflater.from(getApplicationContext());
        }

        @Override
        public int getCount() {
            return tabNames.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.tab_main, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(tabNames[position]);
            textView.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[position], 0, 0);
            return textView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            Fragment mainFragment = null;
            if (position == 0) {
                mainFragment = new FirstLayerFragment();
                Bundle bundle = new Bundle();
                bundle.putString(FirstLayerFragment.INTENT_STRING_TABNAME, tabNames[position]);
                bundle.putInt(FirstLayerFragment.INTENT_INT_INDEX, position);
                mainFragment.setArguments(bundle);
            } else {
                mainFragment = new SecondLayerFragment();
                Bundle bundle = new Bundle();
                bundle.putString(FirstLayerFragment.INTENT_STRING_TABNAME, tabNames[position]);
                bundle.putInt(FirstLayerFragment.INTENT_INT_INDEX, position);
                mainFragment.setArguments(bundle);
            }
            return mainFragment;
        }
    }

    public static void startActivity(Context context) {
        Intent starter = new Intent(context, ViewPageScrollActivity.class);
        context.startActivity(starter);
    }

//    @Override
//    public void onBackPressed() {
//        EventBus.getDefault().post("act");
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
