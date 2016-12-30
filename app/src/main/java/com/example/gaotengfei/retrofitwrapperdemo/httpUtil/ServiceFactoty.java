package com.example.gaotengfei.retrofitwrapperdemo.httpUtil;


import android.util.Log;

import com.example.gaotengfei.retrofitwrapperdemo.httpUtil.converter.CustomGsonConverterFactory;
import com.example.gaotengfei.retrofitwrapperdemo.httpUtil.interceptor.TokenInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;


/**
 * 服务端接口工厂类.
 */
public class ServiceFactoty {
    public static final String Tag = "retrofitLog";
    public static <T> T getService(Class<T> clazz){
        Retrofit retrofit= createRetrofitService();
        return retrofit.create(clazz);
    }
    /**
     * Creates a retrofit service from an arbitrary class (clazz)
     *
     */
    public static Retrofit createRetrofitService() {
        /**
         * 拦截一个请求使用OkHttp里面的Interceptor
         */
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e(Tag, "logInterceptor=====>" + message);
            }
        });
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptor())//添加token
                .addInterceptor(logInterceptor)//添加消息拦截
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.dev.xxxxx.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(CustomGsonConverterFactory.create())

                //   .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }
}
