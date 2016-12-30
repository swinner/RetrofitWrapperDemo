package com.example.gaotengfei.retrofitwrapperdemo.httpUtil.interceptor;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @anthor: gaotengfei
 * @time: 2016/12/29
 * @tel: 18511913443
 * @desc: 添加请求的token
 */

public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        if (TextUtils.isEmpty("token")) {
            return chain.proceed(originalRequest);
        }
        Request tokenRequest = originalRequest.newBuilder().header("token", "gaotengfei").build();
        return chain.proceed(tokenRequest);
    }

}
