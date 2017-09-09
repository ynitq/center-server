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
 * 生成时间 : 2017-09-09 15:16:14  
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
	/** 权限id */
	@AComment("权限id")
	@NotNull(message = "rightsId不能为空")
	@Column(name = "rights_id" , length = 50)
	private String rightsId;

	/** 权限明细 */
	@AComment("权限明细")
	@NotNull(message = "jsonText不能为空")
	@Column(name = "json_text" , length = 65535)
	private String jsonText;

	
	//--------------- getter和 setter ---------------
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return this.id;
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
	
	/** 权限明细 */
	public void setJsonText(String jsonText) {
		this.jsonText = jsonText;
	}
	/** 权限明细 */
	@Column(name = "json_text" , length = 65535)
	public String getJsonText() {
		return this.jsonText;
	}
	
}
