package com.cfido.center.server.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfido.center.server.api.IPublic;
import com.cfido.center.server.api.responses.AdminUserInfoResponse;
import com.cfido.center.server.logicObj.UserFactory;
import com.cfido.center.server.logicObj.UserObj;
import com.cfido.center.server.security.WebUser;
import com.cfido.commons.annotation.api.AApiServerImpl;
import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.beans.apiServer.impl.CommonSuccessResponse;
import com.cfido.commons.beans.form.ChangePasswordForm;
import com.cfido.commons.beans.form.LoginForm;
import com.cfido.commons.loginCheck.ANeedCheckLogin;
import com.cfido.commons.spring.security.CommonAdminWebUser;

/**
 * <pre>
 * IPublic实现类
 * </pre>
 * 
 * @author 梁韦江 2016年12月19日
 */
@Service
@AApiServerImpl
public class PublicImpl extends BaseApiImpl implements IPublic {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PublicImpl.class);

	@Autowired
	private UserFactory userFactory;

	@Override
	public AdminUserInfoResponse login(LoginForm form) throws BaseApiException {

		UserObj userObj = this.userFactory.getByAccount(form.getAccount());

		// 检查密码
		userObj.checkPassword(form.getPassword());

		// 如果检查都通过了
		WebUser user = new WebUser(userObj);
		this.loginContext.onLoginSuccess(user, form.isRememberMe());

		// 顺便将字典管理用户也记录下来
		this.loginContext.onLoginSuccess(userObj.createCommonAdminWebUser(), form.isRememberMe());

		AdminUserInfoResponse res = new AdminUserInfoResponse();
		res.setAccount(userObj.getPo().getAccount());
		res.setName(userObj.getPo().getName());

		return res;
	}

	@Override
	@ANeedCheckLogin(userClass = WebUser.class)
	public CommonSuccessResponse changePassword(ChangePasswordForm form) throws BaseApiException {

		WebUser webUser = this.loginContext.getUser(WebUser.class);

		// 重新从数据库中获取用户，防止数据不同步
		UserObj user = this.userFactory.reload(webUser);

		// 校验旧密码
		user.checkPassword(form.getOldPassword());

		// 如果校验不抛错，就设置新密码
		user.changePassword(form.getNewPassword());

		log.debug("管理员 {] ，修改密码", webUser.getUsername());

		this.loginContext.onLogout(WebUser.class);
		this.loginContext.onLogout(CommonAdminWebUser.class);

		this.loginContext.sendMsgToNextPage("密码修改成功!");

		return CommonSuccessResponse.DEFAULT;
	}

	@Override
	public CommonSuccessResponse logout() {
		this.loginContext.onLogout(WebUser.class);
		return CommonSuccessResponse.DEFAULT;
	}

}
