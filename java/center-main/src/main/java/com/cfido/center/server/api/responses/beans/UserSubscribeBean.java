package com.cfido.center.server.api.responses.beans;

import com.cfido.center.server.entity.User;

/**
 * <pre>
 * 关注某个项目的用户
 * </pre>
 * 
 * @author 梁韦江 2016年12月22日
 */
public class UserSubscribeBean {

	private User po;

	/** 是否已经订阅 */
	private boolean subscribed;

	public User getPo() {
		return po;
	}

	public void setPo(User po) {
		this.po = po;
	}

	public boolean isSubscribed() {
		return subscribed;
	}

	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}

}
