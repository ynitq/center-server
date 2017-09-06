package com.cfido.center.server.logicObj;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cfido.center.server.entity.MsgLog;
import com.cfido.commons.utils.logicObj.BasePoObj;

/**
 * <pre>
 * MsgLog表的逻辑对象
 * </pre>
 * 
 * @author 梁韦江 生成于: 2016-12-19 17:42:34
 */
@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MsgLogObj extends BasePoObj<MsgLog> {

	public MsgLogViewModel createModel() {
		return new MsgLogViewModel(this);
	}
}
