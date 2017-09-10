package com.cfido.center.server.api.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.cfido.commons.beans.apiExceptions.InvalidLoginStatusException;
import com.cfido.commons.spring.security.CommonAdminWebUser;
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

	protected CommonAdminWebUser getCurUser() {
		return loginContext.getUser(CommonAdminWebUser.class);
	}

	protected CommonAdminWebUser getCurUserNotNull() throws InvalidLoginStatusException {
		CommonAdminWebUser user = loginContext.getUser(CommonAdminWebUser.class);
		if (user == null) {
			throw new InvalidLoginStatusException();
		} else {
			return user;
		}
	}

}
