package com.yzy.wechat.serviceopen.service.wechat;

import com.netflix.discovery.converters.Auto;
import com.yzy.wechat.serviceopen.entity.Wechat;
import com.yzy.wechat.serviceopen.mapper.WechatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @作者：刘富国
 * @创建时间：2018/2/26 14:34
 */
@Service
public class WechatService {
//    @Autowired
//    private WechatMapper wechatMapper;

    public Wechat findOneByStatus(){
//        return wechatMapper.findOneByStatus(1);
        return null;
    }
}
