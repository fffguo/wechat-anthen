package com.yzy.wechat.serviceopen.domain.response;

/**
 * @作者：刘富国
 * @创建时间：2018/2/27 15:44
 */
public class GetGlobalAccessTokenResponse {
    String access_token;

    public GetGlobalAccessTokenResponse(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
