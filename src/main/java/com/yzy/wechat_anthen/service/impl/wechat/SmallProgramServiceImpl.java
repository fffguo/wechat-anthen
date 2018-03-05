package com.yzy.wechat_anthen.service.impl.wechat;

import com.alibaba.fastjson.JSONObject;
import com.yzy.wechat_anthen.entity.Wechat;
import com.yzy.wechat_anthen.service.redis.RedisService;
import com.yzy.wechat_anthen.service.wechat.SmallProgramService;
import com.yzy.wechat_anthen.service.wechat.WechatService;
import com.yzy.wechat_anthen.util.ExecLinuxCMDUtil;
import com.yzy.wechat_anthen.util.HttpSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by 颜德洪 on 2018/2/27.
 */
@Service
public class SmallProgramServiceImpl implements SmallProgramService {
    private static Logger logger = LoggerFactory.getLogger(SmallProgramServiceImpl.class);

    @Autowired
    private RedisService redisService ;
    @Autowired
    private WechatService wechatService;

    @Override
    public String query3rdSession(String appid, String code) {
        Wechat payWechat=wechatService.getWechat(appid);
//        String yzyWechatAppId=payWechat.getAppid();
        String yzyWechatAppsecret=payWechat.getAppsecret();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code" ;
        url = url.replace("APPID", appid).replace("SECRET", yzyWechatAppsecret).replace("JSCODE", code);
        try {
            // 根据url获取微信服务器返回的数据
            String tokenRes = HttpSend.sendGet(url,"json");
            JSONObject tokenJson = (JSONObject) JSONObject.parse(tokenRes);
            String session_key = tokenJson.getString("session_key");
            String openid = tokenJson.getString("openid");
            //生成3rd_session
            String session = ExecLinuxCMDUtil.create3rdSession() ;
            if (session != null) {
                //session存储30天
                redisService.set(session,session_key+","+openid,30 , TimeUnit.DAYS);
                return session;
            }
        } catch (Exception e) {
            logger.error("获取3rd_session出错，query3rdSession(String code)");
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public String queryOpenid(String session) {
        //redis缓存存在指定的3rd_session值
        if (redisService.get(session).isEmpty()) {
            return null;
        }
        return redisService.get(session).split(",")[1] ;
    }
}
