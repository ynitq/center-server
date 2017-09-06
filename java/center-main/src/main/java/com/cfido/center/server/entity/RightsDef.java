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
 * 表 rights_def 权限定义
 * <pre>
 * 生成时间 : 2017-09-06 20:33:16  
 * </pre> 
 */
@Entity
@Table(name = "rights_def")
@NamedQuery(name = "RightsDef.findAll", query = "SELECT a FROM RightsDef a")
public class RightsDef implements Serializable  {
	private static final long serialVersionUID = 1L;

	//--------------- 主键 ---------------
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private Integer id;

	//--------------- 属性 ---------------
	/** 分类名 */
	@AComment("分类名")
	@NotNull(message = "groupName不能为空")
	@Column(name = "group_name" , length = 50)
	private String groupName;

	/** 权限显示名 */
	@AComment("权限显示名")
	@NotNull(message = "rightsName不能为空")
	@Column(name = "rights_name" , length = 50)
	private String rightsName;

	/** 权限id */
	@AComment("权限id")
	@NotNull(message = "rightsId不能为空")
	@Column(name = "rights_id" , length = 50)
	private String rightsId;

	
	//--------------- getter和 setter ---------------
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return this.id;
	}
	
	/** 分类名 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/** 分类名 */
	@Column(name = "group_name" , length = 50)
	public String getGroupName() {
		return this.groupName;
	}
	
	/** 权限显示名 */
	public void setRightsName(String rightsName) {
		this.rightsName = rightsName;
	}
	/** 权限显示名 */
	@Column(name = "rights_name" , length = 50)
	public String getRightsName() {
		return this.rightsName;
	}
	
	/** 权限id */
	public void setRightsId(String rightsId) {
		this.rightsId = rightsId;
	}
	/** 权限id */
	@Column(name = "rights_id" , length = 50)
	public String getRightsId() {
		return this.rightsId;
	}
	
}
