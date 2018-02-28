package com.yzy.wechat.serviceopen.util;

/**
 * Created by 颜德洪 on 2018/2/28 15:52.
 */
public class StringUtil {
    /**
     * 判断字符串是否为空，长度为0被认为是空字符串.
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (null != str) {
            return str.trim().length() == 0;
        } else {
            return true;
        }
    }
}
