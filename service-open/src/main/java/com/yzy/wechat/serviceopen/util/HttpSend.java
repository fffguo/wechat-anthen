package com.yzy.wechat.serviceopen.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpSend {
	public static final Logger logger = LoggerFactory.getLogger(HttpSend.class);

	public static String sendGet(String url, String dataType) throws Exception {

		logger.info("进入httpget请求.................");
		String result = null;
		HttpClient httpclient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(5000)
				.setSocketTimeout(5000).setRedirectsEnabled(true).build();
		
		HttpGet httpget = new HttpGet(url);
		httpget.setConfig(requestConfig);
		httpget.addHeader("Content-Type", "application/" + dataType + "; charset=utf-8");
		HttpResponse response = httpclient.execute(httpget);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			result = EntityUtils.toString(response.getEntity(), "utf-8");
		}
		return result;
	}

	public static String sendPost(String url, JSONObject obj, String dataType) throws Exception {
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		httppost.addHeader("Content-Type", "application/" + dataType + "; charset=utf-8");
		StringEntity se = new StringEntity(obj.toJSONString(), "utf-8");
		httppost.setEntity(se);

		HttpResponse response = httpclient.execute(httppost);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String result = EntityUtils.toString(response.getEntity());
			return result;
		}
		return null;
	}

	public static String sendPostJson(String url, JSONObject obj) throws Exception {
		logger.info("进入httppost请求.................");
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		httppost.addHeader("Content-Type", "application/json; charset=utf-8");
		StringEntity se = new StringEntity(obj.toJSONString(), "utf-8");
		httppost.setEntity(se);

		HttpResponse response = httpclient.execute(httppost);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String result = EntityUtils.toString(response.getEntity());
			return result;
		}
		return null;

	}
}
