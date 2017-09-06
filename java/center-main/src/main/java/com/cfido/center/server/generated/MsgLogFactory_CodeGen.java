package com.cfido.center.server.generated;

import org.springframework.beans.factory.annotation.Autowired;

import com.cfido.center.server.domains.MsgLogDomain;
import com.cfido.center.server.entity.MsgLog;
import com.cfido.center.server.logicObj.MsgLogObj;
import com.cfido.center.server.logicObj.MsgLogViewModel;
import com.cfido.commons.beans.others.IConverter;
import com.cfido.commons.utils.db.IObjFactoryDao;
import com.cfido.commons.utils.logicObj.BaseObjFactory;

/**
 * <pre>
 * MsgLog 工厂类基类
 * 我们使用了带_CodeGen字样的不规范的类名，表面这个类是由代码生成器生成的，不要自己修改
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
public abstract class MsgLogFactory_CodeGen extends BaseObjFactory<MsgLogObj, MsgLog, Integer> {

	private final IConverter<MsgLogObj, MsgLogViewModel> obj2ViewModelConverter = new IConverter<MsgLogObj, MsgLogViewModel>() {
		@Override
		public MsgLogViewModel convert(MsgLogObj src) {
			return new MsgLogViewModel(src);
		}
	};

	@Autowired
	protected MsgLogDomain msgLogDomain;

	@Override
	protected IObjFactoryDao<MsgLog, Integer> getDaoForObjFactory() {
		return msgLogDomain;
	}

	/**
	 * 将obj转为视图对象的转换器
	 * 
	 * @param po
	 * @return
	 */
	public IConverter<MsgLogObj, MsgLogViewModel> getObj2ViewModelConverter() {
		return obj2ViewModelConverter;
	}
}
