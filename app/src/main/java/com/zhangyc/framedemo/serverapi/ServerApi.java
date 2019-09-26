package com.zhangyc.framedemo.serverapi;

import com.zhangyc.framedemo.entity.PublicAddress;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ServerApi {


    @GET("/wxarticle/chapters/json")
    Observable<BaseServerResult<List<PublicAddress>>> getPublicAddressList();


}
