package com.wd.tech.data.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.wd.tech.data.app.App;
import com.wd.tech.data.constant.Constant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        SharedPreferences user = App.getAppContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                        String userId = user.getString("userId", "");
                        String sessionId = user.getString("sessionId", "");
                        Request.Builder builder1 = request.newBuilder();
                        builder1.method(request.method(),request.body());
                        if(!TextUtils.isEmpty(userId)&&!TextUtils.isEmpty(sessionId)){
                            builder1.addHeader("userId",userId);
                            builder1.addHeader("sessionId",sessionId);
                        }
                        Request build = builder1.build();
                        return chain.proceed(build);
                    }
                })
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)

                /*.addInterceptor(new LogInterceptor())*/;
        //支持https证书和域名认证
       // OkHttpClient.Builder newBuilder = httpsUtils.setCertificateForOkhttp(builder);
        return builder.build();
    }

    private RetrofitUtils(){

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
