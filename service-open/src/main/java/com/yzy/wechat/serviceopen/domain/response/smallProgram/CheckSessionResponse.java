package com.yzy.wechat.serviceopen.domain.response.smallProgram;

/**
 * @作者：刘富国
 * @创建时间：2018/3/1 15:19
 */
public class CheckSessionResponse {
    boolean result;

    public CheckSessionResponse(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
