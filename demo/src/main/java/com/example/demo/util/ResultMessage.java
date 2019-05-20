package com.example.demo.util;

/**
 * @Auther: shieldofzues
 * @Date: 2018/10/8 11:04
 * @Description:
 */
public enum ResultMessage {
    SUCCESS(1001, "操作成功!"),
    FAIL(1002, "参数错误"),
    ERROR(1003, "操作失败!"),
    SEC_SUCCESS(1004, "查询无结果"),
    SYS_ERROR(1005, "系统异常"),
    USR_ERROR(1006, "登录过期"),
    USR_LOGIN_ERROR(1007, "用户手机号不存在"),
    USR_PASS_ERROR(1008,"用户密码错误"),
    USR_REPE_USER(1009, "手机号已存在");

    public final Integer code;
    public final String message;

    ResultMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}