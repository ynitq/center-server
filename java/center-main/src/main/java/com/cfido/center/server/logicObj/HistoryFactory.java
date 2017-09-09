package com.cfido.center.server.logicObj;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.cfido.center.server.entity.History;
import com.cfido.center.server.generated.HistoryFactory_CodeGen;
import com.cfido.commons.beans.apiServer.BaseApiException;

/**
 * <pre>
 * History逻辑对象的工厂类，这个文件在生成时默认不覆盖
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
@Service
public class HistoryFactory extends HistoryFactory_CodeGen {

	public History createDefaultPo() {
		History po = new History();

		po.setRecordTime(new Date());

		po.setRequestCount(0);
		po.setSystemLoadAverage(0);
		po.setTotalMemory(0);
		po.setUsedMemory(0);

		return po;
	}

	@Override
	public void delete(HistoryObj obj) throws BaseApiException {
		super.delete(obj);
	}

	public HistoryObj getCurTimeObj(ProjectObj project) throws BaseApiException {
		Calendar c = Calendar.getInstance();
		int dayKey = getDayKey(c);
		int hourKey = getHourKey(c);
		int projectId = project.getPo().getId();

		// 检查数据库是否已经有当前时间点的记录了
		String sqlStartWithFrom = "from History where projectId=?1 and dayKey=?2 and hourKey=?3";
		History po = this.historyDomain.findOne(sqlStartWithFrom,
				projectId, dayKey, hourKey);

		if (po == null) {
			// 如果没有就创建新记录
			po = this.createDefaultPo();
			po.setProjectId(projectId);
			po.setHourKey(hourKey);
			po.setDayKey(dayKey);

			return this.create(po);
		} else {
			// 否则就直接返回
			return this.convertToObj(po);
		}
	}

	/**
	 * 判断该对象是否是当前时间点的数据
	 * 
	 * @param obj
	 * @return
	 */
	public boolean isCurTimeObj(HistoryObj obj) {
		Assert.notNull(obj, "obj不能为空");

		Calendar c = Calendar.getInstance();
		int dayKey = getDayKey(c);
		int hourKey = getHourKey(c);

		History po = obj.getPo();
		return po.getDayKey() == dayKey && po.getHourKey() == hourKey;

	}

	protected static int getDayKey(Calendar c) {
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);

		return year * 10000 + month * 100 + day;
	}

	/**
	 * 每10分钟作为一个统计点
	 */
	protected static int getHourKey(Calendar c) {
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE) / 10 * 10;

		return hour * 100 + min;
	}
}
