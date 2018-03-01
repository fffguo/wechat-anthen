package com.yzy.wechat.serviceopen;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yzy.wechat.serviceopen.domain.dto.AccessTokenDTO;
import com.yzy.wechat.serviceopen.util.SignUtil;
import com.yzy.wechat.serviceopen.util.WXPayConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * @作者：刘富国
 * @创建时间：2018/3/1 9:23
 */
public class test {
    public static void main(String[] args) throws Exception {

        SignUtil signUtil=new SignUtil();
        Map<String,String> map=new HashMap<>();
        map.put("appid","wx111f1b7437e708dd");
        map.put("signType","123");
        map.put("nonceStr","123456");
//        map.put("key","123456");
//        map.put("appid",);
        System.out.println(signUtil.generateSignature(map,"123456", WXPayConstants.SignType.HMACSHA256));
    }
}
