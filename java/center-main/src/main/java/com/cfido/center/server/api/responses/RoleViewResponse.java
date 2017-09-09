package com.cfido.center.server.api.responses;

import com.cfido.center.server.logicObj.RoleViewModel;
import com.cfido.commons.annotation.bean.AComment;
import com.cfido.commons.beans.apiServer.BaseResponse;

/**
 * <pre>
 * Role 的查看Response。
 * </pre>
 * 
 * @author 梁韦江
 */
public class RoleViewResponse extends BaseResponse {

	@AComment("角色信息")
	private RoleViewModel info;

	public RoleViewModel getInfo() {
		return info;
	}

	public void setInfo(RoleViewModel info) {
		this.info = info;
	}

}
