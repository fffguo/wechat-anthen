package com.yzy.wechat.serviceopen;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yzy.wechat.serviceopen.domain.dto.AccessTokenDTO;

/**
 * @作者：刘富国
 * @创建时间：2018/3/1 9:23
 */
public class test {
    public static void main(String[] args) {
        String json="{\n" +
                "\"access_token\":\"ACCESS_TOKEN\",\n" +
                "\"expires_in\":7200,\n" +
                "\"refresh_token\":\"REFRESH_TOKEN\",\n" +
                "\"openid\":\"OPENID\",\n" +
                "\"scope\":\"SCOPE\"\n" +
                "}";
        JSONObject jsonObject= (JSONObject) JSONObject.parse(json);
        AccessTokenDTO accessTokenDTO = JSON.parseObject(json, new TypeReference<AccessTokenDTO>() {});
        System.out.println(accessTokenDTO.toString());
    }
}
