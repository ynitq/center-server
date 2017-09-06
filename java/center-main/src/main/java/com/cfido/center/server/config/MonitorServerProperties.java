package com.cfido.center.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

import com.cfido.commons.utils.utils.PasswordEncoder;

/**
 * <pre>
 * 默认参数
 * 
 * monitorServer.admin.account=admin
 * monitorServer.admin.password=默认密码linzi777
 * 
 * monitorServer.check = true
 * 
 * </pre>
 * 
 * @see PasswordEncoder#encodePassword(密码明文) 密码通过PasswordEncoder工具生成
 * 
 * @author 梁韦江 2016年8月26日
 */
@ConfigurationProperties(prefix = "monitorServer")
public class MonitorServerProperties {

	/**
	 * <pre>
	 * 默认的管理用户账号
	 * </pre>
	 * 
	 * @author 梁韦江 2016年11月16日
	 */
	public static class Admin {
		private String account = "admin";

		/** 默认密码是 linzi777 **/
		private String password = "d70623d9b2ea2d300662bf27c75b45bd";

		public String getAccount() {
			return account;
		}

		public String getPassword() {
			return password;
		}

		public boolean isValid() {
			return StringUtils.hasText(account) && StringUtils.hasText(password);
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public void setPassword(String password) {
			this.password = password;
		}

	}

	/** 保存管理用户的账号和密码 */
	private final Admin admin = new Admin();

	/** 是否定时检查所有项目，开发时可以设置为否 */
	private boolean check = true;

	public Admin getAdmin() {
		return admin;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

}
