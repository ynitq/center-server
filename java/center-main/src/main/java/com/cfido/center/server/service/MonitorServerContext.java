package com.cfido.center.server.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.cfido.center.server.config.MonitorMBeanDomainNaming;
import com.cfido.center.server.config.MonitorServerProperties;
import com.cfido.center.server.logicObj.ProjectFactory;
import com.cfido.center.server.logicObj.ProjectObj;
import com.cfido.center.server.logicObj.UserFactory;
import com.cfido.center.server.logicObj.UserObj;
import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.beans.exceptions.security.UserNotFoundException;
import com.cfido.commons.beans.monitor.ClientGetUserForm;
import com.cfido.commons.beans.monitor.ClientIdBean;
import com.cfido.commons.beans.monitor.ClientInfoResponse;
import com.cfido.commons.beans.monitor.ClientMsgForm;
import com.cfido.commons.beans.monitor.UserInfoInCenterBean;
import com.cfido.commons.spring.jmxInWeb.ADomainOrder;
import com.cfido.commons.utils.threadPool.IMyTask;
import com.cfido.commons.utils.utils.DateUtil;
import com.cfido.commons.utils.utils.LogUtil;

/**
 * <pre>
 * 这个项目的数据全部都是在内存中
 * </pre>
 * 
 * @author 梁韦江 2016年12月19日
 */
@Service
@ManagedResource(description = "监控服务器Context")
@ADomainOrder(order = MonitorMBeanDomainNaming.ORDER, domainName = MonitorMBeanDomainNaming.DOMAIN)
public class MonitorServerContext {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MonitorServerContext.class);

	@Autowired
	private ProjectFactory projectFactory;

	@Autowired
	private MonitorServerProperties prop;

	@Autowired
	private MonitorThreadPool threadPool;

	@Autowired
	private UserFactory userFactory;

	private long lastSaveTime = 0;
	private long saveCount = 0;

	/** 等待保存到数据库的对象 */
	private final List<IAsyncObj> waitingQueue = new LinkedList<>();

	public void addClientMsg(ClientMsgForm form) throws BaseApiException {

		Assert.hasText(form.getIdStr(), "id不能为空");

		ClientIdBean idBean = JSON.parseObject(form.getIdStr(), ClientIdBean.class);

		Assert.hasText(idBean.getHost(), "host不能为空");
		Assert.hasText(idBean.getStartClassName(), "自动累不能为空");
		Assert.isTrue(idBean.getPort() > 0, "端口应该大于0");

		ProjectObj obj = this.projectFactory.getObjByIdBean(idBean);

		if (StringUtils.hasLength(form.getClientInfo())) {
			// 如果客户端有传硬件内容过滤
			ClientInfoResponse clientInfo = JSON.parseObject(form.getClientInfo(), ClientInfoResponse.class);
			if (clientInfo.getCreateTime() > 0) {
				// 并且能解析成功，就保存下来
				obj.getPo().setClientInfoJson(form.getClientInfo());
				obj.asyncSave();
			}
		}

		// 增加系统消息
		this.asyncAddMsgLog(obj, form.getMsgType(), form.getMsg());
	}

	public UserInfoInCenterBean getUserInfo(ClientGetUserForm form) {
		if (StringUtils.isEmpty(form.getAccount())) {
			return null;
		}

		try {
			UserInfoInCenterBean bean = new UserInfoInCenterBean();
			UserObj user = this.userFactory.getByAccount(form.getAccount());

			// 基础信息
			bean.setAccount(user.getPo().getAccount());
			bean.setEncodedPassword(user.getPo().getPassword());
			bean.setName(user.getPo().getName());
			bean.setSuperuser(user.getPo().isSuperUser());

			// 额外信息
			bean.addExInfo("phone", user.getPo().getPhone());
			bean.addExInfo("email", user.getPo().getEmail());

			try {
				if (StringUtils.hasText(form.getIdStr())) {
					ClientIdBean idBean = JSON.parseObject(form.getIdStr(), ClientIdBean.class);

					ProjectObj obj = this.projectFactory.getObjByIdBean(idBean);
					String rightsId = obj.getPo().getRightsId();

					// TODO 中心服务器需要返回权限
					log.debug("获取中心用户 {}, 用户存在，需要的权限id为:{}", form.getAccount(), rightsId);

				}
			} catch (BaseApiException e) {
			}

			return bean;

		} catch (UserNotFoundException e) {
			log.debug("获取中心用户 {}, 但用户不存在", form.getAccount());
		}

		return null;
	}

	/**
	 * 异步添加消息
	 * 
	 * @param obj
	 * @param msgType
	 * @param msg
	 */
	private void asyncAddMsgLog(final ProjectObj obj, final int msgType, final String msg) {
		this.threadPool.addNewTask(new IMyTask() {

			@Override
			public void run() {
				try {
					obj.addMsgLog(msgType, msg);
				} catch (BaseApiException e) {
					LogUtil.traceError(log, e, "增加报警消息时，出错了");
				}
			}

			@Override
			public String getUniqueId() {
				return null;
			}
		});
	}

	/**
	 * 每分钟检查一次
	 */
	@Scheduled(fixedRate = 60 * 1000)
	protected void loopForCheck() {

		if (this.prop.isCheck()) {

			// 循环所有的项目
			List<ProjectObj> list = this.projectFactory.findAll();

			log.debug("开始检查所有项目,共 {} 个项目需要检查", list.size());

			for (ProjectObj obj : list) {
				this.asyncCheck(obj);
			}
		}
	}

	/**
	 * 异步检查每个项目
	 */
	private void asyncCheck(final ProjectObj obj) {
		this.threadPool.addNewTask(new IMyTask() {

			@Override
			public void run() {
				obj.check();
			}

			@Override
			public String getUniqueId() {
				return "CheckProject:" + obj.getPo().getId();
			}
		});
	}

	/**
	 * 每10秒检查一次异步保存
	 */
	@Scheduled(fixedRate = 10 * 1000)
	protected void loopForSave() {
		this.doSaveAll();
	}

	@PreDestroy
	protected void onShutdown() {
		// shutdown的时候，将队列中的所有数据保存一下
		this.doSaveAll();
	}

	/**
	 * 将队列中待保存的对象全部保存下来
	 */
	private void doSaveAll() {
		List<IAsyncObj> list = new LinkedList<>();

		synchronized (waitingQueue) {
			// 还是要加同步锁，在实际运行过程中，这里曾经出现过两个线程同时操作waitingQueue
			list.addAll(this.waitingQueue);
			this.waitingQueue.clear();
		}

		if (list.isEmpty()) {
			return;
		} else {
			log.debug("异步保存 {} 条记录到数据库", list.size());
		}

		for (IAsyncObj obj : list) {
			try {
				obj.doSave();

			} catch (BaseApiException e) {
				// 保存时，如果出了逻辑错误，也管不了了
				LogUtil.traceError(log, e);
			} finally {
				// 最后将状态设置为不在队列中
				obj.getInQueueFlag().set(false);
			}

		}

		this.lastSaveTime = System.currentTimeMillis();
		this.saveCount++;
	}

	/**
	 * 异步保存对象
	 * 
	 * @param obj
	 *            要保存的对象
	 */
	public void asyncSave(IAsyncObj obj) {
		AtomicBoolean inQueueFlag = obj.getInQueueFlag();
		if (inQueueFlag.get()) {
			// 如果已经在保存队列中了，就直接返回
			return;
		} else {
			// 否则就加入队列，同时修改状态为：保存中
			inQueueFlag.set(true);
			synchronized (waitingQueue) {
				this.waitingQueue.add(obj);
			}
		}

	}

	@ManagedOperation(description = "测试检查所有项目")
	public void jmxTestLoopCheck() {
		this.loopForCheck();
	}

	@ManagedOperation(description = "测试保存所有在缓存中的数据")
	public void jmxTestSaveAll() {
		this.doSaveAll();
	}

	@ManagedAttribute(description = "是否定时检查所有项目")
	public boolean isCheck() {
		return this.prop.isCheck();
	}

	@ManagedAttribute
	public void setCheck(boolean check) {
		this.prop.setCheck(check);
	}

	@ManagedAttribute(description = "上一次异步保存的时间")
	public String getLastSaveTime() {
		if (this.lastSaveTime > 0) {
			return DateUtil.dateFormat(new Date(this.lastSaveTime));
		} else {
			return "没有保存过";
		}
	}

	@ManagedAttribute(description = "异步保存的次数")
	public long getSaveCount() {
		return this.saveCount;
	}
}
