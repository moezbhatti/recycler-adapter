package com.moezbhatti.recycleradapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class MBViewHolder<DataType> extends RecyclerView.ViewHolder {

    protected MBAdapter<DataType> mAdapter;
    protected Context mContext;

    public MBViewHolder(MBAdapter<DataType> adapter, View view) {
        super(view);
        mAdapter = adapter;
        mContext = adapter.getContext();
    }

    public abstract void bind(int position);
}
