package com.dong.dialypractice.adapter;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;


public abstract class MultiTypeRecyclerViewAdapter<T> extends CommonRecyclerViewAdapter<T> {
    protected MultiTypeItemSupport<T> mTypeSupport;
    public MultiTypeRecyclerViewAdapter(Context context, List<T> list, MultiTypeItemSupport<T> typeSupport) {
        super(context, list, -1);
        mTypeSupport=typeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        return mTypeSupport.getItemViewType(position,position>=mItemList.size()?null:mItemList.get(position),mItemList.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder=ViewHolder.getHolder(mContext,parent,mTypeSupport.getItemViewLayoutId(viewType));
        return holder;
    }
}
