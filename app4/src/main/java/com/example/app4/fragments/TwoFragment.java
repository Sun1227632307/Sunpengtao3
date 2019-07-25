package com.example.app4.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.app4.R;
import com.example.app4.adapters.RecycViewAdatper;
import com.example.app4.apis.MyApplications;
import com.example.app4.beans.Shuju;

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

public class TwoFragment extends Fragment {
    private RecyclerView Rec;
    private RecycViewAdatper recycViewAdatper;
    ArrayList<Shuju.DataBean.DatasBean> datasBeans = new ArrayList<>();
    private static final String TAG = "TwoFragment";

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            initData();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_twofragment_item2, null);
        setHasOptionsMenu(true);//activity和fragment不能共用选项时候设置
        initView(view);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.add(1,1,1,"线性布局");
        menu.add(2,2,2,"网格布局");
        menu.add(3,3,3,"瀑布流布局");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==1){
            Toast.makeText(getActivity(), "线性布局", Toast.LENGTH_SHORT).show();
            Rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        if (item.getItemId()==2){
            Toast.makeText(getActivity(), "网格布局", Toast.LENGTH_SHORT).show();
            Rec.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        }
        if (item.getItemId()==3){
            Toast.makeText(getActivity(), "瀑布流布局", Toast.LENGTH_SHORT).show();
            Rec.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));
        }
        return super.onOptionsItemSelected(item);
    }

}
