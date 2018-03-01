package com.yzy.wechat.serviceopen.service.impl.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yzy.wechat.serviceopen.domain.dto.AccessTokenDTO;
import com.yzy.wechat.serviceopen.service.wechat.OpenPlatformService;
import com.yzy.wechat.serviceopen.util.HttpSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @作者：刘富国
 * @创建时间：2018/3/1 9:12
 */
@Service
public class OpenPlatformServiceImpl implements OpenPlatformService {

    private static final Logger logger = LoggerFactory.getLogger(OpenPlatformServiceImpl.class);

    @Override
    public AccessTokenDTO getAccessToken(String code, String appid)  {
        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        try {
            String component_appid="";
            String component_access_token="";

            String url="https://api.weixin.qq.com/sns/oauth2/component/access_token?appid="
                    +appid+"&code="+code+"&grant_type=authorization_code&component_appid="
                    +component_appid+"&component_access_token="+component_access_token;
            String tokenRes=HttpSend.sendGet(url,"json");
            accessTokenDTO= JSON.parseObject(tokenRes,new TypeReference<AccessTokenDTO>(){});
            //校验请求是否失败
            JSONObject tokenJson= (JSONObject) JSONObject.parse(tokenRes);
            String errcode=tokenJson.getString("errcode");
            if(!StringUtils.isEmpty(errcode)){
                logger.error("代公众号发起网页授权，失败！错误码：{}",errcode);
                accessTokenDTO.setMessage("错误码："+errcode);
            }
        }catch (Exception e){
            logger.error("代公众号发起网页授权，出现异常：{}",e.getMessage());
            accessTokenDTO.setMessage("代公众号发起网页授权，失败!");
        }
        return accessTokenDTO;
    }
}
