package com.cfido.center.server.logicObj;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cfido.center.server.entity.History;
import com.cfido.commons.beans.monitor.ClientInfoResponse;
import com.cfido.commons.utils.logicObj.BasePoObj;

/**
 * <pre>
 * History表的逻辑对象
 * </pre>
 * 
 * @author 梁韦江 生成于: 2016-12-19 17:42:34
 */
@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HistoryObj extends BasePoObj<History> {

	public HistoryViewModel createModel() {
		return new HistoryViewModel(this);
	}

	/**
	 * 更新统计数据
	 * 
	 * @param res
	 */
	public void updateInfo(ClientInfoResponse res) {
		if (res != null) {
			this.po.setSystemLoadAverage(res.getOsInfo().getSystemLoadAverage());
			this.po.setTotalMemory(res.getOsInfo().getTotalMemory());
			this.po.setUsedMemory(res.getOsInfo().getUsedMemory());

			// 访问次数时个累加值，需要和旧的相加
			this.po.setRequestCount(po.getRequestCount() + res.getRequestCount());
			this.po.setDown(false);
		} else {
			// 如果宕机了，数据清0
			this.po.setSystemLoadAverage(-1);
			this.po.setTotalMemory(0);
			this.po.setUsedMemory(0);
			this.po.setDown(true);
		}
	}
}
