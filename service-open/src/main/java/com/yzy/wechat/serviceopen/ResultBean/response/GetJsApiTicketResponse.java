package com.yzy.wechat.serviceopen.ResultBean.response;

/**
 * @作者：刘富国
 * @创建时间：2018/2/27 15:41
 */
public class GetJsApiTicketResponse {
    String wx_jsapi_ticket_yzy;

    public GetJsApiTicketResponse(String wx_jsapi_ticket_yzy) {
        this.wx_jsapi_ticket_yzy = wx_jsapi_ticket_yzy;
    }

    public String getWx_jsapi_ticket_yzy() {
        return wx_jsapi_ticket_yzy;
    }

    public void setWx_jsapi_ticket_yzy(String wx_jsapi_ticket_yzy) {
        this.wx_jsapi_ticket_yzy = wx_jsapi_ticket_yzy;
    }
}
