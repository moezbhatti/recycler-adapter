package com.moezbhatti.recycleradapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Base implementation of RecyclerView.Adapter to use with simple lists. Automatically handles
 * internal data lists and some other boilerplate for data insertion/deletion, etc.
 * <p>
 * Does not support ViewTypes
 */
public abstract class MBAdapter<DataType> extends RecyclerView.Adapter<MBViewHolder> {

    protected Context mContext;
    protected ArrayList<DataType> mData;
    protected ArrayList<Boolean> mSelection;

    public MBAdapter(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    private void verifyArrays() {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        if (mSelection == null) {
            mSelection = new ArrayList<>();
        }
    }

    public void setItems(ArrayList<DataType> data) {
        mData = data;

        verifyArrays();

        mSelection.clear();
        for (int i = 0; i < mData.size(); i++) mSelection.add(false);
        notifyDataSetChanged();
    }

    public void addItems(ArrayList<DataType> data) {
        verifyArrays();

        int positionStart = mData.size() + 1;
        mData.addAll(data);
        for (int i = 0; i < data.size(); i++) mSelection.add(false);
        notifyItemRangeInserted(positionStart, data == null ? 0 : data.size());
    }

    public void addItem(DataType data) {
        verifyArrays();
        int position = mData.size() + 1;
        mData.add(data);
        mSelection.add(false);
        notifyItemInserted(position);
    }

    public void addItem(int position, DataType data) {
        verifyArrays();
        mData.add(position, data);
        mSelection.add(false);
        notifyItemInserted(position);
    }

    public void moveUp(int position) {
        verifyArrays();

        if (position > 0 && position < mData.size()) {
            Collections.swap(mData, position, position - 1);
            Collections.swap(mSelection, position, position - 1);
            notifyItemMoved(position, position - 1);
            notifyItemChanged(position);
            notifyItemChanged(position - 1);
        }
    }

    public void moveDown(int position) {
        verifyArrays();

        if (position >= 0 && position < mData.size() - 1) {
            Collections.swap(mData, position, position + 1);
            Collections.swap(mSelection, position, position + 1);
            notifyItemMoved(position, position + 1);
            notifyItemChanged(position);
            notifyItemChanged(position + 1);
        }
    }

    public void replaceItem(int position, DataType data) {
        mData.set(position, data);
        notifyItemChanged(position);
    }

    public void removeItem(DataType data) {
        int position = mData.indexOf(data);
        if (position != -1) {
            mData.remove(data);
            mSelection.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void removeItem(int position) {
        mData.remove(position);
        mSelection.remove(position);
        notifyItemRemoved(position);
    }

    public void setSelected(int position, boolean selected) {
        mSelection.set(position, selected);
        notifyItemChanged(position);
    }

    public boolean isSelected(int position) {
        return mSelection.get(position);
    }

    public DataType getItem(int position) {
        return mData.get(position);
    }

    public ArrayList<DataType> getItems() {
        return mData;
    }

    public void clear() {
        setItems(new ArrayList<DataType>());
    }

    @Override
    public MBViewHolder<DataType> onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return onCreateViewHolder(inflater, parent);
    }

    public abstract MBViewHolder<DataType> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent);

    @Override
    public void onBindViewHolder(MBViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
