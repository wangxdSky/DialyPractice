package com.dong.dialypractice.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public abstract class CommonRecyclerViewAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    public List<T> mItemList;
    protected Context mContext;
    private int mLayoutId;

    public CommonRecyclerViewAdapter(Context context, List<T> list, int layoutId) {
        mItemList = list;
        mContext = context;
        mLayoutId = layoutId;
    }

    public void updateItemList(List<T> list) {
        mItemList = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = ViewHolder.getHolder(mContext, parent, mLayoutId);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        T item = position >= mItemList.size() ? null : mItemList.get(position);
        initView(holder, item, position);
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    /**
     * 更新条目信息
     *
     * @param holder
     * @param item
     */
    public abstract void initView(ViewHolder holder, T item, int position);

    public void removeItem(int position) {
        if (position > mItemList.size() - 1) {
            return;
        }
        mItemList.remove(position);
        if (mItemList.size() > position) {
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mItemList.size());
        } else {
            notifyDataSetChanged();
        }
    }
}
