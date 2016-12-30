package com.example.gaotengfei.retrofitwrapperdemo.bean;

/**
 * @anthor: gaotengfei
 * @time: 2016/12/28
 * @tel: 18511913443
 * @desc:基础数据bean 的status，和服务端约定好的，每个公司情况会不同
 */

public class BaseStatus {
    private int code = -4;
    private String msg;
    public BaseStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
