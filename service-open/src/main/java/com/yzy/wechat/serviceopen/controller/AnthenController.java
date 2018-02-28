package com.yzy.wechat.serviceopen.controller;

import com.alibaba.fastjson.JSONObject;
import com.yzy.wechat.serviceopen.ResultBean.ServiceResponse;
import com.yzy.wechat.serviceopen.ResultBean.response.GetApiTicketResponse;
import com.yzy.wechat.serviceopen.ResultBean.response.GetGlobalAccessTokenResponse;
import com.yzy.wechat.serviceopen.ResultBean.response.GetJsApiTicketResponse;
import com.yzy.wechat.serviceopen.ResultBean.response.GetOpenId;
import com.yzy.wechat.serviceopen.entity.Wechat;
import com.yzy.wechat.serviceopen.service.redis.RedisService;
import com.yzy.wechat.serviceopen.service.impl.wechat.WechatServiceImpl;
import com.yzy.wechat.serviceopen.util.HttpSend;
import com.yzy.wechat.serviceopen.util.ServiceResponseUtil;
import com.yzy.wechat.serviceopen.util.WXPayConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.yzy.wechat.serviceopen.util.Request2Map.getParameterMap;
import static com.yzy.wechat.serviceopen.util.WXPayUtil.generateSignature;
import static com.yzy.wechat.serviceopen.util.WXPayUtil.isSignatureValid;


@Controller
public class AnthenController {

    private static Logger logger = LoggerFactory.getLogger(AnthenController.class);
    @Autowired
    private WechatServiceImpl wechatService;
    @Autowired
    private RedisService redisService;


    @RequestMapping("/getAccessToken")
    @ResponseBody
    public ServiceResponse accessAnthen(HttpServletRequest request) {
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
            return ServiceResponseUtil.success(tokenJson);
        } catch (Exception e) {
            logger.error("获取网页授权access_token失败！");
            e.printStackTrace();
        }
        return ServiceResponseUtil.error("获取网页授权access_token失败！");
    }

    @RequestMapping("/getOpenId")
    @ResponseBody
    public ServiceResponse<GetOpenId> getOpenId(HttpServletRequest request) {
        logger.info("正在获取用户授权信息");
        try {
            String code = request.getParameter("code");
            String appid = request.getParameter("appid");
            //获取pay appid appsecret
            Wechat payWechat = wechatService.getWechatTimePlan(appid);
            String yzyWechatAppId = payWechat.getAppid();
            String yzyWechatAppsecret = payWechat.getAppsecret();


            String access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
            access_token_url = access_token_url.replace("APPID", yzyWechatAppId).replace("SECRET", yzyWechatAppsecret).replace("CODE", code);
            String tokenRes = HttpSend.sendGet(access_token_url, "json");
            JSONObject tokenJson = (JSONObject) JSONObject.parse(tokenRes);
            String openid = tokenJson.getString("openid");
            return ServiceResponseUtil.success(new GetOpenId(openid));
        } catch (Exception e) {
            logger.error("获取openid失败");
            e.printStackTrace();
        }
        return ServiceResponseUtil.error("获取openid失败");
    }

    @RequestMapping("/getGlobalAccessToken")
    @ResponseBody
    public ServiceResponse<GetGlobalAccessTokenResponse> getGlobalAccessToken() {
        logger.info("正在获取全局access_token");
        try {
            return ServiceResponseUtil.success(new GetGlobalAccessTokenResponse(redisService.get("wx_access_token_yzy")));
        } catch (Exception e) {
            logger.error("获取全局access_token失败");
            e.printStackTrace();
        }
        return ServiceResponseUtil.error("获取全局access_token失败");
    }

    @RequestMapping("/getJsapiTicket")
    @ResponseBody
    public ServiceResponse<GetJsApiTicketResponse> getJsapiTicket() {
        logger.info("正在获取jsapi_ticket");
        try {
            return ServiceResponseUtil.success(new GetJsApiTicketResponse(redisService.get("wx_jsapi_ticket_yzy")));
        } catch (Exception e) {
            logger.error("获取jsapi_ticket失败");
            e.printStackTrace();
        }
        return ServiceResponseUtil.error("获取jsapi_ticket失败");
    }

    @RequestMapping("/getApiTicket")
    @ResponseBody
    public ServiceResponse<GetApiTicketResponse> getApiTicket() {
        logger.info("正在获取api_ticket");
        try {
            return ServiceResponseUtil.success(new GetApiTicketResponse(redisService.get("wx_api_ticket_yzy")));
        } catch (Exception e) {
            logger.error("获取api_ticket失败");
            e.printStackTrace();
        }
        return ServiceResponseUtil.error("获取api_ticket失败");
    }
}
