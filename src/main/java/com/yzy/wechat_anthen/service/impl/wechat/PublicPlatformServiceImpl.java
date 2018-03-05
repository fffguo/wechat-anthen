package com.yzy.wechat_anthen.service.impl.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yzy.wechat_anthen.domain.dto.AccessTokenDTO;
import com.yzy.wechat_anthen.entity.Wechat;
import com.yzy.wechat_anthen.service.wechat.PublicPlatformService;
import com.yzy.wechat_anthen.service.wechat.WechatService;
import com.yzy.wechat_anthen.util.HttpSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @作者：刘富国
 * @创建时间：2018/3/1 9:07
 */
@Service
public class PublicPlatformServiceImpl implements PublicPlatformService {

    @Autowired
    private WechatService wechatService;

    private static final Logger logger = LoggerFactory.getLogger(PublicPlatformServiceImpl.class);

    @Override
    public AccessTokenDTO getAccessToken(String code, String appid) {
        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        try {
            logger.info("正在获取网页授权access_token:");
            //校验appid
            Wechat wechat = wechatService.getWechat(appid);
            if (wechat == null) {
                logger.error("无效的appid");
                accessTokenDTO.setMessage("无效的appid");
                return accessTokenDTO;
            }
            String appsecret = wechat.getAppsecret();

            String access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
            access_token_url = access_token_url.replace("APPID", appid).replace("SECRET", appsecret).replace("CODE", code);
            String tokenRes = HttpSend.sendGet(access_token_url, "json");
            accessTokenDTO= JSON.parseObject(tokenRes,new TypeReference<AccessTokenDTO>(){});
            //检验请求是否失败
            JSONObject tokenJson = (JSONObject) JSONObject.parse(tokenRes);
            String errcode=tokenJson.getString("errcode");
            if(!StringUtils.isEmpty(errcode)){
                logger.error("获取网页授权access_token失败！错误码：{}",errcode);
                accessTokenDTO.setMessage("获取网页授权access_token失败！错误码："+errcode);
            }
        } catch (Exception e) {
            logger.error("获取网页授权access_token失败！{}",e.getMessage());
            accessTokenDTO.setMessage("获取网页授权access_token失败！");
        }
        return accessTokenDTO;
    }
}
