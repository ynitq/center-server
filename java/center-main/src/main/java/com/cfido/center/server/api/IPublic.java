package com.cfido.center.server.api;

import com.cfido.center.server.api.responses.AdminUserInfoResponse;
import com.cfido.commons.annotation.api.AClass;
import com.cfido.commons.annotation.api.AMethod;
import com.cfido.commons.annotation.bean.AComment;
import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.beans.apiServer.impl.CommonSuccessResponse;
import com.cfido.commons.beans.form.ChangePasswordForm;
import com.cfido.commons.beans.form.LoginForm;

/**
 * <pre>
 * 登录用
 * </pre>
 * 
 * @author 梁韦江 2016年12月19日
 */
@AClass("public")
@AComment(value = "监控-登录登出")
public interface IPublic {

	@AMethod(comment = "登录")
	AdminUserInfoResponse login(LoginForm form) throws BaseApiException;

	@AMethod(comment = "登出")
	CommonSuccessResponse logout();

	@AMethod(comment = "修改密码")
	CommonSuccessResponse changePassword(ChangePasswordForm form) throws BaseApiException;

}
