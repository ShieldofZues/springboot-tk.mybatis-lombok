package com.example.demo.exception;

import com.example.demo.util.ResultMessage;

/**
 * @Author shieldofzues
 * @Description CustomException
 * @Date 11:12 2016/5/23
 *
 **/
public class CustomException extends RuntimeException{
    private Integer code;

    public CustomException(ResultMessage resultMessage) {
        super(resultMessage.message);
        this.code = resultMessage.code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
