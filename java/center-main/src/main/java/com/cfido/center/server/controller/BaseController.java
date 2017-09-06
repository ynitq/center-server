/** 生成于 2016-12-19 17:42:34 */
package com.cfido.center.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.cfido.center.server.security.WebUser;
import com.cfido.commons.spring.security.LoginContext;

/**
 * <pre>
 * 开发时用的测试接口
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
abstract class BaseController {

	@Autowired
	protected LoginContext loginContext;

	@Autowired
	protected HttpServletRequest request;

	protected WebUser getCurUser() {
		return this.loginContext.getUser(WebUser.class);
	}

}
