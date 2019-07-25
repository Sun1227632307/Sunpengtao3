package com.example.app4.apis;

import com.example.app4.beans.Shuju;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MyApplications {
    String url="https://www.wanandroid.com/";
    @GET("project/list/1/json?cid=294")
    Observable<Shuju>getData();

}
