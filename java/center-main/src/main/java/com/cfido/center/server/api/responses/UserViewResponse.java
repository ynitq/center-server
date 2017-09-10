package com.cfido.center.server.api.responses;

import com.cfido.center.server.logicObj.UserViewModel;
import com.cfido.commons.beans.apiServer.BaseResponse;

/**
 * <pre>
 * User 的查看Response。这个类不会被覆盖
 * 无论该表是否能查询，查看的Response总是存在的
 * </pre>
 * 
 * @author 梁韦江 2016年8月25日
 */
public class UserViewResponse extends BaseResponse {
	private UserViewModel info;

	public UserViewModel getInfo() {
		return info;
	}

	public void setInfo(UserViewModel info) {
		this.info = info;
	}

}
