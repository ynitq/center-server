package com.cfido.center.server.api.responses;

import java.util.List;

import com.cfido.center.server.api.responses.beans.UserRoleInfoBean;
import com.cfido.commons.annotation.bean.AComment;
import com.cfido.commons.beans.apiServer.BaseResponse;

/**
 * <pre>
 * 用户的角色发送变化时，重新更新一下
 * </pre>
 * 
 * @author 梁韦江
 */
@AComment("更新用户和角色的关系")
public class UserRoleUpdateResponse extends BaseResponse {

	@AComment("用户和角色的关系")
	private List<UserRoleInfoBean> userRoles;

	public List<UserRoleInfoBean> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRoleInfoBean> userRoles) {
		this.userRoles = userRoles;
	}

}
