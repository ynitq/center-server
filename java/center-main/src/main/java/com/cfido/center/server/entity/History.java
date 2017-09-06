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
 * 表 history 
 * <pre>
 * 生成时间 : 2017-09-06 20:33:16  
 * </pre> 
 */
@Entity
@Table(name = "history")
@NamedQuery(name = "History.findAll", query = "SELECT a FROM History a")
public class History implements Serializable  {
	private static final long serialVersionUID = 1L;

	//--------------- 主键 ---------------
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private Integer id;

	//--------------- 属性 ---------------
	/** project外键 */
	@AComment("project外键")
	@Column(name = "project_id" )
	private int projectId;

	/** yyyyMMdd 年月日 */
	@AComment("yyyyMMdd 年月日")
	@Column(name = "day_key" )
	private int dayKey;

	/** HHmm 时分 */
	@AComment("HHmm 时分")
	@Column(name = "hour_key" )
	private int hourKey;

	/** 系统负载 */
	@AComment("系统负载")
	@Column(name = "system_load_average" )
	private double systemLoadAverage;

	/** 内存已分配总数 */
	@AComment("内存已分配总数")
	@Column(name = "total_memory" )
	private long totalMemory;

	/** 内存可使用数 */
	@AComment("内存可使用数")
	@Column(name = "used_memory" )
	private long usedMemory;

	/** 请求数量 */
	@AComment("请求数量")
	@Column(name = "request_count" )
	private int requestCount;

	/** 记录时间 */
	@AComment("记录时间")
	@NotNull(message = "recordTime不能为空")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "record_time" )
	private Date recordTime;

	/** 是否宕机 */
	@AComment("是否宕机")
	@Column(name = "down" )
	private boolean down;

	/** 在线人数 */
	@AComment("在线人数")
	@Column(name = "online_num" )
	private int onlineNum;

	
	//--------------- getter和 setter ---------------
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return this.id;
	}
	
	/** project外键 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	/** project外键 */
	@Column(name = "project_id" )
	public int getProjectId() {
		return this.projectId;
	}
	
	/** yyyyMMdd 年月日 */
	public void setDayKey(int dayKey) {
		this.dayKey = dayKey;
	}
	/** yyyyMMdd 年月日 */
	@Column(name = "day_key" )
	public int getDayKey() {
		return this.dayKey;
	}
	
	/** HHmm 时分 */
	public void setHourKey(int hourKey) {
		this.hourKey = hourKey;
	}
	/** HHmm 时分 */
	@Column(name = "hour_key" )
	public int getHourKey() {
		return this.hourKey;
	}
	
	/** 系统负载 */
	public void setSystemLoadAverage(double systemLoadAverage) {
		this.systemLoadAverage = systemLoadAverage;
	}
	/** 系统负载 */
	@Column(name = "system_load_average" )
	public double getSystemLoadAverage() {
		return this.systemLoadAverage;
	}
	
	/** 内存已分配总数 */
	public void setTotalMemory(long totalMemory) {
		this.totalMemory = totalMemory;
	}
	/** 内存已分配总数 */
	@Column(name = "total_memory" )
	public long getTotalMemory() {
		return this.totalMemory;
	}
	
	/** 内存可使用数 */
	public void setUsedMemory(long usedMemory) {
		this.usedMemory = usedMemory;
	}
	/** 内存可使用数 */
	@Column(name = "used_memory" )
	public long getUsedMemory() {
		return this.usedMemory;
	}
	
	/** 请求数量 */
	public void setRequestCount(int requestCount) {
		this.requestCount = requestCount;
	}
	/** 请求数量 */
	@Column(name = "request_count" )
	public int getRequestCount() {
		return this.requestCount;
	}
	
	/** 记录时间 */
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	/** 记录时间 */
	@Column(name = "record_time" )
	public Date getRecordTime() {
		return this.recordTime;
	}
	
	/** 是否宕机 */
	public void setDown(boolean down) {
		this.down = down;
	}
	/** 是否宕机 */
	@Column(name = "down" )
	public boolean isDown() {
		return this.down;
	}
	
	/** 在线人数 */
	public void setOnlineNum(int onlineNum) {
		this.onlineNum = onlineNum;
	}
	/** 在线人数 */
	@Column(name = "online_num" )
	public int getOnlineNum() {
		return this.onlineNum;
	}
	
}
