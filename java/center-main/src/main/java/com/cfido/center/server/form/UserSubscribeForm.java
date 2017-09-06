package com.cfido.center.server.form;

import javax.validation.constraints.NotNull;

import com.cfido.commons.annotation.api.AForm;
import com.cfido.commons.annotation.bean.AComment;
import com.cfido.commons.beans.form.IdForm;

/**
 * <pre>
 * 用户订阅项目表单
 * </pre>
 * 
 * @author 梁韦江 2016年12月22日
 */
@AForm
public class UserSubscribeForm extends IdForm {
	@NotNull(message = "用户id不能为空")
	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	@AComment(value = "用户ID")
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
