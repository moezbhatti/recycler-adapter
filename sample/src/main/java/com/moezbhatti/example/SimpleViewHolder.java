package com.moezbhatti.example;

import android.view.View;
import android.widget.TextView;
import com.moezbhatti.recycleradapter.MBAdapter;
import com.moezbhatti.recycleradapter.MBViewHolder;

public class SimpleViewHolder extends MBViewHolder<String> {

    private TextView mLabel;

    public SimpleViewHolder(MBAdapter<String> adapter, View view) {
        super(adapter, view);
        mLabel = (TextView) view.findViewById(R.id.label);
    }

    @Override
    public void bind(int position) {
        String s = mAdapter.getItem(position);

        mLabel.setText(s);
    }
}
