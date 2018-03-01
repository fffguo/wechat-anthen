package com.yzy.wechat.serviceopen.domain;

/**
 * @作者：刘富国
 * @创建时间：2018/2/27 14:59
 */
public class ServiceResponse<T> {
    private Integer code;
    private Integer subCode;
    private String subMsg;
    private String msg;
    private T result;

    public ServiceResponse() {
    }

    public ServiceResponse(Integer code, Integer subCode, String subMsg, String msg, T result) {
        this.code = code;
        this.subCode = subCode;
        this.subMsg = subMsg;
        this.msg = msg;
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getSubCode() {
        return subCode;
    }

    public void setSubCode(Integer subCode) {
        this.subCode = subCode;
    }

    public String getSubMsg() {
        return subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
