package com.cfido.center.server.generated;

import com.cfido.center.server.api.responses.MsgLogListResponse;
import com.cfido.center.server.api.responses.MsgLogViewResponse;
import com.cfido.center.server.form.MsgLogEditForm;
import com.cfido.center.server.form.MsgLogListForm;
import com.cfido.commons.annotation.api.AMethod;
import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.beans.apiServer.impl.CommonSuccessResponse;
import com.cfido.commons.beans.form.IdForm;
/**
 * <pre>
 * MsgLog 相关ajax接口
 * 我们使用了带_CodeGen字样的不规范的类名，表面这个类是由代码生成器生成的，不要自己修改
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
public interface IMsgLogApi_CodeGen {

	@AMethod(comment = "列表", saveFormToSession = true)
	MsgLogListResponse list(MsgLogListForm form) throws BaseApiException;
	
	@AMethod(comment = "保存")
	CommonSuccessResponse save(MsgLogEditForm form) throws BaseApiException;

	@AMethod(comment = "删除")
	CommonSuccessResponse delete(IdForm form) throws BaseApiException;

	@AMethod(comment = "查看")
	MsgLogViewResponse view(IdForm form) throws BaseApiException;

}
