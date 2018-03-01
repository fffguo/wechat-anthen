package com.yzy.wechat.serviceopen.service.impl.wechat;

import com.alibaba.fastjson.JSONObject;
import com.yzy.wechat.serviceopen.domain.dto.AccessTokenDTO;
import com.yzy.wechat.serviceopen.entity.Wechat;
import com.yzy.wechat.serviceopen.enums.WechatStatusEnum;
import com.yzy.wechat.serviceopen.enums.WechatTypeEnum;
import com.yzy.wechat.serviceopen.mapper.WechatMapper;
import com.yzy.wechat.serviceopen.service.wechat.WechatService;
import com.yzy.wechat.serviceopen.util.HttpSend;
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


}