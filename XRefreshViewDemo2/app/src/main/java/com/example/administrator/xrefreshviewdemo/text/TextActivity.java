package com.example.administrator.xrefreshviewdemo.text;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.xrefreshviewdemo.R;

public class TextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
    }
    public static void startActivity(Context context) {
        Intent starter = new Intent(context, TextActivity.class);
        context.startActivity(starter);
    }
}
