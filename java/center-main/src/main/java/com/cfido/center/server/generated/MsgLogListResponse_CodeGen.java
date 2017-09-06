package com.cfido.center.server.generated;

import com.cfido.center.server.form.MsgLogListForm;
import com.cfido.center.server.logicObj.MsgLogViewModel;
import com.cfido.commons.beans.apiServer.BaseResponse;
import com.cfido.commons.utils.db.PageQueryResult;

/**
 * <pre>
 * MsgLog list 的返回结果
 * 我们使用了带_CodeGen字样的不规范的类名，表面这个类是由代码生成器生成的，不要自己修改
 * </pre>
 * 
* @author 梁韦江 生成于 2016-12-19 17:42:34
 */
public abstract class MsgLogListResponse_CodeGen extends BaseResponse {

	private MsgLogListForm form;

	private PageQueryResult<MsgLogViewModel> page;

	public MsgLogListForm getForm() {
		return form;
	}

	public void setForm(MsgLogListForm form) {
		this.form = form;
	}

	public PageQueryResult<MsgLogViewModel> getPage() {
		return page;
	}

	public void setPage(PageQueryResult<MsgLogViewModel> page) {
		this.page = page;
	}
}
