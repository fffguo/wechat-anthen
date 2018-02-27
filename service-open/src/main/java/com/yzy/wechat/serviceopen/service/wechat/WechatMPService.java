package com.yzy.wechat.serviceopen.service.wechat;

import com.yzy.wechat.serviceopen.ResultBean.ServiceResponse;
import com.yzy.wechat.serviceopen.ResultBean.response.Get3rdSessionResponse;
import com.yzy.wechat.serviceopen.ResultBean.response.GetApiTicketResponse;

/**
 *  小程序实现接口
 * Created by 颜德洪 on 2018/2/27.
 */
public interface WechatMPService {
    /**
     * 获取3rd_session
     * @return
     */
    public ServiceResponse<Get3rdSessionResponse> get3rdSession(String code) ;

    /**
     * 判断3rd_session是否有效
     * @return
     */
    public Boolean isValid(String session) ;

}
