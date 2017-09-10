package com.cfido.center.server.api;

import com.cfido.center.server.api.responses.UserListResponse;
import com.cfido.center.server.api.responses.UserRoleUpdateResponse;
import com.cfido.center.server.api.responses.UserViewResponse;
import com.cfido.center.server.form.RoleAndUserIdForm;
import com.cfido.center.server.form.UserEditForm;
import com.cfido.commons.annotation.api.AClass;
import com.cfido.commons.annotation.api.AMethod;
import com.cfido.commons.annotation.bean.AComment;
import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.beans.apiServer.impl.CommonSuccessResponse;
import com.cfido.commons.beans.form.IdForm;
import com.cfido.commons.beans.form.ResetPasswordForm;

/**
 * <pre>
 * User 相关ajax接口
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
@AClass(value = "user")
@AComment(value = "监控-用户管理")
public interface IUserApi {

	@AMethod(comment = "列表")
	UserListResponse list() throws BaseApiException;

	@AMethod(comment = "保存")
	CommonSuccessResponse save(UserEditForm form) throws BaseApiException;

	@AMethod(comment = "删除")
	CommonSuccessResponse delete(IdForm form) throws BaseApiException;

	@AMethod(comment = "查看")
	UserViewResponse view(IdForm form) throws BaseApiException;

	@AMethod(comment = "重置密码")
	CommonSuccessResponse resetPassword(ResetPasswordForm form) throws BaseApiException;

	@AMethod(comment = "封号或者解封")
	UserViewResponse lockOrUnlock(IdForm form) throws BaseApiException;

	@AMethod(comment = "改变用户的角色")
	UserRoleUpdateResponse changeUserRole(RoleAndUserIdForm form) throws BaseApiException;
}
