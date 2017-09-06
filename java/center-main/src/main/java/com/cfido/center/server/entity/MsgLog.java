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
 * 表 msg_log 
 * <pre>
 * 生成时间 : 2016-12-23 11:16:24  
 * </pre> 
 */
@Entity
@Table(name = "msg_log")
@NamedQuery(name = "MsgLog.findAll", query = "SELECT a FROM MsgLog a")
public class MsgLog implements Serializable  {
	private static final long serialVersionUID = 1L;

	//--------------- 主键 ---------------
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private Integer id;

	//--------------- 属性 ---------------
	/** 相关项目 */
	@AComment(value="相关项目")
	@Column(name = "project_id" )
	private int projectId;

	/** 消息类型 */
	@AComment(value="消息类型")
	@Column(name = "type" )
	private int type;

	/** 消息 */
	@AComment(value="消息")
	@NotNull
	@Column(name = "msg" , length = 65535)
	private String msg;

	/** 创建时间 */
	@AComment(value="创建时间")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time" )
	private Date createTime;

	
	//--------------- getter和 setter ---------------
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return this.id;
	}
	
	/** 相关项目 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	/** 相关项目 */
	@Column(name = "project_id" )
	public int getProjectId() {
		return this.projectId;
	}
	
	/** 消息类型 */
	public void setType(int type) {
		this.type = type;
	}
	/** 消息类型 */
	@Column(name = "type" )
	public int getType() {
		return this.type;
	}
	
	/** 消息 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/** 消息 */
	@Column(name = "msg" , length = 65535)
	public String getMsg() {
		return this.msg;
	}
	
	/** 创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/** 创建时间 */
	@Column(name = "create_time" )
	public Date getCreateTime() {
		return this.createTime;
	}
	
}
