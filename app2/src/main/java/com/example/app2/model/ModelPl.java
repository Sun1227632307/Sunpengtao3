package com.example.app2.model;

import com.example.app2.apis.MyAppplication;
import com.example.app2.beans.Fuli;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModelPl implements ICModel {
    @Override
    public void GetData(final ICBack icBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyAppplication.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyAppplication myAppplication = retrofit.create(MyAppplication.class);
        Observable<Fuli> data = myAppplication.getData();
        data.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Fuli>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Fuli value) {
                        icBack.Ok(value.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {
                        icBack.No(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
