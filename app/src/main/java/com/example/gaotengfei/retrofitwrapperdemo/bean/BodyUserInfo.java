package com.example.gaotengfei.retrofitwrapperdemo.bean;

/**
 * @anthor: gaotengfei
 * @time: 2016/12/28
 * @tel: 18511913443
 * @desc:
 */

public class BodyUserInfo {
    private String mobile; // 手机号码

    private String captcha; // 验证码

    public BodyUserInfo(String mobile, String captcha) {
        this.mobile = mobile;
        this.captcha = captcha;
    }


    @Override
    public String toString() {
        return "BodyUserInfo{" +
                "mobile='" + mobile + '\'' +
                ", captcha='" + captcha + '\'' +
                '}';
    }
}
