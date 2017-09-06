package com.cfido.center.server.domains;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.cfido.center.server.entity.History;
import com.cfido.center.server.form.HistoryListForm;
import com.cfido.center.server.generated.HistoryDomain_CodeGen;
import com.cfido.commons.utils.utils.TimeLimitMap;

/**
 * <pre>
 * 操作History表的domain,
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
@Component
public class HistoryDomain extends HistoryDomain_CodeGen {

	/** 用于缓存图表数据而设置的cache */
	private final TimeLimitMap<String, List<History>> cacheForChart = new TimeLimitMap<>(5, TimeUnit.SECONDS, 100);

	/**
	 * 为了显示线图而准备的数据
	 * 
	 * @param form
	 * @return
	 */
	public List<History> findForChart(HistoryListForm form) {

		String sqlStartWithFrom = "from History where projectId=?1 and recordTime between ?2 and ?3 order by id";

		Date end = new Date();
		// 暂时就是拿过去7天的数据
		Date begin = new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(2));

		// 根据查询条件获得key
		String key = this.createChartKey(form.getProjectId(), begin, end);

		// 先尝试从cache中获取
		List<History> list = this.cacheForChart.get(key);
		if (list == null) {
			// 如果cache中没有，或者已经超时了，就重新从数据库中获取
			list = this.getCommonDao().find(sqlStartWithFrom, null, History.class, form.getProjectId(), begin, end);

			this.cacheForChart.put(key, list);
		}

		return list;
	}

	private String createChartKey(int projectId, Date begin, Date end) {
		StringBuffer sb = new StringBuffer();

		sb.append(projectId);
		sb.append('\t');
		sb.append(begin.getTime());
		sb.append('\t');
		sb.append(end.getTime());

		return sb.toString();
	}
}
