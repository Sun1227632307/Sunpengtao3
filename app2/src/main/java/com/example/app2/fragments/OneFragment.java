package com.example.app2.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.R;
import com.example.app2.adapters.RecycViewAdapter;
import com.example.app2.apis.MyApplication;
import com.example.app2.beans.Fuli;
import com.example.app2.beans.ResultsBean;
import com.example.app2.db.ResultsBeanDao;
import com.example.app2.present.PresentPl;
import com.example.app2.view.ICView;

import java.util.ArrayList;
import java.util.List;

public class OneFragment extends Fragment implements ICView {
    private RecyclerView Rec;
    ArrayList<ResultsBean> resultsBeans = new ArrayList<>();
    private RecycViewAdapter recycViewAdapter;
    private static final String TAG = "OneFragment";
    private PresentPl presentPl;
    private ResultsBeanDao resultsBeanDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_onefragment_item1, null);
        resultsBeanDao = MyApplication.getInstance().getDaoSession().getResultsBeanDao();
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        presentPl = new PresentPl(this);
        presentPl.GetData();
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
                Toast.makeText(getActivity(), "长按收藏", Toast.LENGTH_SHORT).show();
                ResultsBean resultsBean = resultsBeans.get(position);
                resultsBeanDao.insertOrReplace(resultsBean);
            }
        });
        recycViewAdapter.setA(new RecycViewAdapter.A() {
            @Override
            public void Onclicked(View view, final int position) {
                RadioButton rb = view.findViewById(R.id.rb);
                rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if (b) {
                            ResultsBean resultsBean = resultsBeans.get(position);
                            resultsBeanDao.insertOrReplace(resultsBean);
                        }
                    }
                });
            }
        });
    }

    @Override
    public void Ok(List<ResultsBean> list) {
        resultsBeans.addAll(list);
        recycViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void No(String error) {
        Log.e(TAG, "No: " + error);
        Toast.makeText(getActivity(), "数据错误" + error, Toast.LENGTH_SHORT).show();
    }
}
