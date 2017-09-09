package com.cfido.center.server.api.responses;

import java.util.List;

import com.cfido.center.server.logicObj.RoleViewModel;
import com.cfido.commons.annotation.bean.AComment;
import com.cfido.commons.beans.apiServer.BaseResponse;

/**
 * <pre>
 * Role 列表 的Response
 * </pre>
 * 
 * @author 梁韦江
 */
public class RoleListResponse extends BaseResponse {

	@AComment("角色列表")
	private List<RoleViewModel> list;

	public List<RoleViewModel> getList() {
		return list;
	}

	public void setList(List<RoleViewModel> list) {
		this.list = list;
	}

}
