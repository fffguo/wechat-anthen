package com.yzy.wechat.serviceopen.controller;

import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.yzy.wechat.serviceopen.domain.ServiceResponse;
import com.yzy.wechat.serviceopen.domain.dto.AccessTokenDTO;
import com.yzy.wechat.serviceopen.domain.response.OpenPlatform.GetAccessTokenResponse;
import com.yzy.wechat.serviceopen.domain.response.OpenPlatform.GetOpenIdResponse;
import com.yzy.wechat.serviceopen.entity.OpenPlatform;
import com.yzy.wechat.serviceopen.service.redis.RedisService;
import com.yzy.wechat.serviceopen.service.wechat.OpenPlatformService;
import com.yzy.wechat.serviceopen.service.wechat.WechatService;
import com.yzy.wechat.serviceopen.util.SRUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Map;

import static com.yzy.wechat.serviceopen.util.WechatUtil.xmlToMap;

/**
 * 微信开放平台 相关授权
 *
 * @作者：刘富国
 * @创建时间：2018/3/1 8:52
 */
@Controller
@RequestMapping("/open")
@SuppressWarnings(value= {"unchecked"})
public class OpenPlatformController {

    private static final Logger logger = LoggerFactory.getLogger(OpenPlatformController.class);

    @Autowired
    private OpenPlatformService openPlatformService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private WechatService wechatService;

    @GetMapping("/getAccessToken")
    @ResponseBody
    /** 代公众号发起网页授权 获取access_token*/
    public ServiceResponse<GetAccessTokenResponse> getAccessToken(HttpServletRequest request) {
        logger.info("代公众号发起网页授权 获取access_token开始：");
        String accessToken;
        try {
            String appid = request.getParameter("appid");
            String code = request.getParameter("code");
//            String state=request.getParameter("state");
            AccessTokenDTO accessTokenDTO = openPlatformService.getAccessToken(code, appid);
            //校验是否成功
            if (!StringUtils.isEmpty(accessTokenDTO.getMessage())) {
                return SRUtil.error("获取access_token失败," + accessTokenDTO.getMessage());
            }
            accessToken = accessTokenDTO.getAccess_token();
        } catch (Exception e) {
            logger.error("代公众号发起网页授权,获取access_token失败：{}", e.getMessage());
            return SRUtil.error("获取access_token失败");
        }
        return SRUtil.success(new GetAccessTokenResponse(accessToken));
    }

    @GetMapping("/getOpenId")
    @ResponseBody
    /** 代公众号发起网页授权 获取getOpenId */
    public ServiceResponse<GetOpenIdResponse> getOpenId(HttpServletRequest request) {
        logger.info("代公众号发起网页授权,获取openId开始：");
        String openId;
        try {
            String appid = request.getParameter("appid");
            String code = request.getParameter("code");
//            String state = request.getParameter("state");
            AccessTokenDTO accessTokenDTO = openPlatformService.getAccessToken(code, appid);
            if (!StringUtils.isEmpty(accessTokenDTO.getMessage())) {
                return SRUtil.error("获取openid失败，" + accessTokenDTO.getMessage());
            }
            openId = accessTokenDTO.getOpenid();
        } catch (Exception e) {
            logger.error("代公众号发起网页授权,获取openId失败：{}", e.getMessage());
            return SRUtil.error("获取openId失败");
        }
        return SRUtil.success(new GetOpenIdResponse(openId));
    }

    @PostMapping("/acceptTicket")
    @ResponseBody
    /** 接收 component_verify_ticket */
    public void acceptComponentVerifyTicket(HttpServletRequest request) {
        try {
            OpenPlatform openPlatform = wechatService.getWechatComponent();
            if (openPlatform == null) {
                logger.error("open_platform表，无相关记录");
                return;
            }
            String token = openPlatform.getToken();
            String encodingAesKey = openPlatform.getEncodingAesKey();
            String appid = openPlatform.getAppid();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(request.getInputStream());

            Element root = document.getDocumentElement();

            String encrypt = root.getElementsByTagName("Encrypt").item(0).getTextContent();
            String msgSignature = root.getElementsByTagName("MsgSignature").item(0).getTextContent();
            String timestamp = root.getElementsByTagName("Timestamp").item(0).getTextContent();
            String nonce = root.getElementsByTagName("Nonce").item(0).getTextContent();

            String format = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
            String postData = String.format(format, encrypt);

            WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appid);
            String result = pc.decryptMsg(msgSignature, timestamp, nonce, postData);
            logger.info("解密后明文: " + result);
            Map<String, String> map = xmlToMap(result);
            redisService.set("wx_component_verify_ticket_yzy", map.get("ComponentVerifyTicket"));
        } catch (Exception e) {
            logger.error("接收component_verify_ticket 失败：{}", e.getMessage());
            e.printStackTrace();
        }
    }
}
