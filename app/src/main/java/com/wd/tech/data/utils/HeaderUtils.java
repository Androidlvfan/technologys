package com.wd.tech.data.utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderUtils implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Object userId = SpUtils.getInstance().getData("userId", "");
        Object sessionId = SpUtils.getInstance().getData("sessionId", "");
        Request request = chain.request();
        Request build = request.newBuilder()
                .addHeader("userId", (String) userId)
                .addHeader("sessionId", (String) sessionId)
                .build();
        Response proceed = chain.proceed(build);
        return proceed;
    }
}
