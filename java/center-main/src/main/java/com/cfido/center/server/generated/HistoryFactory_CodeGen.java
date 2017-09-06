package com.cfido.center.server.generated;

import org.springframework.beans.factory.annotation.Autowired;

import com.cfido.center.server.domains.HistoryDomain;
import com.cfido.center.server.entity.History;
import com.cfido.center.server.logicObj.HistoryObj;
import com.cfido.center.server.logicObj.HistoryViewModel;
import com.cfido.commons.beans.others.IConverter;
import com.cfido.commons.utils.db.IObjFactoryDao;
import com.cfido.commons.utils.logicObj.BaseObjFactory;

/**
 * <pre>
 * History 工厂类基类
 * 我们使用了带_CodeGen字样的不规范的类名，表面这个类是由代码生成器生成的，不要自己修改
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
public abstract class HistoryFactory_CodeGen extends BaseObjFactory<HistoryObj, History, Integer> {

	private final IConverter<HistoryObj, HistoryViewModel> obj2ViewModelConverter = new IConverter<HistoryObj, HistoryViewModel>() {
		@Override
		public HistoryViewModel convert(HistoryObj src) {
			return new HistoryViewModel(src);
		}
	};

	@Autowired
	protected HistoryDomain historyDomain;

	@Override
	protected IObjFactoryDao<History, Integer> getDaoForObjFactory() {
		return historyDomain;
	}

	/**
	 * 将obj转为视图对象的转换器
	 * 
	 * @param po
	 * @return
	 */
	public IConverter<HistoryObj, HistoryViewModel> getObj2ViewModelConverter() {
		return obj2ViewModelConverter;
	}
}
