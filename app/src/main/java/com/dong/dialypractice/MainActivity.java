package com.dong.dialypractice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dong.dialypractice.adapter.CommonRecyclerViewAdapter;
import com.dong.dialypractice.adapter.ViewHolder;
import com.dong.dialypractice.modle.HomeItemBean;
import com.dong.pratice.R;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private List<HomeItemBean> itemList = new ArrayList<>();
    private CommonRecyclerViewAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        itemList.clear();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvList.setLayoutManager(manager);
        homeAdapter = new CommonRecyclerViewAdapter<HomeItemBean>(MainActivity.this, itemList, R.layout.item_home_item_layout) {
            @Override
            public void initView(ViewHolder holder, HomeItemBean item, int position) {
                TextView tvName = holder.getView(R.id.tv_name);
                tvName.setText(item.getItemName());

                holder.getmItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = null;
                        switch (position) {
                            case 0:
                                intent = new Intent(MainActivity.this, CustomRecyclerViewActivity.class);
                                startActivity(intent);
                                break;
                            case 1:
                                intent = new Intent(MainActivity.this,MainActivity.class);
                                startActivity(intent);
                                break;
                        }
                    }
                });
            }
        };
        rvList.setAdapter(homeAdapter);
    }

    @Override
    public void initData() {
        itemList.add(new HomeItemBean("自定义RecyclerView"));
        itemList.add(new HomeItemBean("仿朋友圈效果--大图浏览"));
        if (homeAdapter != null) homeAdapter.notifyDataSetChanged();
    }
}
