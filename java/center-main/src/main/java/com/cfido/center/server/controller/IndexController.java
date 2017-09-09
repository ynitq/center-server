/** 生成于 2016-12-19 17:42:34 */
package com.cfido.center.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cfido.center.server.logicObj.UserFactory;
import com.cfido.center.server.logicObj.UserViewModel;
import com.cfido.center.server.security.WebUser;
import com.cfido.commons.beans.apiExceptions.InvalidLoginStatusException;
import com.cfido.commons.spring.security.LoginContext;
import com.cfido.commons.utils.utils.ConverterUtil;

/**
 * <pre>
 * 开发时用的测试接口
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
@Controller
public class IndexController {

	@Autowired
	private LoginContext loginContext;

	@Autowired
	private UserFactory userFactory;

	@RequestMapping(path = {
			"/", "/admin/*"
	})
	public String index(Model model) throws InvalidLoginStatusException {
		boolean logined = false;

		WebUser user = this.loginContext.getUser(WebUser.class);
		if (user != null) {
			model.addAttribute("user", user);
			logined = true;
		}
		model.addAttribute("logined", logined);

		return "index";
	}

	@RequestMapping("/projects")
	public String projects(Model model) throws InvalidLoginStatusException {

		List<UserViewModel> list = ConverterUtil.convertList(this.userFactory.findAll(),
				this.userFactory.getObj2ViewModelConverter());

		model.addAttribute("userList", list);

		return "projects";
	}
}
