package com.cfido.center.server.logicObj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.cfido.center.server.domains.RightsDefDomain;
import com.cfido.center.server.entity.RightsDef;
import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.beans.monitor.ServerRightsBean;
import com.cfido.commons.beans.others.IConverter;
import com.cfido.commons.utils.db.IObjFactoryDao;
import com.cfido.commons.utils.logicObj.BaseObjFactory;

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
		return po;
	}

	@Override
	public void delete(RightsDefObj obj) throws BaseApiException {
		super.delete(obj);
	}

	/** 增加或者保存权限定义 */
	public void addRihgtsDef(ServerRightsBean bean) throws BaseApiException {
		Assert.notNull(bean, "权限bean不能为空");
		Assert.hasText(bean.getId(), "必须有权限id");

		RightsDef po = this.rightsDefDomain.findByRights(bean.getId());
		if (po == null) {
			po = this.createDefaultPo();
			po.setRightsId(bean.getId());
			po.setJsonText(JSON.toJSONString(bean));
			this.rightsDefDomain.insert(po);
		} else {
			po.setJsonText(JSON.toJSONString(bean));
			this.rightsDefDomain.update(po, false);
		}
	}

}
