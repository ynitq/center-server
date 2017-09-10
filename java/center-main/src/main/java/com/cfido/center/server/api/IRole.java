package com.cfido.center.server.api;

import com.cfido.center.server.api.responses.RoleListResponse;
import com.cfido.center.server.api.responses.RoleViewResponse;
import com.cfido.center.server.form.RoleEditForm;
import com.cfido.commons.annotation.api.AClass;
import com.cfido.commons.annotation.api.AMethod;
import com.cfido.commons.annotation.bean.AComment;
import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.beans.apiServer.impl.CommonSuccessResponse;
import com.cfido.commons.beans.form.IdForm;

/**
 * <pre>
 * 登录用
 * </pre>
 * 
 * @author 梁韦江 2016年12月19日
 */
@AClass
@AComment(value = "监控-角色")
public interface IRole {
	@AMethod(comment = "列表")
	RoleListResponse list() throws BaseApiException;

	@AMethod(comment = "保存")
	RoleViewResponse save(RoleEditForm form) throws BaseApiException;

	@AMethod(comment = "删除")
	CommonSuccessResponse delete(IdForm form) throws BaseApiException;

	@AMethod(comment = "查看")
	RoleViewResponse view(IdForm form) throws BaseApiException;

}
