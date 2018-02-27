package com.yzy.wechat.serviceopen.ResultBean.response;

/**
 * @作者：刘富国
 * @创建时间：2018/2/27 15:34
 */
public class GetApiTicketResponse {
    private String wx_api_ticket_yzy;

    public GetApiTicketResponse(String wx_api_ticket_yzy) {
        this.wx_api_ticket_yzy = wx_api_ticket_yzy;
    }

    public String getWx_api_ticket_yzy() {
        return wx_api_ticket_yzy;
    }

    public void setWx_api_ticket_yzy(String wx_api_ticket_yzy) {
        this.wx_api_ticket_yzy = wx_api_ticket_yzy;
    }
}
