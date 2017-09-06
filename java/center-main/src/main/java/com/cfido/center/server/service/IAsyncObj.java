package com.cfido.center.server.service;

import java.util.concurrent.atomic.AtomicBoolean;

import com.cfido.commons.beans.apiServer.BaseApiException;

/**
 * <pre>
 * 可异步保存的对象
 * </pre>
 * 
 * @author 梁韦江 2016年12月19日
 */
public interface IAsyncObj {

	AtomicBoolean getInQueueFlag();

	void doSave() throws BaseApiException;

}
