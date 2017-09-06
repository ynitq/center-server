package com.cfido.center.server.logicObj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.utils.logicObj.BasePoObj;
import com.cfido.center.server.entity.ProjectRole;

/**
 * <pre>
 * ProjectRole表的逻辑对象
 * </pre>
 * 
 * @author 梁韦江 生成于: 2017-09-06 20:34:35
 */
@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProjectRoleObj extends BasePoObj<ProjectRole> {

	@Autowired
	private ProjectRoleFactory factory;

	public ProjectRoleViewModel createModel() {
		return new ProjectRoleViewModel(this);
	}
	
	public void update() throws BaseApiException {
		this.factory.update(this, true);
	}
}
