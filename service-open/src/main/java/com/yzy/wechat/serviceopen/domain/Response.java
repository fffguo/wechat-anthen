package com.yzy.wechat.serviceopen.domain;

/**
 * @作者：胡鹏鹏
 * @创建时间：2018/2/28 17:13
 */
public class Response<T> {
    public static String SUCCESS = "1";
    public static String SUCCESS_TEXT = "操作成功!";
    public static String FAILURE = "0";
    public static String FAILURE_TEXT = "操作失败!";
    public static String SKIP = "2";
    public static String SKIP_TEXT = "忽略记录!";
    public static String SYSTEM_ERROR = "系统出错!";
    private String code;
    private String subCode;
    private String subMsg;
    private String msg;
    private static final long serialVersionUID = -5709436619120979415L;
    private T result;

    public Response() {
        this.code = SUCCESS;
        this.subCode = SUCCESS;
        this.msg = SUCCESS_TEXT;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSubCode() {
        return this.subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubMsg() {
        return this.subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }

    public void addError(String msg) {
        this.code = FAILURE;
        this.subCode = FAILURE;
        this.msg = msg;
        this.subMsg = msg;
    }

    public void addSubError(String subCode, String subMsg) {
        this.code = FAILURE;
        this.subCode = subCode;
        this.msg = subMsg;
        this.subMsg = subMsg;
    }

    public void addSuccess() {
        this.code = SUCCESS;
        this.subCode = SUCCESS;
    }

    public void addSuccess(T result) {
        this.addSuccess();
        this.result = result;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
