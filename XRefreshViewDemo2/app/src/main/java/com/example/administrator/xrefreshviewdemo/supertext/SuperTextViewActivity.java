package com.example.administrator.xrefreshviewdemo.supertext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.allen.library.SuperButton;
import com.allen.library.SuperTextView;
import com.example.administrator.xrefreshviewdemo.R;

/**
 * https://github.com/lygttpod/SuperTextView
 */
public class SuperTextViewActivity extends AppCompatActivity implements View.OnClickListener {

    private SuperTextView tv_yinhangka;
    private SuperButton superbutton;
    private LinearLayout layout_superbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_text_view);
        initView();
        tv_yinhangka.setRightString("tv_123123123123sdfafdadf123");
        tv_yinhangka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_yinhangka.setRightString("tv_123123123123sdfafdadf123");
            }
        });
    }

    public static void startActivity(Context context) {
        Intent starter = new Intent(context, SuperTextViewActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }

    private void initView() {
        tv_yinhangka = (SuperTextView) findViewById(R.id.tv_yinhangka);
        superbutton = (SuperButton) findViewById(R.id.superbutton);
        superbutton.setClickable(false);
//        superbutton.setOnClickListener(this);
        layout_superbutton = (LinearLayout) findViewById(R.id.layout_superbutton);
        layout_superbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.layout_superbutton:
                Toast.makeText(this, "布局点击了", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
