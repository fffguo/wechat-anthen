package com.yzy.wechat.serviceopen.service.wechat;

import com.yzy.wechat.serviceopen.domain.dto.AccessTokenDTO;

/**
 * @作者：刘富国
 * @创建时间：2018/3/1 9:10
 */
public interface OpenPlatformService {

    /** 获取网页授权 access_token */
    AccessTokenDTO getAccessToken(String code, String appid);

}
