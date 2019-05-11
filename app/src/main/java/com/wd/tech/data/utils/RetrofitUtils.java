package com.wd.tech.data.utils;

import com.wd.tech.data.constant.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 14:30
 * @fileName:RetrofitUtils
 * @packageName:com.wd.tech.dimensionalitytechnology.data.utils
 */
public class RetrofitUtils {

    private Retrofit retrofit;

    private Retrofit retrofit(String baseurl){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new LogInterceptor())
                .build();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(buildOkhttpClinet())
                .baseUrl(Constant.BASE_URL)
                .build();

        return builder.build();
    }

    private OkHttpClient buildOkhttpClinet(){
       // HttpsUtils httpsUtils = new HttpsUtils(App.getAppContext());
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new LogInterceptor());
        //支持https证书和域名认证
       // OkHttpClient.Builder newBuilder = httpsUtils.setCertificateForOkhttp(builder);
        return builder.build();
    }

    private RetrofitUtils(String baseurl){
        this.retrofit = retrofit(baseurl);
    }

    public static RetrofitUtils getInstance(){
        return RetroHolder.utils;
    }

    public static class RetroHolder{
        private static RetrofitUtils utils = new RetrofitUtils(Constant.BASE_URL);
    }

    public <T> T create(Class<T> clazz){
        return retrofit.create(clazz);
    }
}
