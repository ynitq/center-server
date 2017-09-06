package com.cfido.center.server.logicObj;

import com.cfido.center.server.entity.History;
import com.cfido.commons.utils.logicObj.BaseViewModel;

/**
 * <pre>
 * {table.javaClassName}对象的 view model专门用于传递数据给页面。
 * 这个类虽然有po，但这个po是拷贝了一份，所以可以自由的修改内容
 * 
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:35
 */
public class HistoryViewModel extends BaseViewModel<HistoryObj, History> {

	public HistoryViewModel(HistoryObj obj) {
		super(obj);
	}
}
