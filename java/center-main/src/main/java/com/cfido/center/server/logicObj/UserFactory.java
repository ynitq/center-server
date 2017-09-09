package com.cfido.center.server.logicObj;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cfido.center.server.config.MonitorServerProperties;
import com.cfido.center.server.entity.User;
import com.cfido.center.server.generated.UserFactory_CodeGen;
import com.cfido.center.server.security.WebUser;
import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.beans.exceptions.security.UserNotFoundException;
import com.cfido.commons.utils.utils.LogUtil;

/**
 * <pre>
 * User逻辑对象的工厂类，这个文件在生成时默认不覆盖
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
@Service
public class UserFactory extends UserFactory_CodeGen {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserFactory.class);

	@Autowired
	private MonitorServerProperties prop;

	public User createDefaultPo() {
		User po = new User();

		po.setCreateTime(new Date());
		po.setDisabled(false);
		po.setSuperUser(false);

		return po;
	}

	@Override
	public void delete(UserObj obj) throws BaseApiException {
		super.delete(obj);
	}

	/**
	 * 根据账号查询对象
	 * 
	 * @param account
	 * @return
	 * @throws UserNotFoundException
	 */
	public UserObj getByAccount(String account) throws UserNotFoundException {
		UserObj obj = null;

		if (StringUtils.hasText(account)) {

			String sql = "from User where account=?1";
			User po = this.userDomain.findOne(sql, account);
			if (po != null) {
				obj = this.convertToObj(po);
			}
		}

		if (obj == null) {
			throw new UserNotFoundException();
		} else {
			return obj;
		}
	}

	@PostConstruct
	protected void init() {
		try {
			this.getByAccount("admin");
			return;
		} catch (UserNotFoundException e) {

			log.info("默认的admin用户不存在，自动创建");
			User po = this.createDefaultPo();
			po.setSuperUser(true);// 超级用户
			po.setAccount("admin");
			po.setPassword(this.prop.getAdmin().getPassword());
			po.setName("admin");
			po.setMemo("后台超级用户");

			try {
				this.create(po);
			} catch (BaseApiException e1) {
				LogUtil.traceError(log, e1);
				throw new RuntimeException("创建默认用户时出错了");
			}
		}
	}

	/**
	 * 将所有用户放到一个已id为key的map中
	 */
	public Map<Integer, UserObj> getAllUserMap() {
		List<UserObj> userList = this.findAll();
		Map<Integer, UserObj> map = new HashMap<>();

		for (UserObj obj : userList) {
			map.put(obj.getPo().getId(), obj);
		}
		return map;
	}

	/**
	 * 刷新对象
	 * 
	 * @param obj
	 */
	public UserObj reload(WebUser webUser) {
		return this.getById(webUser.getUserId());
	}

}
