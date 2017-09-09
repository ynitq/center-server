package com.cfido.center.server.logicObj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfido.center.server.domains.ProjectRoleDomain;
import com.cfido.center.server.entity.ProjectRole;
import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.beans.others.IConverter;
import com.cfido.commons.utils.db.IObjFactoryDao;
import com.cfido.commons.utils.logicObj.BaseObjFactory;

/**
 * <pre>
 * ProjectRole逻辑对象的工厂类，这个文件在生成时默认不覆盖
 * </pre>
 * 
 * @author 梁韦江 生成于 2017-09-06 20:34:35
 */
@Service
public class ProjectRoleFactory extends BaseObjFactory<ProjectRoleObj, ProjectRole, Integer> {

	private final IConverter<ProjectRoleObj, ProjectRoleViewModel> obj2ViewModelConverter = new IConverter<ProjectRoleObj, ProjectRoleViewModel>() {
		@Override
		public ProjectRoleViewModel convert(ProjectRoleObj src) {
			return src.createModel();
		}
	};

	@Autowired
	protected ProjectRoleDomain projectRoleDomain;

	@Override
	protected IObjFactoryDao<ProjectRole, Integer> getDaoForObjFactory() {
		return projectRoleDomain;
	}

	/**
	 * 将obj转为视图对象的转换器
	 */
	public IConverter<ProjectRoleObj, ProjectRoleViewModel> getObj2ViewModelConverter() {
		return obj2ViewModelConverter;
	}

	public ProjectRole createDefaultPo() {
		ProjectRole po = new ProjectRole();
		return po;
	}

	@Override
	public void delete(ProjectRoleObj obj) throws BaseApiException {
		super.delete(obj);
	}

}
