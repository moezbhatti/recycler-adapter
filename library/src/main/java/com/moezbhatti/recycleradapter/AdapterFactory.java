package com.moezbhatti.recycleradapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;

public class AdapterFactory {

    public static <T extends MBViewHolder<S>, S> MBAdapter<S> create(Context context, final Class<T> viewholder, @LayoutRes final int layoutRes) {
        return new MBAdapter<S>(context) {
            @Override
            public T onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
                View view = inflater.inflate(layoutRes, parent, false);

                try {
                    Constructor constructor = viewholder.getConstructor(MBAdapter.class, View.class);
                    return (T) constructor.newInstance(this, view);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return (T) new MBViewHolder<S>(this, view) {
                    @Override
                    public void bind(int pMBition) {
                    }
                };
            }
        };
    }
}
