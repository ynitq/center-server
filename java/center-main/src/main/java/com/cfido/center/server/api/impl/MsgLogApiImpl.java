package com.cfido.center.server.api.impl;

import org.springframework.stereotype.Service;

import com.cfido.center.server.api.IMsgLogApi;
import com.cfido.center.server.entity.MsgLog;
import com.cfido.center.server.form.MsgLogEditForm;
import com.cfido.center.server.generated.MsgLogApiImpl_CodeGen;
import com.cfido.center.server.security.WebUser;
import com.cfido.commons.annotation.api.AApiServerImpl;
import com.cfido.commons.beans.apiExceptions.SimpleApiException;
import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.beans.apiServer.impl.CommonSuccessResponse;
import com.cfido.commons.beans.form.IdForm;
import com.cfido.commons.loginCheck.ANeedCheckLogin;


/**
 * <pre>
 * MsgLog api接口实现类
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
@Service
@AApiServerImpl
@ANeedCheckLogin(userClass = WebUser.class)
public class MsgLogApiImpl extends MsgLogApiImpl_CodeGen implements IMsgLogApi {

	@Override
	protected void tranFormDataToPo(MsgLogEditForm form, MsgLog po, boolean isUpdate) {
	}

	@Override
	public CommonSuccessResponse delete(IdForm form) throws BaseApiException {
		throw new SimpleApiException("消息表不允许api操作");
	}

	@Override
	public CommonSuccessResponse save(MsgLogEditForm form) throws BaseApiException {
		throw new SimpleApiException("消息表不允许api操作");
	}
}
