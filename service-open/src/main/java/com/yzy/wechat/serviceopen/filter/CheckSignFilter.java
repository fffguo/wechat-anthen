package com.yzy.wechat.serviceopen.filter;

import com.alibaba.fastjson.JSONObject;
import com.yzy.wechat.serviceopen.util.SRUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import static com.yzy.wechat.serviceopen.util.Request2Map.getParameterMap;
import static com.yzy.wechat.serviceopen.util.SignUtil.isSignatureValid;

/**
 * @作者：刘富国
 * @创建时间：2018/2/28 16:01
 */
@Component
@WebFilter(urlPatterns = "/*",filterName = "checkSignFilter")
public class CheckSignFilter implements Filter{

    private static final Logger logger = LoggerFactory.getLogger(CheckSignFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        logger.info("校验签名开始:");
        boolean checkSign=false;
        //校验签名
        try {
            Map<String, String> map = getParameterMap(request);
            map.remove("body");
            if (isSignatureValid(map)) {
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
