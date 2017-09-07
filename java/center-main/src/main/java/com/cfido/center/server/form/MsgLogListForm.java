package com.cfido.center.server.form;

import javax.validation.constraints.NotNull;

import com.cfido.commons.annotation.api.AForm;
import com.cfido.commons.beans.form.PageForm;

/**
 * <pre>
 * MsgLog列表的表单
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
@AForm
public class MsgLogListForm extends PageForm {
	// private static final long serialVersionUID = 1L;

	// --------------- 属性 ---------------
	@NotNull
	private Integer projectId;

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

}
