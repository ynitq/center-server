package com.cfido.center.server.domains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cfido.center.server.entity.RightsDef;
import com.cfido.commons.utils.db.BaseDomainWithCache;
import com.cfido.commons.utils.db.ICommonDao;

/**
 * <pre>
 * 操作RightsDef表的domain,
 * </pre>
 * 
 * @author 梁韦江 生成于 2017-09-06 20:34:35
 */
@Component
public class RightsDefDomain extends BaseDomainWithCache<RightsDef, Integer> {
	@Autowired
	private ICommonDao commonDao;

	@Override
	protected ICommonDao getCommonDao() {
		return this.commonDao;
	}

	public RightsDef findByRights(String rightsId) {
		String sql = "from RightsDef where rightsId=?1";
		return this.findOne(sql, rightsId);
	}

}
