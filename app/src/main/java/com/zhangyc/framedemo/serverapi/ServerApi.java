package com.zhangyc.framedemo.serverapi;

import com.zhangyc.framedemo.entity.ArticleList;
import com.zhangyc.framedemo.entity.BannerBean;
import com.zhangyc.framedemo.entity.PublicAddress;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServerApi {


    @GET("/wxarticle/chapters/json")
    Observable<BaseServerResult<List<PublicAddress>>> getPublicAddressList();


    @GET("https://www.wanandroid.com/article/list/{page}/json")
    Observable<BaseServerResult<ArticleList>> getArticleList(@Path("page")int page);


    @GET("https://www.wanandroid.com/banner/json")
    Observable<BaseServerResult<List<BannerBean>>> getBanners();
}
