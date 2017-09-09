/*
 * 文件名：[文件名]
 * 版权：〈版权〉
 * 描述：〈描述〉
 * 修改人：〈修改人〉
 * 修改时间：YYYY-MM-DD
 * 修改单号：〈修改单号〉
 * 修改内容：〈修改内容〉
 */
package com.cfido.center.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cfido.center.server.service.MonitorServerContext;
import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.beans.apiServer.impl.CommonSuccessResponse;
import com.cfido.commons.beans.exceptions.security.PermissionDeniedException;
import com.cfido.commons.beans.monitor.ClientGetUserForm;
import com.cfido.commons.beans.monitor.ClientMsgForm;
import com.cfido.commons.beans.monitor.UserInfoInCenterBean;
import com.cfido.commons.spring.monitor.MonitorUrls;

/**
 * <pre>
 * 监控服务器 接受各系统发送的报告
 * </pre>
 * 
 */
@Controller
public class MonitorServerController {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(MonitorServerController.class);

	@Autowired
	private MonitorServerContext monitorServerContext;

	@Autowired
	private ServerProperties serverProperties;

	@Autowired
	private HttpServletRequest request;

	@ResponseBody
	@RequestMapping(value = MonitorUrls.SERVER_USER, method = RequestMethod.POST)
	public UserInfoInCenterBean getUserInfo(ClientGetUserForm form) {
		return this.monitorServerContext.getUserInfo(form);
	}

	@ResponseBody
	@RequestMapping(value = MonitorUrls.SERVER_REPORT, method = RequestMethod.POST)
	public CommonSuccessResponse report(ClientMsgForm form) throws BaseApiException {

		// 安全检查，不允许使用80端口访问
		int port = this.request.getServerPort();
		if (port != this.geMyPort()) {
			throw new PermissionDeniedException();
		}

		log.debug("接收被监控应用信息：{}", form.getIdStr());

		this.monitorServerContext.addClientMsg(form);

		return CommonSuccessResponse.DEFAULT;
	}

	private int geMyPort() {
		Integer port = this.serverProperties.getPort();
		if (port == null) {
			return 8080;
		} else {
			return port;
		}
	}
}
