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

	private final int userId;
	private final String account;
	private final String name;
	private final String password;

	public WebUser(UserObj user) {
		this.userId = user.getPo().getId();
		this.account = user.getPo().getAccount();
		this.name = user.getPo().getName();
		this.password = user.getPo().getPassword();
	}

	@Override
	public boolean checkRights(String optId) {
		return true;
	}

	@Override
	public String getUsername() {
		return this.account;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	public int getUserId() {
		return this.userId;
	}
}
