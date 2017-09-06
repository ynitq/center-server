package com.cfido.center.server.form;

import javax.validation.constraints.NotNull;

import com.cfido.commons.annotation.api.AForm;
import com.cfido.commons.annotation.bean.AComment;

/**
 * <pre>
 * User保存表单
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
@AForm
public class UserEditForm {

	// --------------- 属性 ---------------
	private Integer id;

	@NotNull(message = "名字不能为空")
	private String name;

	private String phone;

	private String email;

	private String memo;

	private String account;
	private String password;

	public String getAccount() {
		return account;
	}

	public String getEmail() {
		return this.email;
	}

	public String getMemo() {
		return this.memo;
	}

	public String getName() {
		return this.name;
	}

	public String getPassword() {
		return password;
	}

	public String getPhone() {
		return this.phone;
	}

	@AComment(value = "创建新用户时，需要填写账号")
	public void setAccount(String account) {
		this.account = account;
	}

	@AComment(value = "邮箱")
	public void setEmail(String email) {
		this.email = email;
	}

	@AComment(value = "备注")
	public void setMemo(String memo) {
		this.memo = memo;
	}

	@AComment(value = "名字")
	public void setName(String name) {
		this.name = name;
	}

	@AComment(value = "创建新用户时的密码")
	public void setPassword(String password) {
		this.password = password;
	}

	@AComment(value = "电话号码")
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getId() {
		return id;
	}

	@AComment(value = "用户id")
	public void setId(Integer id) {
		this.id = id;
	}
}
