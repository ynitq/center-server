package com.cfido.center.server.api.responses.beans;

import com.cfido.center.server.entity.Role;
import com.cfido.commons.annotation.bean.AComment;

/**
 * <pre>
 * 用户的角色
 * </pre>
 * 
 * @author 梁韦江
 */
public class UserRoleInfoBean implements Comparable<UserRoleInfoBean> {
	@AComment("角色信息")
	private final int roleId;
	@AComment("角色信息")
	private final String roleName;

	@AComment("是否在这个角色")
	private boolean inRole;

	public UserRoleInfoBean(Role rolePo) {
		this.roleId = rolePo.getId();
		this.roleName = rolePo.getName();
	}

	public boolean isInRole() {
		return inRole;
	}

	public void setInRole(boolean inRole) {
		this.inRole = inRole;
	}

	@Override
	public int compareTo(UserRoleInfoBean o) {
		int res = 0;
		if (this.inRole && !o.inRole) {
			res = -1;
		} else if (!this.inRole && o.inRole) {
			res = 1;
		}

		if (res == 0) {
			res = this.roleName.compareTo(o.roleName);
		}
		return res;

	}

}
