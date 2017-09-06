package com.cfido.center.server.logicObj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cfido.center.server.entity.User;
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

	public UserViewModel createModel() {
		return new UserViewModel(this);
	}

	/** 检查密码是否正确 */
	public void checkPassword(String passwordInForm) throws InvalidPasswordException {
		PasswordEncoder.checkPassword(passwordInForm, this.po.getPassword());
	}

	/**
	 * 使用该用户创建字典管理员
	 * 
	 * @return
	 */
	public CommonAdminWebUser createDictAdminWebUser() {
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

}
