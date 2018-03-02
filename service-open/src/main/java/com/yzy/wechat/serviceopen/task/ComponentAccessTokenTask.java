package com.yzy.wechat.serviceopen.task;

import com.alibaba.fastjson.JSONObject;
import com.yzy.wechat.serviceopen.entity.OpenPlatform;
import com.yzy.wechat.serviceopen.entity.Wechat;
import com.yzy.wechat.serviceopen.service.impl.wechat.WechatServiceImpl;
import com.yzy.wechat.serviceopen.service.redis.RedisService;
import com.yzy.wechat.serviceopen.util.HttpSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 微信开放平台 获取access_token
 * @作者：刘富国
 * @创建时间：2018/3/1 14:32
 */
@Component
public class ComponentAccessTokenTask {
    private static Logger logger = LoggerFactory.getLogger(ComponentAccessTokenTask.class);

    @Autowired
    private RedisService redisService;
    @Autowired
    private WechatServiceImpl wechatService;

    public final static long SCHEDULED_TIME = 20 * 60 * 1000;

    @Scheduled(fixedDelay=SCHEDULED_TIME)
    public void updateAccessToken() {
        logger.info("定时计划：更新第三方平台access_token");
        OpenPlatform openPlatform=wechatService.getWechatComponent();
        if(openPlatform==null){
            logger.error("openPlatform，无第三方平台 记录");
            return;
        }
        String appid=openPlatform.getAppid();
        String appsecret=openPlatform.getAppsecret();
        String ticket=redisService.get("wx_component_verify_ticket_yzy");
        try {
            String data="{\n" +
                    "\"component_appid\":"+appid+" ,\n" +
                    "\"component_appsecret\": "+appsecret+",\n" +
                    "\"component_verify_ticket\": "+ticket+"\n" +
                    "}";
            String url="https://api.weixin.qq.com/cgi-bin/component/api_component_token";
            JSONObject jsonObject= (JSONObject) JSONObject.parse(data);
            String tokenRes= HttpSend.sendPostJson(url,jsonObject);
            JSONObject tokenJson=JSONObject.parseObject(tokenRes);
            logger.info("tokenJson:"+tokenJson.toString());
            String component_access_token= (String) tokenJson.get("component_access_token");
            Long expires_in= (Long) tokenJson.get("expires_in");
            redisService.set("wx_component_access_token_yzy",component_access_token,expires_in);
            redisService.set("wx_component_appid_yzy",appid,expires_in);
        }catch (Exception e){
            logger.error("定时计划：更新第三方平台access_token失败："+e.getMessage());
        }
    }
}
