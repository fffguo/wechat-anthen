package com.yzy.wechat.serviceopen.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @作者：刘富国
 * @创建时间：2018/2/26 14:12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WechatMapperTest {

    @Autowired
    private WechatMapper wechatMapper;

    @Test
    public void findOneByStatus() {
        Map<String,Object> map=new HashMap<>();
        map.put("status",1);
        map.put("type",0);
        System.out.println(wechatMapper.findOneByStatusAndType(map).toString());
    }
}