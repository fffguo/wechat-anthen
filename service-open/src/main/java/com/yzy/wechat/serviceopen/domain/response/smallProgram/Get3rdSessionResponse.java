package com.yzy.wechat.serviceopen.domain.response.smallProgram;

/**
 * Created by 颜德洪 on 2018/2/27 16:03.
 */
public class Get3rdSessionResponse {
    private String session ;

    public Get3rdSessionResponse(String session) {
        this.session = session;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
