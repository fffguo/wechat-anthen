package com.yzy.wechat.serviceopen.util;

import com.alibaba.druid.support.json.JSONUtils;

import java.util.HashMap;
import java.util.Map;

import static com.yzy.wechat.serviceopen.util.SignUtil.*;

/**
 * @作者：刘富国
 * @创建时间：2018/2/28 10:38
 */
public class main {
    public static void main(String[] args) throws Exception {
        Map<String,String> map=new HashMap<>();
        map.put("appid","wx111f1b7437e708dd");
        map.put("mchId","123");
        map.put("signType","MD5");
        map.put("code","123456");


//        String jsonArray= JSONUtils.toJSONString(map);
//        map= (Map<String, String>) JSONUtils.parse(jsonArray);
//        System.out.println(map.get("appid"));
//        System.out.println(jsonArray);

//        map.put("nonceStr",generateUUID());
        System.out.println(generateSignature(map, WXPayConstants.SignType.MD5));
    }
}
