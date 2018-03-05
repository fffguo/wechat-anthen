package com.yzy.wechat_anthen.service.wechat;

import com.yzy.wechat_anthen.entity.OpenPlatform;
import com.yzy.wechat_anthen.entity.Wechat;

/**
 * @作者：刘富国
 * @创建时间：2018/2/27 14:01
 */
public interface WechatService {
    /** 获取 wechat appid 和 appsecret */
    Wechat getWechat();

    Wechat getWechat(String appid);

    /** 获取 定时计划的wechat appid 和appsecret */
    Wechat getWechatTimePlan();

    Wechat getWechatTimePlan(String appid);

    /** 获取 服务开发商的 appid  token 和 encodingAesKey */
    OpenPlatform getWechatComponent();

    OpenPlatform getWechatComponent(String appid);



}
