package com.dong.dialypractice.adapter;


public interface MultiTypeItemSupport<T> {
    /**
     * 根据item，获取布局类别
     * @param position
     * @param item
     * @param dataSize 数据列表的书目，而不是recyclerview 的子view个数（两者有可能不想等，
     *                 比如实现加载更多功能）
     * @return
     */
    int getItemViewType(int position, T item, int dataSize);

    /**
     * 根据type类别，获取对应的布局id
     * @param type
     * @return
     */
    int getItemViewLayoutId(int type);
}
