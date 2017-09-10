package com.cfido.center.server.logicObj;

import java.util.List;

import com.cfido.center.server.api.responses.beans.UserRoleInfoBean;
import com.cfido.center.server.entity.User;
import com.cfido.commons.utils.logicObj.BaseViewModel;
import com.cfido.commons.utils.utils.DateUtil;

/**
 * <pre>
 * {table.javaClassName}对象的 view model专门用于传递数据给页面。
 * 这个类虽然有po，但这个po是拷贝了一份，所以可以自由的修改内容
 * 
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:35
 */
public class UserViewModel extends BaseViewModel<UserObj, User> {

	private List<UserRoleInfoBean> userRoles;

	public UserViewModel(UserObj obj) {
		super(obj);
	}

	public String getCreateTime() {
		return DateUtil.dateFormat(po.getCreateTime());
	}

	public List<UserRoleInfoBean> getUserRoles() {
		return userRoles;
	}

	/** 更新用户所属的角色 */
	public void updateUserRoles() {
		this.userRoles = this.obj.getUserRoleInfoList();
	}
}
