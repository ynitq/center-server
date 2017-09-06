package com.cfido.center.server.generated;

import org.springframework.beans.factory.annotation.Autowired;

import com.cfido.center.server.api.impl.BaseApiImpl;
import com.cfido.center.server.api.responses.MsgLogListResponse;
import com.cfido.center.server.api.responses.MsgLogViewResponse;
import com.cfido.center.server.entity.MsgLog;
import com.cfido.center.server.form.MsgLogEditForm;
import com.cfido.center.server.form.MsgLogListForm;
import com.cfido.center.server.logicObj.MsgLogFactory;
import com.cfido.center.server.logicObj.MsgLogObj;
import com.cfido.center.server.logicObj.MsgLogViewModel;
import com.cfido.commons.beans.apiServer.BaseApiException;
import com.cfido.commons.beans.apiServer.impl.CommonSuccessResponse;
import com.cfido.commons.beans.form.IdForm;
import com.cfido.commons.utils.db.PageQueryResult;
import com.cfido.commons.utils.db.WhereBuilder;

/**
 * <pre>
 * MsgLog api接口 实现类
 * 我们使用了带_CodeGen字样的不规范的类名，表面这个类是由代码生成器生成的，不要自己修改
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
public abstract class MsgLogApiImpl_CodeGen extends BaseApiImpl{


	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MsgLogApiImpl_CodeGen.class);

	@Autowired
	protected MsgLogFactory factory;

	/**
	 * 查看
	 * 
	 * @param form
	 *            IdForm
	 * @return
	 * @throws BaseApiException
	 */
	public MsgLogViewResponse view(IdForm form) throws BaseApiException {

		log.debug("查看 MsgLog, id={}" , form.getId());

		MsgLogObj obj = this.factory.getByIdNotNull(form.getId());

		// 构建返回内容
		MsgLogViewResponse res = new MsgLogViewResponse();
		res.setInfo(this.factory.getObj2ViewModelConverter().convert(obj));

		return res;
	}

	/**
	 * 删除
	 * 
	 * @param form
	 *            IdForm
	 * @return
	 * @throws BaseApiException
	 */
	public CommonSuccessResponse delete(IdForm form) throws BaseApiException {

		log.debug("删除 MsgLog, id={}" , form.getId());

		// 先获取旧的对象
		MsgLogObj old = this.factory.getByIdNotNull(form.getId());

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
	public MsgLogListResponse list(MsgLogListForm form) throws BaseApiException {

		log.debug("查询 MsgLog 列表");

		// 构建条件生成器
		WhereBuilder builder = WhereBuilder.create(form);
		// 将对象查询处理
		PageQueryResult<MsgLogObj> objPage = this.factory.pageList(form, builder, null);

		// 将查询结果转为视图对象
		PageQueryResult<MsgLogViewModel> page = objPage.convert(this.factory.getObj2ViewModelConverter());

		// 封装结果
		MsgLogListResponse res = new MsgLogListResponse();
		res.setForm(form);
		res.setPage(page);

		return res;
	}

	/**
	 * 保存
	 * 
	 * @param form
	 *            MsgLogEditForm
	 * @return
	 * @throws BaseApiException
	 */
	public CommonSuccessResponse save(MsgLogEditForm form) throws BaseApiException {
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
	 * 新建对象
	 * 
	 * @param form
	 * @throws BaseApiException
	 */
	protected void insert(MsgLogEditForm form) throws BaseApiException {

		// 创建默认数值的po
		MsgLog po = this.factory.createDefaultPo();

		this.tranFormDataToPo(form, po, false);

		// 创建应用
		this.factory.create(po);
	}

	/**
	 * 将form中的值填写到po中去
	 * 
	 * @param form
	 *            编辑表单
	 * @param po
	 * @param isUpdate
	 *            是否是为了更新，更新时，有些字段是禁止传到po中的
	 */
	protected abstract void tranFormDataToPo(MsgLogEditForm form, MsgLog po, boolean isUpdate);

	/**
	 * 更新
	 * 
	 * @param form
	 *            MsgLogEditForm
	 * @throws BaseApiException
	 */
	protected void update(MsgLogEditForm form) throws BaseApiException {
		// 先获取旧的对象
		MsgLogObj old = this.factory.getByIdNotNull(form.getId());

		// 根据表单填充数据
		this.tranFormDataToPo(form, old.getPo(), true);

		// 更新，并刷新cache
		this.factory.update(old, true);
	}
}
