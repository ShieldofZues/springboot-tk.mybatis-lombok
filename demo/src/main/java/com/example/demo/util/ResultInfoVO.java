package com.example.demo.util;


import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author shieldofzues
 * @version V1.0
 */

/**
 *
 * 限制返回值（属性）为NULL时，不返回
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultInfoVO<T> {

    private Integer code;

    private T data;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public ResultInfoVO(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public ResultInfoVO() {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultInfoVO{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
