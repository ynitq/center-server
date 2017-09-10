package com.cfido.center.server.logicObj;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cfido.center.server.api.responses.beans.UserRoleInfoBean;
import com.cfido.center.server.domains.RoleDomain;
import com.cfido.center.server.domains.UserRoleDomain;
import com.cfido.center.server.entity.Role;
import com.cfido.center.server.entity.User;
import com.cfido.center.server.entity.UserRole;
import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.beans.exceptions.security.InvalidPasswordException;
import com.cfido.commons.spring.security.CommonAdminWebUser;
import com.cfido.commons.utils.logicObj.BasePoObj;
import com.cfido.commons.utils.utils.PasswordEncoder;

/**
 * <pre>
 * User表的逻辑对象
 * </pre>
 * 
 * @author 梁韦江 生成于: 2016-12-19 17:42:35
 */
@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserObj extends BasePoObj<User> {

	@Autowired
	private UserFactory userFactory;

	@Autowired
	private RoleDomain roleDomain;

	@Autowired
	private UserRoleDomain userRoleDomain;

	public UserViewModel createModel() {
		return new UserViewModel(this);
	}

	/** 检查密码是否正确 */
	public void checkPassword(String passwordInForm) throws InvalidPasswordException {
		PasswordEncoder.checkPassword(passwordInForm, this.po.getPassword());
	}

	/**
	 * 使用该用户通用管理员
	 * 
	 * @return
	 */
	public CommonAdminWebUser createCommonAdminWebUser() {
		return new CommonAdminWebUser(po.getAccount(), po.getPassword());
	}

	/**
	 * 修改密码
	 * 
	 * @param newPassword
	 * @throws BaseApiException
	 */
	public void changePassword(String newPassword) throws BaseApiException {
		this.po.setPassword(PasswordEncoder.encodePassword(newPassword));
		this.userFactory.update(this, false);
	}

	/** 获得该用户与所有角色的关系 */
	public List<UserRoleInfoBean> getUserRoleInfoList() {
		List<Role> allRoles = this.roleDomain.findAll();// 获取所有的角色信息
		List<UserRole> allUserRole = this.userRoleDomain.findByUserId(this.po.getId()); // 获取该用户的角色信息

		List<UserRoleInfoBean> userRoles = new LinkedList<>();

		// 将用户的角色信息放入set，用于查找该用户是否属于某角色
		Set<Integer> roleIdSet = new HashSet<>();
		for (UserRole userRolePo : allUserRole) {
			roleIdSet.add(userRolePo.getRoleId());
		}

		// 变量所有角色，构建列表
		for (Role rolePo : allRoles) {
			UserRoleInfoBean bean = new UserRoleInfoBean(rolePo);
			userRoles.add(bean);
			bean.setInRole(roleIdSet.contains(rolePo.getId()));
		}
		return userRoles;
	}

	public int getUserId() {
		return this.po.getId();
	}

	/** 变更角色和用户的关系 */
	public void changeRole(RoleObj role) {
		UserRole ur = this.userRoleDomain.getByUserIdAndRoleId(this.getUserId(), role.getRoleId());
		if (ur == null) {
			// 如果用户不在这个组，就添加关系
			ur = new UserRole();
			ur.setRoleId(role.getRoleId());
			ur.setUserId(this.getUserId());
			this.userRoleDomain.insert(ur);
		} else {
			// 如果用户在这个组，就删除这个关系
			this.userRoleDomain.delete(ur);
		}

	}

}
