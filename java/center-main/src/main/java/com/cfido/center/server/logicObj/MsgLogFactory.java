package com.cfido.center.server.logicObj;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.cfido.center.server.entity.MsgLog;
import com.cfido.center.server.generated.MsgLogFactory_CodeGen;
import com.cfido.commons.beans.apiServer.BaseApiException;

/**
 * <pre>
 * MsgLog逻辑对象的工厂类，这个文件在生成时默认不覆盖
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
@Service
public class MsgLogFactory extends MsgLogFactory_CodeGen {

	public MsgLog createDefaultPo() {
		MsgLog po = new MsgLog();

		po.setCreateTime(new Date());

		return po;
	}

	@Override
	public void delete(MsgLogObj obj) throws BaseApiException {
		super.delete(obj);
	}

}
