package com.yzy.wechat.serviceopen.controller;

import com.alibaba.fastjson.JSONObject;
import com.yzy.wechat.serviceopen.ResultBean.ServiceResponse;
import com.yzy.wechat.serviceopen.ResultBean.response.*;
import com.yzy.wechat.serviceopen.entity.Wechat;
import com.yzy.wechat.serviceopen.service.redis.RedisService;
import com.yzy.wechat.serviceopen.service.impl.wechat.WechatServiceImpl;
import com.yzy.wechat.serviceopen.service.wechat.WechatService;
import com.yzy.wechat.serviceopen.util.HttpSend;
import com.yzy.wechat.serviceopen.util.ServiceResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.yzy.wechat.serviceopen.util.Request2Map.getParameterMap;
import static com.yzy.wechat.serviceopen.util.WXPayUtil.generateSignature;
import static com.yzy.wechat.serviceopen.util.WXPayUtil.isSignatureValid;

/**
 * 类的功能描述：微信公众平台 相关接口
 * @作者：刘富国
 * @创建时间：2018/2/28 15:16  */
@Controller
public class WebPageAnthController {

    private static Logger logger = LoggerFactory.getLogger(WebPageAnthController.class);
    @Autowired
    private WechatService wechatService;
    @Autowired
    private RedisService redisService;

    @RequestMapping("/getAccessToken")
    @ResponseBody
    /** 获取网页授权 access_token */
    public ServiceResponse<GetAccessTokenResponse> getAccessToken(HttpServletRequest request) {
        String access_token;
        try {
            logger.info("正在获取网页授权access_token:");
            Map<String, String> map = getParameterMap(request);
            map.remove("body");
            //校验签名
            if (!isSignatureValid(map)) {
                logger.error("签名校验不通过!");
                return ServiceResponseUtil.error("签名校验不通过！");
            }
            //校验appid
            String appid = request.getParameter("appid");
            Wechat wechatTimePlan = wechatService.getWechatTimePlan(appid);
            if (wechatTimePlan == null) {
                logger.error("无效的appid");
                return ServiceResponseUtil.error("无效的appid");
            }
            String appsecret = wechatTimePlan.getAppsecret();

            String access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
            access_token_url = access_token_url.replace("APPID", appid).replace("SECRET", appsecret).replace("CODE", map.get("code"));
            String tokenRes = HttpSend.sendGet(access_token_url, "json");
            JSONObject tokenJson = (JSONObject) JSONObject.parse(tokenRes);
            access_token=tokenJson.getString("access_token");
            if(StringUtils.isEmpty(access_token)){
                logger.error("获取网页授权access_token失败！错误码：{}",tokenJson.getString("errcode"));
                return ServiceResponseUtil.error("获取网页授权access_token失败！");
            }
        } catch (Exception e) {
            logger.error("获取网页授权access_token失败！");
            e.printStackTrace();
            return ServiceResponseUtil.error("获取网页授权access_token失败！");
        }
        return ServiceResponseUtil.success(new GetAccessTokenResponse(access_token));
    }

    @RequestMapping("/getOpenId")
    @ResponseBody
    /** 获取网页授权 openid */
    public ServiceResponse<GetOpenIdResponse> getOpenId(HttpServletRequest request){
        try {
            logger.info("正在获取网页授权获取网页授权 openid :");
            Map<String, String> map = getParameterMap(request);
            map.remove("body");
            //校验签名
            if (!isSignatureValid(map)) {
                logger.error("签名校验不通过!");
                return ServiceResponseUtil.error("签名校验不通过！");
            }
            //校验appid
            String appid = request.getParameter("appid");
            Wechat wechatTimePlan = wechatService.getWechatTimePlan(appid);
            if (wechatTimePlan == null) {
                logger.error("无效的appid");
                return ServiceResponseUtil.error("无效的appid");
            }
            String appsecret = wechatTimePlan.getAppsecret();

            String access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
            access_token_url = access_token_url.replace("APPID", appid).replace("SECRET", appsecret).replace("CODE", map.get("code"));
            String tokenRes = HttpSend.sendGet(access_token_url, "json");
            JSONObject tokenJson = (JSONObject) JSONObject.parse(tokenRes);
            return ServiceResponseUtil.success(new GetOpenIdResponse(tokenJson.getString("openid")));
        } catch (Exception e) {
            logger.error("获取网页授权access_token失败！");
            e.printStackTrace();
        }
        return ServiceResponseUtil.error("获取网页授权access_token失败！");
    }

    @RequestMapping("/getGlobalAccessToken")
    @ResponseBody
    /** 获取全局 access_token */
    public ServiceResponse<GetGlobalAccessTokenResponse> getGlobalAccessToken(HttpServletRequest request) {
        logger.info("正在获取全局access_token");
        try {
            Map<String, String> map = getParameterMap(request);
            map.remove("body");
            //校验签名
            if (!isSignatureValid(map)) {
                logger.error("签名校验不通过!");
                return ServiceResponseUtil.error("签名校验不通过！");
            }
            return ServiceResponseUtil.success(new GetGlobalAccessTokenResponse(redisService.get("wx_access_token_yzy")));
        } catch (Exception e) {
            logger.error("获取全局access_token失败");
            e.printStackTrace();
        }
        return ServiceResponseUtil.error("获取全局access_token失败");
    }

    @RequestMapping("/getJsapiTicket")
    @ResponseBody
    /** 获取 jsapi_ticket */
    public ServiceResponse<GetJsApiTicketResponse> getJsapiTicket(HttpServletRequest request) {
        logger.info("正在获取jsapi_ticket");
        try {
            Map<String, String> map = getParameterMap(request);
            map.remove("body");
            //校验签名
            if (!isSignatureValid(map)) {
                logger.error("签名校验不通过!");
                return ServiceResponseUtil.error("签名校验不通过！");
            }
            return ServiceResponseUtil.success(new GetJsApiTicketResponse(redisService.get("wx_jsapi_ticket_yzy")));
        } catch (Exception e) {
            logger.error("获取jsapi_ticket失败");
            e.printStackTrace();
        }
        return ServiceResponseUtil.error("获取jsapi_ticket失败");
    }

    @RequestMapping("/getApiTicket")
    @ResponseBody
    /** 获取 api_ticket */
    public ServiceResponse<GetApiTicketResponse> getApiTicket(HttpServletRequest request) {
        logger.info("正在获取api_ticket");
        try {
            Map<String, String> map = getParameterMap(request);
            map.remove("body");
            //校验签名
            if (!isSignatureValid(map)) {
                logger.error("签名校验不通过!");
                return ServiceResponseUtil.error("签名校验不通过！");
            }
            return ServiceResponseUtil.success(new GetApiTicketResponse(redisService.get("wx_api_ticket_yzy")));
        } catch (Exception e) {
            logger.error("获取api_ticket失败");
            e.printStackTrace();
        }
        return ServiceResponseUtil.error("获取api_ticket失败");
    }
}
