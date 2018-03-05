package com.yzy.wechat.serviceopen.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


/**
 * @作者：刘富国
 * @创建时间：2018/3/2 9:32
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OpenPlatformMapperTest {

    @Autowired
    private OpenPlatformMapper openPlatformMapper;

    @Test
    public void findOneByAppidAndStatus() {
        Map<String,Object> map=new HashMap<>();
//        map.put("status",1);
//        map.put("appid",123);
//        System.out.println(openPlatformMapper.findOneByAppidAndStatus(map));
    }
}