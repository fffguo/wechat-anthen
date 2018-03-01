package com.yzy.wechat.serviceopen.service.wechat;

import com.yzy.wechat.serviceopen.domain.dto.AccessTokenDTO;

/**
 * @作者：刘富国
 * @创建时间：2018/3/1 9:06
 */
public interface PublicPlatformService {

    /** 获取网页授权 accesstoken */
    AccessTokenDTO getAccessToken(String code,String appid);
}
