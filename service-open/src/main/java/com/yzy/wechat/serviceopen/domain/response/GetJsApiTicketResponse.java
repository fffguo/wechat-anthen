package com.yzy.wechat.serviceopen.domain.response;

/**
 * @作者：刘富国
 * @创建时间：2018/2/27 15:41
 */
public class GetJsApiTicketResponse {
    String jsapi_ticket;

    public GetJsApiTicketResponse(String jsapi_ticket) {
        this.jsapi_ticket = jsapi_ticket;
    }

    public String getJsapi_ticket() {
        return jsapi_ticket;
    }

    public void setJsapi_ticket(String jsapi_ticket) {
        this.jsapi_ticket = jsapi_ticket;
    }
}
