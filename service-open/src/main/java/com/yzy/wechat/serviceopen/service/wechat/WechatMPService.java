package com.yzy.wechat.serviceopen.service.wechat;

/**
 *  小程序实现接口
 * Created by 颜德洪 on 2018/2/27.
 */
public interface WechatMPService {
    /**
     * 根据code获取3rd_session
     * @return
     */
    public String query3rdSession(String appid, String code) ;

    /**
     * 判断3rd_session是否有效
     * @return
     */
    public Boolean isValid(String session) ;

}
