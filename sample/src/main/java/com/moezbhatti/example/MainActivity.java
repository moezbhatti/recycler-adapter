package com.moezbhatti.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.moezbhatti.recycleradapter.AdapterFactory;
import com.moezbhatti.recycleradapter.MBAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("test string " + i);
        }

        MBAdapter<String> adapter = AdapterFactory.create(this, SimpleViewHolder.class, R.layout.list_item_simple);
        adapter.setItems(list);

        mList = (RecyclerView) findViewById(R.id.list);
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.setAdapter(adapter);
    }
}
