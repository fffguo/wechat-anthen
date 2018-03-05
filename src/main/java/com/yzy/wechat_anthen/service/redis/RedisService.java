package com.yzy.wechat_anthen.service.redis;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface RedisService {

	/**
	 * 根据键删除数据
	 * @author 崔世磊
	 * @date 2017年7月19日 下午5:22:33 
	 * @param keys
	 */
	public void del(List<String> keys);
	
	/**
	 * 新增记录
	 * @author 崔世磊
	 * @date 2017年7月19日 下午5:22:51 
	 * @param key
	 * @param value
	 * @param liveTime
	 */
	public void set(byte[] key, byte[] value, long liveTime);
	
	/**
	 * 新增记录
	 * @author 崔世磊
	 * @date 2017年7月19日 下午5:23:12 
	 * @param key
	 * @param value
	 * @param liveTime
	 */
	public void set(String key, String value, long liveTime);

	/**
	 * 新增记录并设置有效时间
	 * @param key
	 * @param value
	 * @param liveTime
	 * @param timeUnit
	 */
	public void set(String key, String value, long liveTime, TimeUnit timeUnit);
	
	/**
	 * 新增记录
	 * @author 崔世磊
	 * @date 2017年7月19日 下午5:23:21 
	 * @param key
	 * @param value
	 */
	public void set(String key, String value);
	
	/**
	 * 新增记录
	 * @author 崔世磊
	 * @date 2017年7月19日 下午5:23:27 
	 * @param key
	 * @param value
	 */
	public void set(byte[] key, byte[] value);
	
	/**
	 * 获取记录
	 * @author 崔世磊
	 * @date 2017年7月19日 下午5:23:34 
	 * @param key
	 * @return
	 */
	public String get(String key);
	
}
