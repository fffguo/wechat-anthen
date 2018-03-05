package com.yzy.wechat_anthen.domain.response.smallProgram;

/**
 * @作者：刘富国
 * @创建时间：2018/3/1 15:19
 */
public class CheckSessionResponse {
    private String openid;

    public CheckSessionResponse(String openid) {
        this.openid = openid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
