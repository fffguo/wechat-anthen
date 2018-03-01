package com.yzy.wechat.serviceopen.controller;

import com.yzy.wechat.serviceopen.domain.ServiceResponse;
import com.yzy.wechat.serviceopen.domain.response.smallProgram.CheckSessionResponse;
import com.yzy.wechat.serviceopen.domain.response.smallProgram.Get3rdSessionResponse;
import com.yzy.wechat.serviceopen.service.wechat.SmallProgramService;
import com.yzy.wechat.serviceopen.util.SRUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 颜德洪 on 2018/2/28 9:23.
 */
@Controller
public class SmallProgramController {
    private static Logger logger = LoggerFactory.getLogger(SmallProgramController.class);
    @Autowired
    private SmallProgramService smallProgramService;

    @RequestMapping("/get3rdSession")
    @ResponseBody
    public ServiceResponse<Get3rdSessionResponse> get3rdSession(HttpServletRequest request) {
        logger.info("用户正在获取3rdSession");
        String appid = request.getParameter("appid") ;
        String code = request.getParameter("code");
        if (StringUtils.isEmpty(appid)) {
            return SRUtil.error("操作失败，appid不能为空！") ;
        }
        else if (StringUtils.isEmpty(code)) {
            return SRUtil.error("操作失败，code不能为空！") ;
        } else {
            String session = smallProgramService.query3rdSession(appid,code) ;
            if (session != null) {
                return SRUtil.success(new Get3rdSessionResponse(session)) ;
            }
            return SRUtil.error("系统内部出错，获取3rd_session失败！") ;
        }
    }

    @RequestMapping("/checkSession")
    @ResponseBody
    public ServiceResponse<CheckSessionResponse> checkSession(HttpServletRequest request) {
        logger.info("用户正在检测3rdSession是否有效");
        String session = request.getParameter("3rd_session");
        if (StringUtils.isEmpty(session)) {
            return SRUtil.error("操作失败，3rd_session不能为空！") ;
        } else {
            Boolean flag = this.smallProgramService.isValid(session) ;
            return SRUtil.success(new CheckSessionResponse(flag)) ;
        }
    }
}
