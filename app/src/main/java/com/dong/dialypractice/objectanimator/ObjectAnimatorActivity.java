package com.dong.dialypractice.objectanimator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;

import com.dong.pratice.R;

/**
 * 属性动画测试
 *
 * @author wangxd
 * @data 2020/3/6 @time 11:09
 */
public class ObjectAnimatorActivity extends AppCompatActivity {
    private FrameLayout mMainView;
    private SplashView splashView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator);

        //将动画层盖在实际的操作图层上
        mMainView = new FrameLayout(this);
        ContentView contentView = new ContentView(this);
        mMainView.addView(contentView);
        splashView = new SplashView(this);
        mMainView.addView(splashView);

        setContentView(mMainView);
        //后台开始加载数据
        startLoadData();



    }
    //    ------------------------加载动画--------------------------------------
    Handler handler = new Handler();
    private void startLoadData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //数据加载完毕，进入主界面--->开启后面的两个动画
                splashView.splashDisappear();
            }
        },5000);//延迟时间
    }
        }
