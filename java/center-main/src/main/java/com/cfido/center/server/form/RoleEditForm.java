package com.cfido.center.server.form;

import org.hibernate.validator.constraints.NotEmpty;

import com.cfido.commons.annotation.bean.AComment;
import com.cfido.commons.beans.form.IdForm;

/**
 * <pre>
 * 角色新建和保存的表单，只有一个名字
 * </pre>
 * 
 * @author 梁韦江
 */
public class RoleEditForm extends IdForm {

	@AComment("角色名字")
	@NotEmpty(message = "角色名字不能为空")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
