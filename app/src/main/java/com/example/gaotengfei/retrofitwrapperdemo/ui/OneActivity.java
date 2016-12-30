package com.example.gaotengfei.retrofitwrapperdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gaotengfei.retrofitwrapperdemo.R;
import com.example.gaotengfei.retrofitwrapperdemo.base.BaseActivity;
import com.example.gaotengfei.retrofitwrapperdemo.bean.AccountInfo;
import com.example.gaotengfei.retrofitwrapperdemo.bean.BaseJson;
import com.example.gaotengfei.retrofitwrapperdemo.bean.BodyUserInfo;
import com.example.gaotengfei.retrofitwrapperdemo.httpUtil.RetrofitControler;
import com.example.gaotengfei.retrofitwrapperdemo.httpUtil.ServiceFactoty;
import com.example.gaotengfei.retrofitwrapperdemo.httpUtil.exception.RetryWhenNetworkException;
import com.example.gaotengfei.retrofitwrapperdemo.httpUtil.rxjava.ApplySchedulers;
import com.example.gaotengfei.retrofitwrapperdemo.httpUtil.rxjava.BaseSubscriber;
import com.example.gaotengfei.retrofitwrapperdemo.httpUtil.rxjava.BindActivityOperator;

import rx.Observable;

/**
 * @anthor: gaotengfei
 * @time: 2016/12/30
 * @tel: 18511913443
 * @desc:
 */

public class OneActivity extends BaseActivity implements View.OnClickListener {
    private Button btn_click,btn_getcode,btn_finish;
    private EditText et_code;
    private OneActivity ctx;

    private RetrofitControler retrofitControler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        ctx = this;
        btn_getcode = (Button)findViewById(R.id.btn_getcode);
        btn_click = (Button)findViewById(R.id.btn_click);
        et_code = (EditText)findViewById(R.id.et_code);
        btn_finish= (Button)findViewById(R.id.btn_finish);

        btn_getcode.setOnClickListener(this);
        btn_click.setOnClickListener(this);
        btn_finish.setOnClickListener(this);
        retrofitControler = ServiceFactoty.getService(RetrofitControler.class);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_getcode:
                Log.d("MyTest", "btn_getcode=======>");
                Observable<String> observable0 = retrofitControler.getCaptcha("18888888888");

                observable0
                        .compose(new ApplySchedulers())
                        .lift(new BindActivityOperator(this))
                        .subscribe(new BaseSubscriber<String>(ctx){
                                       @Override
                                       public void onNext(String s) {
                                           Log.e("MyTestgetCaptcha", "onNext"+s);
                                       }
                                   }
                        );
                break;
            case R.id.btn_click:
                Observable<BaseJson<AccountInfo>> observable = retrofitControler. login(new BodyUserInfo("18888888888", et_code.getText().toString()));

                observable
                        .compose(new ApplySchedulers())
                        .lift(new BindActivityOperator(this))
                        .subscribe(new BaseSubscriber<BaseJson<AccountInfo>>(ctx) {
                            @Override
                            public void onNext(BaseJson<AccountInfo> accountInfo) {
                                Log.e("MyTest", accountInfo.getStatus().getCode()  +accountInfo.getStatus().getMsg());
                            }
                        });
                break;
            case R.id.btn_finish:
                finish();
                break;
        }
    }
}
