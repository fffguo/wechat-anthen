package com.yzy.wechat.serviceopen.service.wechat;

import com.yzy.wechat.serviceopen.entity.Wechat;
import org.springframework.stereotype.Service;

/**
 * @作者：刘富国
 * @创建时间：2018/2/27 14:01
 */
public interface WechatService {
    /** 获取 wechat appid 和 appsecret */
    public Wechat getWechat();

    public Wechat getWechat(String appid);

    /** 获取 paywechat appid 和appsecret */
    public Wechat getWechatTimePlan();

    public Wechat getWechatTimePlan(String appid);
}
