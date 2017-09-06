package com.cfido.center.server.generated;

import com.cfido.center.server.logicObj.UserViewModel;
import com.cfido.commons.beans.apiServer.BaseResponse;

/**
 * <pre>
 * 查看User的Response
 * 我们使用了带_CodeGen字样的不规范的类名，表面这个类是由代码生成器生成的，不要自己修改
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
public abstract class UserViewResponse_CodeGen extends BaseResponse {

	private UserViewModel info;

	public UserViewModel getInfo() {
		return info;
	}

	public void setInfo(UserViewModel info) {
		this.info = info;
	}

}
