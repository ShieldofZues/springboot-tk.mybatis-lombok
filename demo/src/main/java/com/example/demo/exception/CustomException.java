package com.example.demo.exception;

import com.example.demo.util.ResultMessage;

/**
 * @Auther: shieldofzues
 * @Date: 2018/10/11 14:49
 * @Description:
 */
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
