package com.example.app2.apis;

import com.example.app2.beans.Fuli;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MyAppplication {
    String url="https://gank.io/api/";
    @GET("data/%E7%A6%8F%E5%88%A9/20/1")
    Observable<Fuli>getData();
}
