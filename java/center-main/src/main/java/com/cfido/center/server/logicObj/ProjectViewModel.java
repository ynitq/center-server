package com.cfido.center.server.logicObj;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.cfido.center.server.api.responses.beans.UserSubscribeBean;
import com.cfido.center.server.entity.Project;
import com.cfido.commons.beans.monitor.ClientInfoResponse;
import com.cfido.commons.utils.logicObj.BaseViewModel;
import com.cfido.commons.utils.utils.DateUtil;

/**
 * <pre>
 * {table.javaClassName}对象的 view model专门用于传递数据给页面。
 * 这个类虽然有po，但这个po是拷贝了一份，所以可以自由的修改内容
 * 
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:35
 */
public class ProjectViewModel extends BaseViewModel<ProjectObj, Project> {

	private final static int M = 1024 * 1024;

	private ClientInfoResponse clientInfo;

	private final List<UserSubscribeBean> userSubscribe = new LinkedList<>();

	public ProjectViewModel(ProjectObj obj) {
		super(obj);
		this.getClientInfo();
	}

	/**
	 * 获得客户端的信息
	 */
	public ClientInfoResponse getClientInfo() {
		if (this.clientInfo == null) {
			String jsonStr = this.po.getClientInfoJson();

			if (StringUtils.hasText(jsonStr)) {
				// 如果有内容就解析出来
				this.clientInfo = JSON.parseObject(jsonStr, ClientInfoResponse.class);
			} else {
				// 如果没有就创建一个对象，避免空指针
				this.clientInfo = new ClientInfoResponse();
			}
		}
		return this.clientInfo;
	}

	/**
	 * 加入的时间
	 */
	public String getCreateTime() {
		return DateUtil.dateFormat(po.getCreateTime());
	}

	/** 项目系统启动时间 */
	public String getStartTime() {
		return this.getTimeStr(this.getClientInfo().getStartTime());
	}

	private String getTimeStr(long time) {
		if (time == 0) {
			return "";
		} else {
			return DateUtil.dateFormat(new Date(time));
		}
	}

	/** 项目主程序build的时间 */
	public String getStartClassBuildTime() {
		return this.getTimeStr(this.getClientInfo().getStartClassBuildTime());
	}

	/**
	 * 最后检查的时间
	 */
	public String getLastCheckTime() {
		return DateUtil.dateFormat(po.getLastCheckTime());
	}

	public long getMaxMemoryM() {
		ClientInfoResponse info = this.getClientInfo();
		if (info.getCreateTime() > 0) {
			// 如果有内容
			return info.getOsInfo().getMaxMemory() / M;
		} else {
			return 0;
		}
	}

	public long getTotalMemoryM() {
		ClientInfoResponse info = this.getClientInfo();
		if (info.getCreateTime() > 0) {
			// 如果有内容
			return info.getOsInfo().getTotalMemory() / M;
		} else {
			return 0;
		}
	}

	/**
	 * 已分配内存的百分比
	 */
	public long getTotalMemoryPrecent() {
		ClientInfoResponse info = this.getClientInfo();
		if (info.getCreateTime() > 0 && info.getOsInfo().getMaxMemory() > 0) {
			// 如果有内容
			return info.getOsInfo().getTotalMemory() * 100 / info.getOsInfo().getMaxMemory();
		} else {
			return 0;
		}
	}

	/**
	 * 总硬盘空间
	 */
	public long getTotalSpaceM() {
		ClientInfoResponse info = this.getClientInfo();
		if (info.getCreateTime() > 0 && info.getOsInfo().getMaxMemory() > 0) {
			// 如果有内容
			return info.getDiskInfo().getTotalSpace() / M;
		} else {
			return 0;
		}
	}

	/**
	 * 可用硬盘空间
	 */
	public long getUsableSpaceM() {
		ClientInfoResponse info = this.getClientInfo();
		if (info.getCreateTime() > 0 && info.getOsInfo().getMaxMemory() > 0) {
			// 如果有内容
			return info.getDiskInfo().getUsableSpace() / M;
		} else {
			return 0;
		}
	}

	/**
	 * 可用空间百分比
	 */
	public long getUsableSpacePrecent() {
		ClientInfoResponse info = this.getClientInfo();
		if (info.getCreateTime() > 0 && info.getDiskInfo().getTotalSpace() > 0) {
			// 如果有内容
			return info.getDiskInfo().getUsableSpace() * 100 / info.getDiskInfo().getTotalSpace();
		} else {
			return 0;
		}
	}

	public long getUsedMemoryM() {
		ClientInfoResponse info = this.getClientInfo();
		if (info.getCreateTime() > 0) {
			// 如果有内容
			return info.getOsInfo().getUsedMemory() / M;
		} else {
			return 0;
		}
	}

	/**
	 * 已使用内存的百分比
	 */
	public long getUsedMemoryPrecent() {
		ClientInfoResponse info = this.getClientInfo();
		if (info.getCreateTime() > 0 && info.getOsInfo().getMaxMemory() > 0) {
			// 如果有内容
			return info.getOsInfo().getUsedMemory() * 100 / info.getOsInfo().getMaxMemory();
		} else {
			return 0;
		}
	}

	public List<UserSubscribeBean> getUserSubscribe() {
		return userSubscribe;
	}

	/**
	 * 传入用户列表，更新用户对该项目的订阅信息
	 * 
	 * @param users
	 */
	public void updateSubscribe(List<UserObj> users) {

		for (UserObj user : users) {
			UserSubscribeBean bean = new UserSubscribeBean();
			bean.setPo(user.getPo());
			bean.setSubscribed(obj.getUserIdSet().contains(user.getPo().getId()));
			this.userSubscribe.add(bean);
		}
	}

}
