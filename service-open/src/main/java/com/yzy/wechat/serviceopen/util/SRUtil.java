package com.yzy.wechat.serviceopen.util;

import com.yzy.wechat.serviceopen.domain.ServiceResponse;

/**
 * serviceResponse 的通用工具类
 * @作者：刘富国
 * @创建时间：2018/2/27 15:04
 */
@SuppressWarnings(value= {"unchecked","rawtypes"})
public class SRUtil {

    /** 成功，返回空数据 */
    
	public static ServiceResponse success(){
        return success(null);
    }
    /** 成功，返回Object数据 */
    public static ServiceResponse success(Object result){
        return success(null,result);
    }
    public static ServiceResponse success(String subMsg, Object result){
        return success(subMsg,"操作成功!",result);
    }
	public static ServiceResponse success(String subMsg, String msg, Object result){
        return new ServiceResponse(1,1,subMsg,msg,result);
    }

    /** 失败，返回默认信息 */
    public static ServiceResponse error(){
        return error(null);
    }
    /** 失败，返回自定义错误信息 */
    public static ServiceResponse error(String subMsg){
        return error(subMsg,"操作失败！");
    }
    public static ServiceResponse error(String subMsg, String msg){
        return error(subMsg,msg,null);
    }
	public static ServiceResponse error(String subMsg, String msg, Object result){
        return new ServiceResponse(0,0,subMsg,msg,result);
    }
}
