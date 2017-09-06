package com.cfido.center.server.generated;

import org.springframework.beans.factory.annotation.Autowired;

import com.cfido.center.server.domains.UserDomain;
import com.cfido.center.server.entity.User;
import com.cfido.center.server.logicObj.UserObj;
import com.cfido.center.server.logicObj.UserViewModel;
import com.cfido.commons.beans.others.IConverter;
import com.cfido.commons.utils.db.IObjFactoryDao;
import com.cfido.commons.utils.logicObj.BaseObjFactory;

/**
 * <pre>
 * User 工厂类基类
 * 我们使用了带_CodeGen字样的不规范的类名，表面这个类是由代码生成器生成的，不要自己修改
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
public abstract class UserFactory_CodeGen extends BaseObjFactory<UserObj, User, Integer> {

	private final IConverter<UserObj, UserViewModel> obj2ViewModelConverter = new IConverter<UserObj, UserViewModel>() {
		@Override
		public UserViewModel convert(UserObj src) {
			return new UserViewModel(src);
		}
	};

	@Autowired
	protected UserDomain userDomain;

	@Override
	protected IObjFactoryDao<User, Integer> getDaoForObjFactory() {
		return userDomain;
	}

	/**
	 * 将obj转为视图对象的转换器
	 * 
	 * @param po
	 * @return
	 */
	public IConverter<UserObj, UserViewModel> getObj2ViewModelConverter() {
		return obj2ViewModelConverter;
	}
}
