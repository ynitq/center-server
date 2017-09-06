package com.cfido.center.server.generated;

import org.springframework.beans.factory.annotation.Autowired;

import com.cfido.center.server.entity.User;
import com.cfido.commons.utils.db.BaseDomainWithCache;
import com.cfido.commons.utils.db.ICommonDao;

/**
 * <pre>
 * 操作User表的domain,
 * 我们使用了带_CodeGen字样的不规范的类名，表面这个类是由代码生成器生成的，不要自己修改
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
public abstract class UserDomain_CodeGen extends BaseDomainWithCache<User, Integer> {

	@Autowired
	private ICommonDao commonDao;

	@Override
	protected ICommonDao getCommonDao() {
		return this.commonDao;
	}

}
