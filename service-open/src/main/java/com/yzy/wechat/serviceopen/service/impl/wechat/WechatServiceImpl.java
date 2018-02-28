package com.yzy.wechat.serviceopen.service.impl.wechat;

import com.alibaba.fastjson.JSONObject;
import com.yzy.wechat.serviceopen.ResultBean.dto.AccessTokenDTO;
import com.yzy.wechat.serviceopen.controller.WebPageAnthController;
import com.yzy.wechat.serviceopen.entity.Wechat;
import com.yzy.wechat.serviceopen.enums.WechatStatusEnum;
import com.yzy.wechat.serviceopen.enums.WechatTypeEnum;
import com.yzy.wechat.serviceopen.mapper.WechatMapper;
import com.yzy.wechat.serviceopen.service.wechat.WechatService;
import com.yzy.wechat.serviceopen.util.HttpSend;
import com.yzy.wechat.serviceopen.util.ServiceResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @作者：刘富国
 * @创建时间：2018/2/26 14:34
 */
@Service
public class WechatServiceImpl implements WechatService{
    private static final Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);

    @Autowired
    private WechatMapper wechatMapper;

    @Override
    public Wechat getWechat(){
        Map<String,Object> map=new HashMap<>();
        map.put("status", WechatStatusEnum.BINDING.getCode());
        map.put("type", WechatTypeEnum.WECHAT.getCode());
        return wechatMapper.findOneByStatusAndTypeAndAppid(map);
    }

    @Override
    public Wechat getWechat(String appid){
        Map<String,Object> map=new HashMap<>();
        map.put("status", WechatStatusEnum.BINDING.getCode());
        map.put("type", WechatTypeEnum.WECHAT.getCode());
        map.put("appid", appid);
        return wechatMapper.findOneByStatusAndTypeAndAppid(map);
    }

    @Override
    public Wechat getWechatTimePlan(){
        Map<String,Object> map=new HashMap<>();
        map.put("status", WechatStatusEnum.BINDING.getCode());
        map.put("type", WechatTypeEnum.WECHAT_TIME_PLAN.getCode());
        return wechatMapper.findOneByStatusAndTypeAndAppid(map);
    }

    @Override
    public Wechat getWechatTimePlan(String appid){
        Map<String,Object> map=new HashMap<>();
        map.put("status", WechatStatusEnum.BINDING.getCode());
        map.put("type", WechatTypeEnum.WECHAT_TIME_PLAN.getCode());
        map.put("appid", appid);
        return wechatMapper.findOneByStatusAndTypeAndAppid(map);
    }

    @Override
    public AccessTokenDTO getWebPageAccessToken(String code, String appid) {
        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        try {
            logger.info("正在获取网页授权access_token:");
            //校验appid
            Wechat wechat = getWechat(appid);
            if (wechat == null) {
                logger.error("无效的appid");
                accessTokenDTO.setMessage("无效的appid");
                return accessTokenDTO;
            }
            String appsecret = wechat.getAppsecret();

            String access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
            access_token_url = access_token_url.replace("APPID", appid).replace("SECRET", appsecret).replace("CODE", code);
            String tokenRes = HttpSend.sendGet(access_token_url, "json");
            JSONObject tokenJson = (JSONObject) JSONObject.parse(tokenRes);
            String errcode=tokenJson.getString("errcode");
            if(!StringUtils.isEmpty(errcode)){
                logger.error("获取网页授权access_token失败！错误码：{}",errcode);
                accessTokenDTO.setMessage("获取网页授权access_token失败！错误码："+errcode);
                return accessTokenDTO;
            }else{
                accessTokenDTO.setAccessToken(tokenJson.getString("access_token"));
                accessTokenDTO.setExpires_in(tokenJson.getString("expires_in"));
                accessTokenDTO.setOpenid(tokenJson.getString("openid"));
                accessTokenDTO.setRefresh_token(tokenJson.getString("refresh_token"));
                accessTokenDTO.setScope(tokenJson.getString("scope"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取网页授权access_token失败！");
            accessTokenDTO.setMessage("获取网页授权access_token失败！");
        }
        return accessTokenDTO;
    }
}