package com.cfido.center.server.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.cfido.commons.annotation.bean.AComment;

/**
 * 表 project 记录每个项目
 * <pre>
 * 生成时间 : 2016-12-23 11:16:24  
 * </pre> 
 */
@Entity
@Table(name = "project")
@NamedQuery(name = "Project.findAll", query = "SELECT a FROM Project a")
public class Project implements Serializable  {
	private static final long serialVersionUID = 1L;

	//--------------- 主键 ---------------
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private Integer id;

	//--------------- 属性 ---------------
	/** 启动的类名 */
	@AComment(value="启动的类名")
	@NotNull
	@Column(name = "start_class_name" , length = 100)
	private String startClassName;

	/** 服务器监听的端口 */
	@AComment(value="服务器监听的端口")
	@Column(name = "port" )
	private int port;

	/** 服务器IP */
	@AComment(value="服务器IP")
	@NotNull
	@Column(name = "host" , length = 50)
	private String host;

	/** contextPath */
	@AComment(value="contextPath")
	@Column(name = "context_path" , nullable = true , length = 50)
	private String contextPath;

	/** 显示的名字 */
	@AComment(value="显示的名字")
	@NotNull
	@Column(name = "display_name" , length = 100)
	private String displayName;

	/** 备注 */
	@AComment(value="备注")
	@Column(name = "memo" , nullable = true , length = 255)
	private String memo;

	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time" )
	private Date createTime;

	/** 是否宕机了 */
	@AComment(value="是否宕机了")
	@Column(name = "down" )
	private boolean down;

	/** 是否发送报警信息 */
	@AComment(value="是否发送报警信息")
	@Column(name = "send_warn" )
	private boolean sendWarn;

	/** 是否收到了新信息 */
	@AComment(value="是否收到了新信息")
	@Column(name = "has_new_message" )
	private boolean hasNewMessage;

	/** 是否需要检查 */
	@AComment(value="是否需要检查")
	@Column(name = "need_check" )
	private boolean needCheck;

	/** 关注的用户 */
	@AComment(value="关注的用户")
	@Column(name = "user_ids" , nullable = true , length = 255)
	private String userIds;

	/** 最后检查的时间 */
	@AComment(value="最后检查的时间")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_check_time" , nullable = true )
	private Date lastCheckTime;

	/** 服务器硬件信息的json */
	@AComment(value="服务器硬件信息的json")
	@Column(name = "client_info_json" , nullable = true , length = 1024)
	private String clientInfoJson;

	
	//--------------- getter和 setter ---------------
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return this.id;
	}
	
	/** 启动的类名 */
	public void setStartClassName(String startClassName) {
		this.startClassName = startClassName;
	}
	/** 启动的类名 */
	@Column(name = "start_class_name" , length = 100)
	public String getStartClassName() {
		return this.startClassName;
	}
	
	/** 服务器监听的端口 */
	public void setPort(int port) {
		this.port = port;
	}
	/** 服务器监听的端口 */
	@Column(name = "port" )
	public int getPort() {
		return this.port;
	}
	
	/** 服务器IP */
	public void setHost(String host) {
		this.host = host;
	}
	/** 服务器IP */
	@Column(name = "host" , length = 50)
	public String getHost() {
		return this.host;
	}
	
	/** contextPath */
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	/** contextPath */
	@Column(name = "context_path" , nullable = true , length = 50)
	public String getContextPath() {
		return this.contextPath;
	}
	
	/** 显示的名字 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/** 显示的名字 */
	@Column(name = "display_name" , length = 100)
	public String getDisplayName() {
		return this.displayName;
	}
	
	/** 备注 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/** 备注 */
	@Column(name = "memo" , nullable = true , length = 255)
	public String getMemo() {
		return this.memo;
	}
	
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "create_time" )
	public Date getCreateTime() {
		return this.createTime;
	}
	
	/** 是否宕机了 */
	public void setDown(boolean down) {
		this.down = down;
	}
	/** 是否宕机了 */
	@Column(name = "down" )
	public boolean isDown() {
		return this.down;
	}
	
	/** 是否发送报警信息 */
	public void setSendWarn(boolean sendWarn) {
		this.sendWarn = sendWarn;
	}
	/** 是否发送报警信息 */
	@Column(name = "send_warn" )
	public boolean isSendWarn() {
		return this.sendWarn;
	}
	
	/** 是否收到了新信息 */
	public void setHasNewMessage(boolean hasNewMessage) {
		this.hasNewMessage = hasNewMessage;
	}
	/** 是否收到了新信息 */
	@Column(name = "has_new_message" )
	public boolean isHasNewMessage() {
		return this.hasNewMessage;
	}
	
	/** 是否需要检查 */
	public void setNeedCheck(boolean needCheck) {
		this.needCheck = needCheck;
	}
	/** 是否需要检查 */
	@Column(name = "need_check" )
	public boolean isNeedCheck() {
		return this.needCheck;
	}
	
	/** 关注的用户 */
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	/** 关注的用户 */
	@Column(name = "user_ids" , nullable = true , length = 255)
	public String getUserIds() {
		return this.userIds;
	}
	
	/** 最后检查的时间 */
	public void setLastCheckTime(Date lastCheckTime) {
		this.lastCheckTime = lastCheckTime;
	}
	/** 最后检查的时间 */
	@Column(name = "last_check_time" , nullable = true )
	public Date getLastCheckTime() {
		return this.lastCheckTime;
	}
	
	/** 服务器硬件信息的json */
	public void setClientInfoJson(String clientInfoJson) {
		this.clientInfoJson = clientInfoJson;
	}
	/** 服务器硬件信息的json */
	@Column(name = "client_info_json" , nullable = true , length = 1024)
	public String getClientInfoJson() {
		return this.clientInfoJson;
	}
	
}
