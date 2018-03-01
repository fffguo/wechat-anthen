package com.yzy.wechat.serviceopen.enums;

/**
 * wechat表type字段
 * @作者：刘富国
 * @创建时间：2018/2/27 11:16
 */
public enum WechatTypeEnum {
    WECHAT_TIME_PLAN('0',"定时计划的wechat"),
    WECHAT('1',"wecaht"),
    WECHAT_COMPONENT('2',"服务开发商的wechat"),
    ;
    private Character code;
    private String message;

    WechatTypeEnum(Character code, String message) {
        this.code = code;
        this.message = message;
    }

    public Character getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
