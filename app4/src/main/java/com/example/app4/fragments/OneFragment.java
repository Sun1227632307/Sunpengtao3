package com.example.app4.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app4.R;
import com.example.app4.adapters.RecycViewAdatper;
import com.example.app4.apis.MyApplications;
import com.example.app4.beans.Shuju;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class OneFragment extends Fragment {
    private RecyclerView Rec;
    ArrayList<Shuju.DataBean.DatasBean> datasBeans = new ArrayList<>();
    private RecycViewAdatper recycViewAdatper;
    private static final String TAG = "OneFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_onefragment_item1, null);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(MyApplications.url)
                .build();
        MyApplications myApplications = retrofit.create(MyApplications.class);
        Observable<Shuju> data = myApplications.getData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Shuju>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Shuju value) {
                        List<Shuju.DataBean.DatasBean> datas = value.getData().getDatas();
                        datasBeans.addAll(datas);
                        recycViewAdatper.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+e.getMessage() );
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView(View view) {
        Rec = (RecyclerView) view.findViewById(R.id.Rec);
        Rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        Rec.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        recycViewAdatper = new RecycViewAdatper(datasBeans, getActivity());
        Rec.setAdapter(recycViewAdatper);
    }
}
