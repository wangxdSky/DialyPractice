package com.dong.dialypractice.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class ViewHolder extends RecyclerView.ViewHolder {
    // 以viewId作为key，方便下次查找
    private SparseArray<View> mViews;
    private Context mContext;

    public ViewHolder(int layout, Context context) {
        this(LayoutInflater.from(context).inflate(layout, null), context);
    }

    public ViewHolder(View itemView, Context context) {
        super(itemView);
        mContext = context;
        mViews = new SparseArray<View>();
    }

    public static ViewHolder getHolder(Context context, ViewGroup parent, int layoutId) {
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new ViewHolder(view, context);
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public void setText(int viewId, String text) {
        TextView textView = getView(viewId);
        if (textView != null) {
            textView.setText(text);
            textView.setVisibility(View.VISIBLE);
        }
    }

    public void setImage(int viewId, int resId) {
        ImageView iv = getView(viewId);
        if (iv != null) {
            iv.setImageResource(resId);
            iv.setVisibility(View.VISIBLE);
        }
    }

    public void setItemClickListener(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);
    }

    public void setViewClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        if (view != null)
            view.setOnClickListener(listener);
    }

    public View getmItemView() {
        return itemView;
    }

    public void initData(int position) {
    }

    public void initData(int position, Object o) {
        initData(position);
    }
}
