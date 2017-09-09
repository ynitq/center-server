package com.cfido.center.server.logicObj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfido.center.server.domains.UserRoleDomain;
import com.cfido.center.server.entity.UserRole;
import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.beans.others.IConverter;
import com.cfido.commons.utils.db.IObjFactoryDao;
import com.cfido.commons.utils.logicObj.BaseObjFactory;

/**
 * <pre>
 * UserRole逻辑对象的工厂类，这个文件在生成时默认不覆盖
 * </pre>
 * 
 * @author 梁韦江 生成于 2017-09-06 20:34:35
 */
@Service
public class UserRoleFactory extends BaseObjFactory<UserRoleObj, UserRole, Integer> {

	private final IConverter<UserRoleObj, UserRoleViewModel> obj2ViewModelConverter = new IConverter<UserRoleObj, UserRoleViewModel>() {
		@Override
		public UserRoleViewModel convert(UserRoleObj src) {
			return src.createModel();
		}
	};

	@Autowired
	protected UserRoleDomain userRoleDomain;

	@Override
	protected IObjFactoryDao<UserRole, Integer> getDaoForObjFactory() {
		return userRoleDomain;
	}

	/**
	 * 将obj转为视图对象的转换器
	 */
	public IConverter<UserRoleObj, UserRoleViewModel> getObj2ViewModelConverter() {
		return obj2ViewModelConverter;
	}

	public UserRole createDefaultPo() {
		UserRole po = new UserRole();
		return po;
	}

	@Override
	public void delete(UserRoleObj obj) throws BaseApiException {
		super.delete(obj);
	}

}
