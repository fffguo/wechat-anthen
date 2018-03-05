package com.yzy.wechat_anthen.service.impl.wechat;

import com.yzy.wechat_anthen.entity.OpenPlatform;
import com.yzy.wechat_anthen.entity.Wechat;
import com.yzy.wechat_anthen.enums.OpenPlatformStatusEnum;
import com.yzy.wechat_anthen.enums.WechatStatusEnum;
import com.yzy.wechat_anthen.enums.WechatTypeEnum;
import com.yzy.wechat_anthen.mapper.OpenPlatformMapper;
import com.yzy.wechat_anthen.mapper.WechatMapper;
import com.yzy.wechat_anthen.service.wechat.WechatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @作者：刘富国
 * @创建时间：2018/2/26 14:34
 */
@Service
@SuppressWarnings("unused")
public class WechatServiceImpl implements WechatService{
    
	private static final Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);

    @Autowired
    private WechatMapper wechatMapper;
    @Autowired
    private OpenPlatformMapper openPlatformMapper;

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
    public OpenPlatform getWechatComponent() {
        Map<String,Object> map=new HashMap<>();
        map.put("status", OpenPlatformStatusEnum.BINDING.getCode());
        return openPlatformMapper.findOneByAppidAndStatus(map);
    }

    @Override
    public OpenPlatform getWechatComponent(String appid) {
        Map<String,Object> map=new HashMap<>();
        map.put("status", OpenPlatformStatusEnum.BINDING.getCode());
        map.put("appid", appid);
        return openPlatformMapper.findOneByAppidAndStatus(map);
    }


}