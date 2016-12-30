package com.example.gaotengfei.retrofitwrapperdemo.httpUtil;

import com.example.gaotengfei.retrofitwrapperdemo.bean.AccountInfo;
import com.example.gaotengfei.retrofitwrapperdemo.bean.BaseJson;
import com.example.gaotengfei.retrofitwrapperdemo.bean.BodyUserInfo;

import org.json.JSONObject;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @anthor: gaotengfei
 * @time: 2016/12/28
 * @tel: 18511913443
 * @desc:
 */

public interface RetrofitControler {
    /**
     * 获取验证码.
     *
     * @param mobile
     * @return
     */
    @POST("v1/mobiles/{mobile}/captcha")
    Observable<String> getCaptcha(@Path("mobile") String mobile);
    /**
     * 用户注册&用户登录.
     *
     * @param info
     * @return
     */
    @PUT("v1/users/login")
    Observable<BaseJson<AccountInfo>> login(@Body BodyUserInfo info);
}
