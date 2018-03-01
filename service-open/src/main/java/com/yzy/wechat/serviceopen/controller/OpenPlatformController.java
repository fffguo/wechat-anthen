package com.yzy.wechat.serviceopen.controller;

import com.yzy.wechat.serviceopen.domain.ServiceResponse;
import com.yzy.wechat.serviceopen.domain.dto.AccessTokenDTO;
import com.yzy.wechat.serviceopen.domain.response.OpenPlatform.GetAccessTokenResponse;
import com.yzy.wechat.serviceopen.domain.response.OpenPlatform.GetOpenIdResponse;
import com.yzy.wechat.serviceopen.service.wechat.OpenPlatformService;
import com.yzy.wechat.serviceopen.util.SRUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信开放平台 相关授权
 * @作者：刘富国
 * @创建时间：2018/3/1 8:52
 */
@Controller
@RequestMapping("/open")
public class OpenPlatformController {

    private static final Logger logger= LoggerFactory.getLogger(OpenPlatformController.class);

    @Autowired
    private OpenPlatformService openPlatformService;

    @GetMapping("/getAccessToken")
    @ResponseBody
    /** 代公众号发起网页授权 获取access_token*/
    public ServiceResponse<GetAccessTokenResponse> getAccessToken(HttpServletRequest request){
        logger.info("代公众号发起网页授权 获取access_token开始：");
        String accessToken;
        try {
            String appid=request.getParameter("appid");
            String code=request.getParameter("code");
//            String state=request.getParameter("state");
            AccessTokenDTO accessTokenDTO=openPlatformService.getAccessToken(code,appid);
            //校验是否成功
            if(!StringUtils.isEmpty(accessTokenDTO.getMessage())){
                return SRUtil.error("获取access_token失败,"+accessTokenDTO.getMessage());
            }
            accessToken=accessTokenDTO.getAccess_token();
        }catch (Exception e){
            logger.error("代公众号发起网页授权,获取access_token失败：{}",e.getMessage());
            return SRUtil.error("获取access_token失败");
        }
        return SRUtil.success(new GetAccessTokenResponse(accessToken));
    }

    @GetMapping("/getOpenId")
    @ResponseBody
    /** 代公众号发起网页授权 获取getOpenId */
    public ServiceResponse<GetOpenIdResponse> getOpenId(HttpServletRequest request){
        logger.info("代公众号发起网页授权,获取openId开始：");
        String openId;
        try {
            String appid=request.getParameter("appid");
            String code=request.getParameter("code");
            String state=request.getParameter("state");
            AccessTokenDTO accessTokenDTO=openPlatformService.getAccessToken(code,appid);
            if(!StringUtils.isEmpty(accessTokenDTO.getMessage())){
                return SRUtil.error("获取openid失败，"+accessTokenDTO.getMessage());
            }
            openId=accessTokenDTO.getOpenid();
        }catch (Exception e){
            logger.error("代公众号发起网页授权,获取openId失败：{}",e.getMessage());
            return SRUtil.error("获取openId失败");
        }
        return SRUtil.success(new GetOpenIdResponse(openId));
    }
}
