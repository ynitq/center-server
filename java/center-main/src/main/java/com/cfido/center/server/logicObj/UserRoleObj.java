package com.cfido.center.server.logicObj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.utils.logicObj.BasePoObj;
import com.cfido.center.server.entity.UserRole;

/**
 * <pre>
 * UserRole表的逻辑对象
 * </pre>
 * 
 * @author 梁韦江 生成于: 2017-09-06 20:34:36
 */
@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserRoleObj extends BasePoObj<UserRole> {

	@Autowired
	private UserRoleFactory factory;

	public UserRoleViewModel createModel() {
		return new UserRoleViewModel(this);
	}
	
	public void update() throws BaseApiException {
		this.factory.update(this, true);
	}
}
