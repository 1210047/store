package com.nkb.store.common;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private Integer state;
    private String msg;
    private T data;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }


    public Result(Throwable e){
        super();
        this.msg=e.getMessage();
    }

    public static Result success() {
        Result result = new Result<>();
        result.setState(200);
        result.setMsg("成功");
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(data);
        result.setState(200);
        result.setMsg("成功");
        return result;
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setState(code);
        result.setMsg(msg);
        return result;
    }
}
