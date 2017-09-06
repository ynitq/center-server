package com.cfido.center.server.logicObj;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.beans.others.IConverter;
import com.cfido.commons.utils.db.IObjFactoryDao;
import com.cfido.commons.utils.logicObj.BaseObjFactory;


import com.cfido.center.server.entity.RightsDef;

import com.cfido.center.server.logicObj.RightsDefObj;
import com.cfido.center.server.logicObj.RightsDefViewModel;
import com.cfido.center.server.domains.RightsDefDomain;

/**
 * <pre>
 * RightsDef逻辑对象的工厂类，这个文件在生成时默认不覆盖
 * </pre>
 * 
 * @author 梁韦江 生成于 2017-09-06 20:34:35
 */
@Service
public class RightsDefFactory extends BaseObjFactory<RightsDefObj, RightsDef, Integer> {

	private final IConverter<RightsDefObj, RightsDefViewModel> obj2ViewModelConverter = new IConverter<RightsDefObj, RightsDefViewModel>() {
		@Override
		public RightsDefViewModel convert(RightsDefObj src) {
			return src.createModel();
		}
	};

	@Autowired
	protected RightsDefDomain rightsDefDomain;

	@Override
	protected IObjFactoryDao<RightsDef, Integer> getDaoForObjFactory() {
		return rightsDefDomain;
	}

	/**
	 * 将obj转为视图对象的转换器
	 */
	public IConverter<RightsDefObj, RightsDefViewModel> getObj2ViewModelConverter() {
		return obj2ViewModelConverter;
	}

	public RightsDef createDefaultPo() {
		RightsDef po = new RightsDef();

		// TODO 创建新的RightsDef时，设置默认值

		return po;
	}

	@Override
	public void delete(RightsDefObj obj) throws BaseApiException {
		// TODO 删除RightsDef时，需要人工判断是否真的从数据库中删除
		super.delete(obj);
	}

}
