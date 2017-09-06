package com.cfido.center.server.api;

import com.cfido.center.server.api.responses.HistoryChartResponse;
import com.cfido.center.server.api.responses.ProjectListResponse;
import com.cfido.center.server.api.responses.ProjectViewResponse;
import com.cfido.center.server.api.responses.UserSubscribeResponse;
import com.cfido.center.server.form.HistoryListForm;
import com.cfido.center.server.form.ProjectEditForm;
import com.cfido.center.server.form.UserSubscribeForm;
import com.cfido.commons.annotation.api.AClass;
import com.cfido.commons.annotation.api.AMethod;
import com.cfido.commons.annotation.bean.AComment;
import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.beans.apiServer.impl.CommonSuccessResponse;
import com.cfido.commons.beans.form.IdForm;

/**
 * <pre>
 * Project 相关ajax接口
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
@AClass(value = "project")
@AComment(value = "监控-项目管理")
public interface IProjectApi {

	@AMethod(comment = "列表", saveFormToSession = true)
	ProjectListResponse list() throws BaseApiException;

	@AMethod(comment = "保存")
	CommonSuccessResponse save(ProjectEditForm form) throws BaseApiException;

	@AMethod(comment = "删除")
	CommonSuccessResponse delete(IdForm form) throws BaseApiException;

	@AMethod(comment = "查看")
	ProjectViewResponse view(IdForm form) throws BaseApiException;

	@AMethod(comment = "用户订阅或者取消订阅项目")
	UserSubscribeResponse userSubscribe(UserSubscribeForm form) throws BaseApiException;

	@AMethod(comment = "内存使用线图")
	HistoryChartResponse chartOfMemory(HistoryListForm form) throws BaseApiException;

	@AMethod(comment = "系统负载")
	HistoryChartResponse chartOfSystemLoad(HistoryListForm form) throws BaseApiException;

	@AMethod(comment = "请求数量")
	HistoryChartResponse chartOfAction(HistoryListForm form) throws BaseApiException;
}
