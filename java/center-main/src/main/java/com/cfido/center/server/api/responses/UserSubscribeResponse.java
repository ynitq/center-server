package com.cfido.center.server.api.responses;

import java.util.List;

import com.cfido.center.server.api.responses.beans.UserSubscribeBean;
import com.cfido.commons.beans.apiServer.BaseResponse;

/**
 * <pre>
 * 用户订阅的响应，就是重新返回一个列表
 * </pre>
 * 
 * @author 梁韦江 2016年12月22日
 */
public class UserSubscribeResponse extends BaseResponse {

	private List<UserSubscribeBean> userSubscribe;

	public List<UserSubscribeBean> getUserSubscribe() {
		return userSubscribe;
	}

	public void setUserSubscribe(List<UserSubscribeBean> userSubscribe) {
		this.userSubscribe = userSubscribe;
	}

}
