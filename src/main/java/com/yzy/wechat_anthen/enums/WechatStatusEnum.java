package com.yzy.wechat_anthen.enums;

/**
 * wechat表status字段
 * @作者：刘富国
 * @创建时间：2018/2/27 11:13
 */
public enum WechatStatusEnum {
    BINDING(1,"绑定"),
    UNBINDING(0,"解绑"),
    ;
    private Integer code;
    private String message;

    WechatStatusEnum(Integer code, String message) {
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
