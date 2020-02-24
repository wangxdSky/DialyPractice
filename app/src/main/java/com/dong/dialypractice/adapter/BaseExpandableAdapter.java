package com.dong.dialypractice.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public abstract class BaseExpandableAdapter<K,V> extends BaseExpandableListAdapter {
    protected int groupLayout;
    protected int childLayout;
    protected Context context;
    protected ArrayList<K> groupList =new ArrayList<K>();
    protected ArrayList<ArrayList<V>> childList=new ArrayList<ArrayList<V>>();

    public BaseExpandableAdapter(int groupLayout, int childLayout, Context context){
        this.groupLayout=groupLayout;
        this.childLayout=childLayout;
        this.context=context;
    }

    public void setList(ArrayList<K> groupList, ArrayList<ArrayList<V>> childList){
        this.groupList=groupList;
        this.childList=childList;
    }

    public void setGroupList(ArrayList<K> groupList){
        this.groupList=groupList;
    }

    public void setChildList(ArrayList<ArrayList<V>> childList){
        this.childList=childList;
    }

    @Override
    public int getGroupCount() {
        if(groupList ==null)return 0;
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(childList==null)return 0;
        return childList.get(groupPosition).size();
    }

    @Override
    public K getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public V getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(groupLayout,null);
        }
        initGroupView(groupPosition,convertView);
        return convertView;
    }

    public abstract void initGroupView(int groupPosition, View convertView);

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(childLayout,null);
        }
        initChildView(groupPosition,childPosition,convertView,isLastChild);
        return convertView;
    }

    public abstract void initChildView(int groupPosition, int childPosition, View convertView, boolean isLastChild);

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public static class ViewHolder{
        public static <T extends View> T getView(View view, int id){
            SparseArray<View> holder= (SparseArray<View>) view.getTag();
            if(holder==null){
                holder=new SparseArray<View>();
                view.setTag(holder);
            }
            View child=holder.get(id);
            if(child==null){
                child=view.findViewById(id);
                holder.put(id,child);
            }
            return (T) child;
        }
        public static void setText(View view, int id, String text){
            SparseArray<View> holder= (SparseArray<View>) view.getTag();
            if(holder==null){
                holder=new SparseArray<View>();
                view.setTag(holder);
            }
            View child=holder.get(id);
            if(child==null){
                child=view.findViewById(id);
                holder.put(id,child);
            }
            ((TextView)child).setText(text);
        }
    }
}
