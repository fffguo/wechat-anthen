package com.yzy.wechat.serviceopen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
@EnableCaching
public class ServiceOpenApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceOpenApplication.class, args);
	}
}
