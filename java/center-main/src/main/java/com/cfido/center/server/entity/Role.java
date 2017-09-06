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
import javax.validation.constraints.NotNull;

/**
 * 表 role 角色
 * <pre>
 * 生成时间 : 2017-09-06 20:33:16  
 * </pre> 
 */
@Entity
@Table(name = "role")
@NamedQuery(name = "Role.findAll", query = "SELECT a FROM Role a")
public class Role implements Serializable  {
	private static final long serialVersionUID = 1L;

	//--------------- 主键 ---------------
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private Integer id;

	//--------------- 属性 ---------------
	/** 角色名字 */
	@AComment("角色名字")
	@NotNull(message = "name不能为空")
	@Column(name = "name" , length = 50)
	private String name;

	/** 用逗号分隔的权限id */
	@AComment("用逗号分隔的权限id")
	@Column(name = "rights" , nullable = true , length = 1000)
	private String rights;

	
	//--------------- getter和 setter ---------------
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return this.id;
	}
	
	/** 角色名字 */
	public void setName(String name) {
		this.name = name;
	}
	/** 角色名字 */
	@Column(name = "name" , length = 50)
	public String getName() {
		return this.name;
	}
	
	/** 用逗号分隔的权限id */
	public void setRights(String rights) {
		this.rights = rights;
	}
	/** 用逗号分隔的权限id */
	@Column(name = "rights" , nullable = true , length = 1000)
	public String getRights() {
		return this.rights;
	}
	
}
