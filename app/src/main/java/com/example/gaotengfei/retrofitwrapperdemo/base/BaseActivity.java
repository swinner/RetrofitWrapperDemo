package com.example.gaotengfei.retrofitwrapperdemo.base;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.gaotengfei.retrofitwrapperdemo.httpUtil.rxjava.ActivityLifecycle;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import rx.Subscriber;

/**
 * @anthor: gaotengfei
 * @time: 2016/12/28
 * @tel: 18511913443
 * @desc:
 */

public class BaseActivity extends FragmentActivity {


    @Override
    protected void onDestroy() {
        //unsubscribe网络请求
        Iterator<SubscriberWrapper> it = subscribers.iterator();
        while (it.hasNext()) {
            SubscriberWrapper subscriberWrapper = it.next();
            if (subscriberWrapper.unsubscribeOn == ActivityLifecycle.OnDestroy) {
                Log.e("rxjava", "onDestroy==============>");
                subscriberWrapper.subscriber.unsubscribe();
                it.remove();
            }
        }
        super.onDestroy();
    }

    private List<SubscriberWrapper> subscribers = new LinkedList<>();
    public void addSubscriber(Subscriber subscriber, ActivityLifecycle unsubscribeOn) {
        subscribers.add(new SubscriberWrapper(subscriber, unsubscribeOn));
    }

    private class SubscriberWrapper {
        Subscriber subscriber;
        ActivityLifecycle unsubscribeOn;

        public SubscriberWrapper(Subscriber subscriber, ActivityLifecycle unsubscribeOn) {
            this.subscriber = subscriber;
            this.unsubscribeOn = unsubscribeOn;
        }
    }
}
