package com.yzy.wechat.serviceopen.service.impl.wechat;

import com.alibaba.fastjson.JSONObject;
import com.yzy.wechat.serviceopen.ResultBean.ServiceResponse;
import com.yzy.wechat.serviceopen.ResultBean.response.Get3rdSessionResponse;
import com.yzy.wechat.serviceopen.entity.Wechat;
import com.yzy.wechat.serviceopen.service.redis.RedisService;
import com.yzy.wechat.serviceopen.service.wechat.WechatMPService;
import com.yzy.wechat.serviceopen.service.wechat.WechatService;
import com.yzy.wechat.serviceopen.util.ExecLinuxCMDUtil;
import com.yzy.wechat.serviceopen.util.HttpSend;
import com.yzy.wechat.serviceopen.util.ServiceResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by 颜德洪 on 2018/2/27.
 */
@Service
public class WechatMPServiceImpl implements WechatMPService{
    private static Logger logger = LoggerFactory.getLogger(WechatMPServiceImpl.class);

    @Autowired
    private RedisService redisService ;
    @Autowired
    private WechatService wechatService;

    @Override
    public ServiceResponse<Get3rdSessionResponse> get3rdSession(String code) {
        Wechat payWechat=wechatService.getPayWechat();
        String yzyWechatAppId=payWechat.getAppid();
        String yzyWechatAppsecret=payWechat.getAppsecret();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code" ;
        url = url.replace("APPID", yzyWechatAppId).replace("SECRET", yzyWechatAppsecret).replace("JSCODE", code);
        try {
            // 根据url获取微信服务器返回的数据
            String tokenRes = HttpSend.sendGet(url,"json");
            JSONObject tokenJson = (JSONObject) JSONObject.parse(tokenRes);
            String session_key = tokenJson.getString("session_key");
            String openid = tokenJson.getString("openid");
            //生成3rd_session
            String session = ExecLinuxCMDUtil.create3rdSession() ;
            //session存储2小时
            redisService.set(session,session_key+","+openid,2 , TimeUnit.HOURS);
            return ServiceResponseUtil.success(new Get3rdSessionResponse(session));
        } catch (Exception e) {
            logger.error("获取3rd_session出错，get3rdSession(String code)");
            e.printStackTrace();
        }
        return ServiceResponseUtil.error() ;
    }

    @Override
    public Boolean isValid(String session) {
        //redis缓存存在指定的3rd_session值
        if (!redisService.get(session).isEmpty()) {
            return true ;
        }
        return false;
    }
}
