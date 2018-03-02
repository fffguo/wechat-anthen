package com.yzy.wechat.serviceopen.enums;

/**
 * openform 表 status字段 枚举类
 * @作者：刘富国
 * @创建时间：2018/3/2 9:40
 */
public enum OpenPlatformStatusEnum {
    BINDING(1,"绑定"),
    UNBINDING(0,"解绑"),
    ;
    private Integer code;
    private String message;

    OpenPlatformStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
