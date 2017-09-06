package com.cfido.center.server.api.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfido.center.server.api.IProjectApi;
import com.cfido.center.server.api.responses.HistoryChartResponse;
import com.cfido.center.server.api.responses.ProjectListResponse;
import com.cfido.center.server.api.responses.ProjectViewResponse;
import com.cfido.center.server.api.responses.UserSubscribeResponse;
import com.cfido.center.server.domains.HistoryDomain;
import com.cfido.center.server.entity.History;
import com.cfido.center.server.entity.Project;
import com.cfido.center.server.form.HistoryListForm;
import com.cfido.center.server.form.ProjectEditForm;
import com.cfido.center.server.form.UserSubscribeForm;
import com.cfido.center.server.logicObj.ProjectFactory;
import com.cfido.center.server.logicObj.ProjectObj;
import com.cfido.center.server.logicObj.ProjectViewModel;
import com.cfido.center.server.logicObj.UserFactory;
import com.cfido.center.server.logicObj.UserObj;
import com.cfido.center.server.security.WebUser;
import com.cfido.commons.annotation.api.AApiServerImpl;
import com.cfido.commons.beans.apiExceptions.SimpleApiException;
import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.beans.apiServer.impl.CommonSuccessResponse;
import com.cfido.commons.beans.form.IdForm;
import com.cfido.commons.loginCheck.ANeedCheckLogin;
import com.cfido.commons.utils.utils.ConverterUtil;

/**
 * <pre>
 * Project api接口实现类
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
@Service
@ANeedCheckLogin(userClass = WebUser.class)
@AApiServerImpl
public class ProjectApiImpl extends BaseApiImpl implements IProjectApi {

	interface IChartDataProvider {
		Object getNumber(History po);
	}

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProjectApiImpl.class);

	@Autowired
	private UserFactory userFactory;

	@Autowired
	private ProjectFactory projectFactory;

	@Autowired
	private HistoryDomain historyDomain;

	/**
	 * 删除
	 * 
	 * @param form
	 *            IdForm
	 * @return
	 * @throws BaseApiException
	 */
	@Override
	public CommonSuccessResponse delete(IdForm form) throws BaseApiException {

		throw new SimpleApiException("暂时不支持删除");
	}

	/**
	 * 列表
	 * 
	 * @param form
	 *            查询表单
	 * @return
	 * @throws BaseApiException
	 */
	@Override
	public ProjectListResponse list() throws BaseApiException {

		log.debug("查询 Project 列表");

		List<ProjectObj> list = this.projectFactory.findAll();
		Collections.sort(list);

		// 封装结果
		ProjectListResponse res = new ProjectListResponse();
		res.setList(ConverterUtil.convertList(list, this.projectFactory.getObj2ViewModelConverter()));
		return res;
	}

	/**
	 * 保存
	 * 
	 * @param form
	 *            ProjectEditForm
	 * @return
	 * @throws BaseApiException
	 */
	@Override
	public CommonSuccessResponse save(ProjectEditForm form) throws BaseApiException {
		if (form.getId() == null || form.getId() == 0) {
			// 如果没有id，就表示插入
			this.insert(form);

		} else {
			// 如果有 id，就表示更新
			this.update(form);
		}
		return CommonSuccessResponse.DEFAULT;
	}

	/**
	 * 查看
	 * 
	 * @param form
	 *            IdForm
	 * @return
	 * @throws BaseApiException
	 */
	@Override
	public ProjectViewResponse view(IdForm form) throws BaseApiException {

		log.debug("查看 Project, id={}", form.getId());

		ProjectObj obj = this.projectFactory.getByIdNotNull(form.getId());
		ProjectViewModel viewModel = this.projectFactory.getObj2ViewModelConverter().convert(obj);

		// 更新一下用户订阅信息
		List<UserObj> allUsers = this.userFactory.findAll();
		viewModel.updateSubscribe(allUsers);

		// 构建返回内容
		ProjectViewResponse res = new ProjectViewResponse();
		res.setInfo(viewModel);

		return res;
	}

	/**
	 * 将数组变成用逗号分隔的字符串
	 */
	private String arrayToString(String[] ary) {
		StringBuffer sb = new StringBuffer();
		if (ary != null && ary.length > 0) {
			for (String str : ary) {
				if (sb.length() > 0) {
					sb.append(",");
				}
				sb.append(str);
			}
		}
		return sb.toString();
	}

	/**
	 * 新建对象
	 * 
	 * @param form
	 * @throws BaseApiException
	 */
	protected void insert(ProjectEditForm form) throws BaseApiException {
		throw new SimpleApiException("不允许手动增加");
	}

	protected void tranFormDataToPo(ProjectEditForm form, Project po, boolean isUpdate) {
		// 需要人工将表单的数据转到po中，请注意数据安全
		po.setDisplayName(form.getDisplayName()); // 显示的名字
		po.setMemo(form.getMemo()); // EF
		po.setSendWarn(form.isSendWarn()); // 是否发送报警信息
		po.setNeedCheck(form.isNeedCheck()); // 是否需要检查

		po.setUserIds(this.arrayToString(form.getUserId())); // 关注的用户
	}

	/**
	 * 更新
	 * 
	 * @param form
	 *            ProjectEditForm
	 * @throws BaseApiException
	 */
	protected void update(ProjectEditForm form) throws BaseApiException {
		// 先获取旧的对象
		ProjectObj old = this.projectFactory.getByIdNotNull(form.getId());

		// 根据表单填充数据
		this.tranFormDataToPo(form, old.getPo(), true);

		// 更新，并刷新cache
		this.projectFactory.update(old, true);
	}

	@Override
	public UserSubscribeResponse userSubscribe(UserSubscribeForm form) throws BaseApiException {
		log.debug("切换订阅状态 ProjectId={}, userId={}", form.getId(), form.getUserId());

		// 获取项目对象
		ProjectObj obj = this.projectFactory.getByIdNotNull(form.getId());

		// 获取用户对象
		UserObj user = this.userFactory.getByIdNotNull(form.getUserId());

		// 改变订阅状态并异步保存
		obj.changeSubscribe(user);

		// 生成用户订阅列表
		List<UserObj> allUsers = this.userFactory.findAll();
		ProjectViewModel viewModel = this.projectFactory.getObj2ViewModelConverter().convert(obj);
		viewModel.updateSubscribe(allUsers);

		// 构建返回内容
		UserSubscribeResponse res = new UserSubscribeResponse();
		res.setUserSubscribe(viewModel.getUserSubscribe());

		return res;
	}

	@Override
	public HistoryChartResponse chartOfMemory(HistoryListForm form) throws BaseApiException {
		return this.getChartData(form, new IChartDataProvider() {

			@Override
			public Object getNumber(History po) {
				return po.getUsedMemory() >> 20;
			}
		});
	}

	@Override
	public HistoryChartResponse chartOfSystemLoad(HistoryListForm form) throws BaseApiException {
		return this.getChartData(form, new IChartDataProvider() {

			@Override
			public Object getNumber(History po) {
				return po.getSystemLoadAverage();
			}
		});
	}

	@Override
	public HistoryChartResponse chartOfAction(HistoryListForm form) throws BaseApiException {
		return this.getChartData(form, new IChartDataProvider() {

			@Override
			public Object getNumber(History po) {
				return po.getRequestCount();
			}
		});
	}

	private HistoryChartResponse getChartData(HistoryListForm form, IChartDataProvider dataProvider) {
		HistoryChartResponse res = new HistoryChartResponse();

		List<History> list = this.historyDomain.findForChart(form);
		for (History po : list) {

			// 生成线图上一个点的数据，第一个数据为 时间，第二个数据为值
			List<Object> dataEntry = new LinkedList<>();
			dataEntry.add(this.getTimeFromHistroyPo(po));
			dataEntry.add(dataProvider.getNumber(po));

			// 将这个点放到列表中
			res.getData().add(dataEntry);
		}

		return res;
	}

	private Long getTimeFromHistroyPo(History po) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmm");
		try {
			Date date = sdf.parse(String.format("%d-%04d", po.getDayKey(), po.getHourKey()));
			return date.getTime();
		} catch (ParseException e) {
			return 0L;
		}

	}
}
