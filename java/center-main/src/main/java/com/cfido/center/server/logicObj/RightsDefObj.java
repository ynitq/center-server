package com.cfido.center.server.logicObj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.utils.logicObj.BasePoObj;
import com.cfido.center.server.entity.RightsDef;

/**
 * <pre>
 * RightsDef表的逻辑对象
 * </pre>
 * 
 * @author 梁韦江 生成于: 2017-09-06 20:34:36
 */
@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RightsDefObj extends BasePoObj<RightsDef> {

	@Autowired
	private RightsDefFactory factory;

	public RightsDefViewModel createModel() {
		return new RightsDefViewModel(this);
	}
	
	public void update() throws BaseApiException {
		this.factory.update(this, true);
	}
}
