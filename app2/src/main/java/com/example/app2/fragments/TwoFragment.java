package com.example.app2.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.Main2Activity;
import com.example.app2.Main3Activity;
import com.example.app2.R;
import com.example.app2.adapters.RecycViewAdapter;
import com.example.app2.apis.MyApplication;
import com.example.app2.beans.ResultsBean;
import com.example.app2.db.ResultsBeanDao;

import java.util.ArrayList;
import java.util.List;

public class TwoFragment extends Fragment {
    private RecyclerView Rec;
    private RecycViewAdapter recycViewAdapter;
    private ResultsBeanDao resultsBeanDao;
    ArrayList<ResultsBean> resultsBeans = new ArrayList<>();


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            initData();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_twofragment_item2, null);
        resultsBeanDao = MyApplication.getInstance().getDaoSession().getResultsBeanDao();
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        List<ResultsBean> resultsBeanList = resultsBeanDao.loadAll();
            resultsBeans.addAll(resultsBeanList);
            recycViewAdapter.notifyDataSetChanged();
    }




    private void initView(View view) {
        Rec = (RecyclerView) view.findViewById(R.id.Rec);
        Rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        Rec.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        recycViewAdapter = new RecycViewAdapter(resultsBeans, getActivity());
        Rec.setAdapter(recycViewAdapter);
        recycViewAdapter.setA(new RecycViewAdapter.A() {
            @Override
            public void Onclicked(View view, int position) {
                Intent intent = new Intent(getActivity(), Main3Activity.class);
                intent.putExtra("tupian",resultsBeans.get(position).getUrl());
                intent.putExtra("name",resultsBeans.get(position).getDesc());
                intent.putExtra("mima",resultsBeans.get(position).getSource());
                getActivity().startActivity(intent);
            }
        });
    }
}
