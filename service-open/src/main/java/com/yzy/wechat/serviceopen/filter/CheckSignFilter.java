package com.yzy.wechat.serviceopen.filter;

import com.alibaba.fastjson.JSONObject;
import com.yzy.wechat.serviceopen.service.wechat.WechatService;
import com.yzy.wechat.serviceopen.util.SRUtil;
import com.yzy.wechat.serviceopen.util.WXPayConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import static com.yzy.wechat.serviceopen.util.Request2Map.getParameterMap;
import static com.yzy.wechat.serviceopen.util.WechatUtil.isSignatureValid;

/**
 * @作者：刘富国
 * @创建时间：2018/2/28 16:01
 */
@Component
@WebFilter(urlPatterns = "/*",filterName = "checkSignFilter")
public class CheckSignFilter implements Filter{

    private static final Logger logger = LoggerFactory.getLogger(CheckSignFilter.class);

    @Autowired
    private WechatService wechatService;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/xml");
        response.setCharacterEncoding("utf-8");
        logger.info("校验签名开始:");
        boolean checkSign=false;
        //校验签名
        try {
            Map<String, String> map = getParameterMap(request);
            map.remove("body");
            String key= wechatService.getWechat(map.get("appid")).getKey();
            String st=map.get("signType");
            WXPayConstants.SignType signType = null;
            //校验 MD5 还是 HMACSHA256
            if(st.equalsIgnoreCase(String.valueOf(WXPayConstants.SignType.MD5))){
                signType= WXPayConstants.SignType.MD5;
            }
            else if(st.equalsIgnoreCase(String.valueOf(WXPayConstants.SignType.HMACSHA256))){
                signType= WXPayConstants.SignType.HMACSHA256;
            }else{
                logger.error("编码格式有误!");
                PrintWriter out=response.getWriter();
                Object jsonObject=JSONObject.toJSONString(SRUtil.error("编码格式有误!"));
                out.print(jsonObject);
                return;
            }
            if (isSignatureValid(map,key,signType)) {
                checkSign=true;
                logger.info("签名校验已通过!");
                filterChain.doFilter(servletRequest,servletResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(checkSign==false){
            logger.error("签名校验未通过!");
            PrintWriter out=response.getWriter();
            Object jsonObject=JSONObject.toJSONString(SRUtil.error("签名校验未通过!"));
            out.print(jsonObject);
        }
    }

    @Override
    public void destroy() {

    }
}
