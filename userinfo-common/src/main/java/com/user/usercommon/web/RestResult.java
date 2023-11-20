package com.user.usercommon.web;
import lombok.Data;


@Data
public class RestResult<T> {

    private int code;


    private String msg;


    private T data;


    // not success
    public static <T> RestResult<T> successNoData(ResultCode code) {
        RestResult<T> result = new RestResult<T>();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        return result;
    }

    // success
    public static <T> RestResult<T> success(T t, ResultCode code) {
        RestResult<T> result = new RestResult<T>();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        result.setData(t);
        return result;
    }

    // fail
    public static <T> RestResult<T> fail(ResultCode code) {
        RestResult<T> result = new RestResult<T>();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        return result;
    }

}
