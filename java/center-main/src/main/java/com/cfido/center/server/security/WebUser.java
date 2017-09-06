package com.cfido.center.server.security;

import com.cfido.center.server.logicObj.UserObj;
import com.cfido.commons.loginCheck.IWebUser;

/**
 * <pre>
 * 模拟一个 叫admin的用户， 密码是1
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:35
 */
public class WebUser implements IWebUser {

	private final UserObj user;

	public WebUser(UserObj user) {
		super();
		this.user = user;
	}

	@Override
	public boolean checkRights(String optId) {
		return true;
	}

	@Override
	public String getUsername() {
		return user.getPo().getAccount();
	}

	public UserObj getUser() {
		return user;
	}

	@Override
	public String getPassword() {
		return user.getPo().getPassword();
	}
}
