package com.cfido.center.server.api.responses;

import java.util.List;

import com.cfido.center.server.logicObj.UserViewModel;
import com.cfido.commons.beans.apiServer.BaseResponse;

/**
 * <pre>
 * User 列表 的Response。这个类不会被覆盖
 * 如果没有ListForm，这个Response也不存在
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
public class UserListResponse extends BaseResponse {

	private List<UserViewModel> list;

	public List<UserViewModel> getList() {
		return list;
	}

	public void setList(List<UserViewModel> list) {
		this.list = list;
	}

}
