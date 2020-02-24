package com.dong.dialypractice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;


public class BaseRecyclerHolder<D> extends RecyclerView.ViewHolder {
    protected Context mContext;

    public BaseRecyclerHolder(View itemView) {
        super(itemView);
    }

    public BaseRecyclerHolder(int layout, Context context) {
        super(LayoutInflater.from(context).inflate(layout, null));
        mContext = context;
    }

    public void initData(int position, D d) {

    }

    public View getItemView() {
        return itemView;
    }
}
