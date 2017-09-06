package com.cfido.center.server.api.responses;

import com.cfido.commons.annotation.bean.AComment;
import com.cfido.commons.beans.apiServer.BaseResponse;

/**
 * <pre>
 * 当前登陆的admin的信息
 * </pre>
 * 
 * @author 梁韦江
 */
@AComment("当前登陆的admin的信息")
public class AdminUserInfoResponse extends BaseResponse {

	@AComment("账号")
	private String account;

	@AComment("名字")
	private String name;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
