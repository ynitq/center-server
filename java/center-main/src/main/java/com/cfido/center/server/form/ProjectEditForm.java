package com.cfido.center.server.form;
import com.cfido.commons.annotation.api.AForm;
import com.cfido.commons.annotation.bean.AComment;
import com.cfido.commons.beans.form.IdForm;

/**
 * <pre>
 * Project保存表单
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
@AForm
public class ProjectEditForm extends IdForm {

	//--------------- 属性 ---------------
	
	private String memo;
	
	private boolean sendWarn;
	
	private String[] userId;

	private String displayName;

	/** 是否需要检查 */
	private boolean needCheck;

	public String getMemo() {
		return memo;
	}

	@AComment(value = "备注")
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public boolean isSendWarn() {
		return sendWarn;
	}

	@AComment(value = "是否需要报警")
	public void setSendWarn(boolean sendWarn) {
		this.sendWarn = sendWarn;
	}

	public String[] getUserId() {
		return userId;
	}

	@AComment(value = "关注的用户id")
	public void setUserId(String[] userId) {
		this.userId = userId;
	}

	public String getDisplayName() {
		return displayName;
	}

	@AComment(value = "显示用名字")
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public boolean isNeedCheck() {
		return needCheck;
	}

	@AComment(value = "是否需要定时检查")
	public void setNeedCheck(boolean needCheck) {
		this.needCheck = needCheck;
	}
	
}
