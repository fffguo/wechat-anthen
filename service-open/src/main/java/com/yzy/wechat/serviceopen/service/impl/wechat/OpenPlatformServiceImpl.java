package com.yzy.wechat.serviceopen.service.impl.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yzy.wechat.serviceopen.domain.dto.AccessTokenDTO;
import com.yzy.wechat.serviceopen.domain.dto.ComponentAccessTokenDTO;
import com.yzy.wechat.serviceopen.entity.Wechat;
import com.yzy.wechat.serviceopen.service.redis.RedisService;
import com.yzy.wechat.serviceopen.service.wechat.OpenPlatformService;
import com.yzy.wechat.serviceopen.service.wechat.WechatService;
import com.yzy.wechat.serviceopen.util.HttpSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @作者：刘富国
 * @创建时间：2018/3/1 9:12
 */
@Service
public class OpenPlatformServiceImpl implements OpenPlatformService {

    private static final Logger logger = LoggerFactory.getLogger(OpenPlatformServiceImpl.class);

    @Autowired
    private RedisService redisService;

    @Override
    public AccessTokenDTO getAccessToken(String code, String appid)  {
        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        try {
            //1.redis中读取component_appid和component_access_token
            String component_appid= redisService.get("wx_component_appid_yzy");
            String component_access_token=redisService.get("wx_component_access_token_yzy");
            //2.发送请求
            String url="https://api.weixin.qq.com/sns/oauth2/component/access_token?appid="
                    +appid+"&code="+code+"&grant_type=authorization_code&component_appid="
                    +component_appid+"&component_access_token="+component_access_token;
            String tokenRes=HttpSend.sendGet(url,"json");
            accessTokenDTO= JSON.parseObject(tokenRes,new TypeReference<AccessTokenDTO>(){});
            //3.校验请求是否失败
            JSONObject tokenJson= (JSONObject) JSONObject.parse(tokenRes);
            String errcode=tokenJson.getString("errcode");
            if(!StringUtils.isEmpty(errcode)){
                logger.error("代公众号发起网页授权，失败！错误码：{}",errcode);
                accessTokenDTO.setMessage("错误码："+errcode);
            }
        }catch (Exception e){
            logger.error("代公众号发起网页授权，出现异常：{}",e.getMessage());
            accessTokenDTO.setMessage("代公众号发起网页授权，失败!");
        }
        return accessTokenDTO;
    }

}
