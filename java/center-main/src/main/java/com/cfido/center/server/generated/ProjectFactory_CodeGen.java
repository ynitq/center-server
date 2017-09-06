package com.cfido.center.server.generated;

import org.springframework.beans.factory.annotation.Autowired;

import com.cfido.center.server.domains.ProjectDomain;
import com.cfido.center.server.entity.Project;
import com.cfido.center.server.logicObj.ProjectObj;
import com.cfido.center.server.logicObj.ProjectViewModel;
import com.cfido.commons.beans.others.IConverter;
import com.cfido.commons.utils.db.IObjFactoryDao;
import com.cfido.commons.utils.logicObj.BaseObjFactory;

/**
 * <pre>
 * Project 工厂类基类
 * 我们使用了带_CodeGen字样的不规范的类名，表面这个类是由代码生成器生成的，不要自己修改
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
public abstract class ProjectFactory_CodeGen extends BaseObjFactory<ProjectObj, Project, Integer> {

	private final IConverter<ProjectObj, ProjectViewModel> obj2ViewModelConverter = new IConverter<ProjectObj, ProjectViewModel>() {
		@Override
		public ProjectViewModel convert(ProjectObj src) {
			return new ProjectViewModel(src);
		}
	};

	@Autowired
	protected ProjectDomain projectDomain;

	@Override
	protected IObjFactoryDao<Project, Integer> getDaoForObjFactory() {
		return projectDomain;
	}

	/**
	 * 将obj转为视图对象的转换器
	 * 
	 * @param po
	 * @return
	 */
	public IConverter<ProjectObj, ProjectViewModel> getObj2ViewModelConverter() {
		return obj2ViewModelConverter;
	}
}
