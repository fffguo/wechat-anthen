package com.yzy.wechat.serviceopen.controller;

import com.yzy.wechat.serviceopen.ResultBean.ServiceResponse;
import com.yzy.wechat.serviceopen.ResultBean.response.Get3rdSessionResponse;
import com.yzy.wechat.serviceopen.service.wechat.WechatMPService;
import com.yzy.wechat.serviceopen.util.ServiceResponseUtil;
import com.yzy.wechat.serviceopen.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 颜德洪 on 2018/2/28 9:23.
 */
@Controller
public class WechatMPController {
    private static Logger logger = LoggerFactory.getLogger(WechatMPController.class);
    @Autowired
    private WechatMPService wechatMPService ;

    @RequestMapping("/get3rdSession")
    @ResponseBody
    public ServiceResponse<Get3rdSessionResponse> get3rdSession(HttpServletRequest request) {
        logger.info("用户正在获取3rdSession");
        String appid = request.getParameter("appid") ;
        String code = request.getParameter("code");
        if (StringUtil.isEmpty(appid)) {
            return ServiceResponseUtil.error("操作失败，appid不能为空！") ;
        }
        else if (StringUtil.isEmpty(code)) {
            return ServiceResponseUtil.error("操作失败，code不能为空！") ;
        } else {
            String session = wechatMPService.query3rdSession(appid,code) ;
            if (session != null) {
                return ServiceResponseUtil.success(new Get3rdSessionResponse(session)) ;
            }
            return ServiceResponseUtil.error("系统内部出错，获取3rd_session失败！") ;
        }
    }

    @RequestMapping("/checkSession")
    @ResponseBody
    public ServiceResponse checkSession(HttpServletRequest request) {
        logger.info("用户正在检测3rdSession是否有效");
        String session = request.getParameter("3rd_session");
        if (StringUtil.isEmpty(session)) {
            return ServiceResponseUtil.error("操作失败，3rd_session不能为空！") ;
        } else {
            Boolean flag = this.wechatMPService.isValid(session) ;
            if (flag == true) {
                return ServiceResponseUtil.success();
            }
            return ServiceResponseUtil.error("3rd_session已失效，请重新获取！") ;
        }
    }
}
