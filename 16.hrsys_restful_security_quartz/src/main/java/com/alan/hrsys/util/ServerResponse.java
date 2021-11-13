package com.alan.hrsys.util;

import java.io.Serializable;

public class ServerResponse<T> implements Serializable {
    private Integer code;//0,未登录 1 登录成功  2登录失败
    private Long total;//查询出来的总记录数
    private T data;//查询出来的数据

    public ServerResponse() {
    }


    public ServerResponse(Long total, T data) {
        this.total = total;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
