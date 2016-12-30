package com.example.gaotengfei.retrofitwrapperdemo.httpUtil.rxjava;

import com.example.gaotengfei.retrofitwrapperdemo.base.BaseActivity;

import rx.Observable;
import rx.Subscriber;

/**
 * @anthor: gaotengfei
 * @time: 2016/12/30
 * @tel: 18511913443
 * @desc:进行Subscriber转换，绑定Activity生命周期
 */

public class BindActivityOperator <T> implements Observable.Operator<T, T> {

    private BaseActivity activity;
    private ActivityLifecycle unsubscribeOn;

    public BindActivityOperator(BaseActivity activity) {
        this(activity, ActivityLifecycle.OnDestroy);
    }

    public BindActivityOperator(BaseActivity activity, ActivityLifecycle unsubscribeOn) {
        this.activity = activity;
        this.unsubscribeOn = unsubscribeOn;
    }

    @Override
    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        activity.addSubscriber(subscriber, unsubscribeOn);
        return subscriber;
    }
}
