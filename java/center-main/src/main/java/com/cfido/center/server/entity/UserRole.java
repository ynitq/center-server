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
 * 表 user_role 用户和权限的关系
 * <pre>
 * 生成时间 : 2017-09-06 20:33:16  
 * </pre> 
 */
@Entity
@Table(name = "user_role")
@NamedQuery(name = "UserRole.findAll", query = "SELECT a FROM UserRole a")
public class UserRole implements Serializable  {
	private static final long serialVersionUID = 1L;

	//--------------- 主键 ---------------
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private Integer id;

	//--------------- 属性 ---------------
	/** 用户id */
	@AComment("用户id")
	@Column(name = "user_id" )
	private int userId;

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
	
	/** 用户id */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/** 用户id */
	@Column(name = "user_id" )
	public int getUserId() {
		return this.userId;
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
