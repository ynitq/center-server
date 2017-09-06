package com.cfido.center.server.api.responses;

import java.util.List;

import com.cfido.center.server.logicObj.ProjectViewModel;
import com.cfido.commons.beans.apiServer.BaseResponse;

/**
 * <pre>
 * Project 列表 的Response。
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
public class ProjectListResponse extends BaseResponse {

	private List<ProjectViewModel> list;

	public List<ProjectViewModel> getList() {
		return list;
	}

	public void setList(List<ProjectViewModel> list) {
		this.list = list;
	}

}
