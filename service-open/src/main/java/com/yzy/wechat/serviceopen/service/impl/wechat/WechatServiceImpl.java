package com.yzy.wechat.serviceopen.service.impl.wechat;

import com.yzy.wechat.serviceopen.entity.Wechat;
import com.yzy.wechat.serviceopen.enums.WechatStatusEnum;
import com.yzy.wechat.serviceopen.enums.WechatTypeEnum;
import com.yzy.wechat.serviceopen.mapper.WechatMapper;
import com.yzy.wechat.serviceopen.service.wechat.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @作者：刘富国
 * @创建时间：2018/2/26 14:34
 */
@Service
public class WechatServiceImpl implements WechatService{
    @Autowired
    private WechatMapper wechatMapper;

    public Wechat getWechat(){
        Map<String,Object> map=new HashMap<>();
        map.put("status", WechatStatusEnum.BINDING.getCode());
        map.put("type", WechatTypeEnum.WECHAT.getCode());
        return wechatMapper.findOneByStatusAndType(map);
    }

    public Wechat getPayWechat(){
        Map<String,Object> map=new HashMap<>();
        map.put("status", WechatStatusEnum.BINDING.getCode());
        map.put("type", WechatTypeEnum.PAY_WECHAT.getCode());
        return wechatMapper.findOneByStatusAndType(map);
    }
}
