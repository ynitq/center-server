package com.cfido.center.server.domains;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cfido.commons.utils.db.BaseCommomDaoEntityManager;

/**
 * <pre>
 * 数据库用的通用repo， 为这个包下面的所有domain服务
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
@Repository
public class CommomDaoWithEntityManager extends BaseCommomDaoEntityManager {

	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	@PersistenceContext
	private EntityManager entityManager;
}
