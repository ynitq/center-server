package com.cfido.center.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfido.center.server.logicObj.UserFactory;
import com.cfido.center.server.logicObj.UserObj;
import com.cfido.commons.beans.exceptions.security.UserNotFoundException;
import com.cfido.commons.loginCheck.IWebUser;
import com.cfido.commons.spring.security.IUserServiceForRememberMe;
import com.cfido.commons.spring.security.LoginCheckInterceptor;

/**
 * <pre>
 * 模拟一个用户获取者
 * </pre>
 * 
 * @see LoginCheckInterceptor 这个类用于为LoginCheckInterceptor提供用户数据
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:35
 */
@Service
public class UserDetailService implements IUserServiceForRememberMe {

	@Autowired
	private UserFactory userFactory;

	@Override
	public IWebUser loadUserByUsername(String username) {

		try {
			UserObj user = this.userFactory.getByAccount(username);
			return new WebUser(user);
		} catch (UserNotFoundException e) {
			return null;
		}
	}

	@Override
	public Class<? extends IWebUser> getSupportUserClassNames() {
		// 设置这个类支持 AdminWebUser这种用户类型的用户数据恢复
		return WebUser.class;
	}
}
