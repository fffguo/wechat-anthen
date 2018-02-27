package com.yzy.wechat.serviceopen.ResultBean.response;

/**
 * @作者：刘富国
 * @创建时间：2018/2/27 15:44
 */
public class GetGlobalAccessTokenResponse {
    String wx_access_token_yzy;

    public GetGlobalAccessTokenResponse(String wx_access_token_yzy) {
        this.wx_access_token_yzy = wx_access_token_yzy;
    }

    public String getWx_access_token_yzy() {
        return wx_access_token_yzy;
    }

    public void setWx_access_token_yzy(String wx_access_token_yzy) {
        this.wx_access_token_yzy = wx_access_token_yzy;
    }
}
