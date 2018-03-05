package com.yzy.wechat_anthen.domain.dto;

/**
 * 第三方平台access_token
 * @作者：刘富国
 * @创建时间：2018/3/1 14:21
 */
public class ComponentAccessTokenDTO {
    String component_access_token;
    String expires_in;

    public String getComponent_access_token() {
        return component_access_token;
    }

    public void setComponent_access_token(String component_access_token) {
        this.component_access_token = component_access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }
}
