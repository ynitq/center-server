package com.cfido.center.server.service;

import org.springframework.stereotype.Service;

import com.cfido.commons.utils.threadPool.BaseThreadPool;

/**
 * <pre>
 * 用于只执行各类异步任务的线程池，例如检查任务
 * </pre>
 * 
 * @author 梁韦江 2016年12月19日
 */
@Service
public class MonitorThreadPool extends BaseThreadPool {

	@Override
	protected String getName() {
		return "Monitor线程池";
	}

	@Override
	protected int getPoolSize() {
		// 可以稍微大一点
		return 5;
	}

	@Override
	protected int getUniqueIdSetInitSize() {
		return 100;
	}
}
