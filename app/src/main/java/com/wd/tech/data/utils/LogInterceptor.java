package com.wd.tech.data.utils;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 14:36
 * @fileName:LogInterceptor
 * @packageName:com.wd.tech.dimensionalitytechnology.data.utils
 */
public class LogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        LogPaint(request);
        Response response = chain.proceed(request);
        LogPaint(request);
        return response;
    }

    private void LogPaint(Request request) {
        Log.d("LogInterceptor", "request:" + request);
        Log.d("LogInterceptor", "System.nanoTime():" + System.nanoTime());
        Log.d("LogInterceptor", request.method());
    }
}
