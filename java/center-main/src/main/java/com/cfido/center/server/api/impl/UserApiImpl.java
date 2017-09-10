package com.cfido.center.server.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cfido.center.server.api.IUserApi;
import com.cfido.center.server.api.responses.UserListResponse;
import com.cfido.center.server.api.responses.UserViewResponse;
import com.cfido.center.server.entity.User;
import com.cfido.center.server.form.UserEditForm;
import com.cfido.center.server.logicObj.UserFactory;
import com.cfido.center.server.logicObj.UserObj;
import com.cfido.center.server.logicObj.UserViewModel;
import com.cfido.center.server.security.WebUser;
import com.cfido.commons.annotation.api.AApiServerImpl;
import com.cfido.commons.beans.apiExceptions.InvalidLoginStatusException;
import com.cfido.commons.beans.apiExceptions.UserAlreadyRegistedException;
import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.beans.apiServer.impl.CommonSuccessResponse;
import com.cfido.commons.beans.exceptions.security.UserNotFoundException;
import com.cfido.commons.beans.form.IdForm;
import com.cfido.commons.beans.form.ResetPasswordForm;
import com.cfido.commons.loginCheck.ANeedCheckLogin;
import com.cfido.commons.spring.security.CommonAdminWebUser;
import com.cfido.commons.utils.utils.ConverterUtil;
import com.cfido.commons.utils.utils.ExceptionUtil;
import com.cfido.commons.utils.utils.PasswordEncoder;

/**
 * <pre>
 * User api接口实现类
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
@Service
@ANeedCheckLogin(userClass = WebUser.class)
@AApiServerImpl
public class UserApiImpl extends BaseApiImpl implements IUserApi {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserApiImpl.class);

	@Autowired
	protected UserFactory factory;

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

		log.debug("删除 User, id={}", form.getId());

		// 先获取旧的对象
		UserObj old = this.factory.getByIdNotNull(form.getId());

		// 删除对象，不建议真的删除，因为有可能有外键
		this.factory.delete(old);

		return CommonSuccessResponse.DEFAULT;
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
	public UserListResponse list() throws BaseApiException {

		log.debug("查询 User 列表");

		List<UserViewModel> list = ConverterUtil.convertList(this.factory.findAll(),
				this.factory.getObj2ViewModelConverter());

		// 封装结果
		UserListResponse res = new UserListResponse();
		res.setList(list);
		return res;
	}

	/**
	 * 保存
	 * 
	 * @param form
	 *            UserEditForm
	 * @return
	 * @throws BaseApiException
	 */
	@Override
	public CommonSuccessResponse save(UserEditForm form) throws BaseApiException {
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
	public UserViewResponse view(IdForm form) throws BaseApiException {

		log.debug("查看 User, id={}", form.getId());

		UserObj obj = this.factory.getByIdNotNull(form.getId());

		// 构建返回内容
		UserViewResponse res = new UserViewResponse();
		res.setInfo(this.factory.getObj2ViewModelConverter().convert(obj));

		return res;
	}

	/**
	 * 新建对象
	 * 
	 * @param form
	 * @throws BaseApiException
	 */
	protected void insert(UserEditForm form) throws BaseApiException {

		ExceptionUtil.hasText(form.getAccount(), "账号不能为空");
		ExceptionUtil.hasText(form.getPassword(), "密码不能为空");

		try {
			factory.getByAccount(form.getAccount());

			// 如果能获取到账号，就抛错
			throw new UserAlreadyRegistedException();
		} catch (UserNotFoundException ex) {
			// 如果抛错了，表示账号不存在，就可以创建用户了
			// 创建默认数值的po
			User po = this.factory.createDefaultPo();

			// 设置账户和密码
			po.setAccount(form.getAccount());

			this.tranFormDataToPo(form, po, false);

			// 创建应用
			this.factory.create(po);

		}

	}

	protected void tranFormDataToPo(UserEditForm form, User po, boolean isUpdate) throws InvalidLoginStatusException {
		// 需要人工将表单的数据转到po中，请注意数据安全
		po.setName(form.getName());
		po.setPhone(form.getPhone());
		po.setEmail(form.getEmail());
		po.setMemo(form.getMemo());

		CommonAdminWebUser curUser = this.getCurUserNotNull();
		if (curUser.isSuperUser() && !curUser.getUsername().equals(po.getAccount()) && StringUtils.hasText(form.getPassword())) {
			// 密码不为空时，就设置新密码，密码非空判断在insert方法中
			po.setPassword(PasswordEncoder.encodePassword(form.getPassword()));
		}
	}

	@Override
	public CommonSuccessResponse resetPassword(ResetPasswordForm form) throws BaseApiException {
		// 获取用户对象
		UserObj user = this.factory.getByIdNotNull(form.getId());

		// 修改密码为新密码
		user.changePassword(form.getNewPassword());

		return CommonSuccessResponse.DEFAULT;
	}

	/**
	 * 更新
	 * 
	 * @param form
	 *            UserEditForm
	 * @throws BaseApiException
	 */
	protected void update(UserEditForm form) throws BaseApiException {
		// 先获取旧的对象
		UserObj old = this.factory.getByIdNotNull(form.getId());

		// 根据表单填充数据
		this.tranFormDataToPo(form, old.getPo(), true);

		// 更新，并刷新cache
		this.factory.update(old, true);
	}

	@Override
	public UserViewResponse lockOrUnlock(IdForm form) throws BaseApiException {

		log.debug("查看 User, id={}", form.getId());

		UserObj obj = this.factory.getByIdNotNull(form.getId());

		// 封号或者解封
		User po = obj.getPo();
		po.setDisabled(!po.isDisabled());

		// 构建返回内容
		UserViewResponse res = new UserViewResponse();
		res.setInfo(this.factory.getObj2ViewModelConverter().convert(obj));

		return res;
	}
}
