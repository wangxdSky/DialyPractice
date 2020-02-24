package com.dong.dialypractice;


import android.os.Bundle;
import android.os.Handler;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.dong.dialypractice.adapter.CustomRvAdapter;
import com.dong.dialypractice.custome.PullRefreshRecyclerView;
import com.dong.dialypractice.modle.NomalItemBean;
import com.dong.pratice.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 自定义RecyclerView 支持
 * 上拉加载下拉刷新
 *
 * @author wangxd
 * @data 2020/1/9 @time 14:37
 */
public class CustomRecyclerViewActivity extends BaseActivity {
    @BindView(R.id.real_pull_refresh_view)
    PullRefreshRecyclerView recyclerView;
    private ArrayList<NomalItemBean> mBodies;
    private LinearLayoutManager mLayoutManager;
    Handler mHandler = new Handler();
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_custom_recycler_view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        mBodies = new ArrayList<>();
        for (int j = 0; j < 17; j++) {
            mBodies.add(new NomalItemBean("test" + j * 5, 100));
        }

        mLayoutManager = new LinearLayoutManager(this);
        CustomRvAdapter mMyAdapter = new CustomRvAdapter(this, mBodies);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mMyAdapter);

        recyclerView.setOnPullListener(new PullRefreshRecyclerView.OnPullListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mBodies.add(0, new NomalItemBean("新数据" + i++, 100));
                        recyclerView.refreshFinish();
                    }
                }, 3000);
            }

            @Override
            public void onLoadMore() {
                final List<NomalItemBean> mMore = new ArrayList<>();

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 3; i++) {
                            mMore.add(new NomalItemBean("more+++" + i, 100));
                        }
                        mBodies.addAll(mMore);
                        recyclerView.loadMroeFinish();
                    }
                }, 1500);
            }
        });
    }
}
