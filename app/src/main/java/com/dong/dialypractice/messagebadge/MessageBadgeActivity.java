package com.dong.dialypractice.messagebadge;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.dong.pratice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 消息小红点
 *
 * @author wangxd
 * @data 2020/2/29 @time 10:47
 */
public class MessageBadgeActivity extends AppCompatActivity {
    @BindView(R.id.iv_lll)
    ImageView ivLll;
    @BindView(R.id.iv_222)
    ImageView iv222;
    @BindView(R.id.iv_333)
    ImageView iv333;
    @BindView(R.id.iv_444)
    ImageView iv444;
    @BindView(R.id.iv_555)
    ImageView iv555;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.iv_666)
    ImageView iv666;
    @BindView(R.id.rl_top2)
    RelativeLayout rlTop2;
    @BindView(R.id.rl_top3)
    RelativeLayout rlTop3;
    @BindView(R.id.rl_top4)
    RelativeLayout rlTop4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_badge);
        ButterKnife.bind(this);

        BadgeFactory.createOval(this).setBadgeBackgroundColor(Color.RED).setBadgeText("99").setBindView(rlTop).setBadgeGravity(Gravity.END | Gravity.TOP);

        BadgeFactory.createOval2(this).setBadgeBackgroundColor(Color.RED).setBadgeText("99+").setBindView(rlTop2).setBadgeGravity(Gravity.END | Gravity.TOP);
        BadgeFactory.createDot(this).setBadgeBackgroundColor(Color.RED).setBindView(rlTop4).setBadgeGravity(Gravity.END | Gravity.TOP);

//        BadgeFactory.createCircle(this).setBadgeBackgroundColor(Color.RED).setBadgeText("9").setBindView(rlTop3).setBadgeGravity(Gravity.END | Gravity.TOP);


        BadgeFactory.createDot(this).setBadgeBackgroundColor(Color.RED).setBindView(iv444);

        BadgeFactory.createCircle(this).setBadgeBackgroundColor(Color.RED).setBadgeText(1).setBindView(ivLll);

//        BadgeFactory.createOval(this).setBadgeBackgroundColor(Color.RED).setBadgeText("99+").setBindView(iv333);

        BadgeFactory.createCircle(this).setBadgeBackgroundColor(Color.RED).setBadgeText("9").setBindView(iv222);

    }
}
