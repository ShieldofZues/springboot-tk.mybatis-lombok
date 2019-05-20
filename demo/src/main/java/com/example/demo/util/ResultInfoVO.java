package com.example.demo.util;


/**
 * @author zlr
 * @version V1.0
 */
public class ResultInfoVO<T> {

    private Integer code;

    private Object data;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public ResultInfoVO(Integer code, Object data, String message) {
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
