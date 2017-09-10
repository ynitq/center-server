package com.cfido.center.server.logicObj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfido.center.server.domains.RoleDomain;
import com.cfido.center.server.domains.UserRoleDomain;
import com.cfido.center.server.entity.Role;
import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.beans.others.IConverter;
import com.cfido.commons.utils.db.IObjFactoryDao;
import com.cfido.commons.utils.logicObj.BaseObjFactory;

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

	@Autowired
	protected UserRoleDomain userRoleDomain;

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
		return po;
	}

	@Override
	public void delete(RoleObj obj) throws BaseApiException {
		// 需要先删除关联表中的数据
		this.userRoleDomain.deleteByRoleId(obj.getPo().getId());

		// 然后再删除本身
		super.delete(obj);
	}

}
