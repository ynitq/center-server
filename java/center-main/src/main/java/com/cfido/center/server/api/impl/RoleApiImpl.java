package com.cfido.center.server.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfido.center.server.api.IRole;
import com.cfido.center.server.api.responses.RoleListResponse;
import com.cfido.center.server.api.responses.RoleViewResponse;
import com.cfido.center.server.entity.Role;
import com.cfido.center.server.form.RoleEditForm;
import com.cfido.center.server.logicObj.RoleFactory;
import com.cfido.center.server.logicObj.RoleObj;
import com.cfido.center.server.security.WebUser;
import com.cfido.commons.annotation.api.AApiServerImpl;
import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.beans.apiServer.impl.CommonSuccessResponse;
import com.cfido.commons.beans.form.IdForm;
import com.cfido.commons.loginCheck.ANeedCheckLogin;
import com.cfido.commons.utils.utils.ConverterUtil;

/**
 * <pre>
 * IRole 的实现类
 * </pre>
 * 
 * @author 梁韦江
 */
@Service
@ANeedCheckLogin(userClass = WebUser.class)
@AApiServerImpl
public class RoleApiImpl implements IRole {

	@Autowired
	private RoleFactory factory;

	@Override
	public RoleListResponse list() throws BaseApiException {
		List<RoleObj> list = this.factory.findAll();

		RoleListResponse res = new RoleListResponse();
		res.setList(ConverterUtil.convertList(list, this.factory.getObj2ViewModelConverter()));
		return res;
	}

	/** 保存或者新建 */
	@Override
	public RoleViewResponse save(RoleEditForm form) throws BaseApiException {

		RoleObj obj;

		if (form.getId() == 0) {
			// 新建
			Role po = this.factory.createDefaultPo();
			po.setName(form.getName());
			obj = this.factory.create(po);
		} else {
			obj = this.factory.getByIdNotNull(form.getId());
			obj.getPo().setName(form.getName());
			this.factory.update(obj, true);
		}

		return this.createViewResponse(obj);
	}

	private RoleViewResponse createViewResponse(RoleObj obj) {
		RoleViewResponse res = new RoleViewResponse();
		res.setInfo(obj.createModel());
		return res;
	}

	/** 删除角色 */
	@Override
	public CommonSuccessResponse delete(IdForm form) throws BaseApiException {
		RoleObj obj = this.factory.getByIdNotNull(form.getId());
		this.factory.delete(obj);
		return CommonSuccessResponse.DEFAULT;
	}

	/** 查看 */
	@Override
	public RoleViewResponse view(IdForm form) throws BaseApiException {
		RoleObj obj = this.factory.getByIdNotNull(form.getId());
		return this.createViewResponse(obj);
	}

}
