package com.cfido.center.server;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cfido.center.server.config.MonitorServerProperties;
import com.cfido.commons.spring.anno.EnableRedisCache;
import com.cfido.commons.spring.anno.EnableSmsCodeService;
import com.cfido.commons.spring.anno.EnableWebPorjectSet;
import com.cfido.commons.spring.dict.core.DictCoreService;
import com.cfido.commons.spring.monitor.MonitorClientProperties;

import game.other.sms.gw253.EnableSmsGw253;

@SpringBootApplication
@EnableWebPorjectSet
@EnableRedisCache
@EnableConfigurationProperties(MonitorServerProperties.class)
@EnableScheduling

@EnableSmsGw253 // 短信网关 -253.com
@EnableSmsCodeService // 短信验证码服务器：必须至少有一个网关才能用

public class StartCenterServer {

	@Autowired
	private DictCoreService dictCoreService;

	@Autowired
	private MonitorClientProperties clientProp;

	public static void main(String[] args) {
		SpringApplication.run(StartCenterServer.class, args);
	}

	@PostConstruct
	protected void init() {
		// 设置监控客户端无需汇报给自己
		this.clientProp.setEnable(false);
		
		this.dictCoreService.getRawText("system.name", "中心服务器");
		
	}

}
