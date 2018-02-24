package com.yzy.wechat.serviceopen.task;

import com.alibaba.fastjson.JSONObject;
import com.yzy.wechat.serviceopen.service.redis.RedisService;
import com.yzy.wechat.serviceopen.util.HttpSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AccessTokenTask {

	private static Logger logger = LoggerFactory.getLogger(AccessTokenTask.class);

	@Autowired
	private RedisService redisService;

	@Value(value = "${yzy.wechat.appid}")
	private String yzyWechatAppId;

	@Value(value = "${yzy.wechat.appsecret}")
	private String yzyWechatAppsecret;

	public final static long SCHEDULED_TIME = 10000;
//	public final static long SCHEDULED_TIME = 20 * 60 * 1000;

    /**
     * 获取微信Token
     * @author 崔世磊
     * @date 2017年8月4日 下午2:54:16 
     */
    @Scheduled(fixedDelay=SCHEDULED_TIME)
	public void updateAccessToken() {
		try {
			logger.info("正在获取ACCESCE TOKEN");
			String getTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
					+ yzyWechatAppId + "&secret=" + yzyWechatAppsecret;
			String tokenRes = HttpSend.sendGet(getTokenUrl, "json");
			JSONObject tokeJson = JSONObject.parseObject(tokenRes);
			logger.info("tokeJson:" + tokeJson.toString());
			redisService.set("wx_access_token_yzy", tokeJson.getString("access_token"));

			String getJsTicketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
					+ tokeJson.getString("access_token") + "&type=jsapi";
			String jsTicketRes = HttpSend.sendGet(getJsTicketUrl, "json");
			JSONObject jsTicketJson = JSONObject.parseObject(jsTicketRes);
			logger.info("jsapi_ticket:" + jsTicketJson.toString());
			redisService.set("wx_jsapi_ticket_yzy", jsTicketJson.getString("ticket"));

			String getApiTicketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
					+ tokeJson.getString("access_token") + "&type=wx_card";
			String apiTicketRes = HttpSend.sendGet(getApiTicketUrl, "json");
			JSONObject apiTicketJson = JSONObject.parseObject(apiTicketRes);
			logger.info("api_ticket:" + apiTicketJson.toString());
			redisService.set("wx_api_ticket_yzy", apiTicketJson.getString("ticket"));
		} catch (Exception e) {
			logger.error("获取accesstoken异常" + e);
		}
	}

}
