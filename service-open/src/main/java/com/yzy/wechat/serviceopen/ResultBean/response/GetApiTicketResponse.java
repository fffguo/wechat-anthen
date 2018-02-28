package com.yzy.wechat.serviceopen.ResultBean.response;

/**
 * @作者：刘富国
 * @创建时间：2018/2/27 15:34
 */
public class GetApiTicketResponse {
    private String api_ticket;

    public GetApiTicketResponse(String api_ticket) {
        this.api_ticket = api_ticket;
    }

    public String getApi_ticket() {
        return api_ticket;
    }

    public void setApi_ticket(String api_ticket) {
        this.api_ticket = api_ticket;
    }
}
