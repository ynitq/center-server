package com.cfido.center.server.logicObj;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.cfido.center.server.entity.MsgLog;
import com.cfido.center.server.entity.Project;
import com.cfido.center.server.service.IAsyncObj;
import com.cfido.center.server.service.MonitorServerContext;
import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.beans.monitor.ClientInfoResponse;
import com.cfido.commons.spring.monitor.MonitorUrls;
import com.cfido.commons.spring.sms.SmsCodeService;
import com.cfido.commons.utils.logicObj.BasePoObj;
import com.cfido.commons.utils.utils.DateUtil;
import com.cfido.commons.utils.utils.HttpUtil;
import com.cfido.commons.utils.utils.LogUtil;

/**
 * <pre>
 * Project表的逻辑对象
 * </pre>
 * 
 * @author 梁韦江 生成于: 2016-12-19 17:42:35
 */
@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProjectObj extends BasePoObj<Project> implements IAsyncObj, Comparable<ProjectObj> {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProjectObj.class);

	@Autowired
	private ProjectFactory projectFactory;

	@Autowired
	private MonitorServerContext serverContext;

	@Autowired
	private MsgLogFactory msgLogFactory;

	@Autowired
	private HistoryFactory historyFactory;

	@Autowired
	private UserFactory userFactory;

	@Autowired
	private SmsCodeService sendSmsCodeService;

	/** 是否真正检查中 */
	private boolean checking = false;

	/** 当前时间点的历史统计数据对象 */
	private HistoryObj curHistroyObj;

	private Set<Integer> idSet;

	private final AtomicBoolean inQueueFlag = new AtomicBoolean(false);

	public ProjectViewModel createModel() {
		return new ProjectViewModel(this);
	}

	public void init() throws BaseApiException {
		// 初始化一下 当前历史数据记录
		this.getCurTimeHistoryObj();
	}

	/**
	 * 增加系统消息
	 * 
	 * @throws BaseApiException
	 */
	public void addMsgLog(int msgType, String msg) throws BaseApiException {

		if (this.po.isDown()) {
			// 如果原来是宕机状态，切换为在线状态
			this.changeStatusToDown(false);
		}

		if (StringUtils.hasText(msg)) {
			// 如果有消息，就需要记录
			MsgLog po = this.msgLogFactory.createDefaultPo();
			po.setMsg(msg);
			po.setType(msgType);
			po.setProjectId(this.po.getId());

			// 增加到数据库
			this.msgLogFactory.create(po);

			// 标志以下有新消息了
			this.markHasNewMessage(true);
		}
	}

	/**
	 * 标志是否有新消息
	 * 
	 * @param hasNewMessage
	 */
	public void markHasNewMessage(boolean hasNewMessage) {
		if (po.isHasNewMessage() != hasNewMessage) {
			this.po.setHasNewMessage(hasNewMessage);
			this.asyncSave();
		}
	}

	/**
	 * 改变状态，如果和原来状态不一样，就会发通知
	 * 
	 * @param isDown
	 * @throws BaseApiException
	 */
	private void changeStatusToDown(boolean isDown) throws BaseApiException {
		// 更新状态前，先记录就状态
		boolean oldStatus = this.po.isDown();
		this.po.setDown(isDown);

		// 如果前后状态不一样，就需要发送通知了
		if (oldStatus != isDown) {
			this.asyncSendStatusChangeMsg();
		}

		this.asyncSave();
	}

	/**
	 * 状态发生变化的时候,发送通知
	 * 
	 * @throws BaseApiException
	 */
	private void asyncSendStatusChangeMsg() throws BaseApiException {
		if (!this.po.isSendWarn()) {
			// 如果不需要发送报警，就直接返回
			return;
		}

		// 寻找需要通知的手机号，并且排重
		Set<String> phoneSet = new HashSet<>();
		Map<Integer, UserObj> userMap = this.userFactory.getAllUserMap();
		for (Integer id : this.getUserIdSet()) {
			UserObj user = userMap.get(id);
			if (user != null) {
				// 如果能找到关注的用户
				String phone = user.getPo().getPhone();
				if (StringUtils.hasText(phone) && phone.length() == 11) {
					// 如果该用户的手机号合法
					phoneSet.add(phone);
				}
			}
		}

		// 发送通知短信
		String boby = LogUtil.format("林资监控系统 - 系统:[%s] %s - %s",
				this.po.getDisplayName(),
				this.po.isDown() ? "宕机了" : "恢复正常了 ",
				DateUtil.dateFormat(new Date()));
		for (String phone : phoneSet) {
			this.sendSmsCodeService.sendSystemSms(phone, boby);

			log.debug("发送短信到:{}, 内容:{}", phone, boby);

		}
	}

	public Boolean isNeedCheck() {
		return this.po.isNeedCheck();
	}

	/** 实际执行检查 */
	public void check() {
		if (this.checking) {
			// 如果真正检查中就返回
			return;
		}
		this.checking = true;

		if (this.isNeedCheck()) {
			// 如果需要检查

			String url = String.format("http://%s:%d%s%s",
					po.getHost(), po.getPort(), po.getContextPath(),
					MonitorUrls.CLIENT_CALLBACK);

			try {
				ClientInfoResponse res = HttpUtil.requestJson(ClientInfoResponse.class, url, null, true, null);
				if (res.getCreateTime() > 0) {
					this.changeStatusToDown(false);

					this.onCheckSuccess(res);

					this.addHistory(res);

					log.debug("检查项目 {}: 在线", this.po.getDisplayName());
				} else {
					log.warn("检查项目时获取的内容有问题，无法获得创建时间 {} ", JSON.toJSONString(res));
				}
			} catch (Exception e) {
				// 如果发生了错误，就视为已经宕机
				try {
					this.changeStatusToDown(true);

					// 增加宕机信息到历史中
					this.addHistory(null);
				} catch (BaseApiException e1) {
					// 这个错误就忽略了
				}
				log.debug("检查项目 {}: 宕机", this.po.getDisplayName());
			} finally {

				// 记录最后检查的时间
				this.po.setLastCheckTime(new Date());

				// 异步保存
				this.asyncSave();
			}
		}

		this.checking = false;
	}

	/**
	 * 当检查成功的时候
	 * 
	 * @throws BaseApiException
	 */
	private void onCheckSuccess(ClientInfoResponse res) throws BaseApiException {

		// 将客户端传过来的数据，已json的方式保存下来
		this.po.setClientInfoJson(JSON.toJSONString(res));
		this.po.setLastCheckTime(new Date());
	}

	/**
	 * 异步保存
	 */
	public void asyncSave() {
		log.debug("准备异步保存 Project到数据库  id={}, {}", this.po.getId(), this.po.getDisplayName());
		this.serverContext.asyncSave(this);
	}

	private void addHistory(ClientInfoResponse res) throws BaseApiException {
		HistoryObj obj = this.getCurTimeHistoryObj();

		// 更新数据
		obj.updateInfo(res);

		// 保存数据
		this.historyFactory.update(obj, true);
	}

	/**
	 * 获取当前时间点的历史数据
	 * 
	 * @return
	 * @throws BaseApiException
	 */
	private HistoryObj getCurTimeHistoryObj() throws BaseApiException {
		if (this.curHistroyObj == null) {
			// 如果是空，则从数据库中查询
			this.curHistroyObj = this.historyFactory.getCurTimeObj(this);
		} else {
			// 如果不为空，则判断时间点是否符合
			if (!this.historyFactory.isCurTimeObj(curHistroyObj)) {
				// 如果不符合，就从数据库中获取数据
				this.curHistroyObj = this.historyFactory.getCurTimeObj(this);
			}
		}
		return this.curHistroyObj;
	}

	@Override
	public AtomicBoolean getInQueueFlag() {
		return this.inQueueFlag;
	}

	/** 执行实际的保存操作 */
	@Override
	public void doSave() throws BaseApiException {
		log.debug("执行异步保存 Project到数据库  id={}, {}", this.po.getId(), this.po.getDisplayName());
		this.projectFactory.update(this, true);
	}

	public Set<Integer> getUserIdSet() {
		if (this.idSet == null) {
			this.idSet = new HashSet<>();

			if (StringUtils.hasText(this.po.getUserIds())) {

				// 解析userIds字符串，这个字符串是用逗号分隔的id
				String[] ary = this.po.getUserIds().split(",");
				for (String idStr : ary) {
					try {
						Integer id = Integer.parseInt(idStr);
						this.idSet.add(id);
					} catch (NumberFormatException ex) {

					}

				}
			}
		}
		return this.idSet;
	}

	public boolean isSubscribed(Integer userId) {
		if (userId == null) {
			return false;
		}

		return this.getUserIdSet().contains(userId);
	}

	/**
	 * 某用户订阅或者取消订阅该项目
	 * 
	 * @param user
	 */
	public void changeSubscribe(UserObj user) {
		Assert.notNull(user, "用户不能为空");

		Integer userId = user.getPo().getId();

		if (this.getUserIdSet().contains(userId)) {
			// 如果该用户已经订阅了该项目，则取消订阅
			this.getUserIdSet().remove(userId);
			// 否则就订阅该项目
		} else {
			this.getUserIdSet().add(userId);
		}

		// 异步保存
		this.asyncSave();
	}

	/**
	 * 在保存前，检查一下
	 */
	@Override
	protected void checkBeforeUpdate() throws BaseApiException {
		if (this.idSet != null) {
			// 如果在这个过程中变更过用户关系，则将新的用户关系保存下来
			StringBuffer sb = new StringBuffer();

			for (Integer id : this.idSet) {
				if (sb.length() > 0) {
					sb.append(",");
				}
				sb.append(id);
			}

			// 将关注用户的id用逗号分隔，放到userIds这个字段中
			this.po.setUserIds(sb.toString());
		}
	}

	@Override
	public int compareTo(ProjectObj o) {
		// 排序, 优先级：是否需要检查，显示名字
		int res = o.isNeedCheck().compareTo(isNeedCheck());
		if (res == 0) {
			res = this.po.getDisplayName().compareTo(o.po.getDisplayName());
		}
		return res;
	}

}
