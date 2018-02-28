package com.yzy.wechat.serviceopen.service.wechat;

import com.yzy.wechat.serviceopen.ResultBean.dto.AccessTokenDTO;
import com.yzy.wechat.serviceopen.entity.Wechat;

/**
 * @作者：刘富国
 * @创建时间：2018/2/27 14:01
 */
public interface WechatService {
    /** 获取 wechat appid 和 appsecret */
    Wechat getWechat();

    Wechat getWechat(String appid);

    /** 获取 paywechat appid 和appsecret */
    Wechat getWechatTimePlan();

    Wechat getWechatTimePlan(String appid);

    /** 获取网页授权 accesstoken */
    AccessTokenDTO getWebPageAccessToken(String code,String appid);
}
