package com.cfido.center.server.logicObj;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.beans.others.IConverter;
import com.cfido.commons.utils.db.IObjFactoryDao;
import com.cfido.commons.utils.logicObj.BaseObjFactory;


import com.cfido.center.server.entity.Role;

import com.cfido.center.server.logicObj.RoleObj;
import com.cfido.center.server.logicObj.RoleViewModel;
import com.cfido.center.server.domains.RoleDomain;

/**
 * <pre>
 * Role逻辑对象的工厂类，这个文件在生成时默认不覆盖
 * </pre>
 * 
 * @author 梁韦江 生成于 2017-09-06 20:34:35
 */
@Service
public class RoleFactory extends BaseObjFactory<RoleObj, Role, Integer> {

	private final IConverter<RoleObj, RoleViewModel> obj2ViewModelConverter = new IConverter<RoleObj, RoleViewModel>() {
		@Override
		public RoleViewModel convert(RoleObj src) {
			return src.createModel();
		}
	};

	@Autowired
	protected RoleDomain roleDomain;

	@Override
	protected IObjFactoryDao<Role, Integer> getDaoForObjFactory() {
		return roleDomain;
	}

	/**
	 * 将obj转为视图对象的转换器
	 */
	public IConverter<RoleObj, RoleViewModel> getObj2ViewModelConverter() {
		return obj2ViewModelConverter;
	}

	public Role createDefaultPo() {
		Role po = new Role();

		// TODO 创建新的Role时，设置默认值

		return po;
	}

	@Override
	public void delete(RoleObj obj) throws BaseApiException {
		// TODO 删除Role时，需要人工判断是否真的从数据库中删除
		super.delete(obj);
	}

}
