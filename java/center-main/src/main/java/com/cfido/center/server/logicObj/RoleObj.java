package com.cfido.center.server.logicObj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cfido.center.server.entity.Role;
import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.utils.logicObj.BasePoObj;

/**
 * <pre>
 * Role表的逻辑对象
 * </pre>
 * 
 * @author 梁韦江 生成于: 2017-09-06 20:34:36
 */
@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RoleObj extends BasePoObj<Role> {

	@Autowired
	private RoleFactory factory;

	public RoleViewModel createModel() {
		return new RoleViewModel(this);
	}
	
	public void update() throws BaseApiException {
		this.factory.update(this, true);
	}

	public int getRoleId() {
		return this.po.getId();
	}
}
