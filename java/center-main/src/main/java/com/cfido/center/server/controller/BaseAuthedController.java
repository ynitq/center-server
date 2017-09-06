/** 生成于 2016-12-19 17:42:34 */
package com.cfido.center.server.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.cfido.center.server.security.WebUser;
import com.cfido.commons.beans.apiExceptions.InvalidLoginStatusException;
import com.cfido.commons.utils.html.MenuVo;

/**
 * <pre>
 * 需要用户登录的 Controller的基类
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
abstract class BaseAuthedController extends BaseController {

	private MenuVo menuVo;

	@ModelAttribute
	protected void addHeader(Model model) throws InvalidLoginStatusException {
		WebUser user = this.getCurUser();
		if (user == null) {
			throw new InvalidLoginStatusException();
		}

		model.addAttribute("user", this.getCurUser());
		model.addAttribute("menuVo", this.getMenu());
	}

	private MenuVo getMenu() throws InvalidLoginStatusException {
		if (this.menuVo == null) {
			this.menuVo = new MenuVo();
			this.menuVo.addMenu("", "使用帮助", "/");
			this.menuVo.addMenu("", "项目查看", "/projects");
			this.menuVo.addMenu("", "用户管理", "/users");
			this.menuVo.addMenu("", "页面字典", "/dict/manager", "", true);
			this.menuVo.addMenu("", "JMX", "/jmxInWeb/", "", true);
		}

		// 生成该用户有权限的菜单对象
		WebUser user = this.getCurUser();
		MenuVo menu = this.menuVo.createForUser(user);

		// 设置菜单中的当前菜单
		menu.setCurMenuByUrl(request);

		return menu;
	}
}
