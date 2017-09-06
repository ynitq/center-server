package com.cfido.center.server.entity;

import com.cfido.commons.annotation.bean.AComment;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 表 project_role 定义这个项目可以由那些角色管理
 * <pre>
 * 生成时间 : 2017-09-06 20:33:16  
 * </pre> 
 */
@Entity
@Table(name = "project_role")
@NamedQuery(name = "ProjectRole.findAll", query = "SELECT a FROM ProjectRole a")
public class ProjectRole implements Serializable  {
	private static final long serialVersionUID = 1L;

	//--------------- 主键 ---------------
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private Integer id;

	//--------------- 属性 ---------------
	/** 项目id */
	@AComment("项目id")
	@Column(name = "project_id" )
	private int projectId;

	/** 角色id */
	@AComment("角色id")
	@Column(name = "role_id" )
	private int roleId;

	
	//--------------- getter和 setter ---------------
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return this.id;
	}
	
	/** 项目id */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	/** 项目id */
	@Column(name = "project_id" )
	public int getProjectId() {
		return this.projectId;
	}
	
	/** 角色id */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	/** 角色id */
	@Column(name = "role_id" )
	public int getRoleId() {
		return this.roleId;
	}
	
}
