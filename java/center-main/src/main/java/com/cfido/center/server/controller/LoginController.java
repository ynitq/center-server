/** 生成于 2016-12-19 17:42:34 */
package com.cfido.center.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cfido.center.server.security.WebUser;
import com.cfido.commons.spring.security.CommonAdminWebUser;

/**
 * <pre>
 * 开发时用的测试接口
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
@Controller
public class LoginController extends BaseController {

	@RequestMapping("/login")
	public String login() {

		if (this.getCurUser() == null) {
			// 如果没有登陆，就到登录界面
			return "login";
		} else {
			// 如果已经登录，就跳到欢迎页
			return "redirect:/";
		}
	}

	@RequestMapping("/logout")
	public String logout() {
		this.loginContext.onLogout(WebUser.class);
		this.loginContext.onLogout(CommonAdminWebUser.class);
		return "redirect:/login";
	}

}
