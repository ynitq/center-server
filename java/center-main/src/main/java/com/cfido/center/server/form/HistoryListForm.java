package com.cfido.center.server.form;

import javax.validation.constraints.NotNull;

import com.cfido.commons.annotation.api.AForm;
import com.cfido.commons.annotation.api.AMock;

/**
 * <pre>
 * TODO History列表的表单
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
@AForm
public class HistoryListForm {

	//--------------- 属性 ---------------
	@NotNull
	private Integer projectId;

	public Integer getProjectId() {
		return projectId;
	}

	@AMock("8")
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	
}
