package com.cfido.center.server.domains;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cfido.center.server.entity.UserRole;
import com.cfido.commons.utils.db.BaseDomainWithCache;
import com.cfido.commons.utils.db.ICommonDao;

/**
 * <pre>
 * 操作UserRole表的domain,
 * </pre>
 * 
 * @author 梁韦江 生成于 2017-09-06 20:34:35
 */
@Component
public class UserRoleDomain extends BaseDomainWithCache<UserRole, Integer> {
	@Autowired
	private ICommonDao commonDao;

	@Override
	protected ICommonDao getCommonDao() {
		return this.commonDao;
	}

	public List<UserRole> findByRoleId(int roleId) {
		String sql = "from UserRole where roleId=?1";
		List<UserRole> list = this.find(sql, roleId);
		return list;
	}

	public List<UserRole> findByUserId(int userId) {
		String sql = "from UserRole where userId=?1";
		List<UserRole> list = this.find(sql, userId);
		return list;
	}

	public void deleteByRoleId(int roleId) {
		List<UserRole> list = this.findByRoleId(roleId);
		for (UserRole po : list) {
			this.commonDao.delete(po);
		}
	}

	public void deleteByUserId(int userId) {
		List<UserRole> list = this.findByUserId(userId);
		for (UserRole po : list) {
			this.commonDao.delete(po);
		}
	}

}
