package com.cfido.center.server.domains;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.cfido.commons.utils.db.BaseDomainWithCache;
import com.cfido.commons.utils.db.ICommonDao;
import com.cfido.center.server.entity.UserRole;


/**
 * <pre>
 * 操作UserRole表的domain, 
 * </pre>
 * 
 * @author 梁韦江 生成于 2017-09-06 20:34:35
 */
@Component
public class UserRoleDomain extends BaseDomainWithCache<UserRole, Integer>  {
	@Autowired
	private ICommonDao commonDao;

	@Override
	protected ICommonDao getCommonDao() {
		return this.commonDao;
	}

}

