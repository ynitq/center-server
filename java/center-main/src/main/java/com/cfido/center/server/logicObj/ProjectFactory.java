package com.cfido.center.server.logicObj;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.cfido.center.server.entity.Project;
import com.cfido.center.server.generated.ProjectFactory_CodeGen;
import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.beans.monitor.ClientIdBean;

/**
 * <pre>
 * Project逻辑对象的工厂类，这个文件在生成时默认不覆盖
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
@Service
public class ProjectFactory extends ProjectFactory_CodeGen {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProjectFactory.class);

	// 用于存储所有的项目对象
	private final Map<String, ProjectObj> objMap = new HashMap<>();
	private final Map<Integer, ProjectObj> idMap = new HashMap<>();

	@PostConstruct
	protected void init() throws BaseApiException {
		List<Project> poList = projectDomain.findAll();
		for (Project po : poList) {
			String key = this.getKeyFromPo(po);

			ProjectObj obj = this.convertToObj(po);
			this.objMap.put(key, obj);
			this.idMap.put(po.getId(), obj);
			obj.init();
		}
		log.info("初始化 ProjectFactory，将所有数据调入内存，合计 {} 个项目", poList.size());
	}

	/**
	 * 根据客户端那边传过来的idbean，获取对象，如果不存在就创建一条
	 * 
	 * @param idBean
	 * @return
	 * @throws BaseApiException
	 */
	public ProjectObj getObjByIdBean(ClientIdBean idBean) throws BaseApiException {
		String key = this.getKeyFromIdBean(idBean);

		ProjectObj obj = this.objMap.get(key);
		if (obj == null) {
			log.info("发现了新的系统 {}", idBean.toString());

			Project po = this.createDefaultPo();
			po.setStartClassName(idBean.getStartClassName());
			po.setPort(idBean.getPort());
			po.setHost(idBean.getHost());
			po.setContextPath(idBean.getContextPath());

			po.setDisplayName(key);// 临时用key作为显示名
			po.setMemo(key);

			obj = this.create(po);
			obj.init();

			// 放到map中，方便查询
			this.objMap.put(key, obj);
			this.idMap.put(po.getId(), obj);
		}

		return obj;
	}

	/**
	 * 根据 idBean 生成唯一标示
	 */
	private String getKeyFromIdBean(ClientIdBean idBean) {
		return String.format("%s@%s:%d",
				idBean.getStartClassName(),
				idBean.getHost(), idBean.getPort());
	}

	@Override
	public List<ProjectObj> findAll() {
		List<ProjectObj> list = new LinkedList<>(this.idMap.values());
		return list;
	}

	/** 我们改为从内存中获取数据，所以需要覆盖父类这个方法 */
	@Override
	public ProjectObj getById(Integer id) {
		return this.idMap.get(id);
	}

	/**
	 * 根据 po 生成唯一标示
	 */
	private String getKeyFromPo(Project po) {
		return String.format("%s@%s:%d",
				po.getStartClassName(),
				po.getHost(), po.getPort());
	}

	public Project createDefaultPo() {
		Project po = new Project();

		po.setCreateTime(new Date());
		po.setHasNewMessage(false);

		po.setDown(false);// 默认状态：在线
		po.setNeedCheck(true); // 默认是需要检查
		po.setSendWarn(true);// 默认不发送警告
		po.setUserIds("");// 默认没有关注的用户

		return po;
	}

	@Override
	public void delete(ProjectObj obj) throws BaseApiException {
		// TODO 需要人工干预是否真的从数据库中删除
		// super.delete(obj);
	}

}
