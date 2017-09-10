package com.cfido.center.server.api.responses;

import com.cfido.center.server.logicObj.ProjectViewModel;
import com.cfido.commons.beans.apiServer.BaseResponse;

/**
 * <pre>
 * Project 的查看Response。这个类不会被覆盖
 * 无论该表是否能查询，查看的Response总是存在的
 * </pre>
 * 
 * @author 梁韦江 2016年8月25日
 */
public class ProjectViewResponse extends BaseResponse {
	private ProjectViewModel info;

	public ProjectViewModel getInfo() {
		return info;
	}

	public void setInfo(ProjectViewModel info) {
		this.info = info;
	}

}
