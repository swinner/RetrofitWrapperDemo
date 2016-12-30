package com.example.gaotengfei.retrofitwrapperdemo.httpUtil.rxjava;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @anthor: gaotengfei
 * @time: 2016/12/29
 * @tel: 18511913443
 * @desc:  进行Observable转换
 */

public class ApplySchedulers<T> implements Observable.Transformer<T, T> {

    @Override
    public Observable<T> call(Observable<T> observable) {
        return observable
                .timeout(1, TimeUnit.SECONDS)// 任务超时设置为8秒
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}