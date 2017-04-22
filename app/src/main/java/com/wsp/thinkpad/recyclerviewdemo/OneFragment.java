package com.wsp.thinkpad.recyclerviewdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${吴心良}
 * on 2017/4/22.
 * description:
 */

public class OneFragment extends Fragment {
    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tab_one, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),2));
        setData();
        return view;
    }

    private void setData() {
        List<String> list=new ArrayList<>();
        for (int i=0;i<25;i++){
            list.add("我等哈ISD啊啊"+i);
        }
        adapter=new MyRecyclerViewAdapter(list,getActivity().getApplicationContext());
        recyclerView.setAdapter(adapter);
    }
}
