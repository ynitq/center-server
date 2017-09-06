package com.cfido.center.server.logicObj;

import com.cfido.commons.utils.logicObj.BaseViewModel;
import com.cfido.center.server.entity.Role;

/**
 * <pre>
 * Role对象的 view model专门用于传递数据给页面。
 * 这个类虽然有po，但这个po是拷贝了一份，所以可以自由的修改内容
 * 
 * </pre>
 * 
 * @author 梁韦江 生成于 2017-09-06 20:34:36
 */
public class RoleViewModel extends BaseViewModel<RoleObj, Role> {

	public RoleViewModel(RoleObj obj) {
		super(obj);
	}
}
