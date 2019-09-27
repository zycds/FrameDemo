package com.zhangyc.framedemo.serverapi;

import com.zhangyc.framedemo.constant.Constants;
import com.zhangyc.framedemo.entity.ArticleList;
import com.zhangyc.framedemo.entity.BannerBean;
import com.zhangyc.framedemo.entity.PublicAddress;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpApiManager {

    private ServerApi mServerApi;
    private static HttpApiManager instance;

    private HttpApiManager() {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(Constants.SERVER_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(Constants.SERVER_TIMEOUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        Request request = chain.request();
                        Request.Builder newBuilder = request.newBuilder();
                        newBuilder.header("Content-Type", "application/json");
                        newBuilder.header("Accept", "application/json");
                        newBuilder.method(request.method(), request.body());
                        return chain.proceed(request);
                    }
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.SERVER_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build();

        mServerApi = retrofit.create(ServerApi.class);
    }

    public static HttpApiManager getInstance() {
        if (instance == null) {
            synchronized (HttpApiManager.class) {
                if (instance == null) instance = new HttpApiManager();
            }
        }
        return instance;
    }

    public Observable<List<PublicAddress>> getPublicAddressList() {
        return mServerApi.getPublicAddressList().compose(RxJavaHelper.<List<PublicAddress>>handlerServerResult());
    }

    public Observable<ArticleList> getArticleList(int page) {
        return mServerApi.getArticleList(page).compose(RxJavaHelper.<ArticleList>handlerServerResult());
    }

    public Observable<List<BannerBean>> getBanners() {
        return mServerApi.getBanners().compose(RxJavaHelper.<List<BannerBean>>handlerServerResult());
    }

}
