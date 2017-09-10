package com.cfido.center.server.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.cfido.commons.annotation.api.AForm;
import com.cfido.commons.annotation.bean.AComment;
import com.cfido.commons.beans.form.IdForm;

/**
 * <pre>
 * 角色id和用户id的表单
 * </pre>
 * 
 * @author 梁韦江
 */
@AForm
public class RoleAndUserIdForm extends IdForm {

	@NotNull(message = "角色id不能为空")
	@Min(value = 1, message = "角色id非法")
	@AComment("角色id")
	private Integer roldId;

	public Integer getRoldId() {
		return roldId;
	}

	public void setRoldId(Integer roldId) {
		this.roldId = roldId;
	}

}
