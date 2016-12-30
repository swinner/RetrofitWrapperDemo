package com.example.gaotengfei.retrofitwrapperdemo.httpUtil.exception;

/**
 * @anthor: gaotengfei
 * @time: 2016/12/28
 * @tel: 18511913443
 * @desc: 自定义的错误异常
 */

public class CustomException extends RuntimeException {

    private int code;
    private String response;

    public CustomException(int code, String msg, String response) {
        super(msg);
        this.code = code;
        this.response = response;
    }

    public int getCode() {
        return code;
    }

    public String getResponse() {
        return response;
    }
}