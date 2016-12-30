package com.example.gaotengfei.retrofitwrapperdemo.bean;

/**
 * @anthor: gaotengfei
 * @time: 2016/12/28
 * @tel: 18511913443
 * @desc: 返回的基础数据bean,和服务端约定好的，每个公司情况会不同
 */

public class BaseJson<T>{

    private T data;

    private BaseStatus status;

    public BaseJson(BaseStatus status,T data) {
        this.data = data;
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BaseStatus getStatus() {
        return status;
    }

    public void setStatus(BaseStatus status) {
        this.status = status;
    }
}
