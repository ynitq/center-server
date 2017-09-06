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
import com.cfido.commons.loginCheck.ANeedCheckLogin;
import com.cfido.commons.utils.utils.ConverterUtil;

/**
 * <pre>
 * 开发时用的测试接口
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
@Controller
public class IndexController extends BaseAuthedController {

	@Autowired
	private UserFactory userFactory;

	@RequestMapping(path = {
			"/", "/admin/*"
	})
	public String index(Model model) throws InvalidLoginStatusException {
		String account = "";
		String name = "";
		boolean logined = false;

		WebUser user = this.getCurUser();
		if (user != null) {
			account = user.getUsername();
			name = user.getName();
			logined = true;
		}

		model.addAttribute("account", account);
		model.addAttribute("name", name);
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

	@ANeedCheckLogin(userClass = WebUser.class)
	@RequestMapping("/users")
	public String users(Model model) throws InvalidLoginStatusException {
		return "users";
	}
}
