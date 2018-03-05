package com.yzy.wechat_anthen.service.wechat;

/**
 *  小程序实现接口
 * Created by 颜德洪 on 2018/2/27.
 */
public interface SmallProgramService {
    /**
     * 根据code获取3rd_session
     * @return
     */
    public String query3rdSession(String appid, String code) ;

    /**
     * 查询Openid根据session
     * @return 如果session失效，返回null
     */
    public String queryOpenid(String session) ;

}
