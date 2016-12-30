package com.example.gaotengfei.retrofitwrapperdemo.httpUtil.rxjava;

import android.content.Context;
import android.widget.Toast;

import com.example.gaotengfei.retrofitwrapperdemo.httpUtil.exception.CustomException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;

/**
 * @anthor: gaotengfei
 * @time: 2016/12/29
 * @tel: 18511913443
 * @desc:处理异常情况，同时把onNext()进行抽象
 */

public abstract class BaseSubscriber<T> extends Subscriber<T> {
    private  Context ctx;

    public BaseSubscriber(Context ctx) {
        this.ctx = ctx;
    }
    //统一处理网络请求失败的情况
    @Override
    public void onError(Throwable throwable) {
        Throwable e = throwable;
        while (throwable.getCause() != null) {
            e = throwable;
            throwable = throwable.getCause();
        }
        if (e instanceof ConnectException || e instanceof SocketTimeoutException || e instanceof TimeoutException) {
            onNetworkException(e);
        } else if (e instanceof CustomException) {
            onCustomException((CustomException) e);
        } else {
           // onUnknownException(e);
        }
    }
    @Override
    public void onCompleted() {}
    @Override
    public abstract void onNext(T t);

    public void onNetworkException(Throwable e) {
        if (ctx != null) {
            Toast.makeText(ctx, "网络较慢，请稍候...", Toast.LENGTH_SHORT).show();
        }
    }
    public void onCustomException(CustomException e) {
        e.printStackTrace();
        if (ctx != null) {
            Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void onUnknownException(Throwable e) {
        e.printStackTrace();
        if (ctx != null) {
            Toast.makeText(ctx,"未知错误", Toast.LENGTH_SHORT).show();
        }
    }
}
