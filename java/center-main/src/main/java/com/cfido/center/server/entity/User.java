package com.cfido.center.server.entity;

import com.cfido.commons.annotation.bean.AComment;
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

/**
 * 表 user 
 * <pre>
 * 生成时间 : 2017-09-06 20:33:16  
 * </pre> 
 */
@Entity
@Table(name = "user")
@NamedQuery(name = "User.findAll", query = "SELECT a FROM User a")
public class User implements Serializable  {
	private static final long serialVersionUID = 1L;

	//--------------- 主键 ---------------
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private Integer id;

	//--------------- 属性 ---------------
	/** 名字 */
	@AComment("名字")
	@Column(name = "name" , nullable = true , length = 50)
	private String name;

	/** 电话 */
	@AComment("电话")
	@Column(name = "phone" , nullable = true , length = 50)
	private String phone;

	/** 邮箱 */
	@AComment("邮箱")
	@Column(name = "email" , nullable = true , length = 50)
	private String email;

	/** 是否已经封号 */
	@AComment("是否已经封号")
	@Column(name = "disabled" )
	private boolean disabled;

	/** 备注 */
	@AComment("备注")
	@Column(name = "memo" , nullable = true , length = 255)
	private String memo;

	/** 创建时间 */
	@AComment("创建时间")
	@NotNull(message = "createTime不能为空")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time" )
	private Date createTime;

	/** 超级管理员 */
	@AComment("超级管理员")
	@Column(name = "super_user" )
	private boolean superUser;

	/** 账号 */
	@AComment("账号")
	@NotNull(message = "account不能为空")
	@Column(name = "account" , length = 50)
	private String account;

	/** 密码 */
	@AComment("密码")
	@NotNull(message = "password不能为空")
	@Column(name = "password" , length = 100)
	private String password;

	
	//--------------- getter和 setter ---------------
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return this.id;
	}
	
	/** 名字 */
	public void setName(String name) {
		this.name = name;
	}
	/** 名字 */
	@Column(name = "name" , nullable = true , length = 50)
	public String getName() {
		return this.name;
	}
	
	/** 电话 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/** 电话 */
	@Column(name = "phone" , nullable = true , length = 50)
	public String getPhone() {
		return this.phone;
	}
	
	/** 邮箱 */
	public void setEmail(String email) {
		this.email = email;
	}
	/** 邮箱 */
	@Column(name = "email" , nullable = true , length = 50)
	public String getEmail() {
		return this.email;
	}
	
	/** 是否已经封号 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	/** 是否已经封号 */
	@Column(name = "disabled" )
	public boolean isDisabled() {
		return this.disabled;
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
	
	/** 创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/** 创建时间 */
	@Column(name = "create_time" )
	public Date getCreateTime() {
		return this.createTime;
	}
	
	/** 超级管理员 */
	public void setSuperUser(boolean superUser) {
		this.superUser = superUser;
	}
	/** 超级管理员 */
	@Column(name = "super_user" )
	public boolean isSuperUser() {
		return this.superUser;
	}
	
	/** 账号 */
	public void setAccount(String account) {
		this.account = account;
	}
	/** 账号 */
	@Column(name = "account" , length = 50)
	public String getAccount() {
		return this.account;
	}
	
	/** 密码 */
	public void setPassword(String password) {
		this.password = password;
	}
	/** 密码 */
	@Column(name = "password" , length = 100)
	public String getPassword() {
		return this.password;
	}
	
}
