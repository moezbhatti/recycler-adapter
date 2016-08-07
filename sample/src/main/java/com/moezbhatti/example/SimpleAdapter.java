package com.moezbhatti.example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.moezbhatti.recycleradapter.MBAdapter;
import com.moezbhatti.recycleradapter.MBViewHolder;

public class SimpleAdapter extends MBAdapter<String> {

    public SimpleAdapter(Context context) {
        super(context);
    }

    @Override
    public MBViewHolder<String> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new SimpleViewHolder(this, inflater.inflate(R.layout.list_item_simple, parent, false));
    }
}
