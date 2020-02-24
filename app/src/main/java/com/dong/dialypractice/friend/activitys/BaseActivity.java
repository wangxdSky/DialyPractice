package com.dong.dialypractice.friend.activitys;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dong.pratice.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}
