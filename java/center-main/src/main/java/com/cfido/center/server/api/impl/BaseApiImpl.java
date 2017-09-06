package com.cfido.center.server.api.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.cfido.commons.spring.security.LoginContext;

/**
 * <pre>
 * api 实现类的基类
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
public class BaseApiImpl {

	@Autowired
	protected LoginContext loginContext;

}
