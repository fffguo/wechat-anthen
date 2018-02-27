package com.yzy.wechat.serviceopen.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
 
/**
 * java在linux环境下执行linux命令，然后返回命令返回值。
 *
 */
public class ExecLinuxCMDUtil {
  public static final Logger logger = LoggerFactory.getLogger(ExecLinuxCMDUtil.class);
  /**
   * 生成3rd_session
   * @return
   */
  public static String create3rdSession() {
    try {
      String[] cmdA = { "/bin/sh", "-c", "cat /dev/urandom |od -x | tr -d ' '| head -n 1" };
      Process process = Runtime.getRuntime().exec(cmdA);
      LineNumberReader br = new LineNumberReader(new InputStreamReader(
          process.getInputStream()));
      StringBuffer sb = new StringBuffer();
      String line;
      while ((line = br.readLine()) != null) {
        System.out.println(line);
        sb.append(line).append("\n");
      }
      return sb.toString();
    } catch (Exception e) {
      logger.error("获取3rd_session失败:");
      e.printStackTrace();
    }
    return null;
  }
}